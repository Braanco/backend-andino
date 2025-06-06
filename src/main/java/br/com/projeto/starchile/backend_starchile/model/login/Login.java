package br.com.projeto.starchile.backend_starchile.model.login;

import br.com.projeto.starchile.backend_starchile.controller.RegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


    public Login(RegisterDto registerDto) {
        this.userName = registerDto.user_name();

    }
}
