package com.redis.pubsub.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CarroDTO {
    private String nome;

    private String cor;
}
