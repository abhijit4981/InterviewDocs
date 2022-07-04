package com.cosmos.nse;

import com.cosmos.nse.config.FlinkStarterConfig;
import com.cosmos.nse.config.JobConfigConstants;
import com.cosmos.nse.schema.WordsCapitalizer;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
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
import org.apache.flink.util.Collector;
import org.apache.flink.util.StringUtils;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

//@SpringBootApplication
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
    	SpringApplication.run(FlinkStarter.class, args);

        ParameterTool params = ParameterTool.fromArgs(args);
        //Add ur Topics
        String marInputTopics = params.get("marInputTopics");
        System.out.print(marInputTopics);
        List<String> sourceTopics = Arrays.asList(marInputTopics.split(","));
        String outputTopic= params.get("marTransformedTopic");
        String uidSuffix = StringUtils.isNullOrWhitespaceOnly(params.get(UID_SUFFIX)) ? "uid" : params.get(UID_SUFFIX);


        String maxConcurrentCheckpoint = params.get(JobConfigConstants.MAX_CONCURRENT_CHECKPOINT);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);
        
        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(sourceTopics)
                .setGroupId("my-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .setProperties(FlinkStarterConfig.getKafkaProducerProperties(bootstrapServers, useSSL, keytabLocation,
                        keytabPrincipal, truststorePath, truststorePassword, keystorePath, keystorePassword))
                .build();
        
        
        KafkaRecordSerializationSchema<String> serializer = KafkaRecordSerializationSchema.builder()
    			.setValueSerializationSchema(new SimpleStringSchema())
    			.setTopic(outputTopic)
    			.build();
        //use as a entry point
        DataStream<String> transformedJsonData = env.fromSource(source, WatermarkStrategy.<String>noWatermarks(), "KafkaSource");
        
        // Split up the lines in pairs (2-tuples) containing: (word,1)
        DataStream<String> counts = transformedJsonData.map(new WordsCapitalizer());

        KafkaSink<String> marNordpoolIntradayProducer = KafkaSink.<String>builder()
                .setBootstrapServers(bootstrapServers)
                .setKafkaProducerConfig(
                        FlinkStarterConfig.getKafkaProducerProperties(bootstrapServers, useSSL, keytabLocation,
                                keytabPrincipal, truststorePath, truststorePassword, keystorePath, keystorePassword))
                .setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .setTransactionalIdPrefix("my-trx-id-prefix")
                .setRecordSerializer(serializer)
                .build();
        counts.sinkTo(marNordpoolIntradayProducer)
                .name("enrichedTradeSource")
                .uid("enrichedTradeSource" + uidSuffix);
        //Execution starts here
        env.execute("Flink-Starter");
    }

/**
 * Implements the string tokenizer that splits sentences into words as a user-defined
 * FlatMapFunction. The function takes a line (String) and splits it into multiple pairs in the
 * form of "(word,1)" ({@code Tuple2<String, Integer>}).
 */
public static final class Tokenizer
        implements FlatMapFunction<String, Tuple2<String, Integer>> {

    private static final long serialVersionUID = 5502983328070192521L;

	@Override
    public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
        // Normalize and split the line
        String[] tokens = value.toLowerCase().split("\\W+");

        // Emit the pairs
        for (String token : tokens) {
            if (token.length() > 0) {
                out.collect(new Tuple2<>(token, 1));
            }
        }
    }
}
//Implements a simple reducer using FlatMap to
// reduce the Tuple2 into a single string for 
// writing to kafka topics
public static final class Reducer
        implements FlatMapFunction<Tuple2<String, Integer>, String> {

	private static final long serialVersionUID = 2218460762858626198L;

	@Override
    public void flatMap(Tuple2<String, Integer> value, Collector<String> out) {
    	// Convert the pairs to a string
    	// for easy writing to Kafka Topic
    	String count = value.f0 + " " + value.f1;
    	out.collect(count);
    }
}
}