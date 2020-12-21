package com.redis.pubsub.resource;

import com.redis.pubsub.redis.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pubsub")
@RequiredArgsConstructor
public class PubSubResource {

    @Qualifier("redisPublisher")
    private final MessagePublisher publisher;

    @PostMapping("/publisher")
    @ResponseStatus(HttpStatus.OK)
    void publisher(final @RequestBody String message) {
        publisher.publish(message);
    }

}
