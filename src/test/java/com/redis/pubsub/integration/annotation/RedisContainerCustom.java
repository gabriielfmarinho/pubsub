package com.redis.pubsub.integration.annotation;

import org.testcontainers.containers.GenericContainer;

public class RedisContainerCustom extends GenericContainer<RedisContainerCustom> {

    private static final String IMAGE = "redis";
    private static RedisContainerCustom container;

    private RedisContainerCustom() {
        super(IMAGE);
    }

    public static RedisContainerCustom getInstance() {
        if (container == null) {
            container = new RedisContainerCustom();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("REDIS_HOST", container.getHost());
        System.setProperty("REDIS_PORT", String.valueOf(container.getMappedPort(6379)));
    }

    //do nothing, JVM handles shut down
    @Override
    public void stop() {
    }
}
