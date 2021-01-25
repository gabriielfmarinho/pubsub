package com.redis.pubsub.config;

import java.util.List;
import java.util.stream.Collectors;

public class CarroMapper {

    public void carroDtosToCarros(List<CarroDTO> carroDtos) {
        List<Carro> carros = carroDtos.stream()
                .map(this::carroDtoToCarro)
                .collect(Collectors.toList());
    }

    public Carro carroDtoToCarro(CarroDTO carro) {
        return Carro.builder()
                .nome(carro.getNome())
                .cor(carro.getCor())
                .build();
    }
}
