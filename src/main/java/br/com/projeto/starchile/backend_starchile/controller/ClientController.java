package br.com.projeto.starchile.backend_starchile.controller;

import br.com.projeto.starchile.backend_starchile.domain.client.Client;
import br.com.projeto.starchile.backend_starchile.domain.client.ClientDTO;
import br.com.projeto.starchile.backend_starchile.domain.client.InsertClientDTO;
import br.com.projeto.starchile.backend_starchile.repository.TourRepository;
import br.com.projeto.starchile.backend_starchile.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Classe que faz o controle dos dados
@RestController
@RequestMapping("/v1/andino")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TourRepository tourRepository;


    @PostMapping("/create")
    public ResponseEntity<Client> createCliente(@RequestBody InsertClientDTO clientDTO) {
        var data = this.clientService.createClient(clientDTO);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClientDTO>> getAllClient() {
        var data = this.clientService.getAllClient();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/getAll/{dateInicial}/{dateFinal}")
    public ResponseEntity<List<ClientDTO>> getData(@PathVariable String dateInicial, @PathVariable String dateFinal) {
        var data = this.clientService.getListClientDate(dateInicial, dateFinal);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(200).body("Sucesso");
    }


}
