package com.redis.pubsub.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Carro {

    private String nome;

    private String cor;
}
