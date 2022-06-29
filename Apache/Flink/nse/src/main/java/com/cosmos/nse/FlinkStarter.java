package com.cosmos.nse;

import com.cosmos.nse.config.FlinkStarterConfig;
import com.cosmos.nse.config.JobConfigConstants;
import com.cosmos.nse.schema.WordsCapitalizer;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.StringUtils;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;

public class FlinkStarter {
    private static String keytabLocation = null;
    private static String keytabPrincipal = null;
    private static String truststorePath = null;
    private static String keystorePath = null;
    private static String truststorePassword = null;
    private static String keystorePassword = null;
    private static String bootstrapServers = null;

    private static final String UID_SUFFIX = "uid_suffix";
    private static boolean useSSL = false;

    static {
        bootstrapServers = "localhost:9092";
        keytabLocation = System.getProperty(JobConfigConstants.KEYTAB_LOCATION);
        keytabPrincipal = System.getProperty(JobConfigConstants.KEYTAB_PRINCIPAL);
        truststorePath = System.getProperty(JobConfigConstants.TRUSTSTORE_PATH);
        keystorePath = System.getProperty(JobConfigConstants.KEYSTORE_PATH);
        String truststroePwdPath = System.getProperty(JobConfigConstants.TRUSTSTORE_PASSWORD_PATH);
        String keystroePwdPath = System.getProperty(JobConfigConstants.KEYSTORE_PASSWORD_PATH);
        useSSL = System.getProperty(JobConfigConstants.USE_SSL) == null ? false
                : Boolean.parseBoolean(System.getProperty(JobConfigConstants.USE_SSL));
        String jaasFile = System.getProperty(JobConfigConstants.JAAS_CONFIG);
        String krbConfig = System.getProperty(JobConfigConstants.KRB_CONFIG);
        if (useSSL) {
            System.setProperty("java.security.auth.login.config", jaasFile);
            System.setProperty("java.security.krb5.conf", krbConfig);
        }
//		bootstrapServers = "10.55.173.36:9092";

    }

    public static void main(String[] args) throws Exception {

        ParameterTool params = ParameterTool.fromArgs(args);
        //Add ur Topics
        String marInputTopics = params.get("marInputTopics");
        List<String> sourceTopics = Arrays.asList(marInputTopics.split(","));
        String outputTopic= params.get("marTransformedTopic");
        String uidSuffix = StringUtils.isNullOrWhitespaceOnly(params.get(UID_SUFFIX)) ? "uid" : params.get(UID_SUFFIX);


        String maxConcurrentCheckpoint = params.get(JobConfigConstants.MAX_CONCURRENT_CHECKPOINT);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);
/*
        DataStream<String> sourceData = env
                .addSource(new FlinkKafkaConsumer<String>(sourceTopics,new SimpleStringSchema(),
                                FlinkStarterConfig.getKafkaConsumerProperties(bootstrapServers, useSSL, keytabLocation,
                                        keytabPrincipal, truststorePath, truststorePassword, keystorePath, keystorePassword))
//						.setStartFromEarliest()
                ).name("enrichedTradeSource");


 */
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(sourceTopics)
                .setGroupId("")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setProperties(FlinkStarterConfig.getKafkaProducerProperties(bootstrapServers, useSSL, keytabLocation,
                        keytabPrincipal, truststorePath, truststorePassword, keystorePath, keystorePassword))
                .build();
        //use as a entry point
        DataStream<String> transformedJsonData = env.fromSource(source, WatermarkStrategy.<String>noWatermarks(), "enrichedTradeSource");

        KafkaSink<String> marNordpoolIntradayProducer = KafkaSink.<String>builder()
                .setBootstrapServers(bootstrapServers)
                .setKafkaProducerConfig(
                        FlinkStarterConfig.getKafkaProducerProperties(bootstrapServers, useSSL, keytabLocation,
                                keytabPrincipal, truststorePath, truststorePassword, keystorePath, keystorePassword))
                .setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .setTransactionalIdPrefix("my-trx-id-prefix")
                .setRecordSerializer(KafkaRecordSerializationSchema.builder()
                        .setTopic(outputTopic)
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build())
                .build();
        transformedJsonData.sinkTo(marNordpoolIntradayProducer)
                .name("enrichedTradeSource")
                .uid("enrichedTradeSource" + uidSuffix);
        //Execution starts here
        env.execute("Flink-Starter");
    }

}
