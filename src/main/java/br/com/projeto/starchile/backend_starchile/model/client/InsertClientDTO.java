package br.com.projeto.starchile.backend_starchile.model.client;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record InsertClientDTO(String name,String phone, String addressClient,
                              String hotelAddress,
                              String hotelName, Integer personQuantity,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate tourDate,
                              Long tourId, Long numberRegistry,String cpf) {
}
