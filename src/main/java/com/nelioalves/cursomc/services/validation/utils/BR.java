package com.nelioalves.cursomc.services.validation.utils;


// Fonte: https://github.com/Rafa-Domingos/Springclass/blob/080deed2f5d29b077449c00ffd57462da6ec4a0d/src/main/java/com/rafael/springclass/services/validation/Utils/BR.java
public class BR {
	// CPF
    private static final int[] weightCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    // CNPJ
    private static final int[] weightCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calculate(final String str, final int[] weight) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    /**
     * Valida CPF
     *
     * @param CPF
     * @return
     */
    public static boolean isValidCPF(final String CPF) {
        if ((CPF == null) || (CPF.length() != 11) || CPF.matches(CPF.charAt(0) + "{11}")) return false;

        final Integer digit1 = calculate(CPF.substring(0, 9), weightCPF);
        final Integer digit2 = calculate(CPF.substring(0, 9) + digit1, weightCPF);
        return CPF.equals(CPF.substring(0, 9) + digit1.toString() + digit2.toString());
    }

    /**
     * Valida CNPJ
     *
     * @param CNPJ
     * @return
     */
    public static boolean isValidCNPJ(final String CNPJ) {
        if ((CNPJ == null) || (CNPJ.length() != 14) || CNPJ.matches(CNPJ.charAt(0) + "{14}")) return false;

        final Integer digit1 = calculate(CNPJ.substring(0, 12), weightCNPJ);
        final Integer digit2 = calculate(CNPJ.substring(0, 12) + digit1, weightCNPJ);
        return CNPJ.equals(CNPJ.substring(0, 12) + digit1.toString() + digit2.toString());
    }
}
