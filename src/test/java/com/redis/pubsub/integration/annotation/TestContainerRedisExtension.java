package com.redis.pubsub.integration.annotation;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestContainerRedisExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        RedisContainerCustom redisContainer = RedisContainerCustom.getInstance();
        redisContainer.start();
    }
}
