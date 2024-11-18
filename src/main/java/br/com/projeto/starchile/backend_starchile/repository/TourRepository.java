package br.com.projeto.starchile.backend_starchile.repository;

import br.com.projeto.starchile.backend_starchile.model.tour.Tours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tours, Long> {

}
