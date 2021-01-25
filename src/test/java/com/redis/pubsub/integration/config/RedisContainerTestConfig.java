package com.redis.pubsub.integration.config;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class RedisContainerTestConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public GenericContainer<?> initializeContainer() {
        final GenericContainer<?> redisContainer = new GenericContainer<>(DockerImageName.parse("redis"))
                .withExposedPorts(6379);
        if (isNotRunning(redisContainer)) {
            redisContainer.start();
        }
        return redisContainer;
    }

    private boolean isNotRunning(GenericContainer<?> redisContainer) {
        return !redisContainer.isRunning();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final GenericContainer<?> redisContainer = initializeContainer();
        TestPropertyValues.of(
                "spring.redis.host=" + redisContainer.getHost(),
                "spring.redis.port=" + redisContainer.getMappedPort(6379)
        ).applyTo(applicationContext.getEnvironment());
    }
}
