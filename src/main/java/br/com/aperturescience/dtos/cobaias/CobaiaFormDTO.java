package br.com.aperturescience.dtos.cobaias;

import br.com.aperturescience.models.Cobaia;

public record CobaiaFormDTO(String nome, Integer idade, String cpf, String altura, String tipoSanguineo, 
    Integer qi, Integer resultadoTesteResistencia, Integer resultadoTesteForca, Integer resultadoTesteVelocidade, String formacaoAcademica) {

    public Cobaia toEntity(){
        return new Cobaia(null, nome, idade, cpf, altura,tipoSanguineo,
        qi, resultadoTesteResistencia, resultadoTesteForca, resultadoTesteVelocidade, formacaoAcademica);
    }
}
