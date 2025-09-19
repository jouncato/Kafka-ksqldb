package com.litethinking.kafka.ksqldb.client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.confluent.ksql.api.client.Row;

public class LogRowSubscriber implements Subscriber<Row> {

    private Subscription subscription;
    private static final Logger LOG = LoggerFactory.getLogger(LogRowSubscriber.class);

    @Override
    public void onSubscribe(Subscription s) {
        LOG.info("onSubscribe(), starting subscription");

        this.subscription = s;

        // request the first row
        this.subscription.request(1);
    }

    @Override
    public void onNext(Row t) {
        LOG.info("onNext(), row: {}", t);

        // request the next row
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        LOG.error("onError(), error: {}", t.getMessage());
    }

    @Override
    public void onComplete() {
        LOG.info("onComplete(), all rows are consumed, subscription ended");
    }

}
