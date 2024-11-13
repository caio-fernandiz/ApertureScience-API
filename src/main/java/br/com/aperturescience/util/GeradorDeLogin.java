package br.com.aperturescience.util;

import java.util.Random;

public class GeradorDeLogin {

    public static String gerarCodigoAleatorio() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        int letraIndex = random.nextInt(3);
        int numeroIndex = random.nextInt(3);

        while (letraIndex == numeroIndex) {
            numeroIndex = random.nextInt(3);
        }

        for (int i = 0; i < 3; i++) {
            if (i == letraIndex) {
                codigo.append(letras.charAt(random.nextInt(letras.length())));
            } else if (i == numeroIndex) {
                codigo.append(numeros.charAt(random.nextInt(numeros.length())));
            } else {
                if (random.nextBoolean()) {
                    codigo.append(letras.charAt(random.nextInt(letras.length())));
                } else {
                    codigo.append(numeros.charAt(random.nextInt(numeros.length())));
                }
            }
        }

        codigo.append("-");

        letraIndex = random.nextInt(2);
        numeroIndex = 1 - letraIndex;

        for (int i = 0; i < 2; i++) {
            if (i == letraIndex) {
                codigo.append(letras.charAt(random.nextInt(letras.length())));
            } else {
                codigo.append(numeros.charAt(random.nextInt(numeros.length())));
            }
        }

        return codigo.toString();
    }
}
