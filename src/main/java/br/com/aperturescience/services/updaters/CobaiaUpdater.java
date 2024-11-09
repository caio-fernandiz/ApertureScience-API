package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.cobaias.CobaiaFormUpdateDTO;
import br.com.aperturescience.models.Cobaia;

@Component
public class CobaiaUpdater {

    public void atualizarCobaia(Cobaia cobaia, CobaiaFormUpdateDTO form) {
        if (form.nome() != null)
            cobaia.setNome(form.nome());
        if (form.idade() != null)
            cobaia.setIdade(form.idade());
        if (form.cpf() != null)
            cobaia.setCpf(form.cpf());
        if (form.altura() != null)
            cobaia.setAltura(form.altura());
        if (form.tipoSanguineo() != null)
            cobaia.setTipoSanguineo(form.tipoSanguineo());
        if (form.qi() != null)
            cobaia.setQi(form.qi());
        if (form.resultadoTesteResistencia() != null)
            cobaia.setResultadoTesteResistencia(form.resultadoTesteResistencia());
        if (form.resultadoTesteForca() != null)
            cobaia.setResultadoTesteForca(form.resultadoTesteForca());
        if (form.resultadoTesteVelocidade() != null)
            cobaia.setResultadoTesteVelocidade(form.resultadoTesteVelocidade());
        if (form.formacaoAcademica() != null)
            cobaia.setFormacaoAcademica(form.formacaoAcademica());
    }
}
