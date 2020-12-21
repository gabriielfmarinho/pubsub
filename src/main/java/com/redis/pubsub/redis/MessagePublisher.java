package com.redis.pubsub.redis;

/*
    Spring DATA REDIS não possui um contrato estabelecido para publish de mensagens no redis, portanto criei um contrato
    simples a fim de patronizar a forma de chamar o publish.
 */
public interface MessagePublisher {

    void publish (final String message);
}
