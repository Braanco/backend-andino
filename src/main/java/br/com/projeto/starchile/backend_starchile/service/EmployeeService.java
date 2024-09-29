package br.com.projeto.starchile.backend_starchile.service;

import br.com.projeto.starchile.backend_starchile.domain.client.Client;
import br.com.projeto.starchile.backend_starchile.domain.client.ClientDataDto;
import br.com.projeto.starchile.backend_starchile.domain.employee.Employee;
import br.com.projeto.starchile.backend_starchile.domain.employee.EmployeeCreateDto;
import br.com.projeto.starchile.backend_starchile.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //cria o funcionario
    public Employee create(EmployeeCreateDto createDto) {
        if (employeeRepository.findByNumberRegistry(createDto.numberRegistry()).isEmpty()) {
            Employee newEmployee = new Employee(createDto);
            var data = employeeRepository.save(newEmployee);
            return data;
        } else {
            throw new RuntimeException("esse numero de registro ja existe");
        }

    }

    //deleta o empregado
    public void deleteEmployee(Long numberRegistry){
        var data = employeeRepository.findByNumberRegistry(numberRegistry).orElseThrow(()->new RuntimeException("Empregado não encontrado"));
        employeeRepository.delete(data);
    }

    //adicionar a comissao e os dados do cliente ao funcionario
    public void addDatasEmployee(Long numeroDeRegistro, Client client) {
        var dados = employeeRepository.findByNumberRegistry(numeroDeRegistro).orElseThrow(() -> new RuntimeException("not found employee"));
        List<Client> currentClients = dados.getClientId();
        if (currentClients == null) {
            currentClients = new ArrayList<>();
        }
        currentClients.add(client);
        dados.setClientId(currentClients);
        dados.setCommission(commission(client, dados));
    }

    //calcular a comissao
    public Double commission(Client client, Employee employee) {
        var dataClient = client.getFinalPrice();
        var dataEmployee = employee.getCommission();
        if (dataEmployee == null) {
            dataEmployee = 0.0;
        }
        return dataEmployee + (dataClient * 0.05);


    }
    //Mostra empregado via numero de registro
    public Employee getEmployee(Long numberRegistry) {
        var data = employeeRepository.findByNumberRegistry(numberRegistry).orElseThrow(() -> new RuntimeException("Employee not found"));
        return data;
    }


    //LIsta de clientes que o empregado fez a venda
    public List<ClientDataDto> getCliente(Long numberRegistry) {
        var data = employeeRepository.findByNumberRegistry(numberRegistry).orElseThrow(() -> new RuntimeException("empregado nao existe"));
        var tes = data.getClientId().stream().map(cl -> new ClientDataDto(cl.getId(), cl.getName(), cl.getPhone(),
                cl.getAddressClient(), cl.getPersonQuantity(), cl.getTourDate(), cl.getTourId().getName(),
                cl.getTourId().getPrice(), cl.getFinalPrice())).collect(Collectors.toList());
        return tes;
    }

    // metodo apenas para testa se ta funcionando
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }


}
