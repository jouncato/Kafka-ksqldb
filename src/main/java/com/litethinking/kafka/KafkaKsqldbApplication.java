package com.litethinking.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litethinking.kafka.ksqldb.client.BasicJavaClient;

@SpringBootApplication
public class KafkaKsqldbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaKsqldbApplication.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(KafkaKsqldbApplication.class);

    @Autowired
    private BasicJavaClient basicJavaClient;

    @Override
    public void run(String... args) throws Exception {
        basicJavaClient.createStream();
        // basicJavaClient.describeStream();
        // basicJavaClient.listObjects();

        // for (int i = 0; i < 5; i++) {
        // basicJavaClient.insertSingle();
        // }

        // basicJavaClient.insertStream(20);
        // basicJavaClient.pullQuery();

        // LOG.info("Starting pushQuerySync()");
        // basicJavaClient.pushQuerySync();
        // LOG.info("Started pushQuerySync()");

        LOG.info("Starting pushQueryAsync()");
        basicJavaClient.pushQueryAsync();
        LOG.info("Started pushQueryAsync()");
    }

}
