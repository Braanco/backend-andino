package br.com.projeto.starchile.backend_starchile.repository;

import br.com.projeto.starchile.backend_starchile.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "Select * from employees where number_registry = :numberRegistry ",nativeQuery = true)
    Optional<Employee> findByNumberRegistry(Long numberRegistry);
}
