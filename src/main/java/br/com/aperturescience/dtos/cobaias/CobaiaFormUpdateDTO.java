package br.com.aperturescience.dtos.cobaias;

public record CobaiaFormUpdateDTO(String nome, Integer idade, String cpf, String altura, String tipoSanguineo, 
    Integer qi, Integer resultadoTesteResistencia, Integer resultadoTesteForca, Integer resultadoTesteVelocidade, String formacaoAcademica) {

}
