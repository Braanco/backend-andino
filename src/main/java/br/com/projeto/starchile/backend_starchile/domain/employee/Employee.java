package br.com.projeto.starchile.backend_starchile.domain.employee;

import br.com.projeto.starchile.backend_starchile.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    @Column(name = "number_registry",unique = true)
    private Long numberRegistry;
    private Double salary;
    private Double commission;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    private List<Client> clientId;

    public Employee(EmployeeCreateDto createDto) {
        this.name = createDto.name().toUpperCase();
        this.numberRegistry = createDto.numberRegistry();
        this.salary = createDto.salary();
    }
}
