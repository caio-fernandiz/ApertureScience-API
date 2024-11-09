package br.com.aperturescience.dtos.cobaias;

import br.com.aperturescience.models.Cobaia;

public record CobaiaDetailsDTO(Long id, String nome, Integer idade, String cpf, String altura, String tipoSanguineo, 
    Integer qi, Integer resultadoTesteResistencia, Integer resultadoTesteForca, Integer resultadoTesteVelocidade, String formacaoAcademica) {

    public CobaiaDetailsDTO(Cobaia cobaia){
        this(cobaia.getId(), cobaia.getNome(), cobaia.getIdade(), cobaia.getCpf(), cobaia.getAltura(), cobaia.getTipoSanguineo(),
        cobaia.getQi(), cobaia.getResultadoTesteResistencia(), cobaia.getResultadoTesteForca(),  cobaia.getResultadoTesteVelocidade(), cobaia.getFormacaoAcademica());
    }
}
