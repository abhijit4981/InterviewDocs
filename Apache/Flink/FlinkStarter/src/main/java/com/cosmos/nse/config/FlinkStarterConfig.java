package com.cosmos.nse.config;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class FlinkStarterConfig {
    public static Properties getKafkaConsumerProperties(String bootstrapServers, boolean useSSL,
                                                        String keyTabLocation, String kerberosPrincipal,
                                                        String truststorePath, String truststorePwd,
                                                        String keystorePath, String keystorePwd) {
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flink_common_enrch_trade");
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        p.setProperty(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        p.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
//		p.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "8000");
        if (useSSL) {
            /* Kerberos Properties*/
            setSASLProperties(keyTabLocation, kerberosPrincipal, truststorePath, truststorePwd, keystorePath, keystorePwd, p);

        }
        return p;
    }

    private static void setSASLProperties(String keyTabLocation, String kerberosPrincipal, String truststorePath,
                                          String truststorePwd, String keystorePath, String keystorePwd, Properties p) {
        p.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        p.setProperty("sasl.mechanism", "GSSAPI");
        p.setProperty("sasl.kerberos.service.name", "kafka");
        p.setProperty("sasl.jaas.config", "com.sun.security.auth.module.Krb5LoginModule required doNotPrompt=true useTicketCache=false useKeyTab=true keyTab=\"" + keyTabLocation + "\" principal=\"" + kerberosPrincipal + "\" serviceName=\"kafka\";");
        p.setProperty("ssl.truststore.location",truststorePath);
        p.setProperty("ssl.truststore.password",truststorePwd);

        p.setProperty("ssl.keystore.location",keystorePath);
        p.setProperty("ssl.keystore.password",keystorePwd);

        p.setProperty("ssl.key.password",keystorePwd);
    }

    public static Properties getKafkaProducerProperties(String bootstrapServers, boolean useSSL,
                                                        String keyTabLocation, String kerberosPrincipal,
                                                        String truststorePath, String truststorePwd,
                                                        String keystorePath, String keystorePwd) {
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        p.setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG, "30000");
        p.setProperty (ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1" );
        p.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        p.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "259200000");
        p.setProperty(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "15000000");
        p.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG, "600000");
        if (useSSL) {
            setSASLProperties(keyTabLocation, kerberosPrincipal, truststorePath, truststorePwd, keystorePath, keystorePwd, p);

        }
        return p;
    }
}
