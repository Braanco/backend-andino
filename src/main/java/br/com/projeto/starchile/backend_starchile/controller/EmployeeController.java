package br.com.projeto.starchile.backend_starchile.controller;

import br.com.projeto.starchile.backend_starchile.domain.employee.EmployeeCreateDto;
import br.com.projeto.starchile.backend_starchile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Classe que faz o controle dos dados
@RestController
@RequestMapping("/v1/star_chile/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity create (@RequestBody EmployeeCreateDto createDto ){
        var data = employeeService.create(createDto);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/get-Employee/{number}")
    public ResponseEntity getEmployee(@PathVariable(name = "number") Long numberRegister){
        var data = employeeService.getEmployee(numberRegister);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{number}")
    public ResponseEntity getClient(@PathVariable Long number){
        var data = employeeService.getCliente(number);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{numberRegistry}")
    public ResponseEntity deleteEmployee(@PathVariable Long numberRegistry){
        employeeService.deleteEmployee(numberRegistry);
        return ResponseEntity.ok().build();
    }
}
