package com.redis.pubsub.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisMessagePublisher implements MessagePublisher {

    private RedisTemplate<String, Object> redisTemplate;

    private ChannelTopic topic;

    public RedisMessagePublisher() {
    }

    public RedisMessagePublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(final String message) {
        log.info("Publishing message, message={}", message);
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
