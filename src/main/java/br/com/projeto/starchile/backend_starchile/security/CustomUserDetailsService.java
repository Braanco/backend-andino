package br.com.projeto.starchile.backend_starchile.security;

import br.com.projeto.starchile.backend_starchile.model.login.Login;
import br.com.projeto.starchile.backend_starchile.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login user = loginRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.name()))  // role.getName() deve retornar algo como "ROLE_USER"
//                .collect(Collectors.toList());

        return new User(user.getUserName(),user.getPassword(), new ArrayList<>());
    }
}
