package br.com.projeto.starchile.backend_starchile.repository;

import br.com.projeto.starchile.backend_starchile.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = """
            SELECT *
            FROM Client
            WHERE tour_date BETWEEN :dateInicial AND :dateFinal;
            """,nativeQuery = true)
    Optional<List<Client>> findByTourDate(String dateInicial, String dateFinal);


}
