package com.cosmos.nse.config;

public class JobConfigConstants {
    public static final String ENRICHMENT_API_URL = "enrichment_api_url";

    public static final String KAFKA_BROKERS = "kafka_brokers";

    public static final String ASYNC_CAPACITY = "async_capacity";

    public static final String TIMEOUT_SECONDS = "timeout_sec";

    public static final String DELAY_TIME = "delay_time";

    public static final String MAX_ATTEMPT = "max_attempt";

    public static final String CHECKPOINT_TIME = "checkpoint_time";

    public static final String MIN_CHECKPOINT_PAUSE = "min_checkpoint_pause";

    public static final String MAX_CONCURRENT_CHECKPOINT = "max_concurrent_checkpoint";

    public static final String SOURCE_APPLICATION = "src_app";

    public static final String TARGET_APPLICATION = "target_app";

    public static final String INTERFACE_NAME = "interface_name";

    // Default Values
    public static final int MAX_CONCURRENT_CHECKPOINTING = 1;

    public static final int DEFAULT_CHECKPOINT_PAUSE = 10000;

    public static final int DEFAULT_CHECKPOINTING = 10000;

    // SSL Related
    public static final String KEYTAB_LOCATION = "keytab_location";

    public static final String KEYTAB_PRINCIPAL = "keytab_principal";

    public static final String USE_SSL = "useSSL";

    public static final String TRUSTSTORE_PATH = "truststorePath";

    public static final String KEYSTORE_PATH = "keystorePath";

    public static final String TRUSTSTORE_PASSWORD_PATH = "truststorePwdPath";

    public static final String KEYSTORE_PASSWORD_PATH = "keystorePwdPath";

    public static final String JAAS_CONFIG = "jaas_config";

    public static final String KRB_CONFIG = "krb_config";

    public static final String FENIX_EDRS_PWD = "EndurFenix-INTERFACE-EDRS-Password";

    public static final String FENIX_EDRS_USER = "EndurFenix-INTERFACE-EDRS-Username";

    public static final String FENIX_GO_PWD = "EndurFenix-INTERFACE-GO-Password";

    public static final String FENIX_GO_USER = "EndurFenix-INTERFACE-GO-Username";

    public static final String GO_MGR_PWD = "EndurGoe-ENDUR-MGR01-Password";

    public static final String GO_MGR_USER = "EndurGoe-ENDUR-MGR01-Username";
    // Topics config
    public static final String ICE_SOURCE_TOPIC = "ice_source_topic";

    public static final String EPEX_SOURCE_TOPIC = "epex_source_topic";

    public static final String NASDAQ_SOURCE_TOPIC = "nasdaq_source_topic";

    public static final String PRISMA_SOURCE_TOPIC = "prisma_source_topic";

    public static final String NORDPOOL_SOURCE_TOPIC = "nordpool_source_topic";

    public static final String TRAYPORT_SOURCE_TOPIC = "trayport_source_topic";

    public static final String SOURCE_TOPIC = "source_topic";

    public static final String SPLIT_TOPIC = "split_topic";

    public static final String TARGET_TOPIC = "target_topic";

    public static final String ENRICHED_TOPIC = "enriched_topic";

    public static final String ERROR_TRADES_TOPIC = "error_trades_topic";

    public static final String ALERT_TRADES_TOPIC = "email_alert_topic";

    public static final String NOT_RELEVANT_TOPIC = "not_relevant_topic";

    public static final String REPROCESSING_TOPIC = "reprocessing_topic";

    public static final String DLT_TOPIC = "dlt_topic";

    public static final String TOPIC_CARBON_FUTURE = "carbon_future_topic";

    public static final String TOPIC_CARBON_FORWARD = "carbon_forward_topic";

    public static final String TOPIC_COAL = "coal_topic";

    public static final String TOPIC_GAS_SPOT = "gas_spot_topic";

    public static final String TOPIC_GAS_FUTURE = "gas_future_topic";

    public static final String TOPIC_GAS_SWAP = "gas_swap_topic";

    public static final String TOPIC_OIL_FUTURE = "oil_future_topic";

    public static final String TOPIC_OIL_SWAP = "oil_swap_topic";

    public static final String TOPIC_POWER_FUTURE = "power_future_topic";

    public static final String TOPIC_UK_POWER = "uk_power_topic";

    public static final String TOPIC_POWER_FORWARD = "power_forward_topic";

    public static final String TOPIC_POWER_SWAP = "power_swap_topic";

    public static final String TOPIC_GAS_FORWARD = "gas_forward_topic";

    public static final String TOPIC_ENDUR_OUT = "endur_out_topic";

    public static final String TOPIC_ENDUR_RECON = "endur_recon_topic";

    public static final String ICE_CONFIG_TOPIC = "ice_config_topic";

    public static final String TRAYPORT_PRODUCT_CONFIG_TOPIC = "trayport_prod_config_topic";

    public static final String TRAYPORT_INSTRUMENT_CONFIG_TOPIC = "trayport_inst_config_topic";

    public static final String TRAYPORT_ITEMS_CONFIG_TOPIC = "trayport_items_config_topic";

    public static final String NASDAQ_CONFIG_TOPIC = "nasdaq_config_topic";

    public static final String RULE_TOPIC = "rule_topic";

    public static final String PERSON_ALIAS_TOPIC = "person_alias_topic";

    public static final String BROKER_ALIAS_TOPIC = "broker_alias_topic";

    public static final String MIC_ALIAS_TOPIC = "mic_alias_topic";

    public static final String COMPANY_ALIAS_TOPIC = "company_alias_topic";

    public static final String ATTRIBUTE_MAPPING_TOPIC = "attribute_mapping_topic";

    public static final String NORDPOOL_CONFIG_TOPIC = "nordpool_config_topic";

    public static final String LOCATION_MAPPING_TOPIC = "location_mapping_topic";

    public static final String LOCATION_MASTER_TOPIC = "location_master_topic";

    public static final String CAPACITY_CATEGORY_TOPIC = "capacity_category_topic";

    public static final String NOTIFICATION_EMAIL_IDS = "notificationEmailIds";

    public static final String TOPIC_FENIX_FF_TOPIC = "fenix_ff_trade_topic";

    public static final String TOPIC_GO_FF_TOPIC = "go_ff_trade_topic";

}
