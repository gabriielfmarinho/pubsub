package com.redis.pubsub.redis;

public interface MessagePublisher {

    void publish (final String message);
}
