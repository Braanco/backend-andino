package br.com.projeto.starchile.backend_starchile.model.client;

import java.time.LocalDate;

public record ClientDTO(Long id, String name, String phone,
                        String addressClient, String hotelAddress,
                        String hotelName, Integer personQuantity,
                        LocalDate tourDate, Double finalPrice,
                        String tourName, Double price) {
}
