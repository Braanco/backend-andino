package br.com.projeto.starchile.backend_starchile.domain.client;

import java.time.LocalDate;

public record ClientDataDto(Long id, String name, String phone, String addressClient, Integer personQuantity,
                            LocalDate tourDate, String tourName, Double price, Double finalPrice) {
}
