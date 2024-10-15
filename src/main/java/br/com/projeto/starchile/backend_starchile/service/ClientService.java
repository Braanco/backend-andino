package br.com.projeto.starchile.backend_starchile.service;

import br.com.projeto.starchile.backend_starchile.domain.client.Client;
import br.com.projeto.starchile.backend_starchile.domain.client.ClientDTO;
import br.com.projeto.starchile.backend_starchile.domain.client.InsertClientDTO;
import br.com.projeto.starchile.backend_starchile.repository.ClientRepository;
import br.com.projeto.starchile.backend_starchile.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Classe de serviço
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private EmployeeService employeeService;

    //Metodo que cria o pedido do cliente
    public Client createClient(InsertClientDTO clientDTO) {
        Client newClient = new Client(clientDTO);
        var dataTour = tourRepository.findById(clientDTO.tourId()).orElseThrow(() -> new RuntimeException("o passeio escolhido nao existe"));
        newClient.setFinalPrice(calculateFinalPrice(dataTour.getPrice(), newClient.getPersonQuantity()));
        newClient.setTourId(dataTour);
        employeeService.addDatasEmployee(clientDTO.numberRegistry(), newClient);
        clientRepository.save(newClient);
        return newClient;
    }

    //metodo que gera a lista de todos os pedidos dos clientes
    public List<ClientDTO> getAllClient() {
        var dataClient = clientRepository.findAll();
        return dataClient.stream().map(client -> new ClientDTO(client.getId(), client.getName(), client.getPhone(),
                        client.getAddressClient(),
                        client.getHotelAddress(),
                        client.getHotelName(),
                        client.getPersonQuantity(),
                        client.getTourDate(),
                        client.getFinalPrice(),
                        client.getTourId().getName(),
                        client.getTourId().getPrice()))
                .collect(Collectors.toList());

    }

    //Metodo  que gera a lista dos pedidos que estar dentro de determinadas datas
    public List<ClientDTO> getListClientDate(String dateInicial, String dateFinal) {
        var dataClient = clientRepository.findByTourDate(dateInicial, dateFinal).orElseThrow();

        return dataClient.stream().map(cl -> new ClientDTO(cl.getId(), cl.getName(), cl.getPhone(),
                        cl.getAddressClient(),
                        cl.getHotelAddress(),
                        cl.getHotelName(),
                        cl.getPersonQuantity(),
                        cl.getTourDate(),
                        cl.getFinalPrice(),
                        cl.getTourId().getName(),
                        cl.getTourId().getPrice()))
                .collect(Collectors.toList());

    }

    //Deleta o pedido do cliente pelo  id
    public void deleteClient(Long id) {
        var data = clientRepository.findById(id).orElseThrow(()->new RuntimeException("cliente nao existe"));
        clientRepository.delete(data);

    }



    //Metodo que faz o calculo final do pedido, pegando o preço do passeio e a quantidade de pessoa
    private Double calculateFinalPrice(Double price, Integer personQuantity) {
        return price * personQuantity;
    }
}
