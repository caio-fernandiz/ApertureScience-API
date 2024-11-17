package br.com.aperturescience.util;

import java.util.Random;

public class GeradorDeSenha {

    public static String gerarSenha() {

        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        String simbolos = "!@#$$%&*";
        Random random = new Random();
        StringBuilder senha = new StringBuilder();

        int letraIndex = random.nextInt(2);
        int numeroIndex = random.nextInt(4);
        int simboloIndex = random.nextInt(2);

        for(int i=0; i<8; i++){
            if(i == letraIndex){
                senha.append(letras.charAt(random.nextInt(letras.length())));
            }
            else if(i == numeroIndex){
                senha.append(numeros.charAt(random.nextInt(numeros.length())));
            }

            else if(i == simboloIndex){
                senha.append(simbolos.charAt(random.nextInt(simbolos.length())));
            }

            else{
                if(random.nextBoolean()){
                    senha.append(letras.charAt(random.nextInt(letras.length())));
                }
                else if (random.nextBoolean()) {
                    senha.append(numeros.charAt(random.nextInt(numeros.length())));
                }
                else{
                    senha.append(simbolos.charAt(random.nextInt(simbolos.length())));
                }
            }
        }

        return senha.toString();
    }
}
