package br.com.aperturescience.models;

import br.com.aperturescience.util.GeradorDeLogin;
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
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer idade;
    private String cpf;
    private String email;
    private String telefone;
    private String cargo;
    private Integer nivelAcesso;
    private String senha;
    private String codigoLogin;
    
    @PrePersist
    protected void onCreate(){
        this.codigoLogin = GeradorDeLogin.gerarCodigoAleatorio();
    }
}
