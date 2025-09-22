package com.prueba.pruebaCrudHFSolution;

public class RutUtils {

    /**
     * Formatea un RUT chileno en el estilo ##.###.###-X
     * Ejemplo: 12345678K -> 12.345.678-K
     */
    public static String formatRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return rut;
        }

        rut = rut.replace(".", "").replace("-", "").toUpperCase();

        if (rut.length() < 2) {
            return rut;
        }

        String dv = rut.substring(rut.length() - 1);
        String number = rut.substring(0, rut.length() - 1);

        StringBuilder sb = new StringBuilder(number);
        StringBuilder formatted = new StringBuilder();

        int count = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            formatted.insert(0, sb.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                formatted.insert(0, ".");
                count = 0;
            }
        }

        return formatted + "-" + dv;
    }

    /**
     * Valida si un RUT es correcto según su dígito verificador.
     */
    public static boolean isValidRut(String rut) {
        if (rut == null || rut.isEmpty()) {
            return false;
        }

        rut = rut.replace(".", "").replace("-", "").toUpperCase();

        if (!rut.matches("\\d+[0-9K]")) {
            return false;
        }

        String dv = rut.substring(rut.length() - 1);
        String number = rut.substring(0, rut.length() - 1);

        int suma = 0, factor = 2;
        for (int i = number.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(number.charAt(i)) * factor;
            factor = factor == 7 ? 2 : factor + 1;
        }

        int resto = 11 - (suma % 11);
        String dvEsperado = switch (resto) {
            case 11 -> "0";
            case 10 -> "K";
            default -> String.valueOf(resto);
        };

        return dvEsperado.equals(dv);
    }
}

