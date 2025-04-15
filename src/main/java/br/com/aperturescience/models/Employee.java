package br.com.aperturescience.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.aperturescience.infra.user.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Employee implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String cpf;
    private String email;
    private String telephone;
    private UserRole role;
    private Integer accessLevel;
    private String psswrd;
    private String loginCode;

    public Employee(String logingCode, String psswrd, UserRole role) {
        this.loginCode = logingCode;
        this.psswrd = psswrd;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.DIRETOR) return List.of(new SimpleGrantedAuthority("ROLE_DIRETOR"), new SimpleGrantedAuthority("CIENTISTA"));
        else return List.of(new SimpleGrantedAuthority("ROLE_CIENTISTA"));
    }

    @Override
    public String getPassword() {
        return psswrd;
    }

    @Override
    public String getUsername() {
        return loginCode;
    }
}
