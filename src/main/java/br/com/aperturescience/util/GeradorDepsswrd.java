package br.com.aperturescience.util;

import java.util.Random;

public class GeradorDepsswrd {

    public static String gerarpsswrd() {

        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        String simbolos = "!@#$$%&*";
        Random random = new Random();
        StringBuilder psswrd = new StringBuilder();

        int letraIndex = random.nextInt(2);
        int numeroIndex = random.nextInt(4);
        int simboloIndex = random.nextInt(2);

        for(int i=0; i<8; i++){
            if(i == letraIndex){
                psswrd.append(letras.charAt(random.nextInt(letras.length())));
            }
            else if(i == numeroIndex){
                psswrd.append(numeros.charAt(random.nextInt(numeros.length())));
            }

            else if(i == simboloIndex){
                psswrd.append(simbolos.charAt(random.nextInt(simbolos.length())));
            }

            else{
                if(random.nextBoolean()){
                    psswrd.append(letras.charAt(random.nextInt(letras.length())));
                }
                else if (random.nextBoolean()) {
                    psswrd.append(numeros.charAt(random.nextInt(numeros.length())));
                }
                else{
                    psswrd.append(simbolos.charAt(random.nextInt(simbolos.length())));
                }
            }
        }

        return psswrd.toString();
    }
}
