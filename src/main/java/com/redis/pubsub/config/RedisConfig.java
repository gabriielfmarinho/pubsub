package com.redis.pubsub.config;

import com.redis.pubsub.redis.MessagePublisher;
import com.redis.pubsub.redis.RedisMessagePublisher;
import com.redis.pubsub.redis.RedisMessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/*
    A documentão diz que com dependencia do DATA REDIS já possuimos tudo que é necessário para trabalhar com PubSub
    utilizando o REDIS.

    * Um connection factory
        - Neste caso já pegamos o que o Spring cria por default é fizemos a injecção do mesmo via parametro no metodo.
    * Um message listener container
        - Neste caso configuramos o BEAN: RedisMessageListenerContainer;
    *Um Redis template
        - Foi configurado manualmente também o BEAN: RedisTemplate, é utilizado para fazer o publish da mensagem.
        Existem outras implementações concetras do RedisTemplate, neste projeto decidimos utilizar a RedisTemplate.
 */

@Configuration
public class RedisConfig {

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(final RedisConnectionFactory connectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }

    @Bean
    RedisMessageListenerContainer redisContainer(final RedisConnectionFactory connectionFactory) {
        final RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    @Primary
    MessagePublisher redisPublisher(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        return new RedisMessagePublisher(redisTemplate, topic);
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub-topic");
    }
}
