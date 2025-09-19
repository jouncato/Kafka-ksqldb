package com.litethinking.kafka.ksqldb.client;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.InsertsPublisher;
import io.confluent.ksql.api.client.KsqlObject;

@Service
public class BasicJavaClient {

    private static final Logger LOG = LoggerFactory.getLogger(BasicJavaClient.class);

    private static final ClientOptions CLIENT_OPTIONS = ClientOptions.create()
            .setHost("localhost")
            .setPort(8088);

    public void createStream() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);
        var statement = "DROP STREAM IF EXISTS `s-java-client` DELETE TOPIC;";

        var result = client.executeStatement(statement).get();

        LOG.info("Stream execution result: {}", result);

        statement = """
                CREATE OR REPLACE STREAM `s-java-client` (
                  `fieldOne` VARCHAR,
                  `fieldTwo` INT,
                  `fieldThree` BOOLEAN
                ) WITH (
                  KAFKA_TOPIC = 'topic-java-client',
                  PARTITIONS = 2,
                  VALUE_FORMAT = 'JSON'
                );
                """;

        result = client.executeStatement(statement).get();

        LOG.info("Stream execution result: {}", result);

        client.close();
    }

    public void describeStream() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);
        var description = client.describeSource("`s-java-client`").get();

        LOG.info("{} {} has the folllowing fields : {}",
                description.type(),
                description.name(),
                description.fields());

        client.close();
    }

    public void listObjects() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);

        LOG.info("Kafka topics");
        client.listTopics().get().forEach(t -> LOG.info(
                "topic {} has {} partitions", t.getName(), t.getPartitions()));

        LOG.info("\n\n");
        LOG.info("Kafka streams");
        client.listStreams().get()
                .forEach(s -> LOG.info("stream {} is coming from topic {}", s.getName(), s.getTopic()));

        LOG.info("\n\n");
        LOG.info("Kafka tables");
        client.listTables().get()
                .forEach(tbl -> LOG.info("table {} is coming from topic {}", tbl.getName(), tbl.getTopic()));

        client.close();
    }

    private KsqlObject generateNewRow() {
        return new KsqlObject().put("`fieldOne`", "Now is " + LocalTime.now())
                .put("`fieldTwo`", ThreadLocalRandom.current().nextInt())
                .put("`fieldThree`", ThreadLocalRandom.current().nextBoolean());
    }

    public void insertSingle() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);
        var newRow = generateNewRow();

        client.insertInto("`s-java-client`", newRow).get();

        client.close();
    }

    public void insertStream(int rows) throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);
        var insertsPublisher = new InsertsPublisher();
        var acksPublisher = client.streamInserts("`s-java-client`", insertsPublisher);

        for (int i = 0; i < rows; i++) {
            insertsPublisher.accept(generateNewRow());
        }

        insertsPublisher.complete();

        acksPublisher.get();

        client.close();
    }

    public void pullQuery() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);
        var pullQueryResult = client.executeQuery("SELECT * FROM `s-java-client` LIMIT 20;").get();

        pullQueryResult.forEach(row -> LOG.info("Row: {}", row));

        client.close();
    }

    public void pushQuerySync() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);
        var pushQueryResult = client.streamQuery("SELECT * FROM `s-java-client` EMIT CHANGES;").get();

        while (true) {
            var row = pushQueryResult.poll();
            LOG.info("Row: {}", row);
        }
    }

    public void pushQueryAsync() throws InterruptedException, ExecutionException {
        var client = Client.create(CLIENT_OPTIONS);

        client.streamQuery("SELECT * FROM `s-java-client` EMIT CHANGES;")
                .thenAccept(
                        pushQueryResult -> {
                            var subscriber = new LogRowSubscriber();

                            pushQueryResult.subscribe(subscriber);
                        })
                .exceptionally(
                        e -> {
                            LOG.error("Error: {}", e.getMessage());
                            return null;
                        });
    }

}
