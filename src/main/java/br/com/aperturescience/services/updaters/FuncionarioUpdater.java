package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.funcionarios.FuncionarioFormUpdateDTO;
import br.com.aperturescience.infra.security.UserRole;
import br.com.aperturescience.models.Funcionario;

@Component
public class FuncionarioUpdater {
    String cargoString;

    public void atualizarFuncionario(Funcionario funcionario, FuncionarioFormUpdateDTO form) {
        if (form.nome() != null)
            funcionario.setNome(form.nome());
        if (form.idade() != null)
            funcionario.setIdade(form.idade());
        if (form.cpf() != null)
            funcionario.setCpf(form.cpf());
        if (form.email() != null)
            funcionario.setEmail(form.email());
        if (form.telefone() != null)
            funcionario.setTelefone(form.telefone());
        if (form.cargo() != null)
            funcionario.setCargo(parseUserRole(form.cargo()));
        if (form.nivelAcesso() != null)
            funcionario.setNivelAcesso(form.nivelAcesso());
        if (form.senha() != null)
            funcionario.setSenha(form.senha());
        if (form.codigoLogin() != null)
            funcionario.setCodigoLogin(form.codigoLogin());
    }

    private UserRole parseUserRole(String cargoString) {
        return UserRole.valueOf(cargoString.toUpperCase());
    }
}
