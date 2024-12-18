package br.com.aperturescience.models;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.aperturescience.infra.security.UserRole;
import br.com.aperturescience.util.GeradorDeLogin;
import br.com.aperturescience.util.GeradorDeSenha;
import br.com.aperturescience.util.UserRoleConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Funcionario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer idade;
    private String cpf;
    private String email;
    private String telefone;
    @Convert(converter = UserRoleConverter.class)
    @Column(name = "cargo", length = 50) 
    private UserRole cargo;
    private Integer nivelAcesso;
    private String senha;
    private String codigoLogin;
    
    public Funcionario(String codigoLogin, String senha, UserRole cargo){
        this.codigoLogin = codigoLogin;
        this.senha = senha;
        this.cargo = cargo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.cargo == UserRole.DIRETOR) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return codigoLogin;
    }
}
