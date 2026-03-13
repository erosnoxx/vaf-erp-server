package vaf.erp.server.core.shared.domain.vos;

import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;
import vaf.erp.server.core.shared.exceptions.vos.InvalidDocumentException;

public final class Document extends StringValueObject {

    private static final int CPF_LENGTH = 11;
    private static final int CNPJ_LENGTH = 14;
    private static final String FORMATTING_CHARS = "[.\\-/]";

    private Document(String value) {
        super(value);
    }

    public static Document of(String value) {
        return new Document(value);
    }

    @Override
    protected String customValidate(String value) {
        var numeric = value.replaceAll(FORMATTING_CHARS, "").toUpperCase();

        if (numeric.length() == CPF_LENGTH) {
            var digits = numeric.replaceAll("\\D", "");
            if (digits.length() != CPF_LENGTH)
                throw createException("invalid cpf: " + value);
            if (!isValidCpf(digits))
                throw createException("invalid cpf: " + value);
            return digits;
        }

        if (numeric.length() == CNPJ_LENGTH) {
            if (!isValidCnpj(numeric))
                throw createException("invalid cnpj: " + value);
            return numeric;
        }

        throw createException("document must be a cpf (11 digits) or cnpj (14 chars)");
    }

    public boolean isCpf() {
        return value.length() == CPF_LENGTH;
    }

    public boolean isCnpj() {
        return value.length() == CNPJ_LENGTH;
    }

    public String getFormatted() {
        if (isCpf())
            return "%s.%s.%s-%s".formatted(
                    value.substring(0, 3),
                    value.substring(3, 6),
                    value.substring(6, 9),
                    value.substring(9)
            );

        return "%s.%s.%s/%s-%s".formatted(
                value.substring(0, 2),
                value.substring(2, 5),
                value.substring(5, 8),
                value.substring(8, 12),
                value.substring(12)
        );
    }

    private boolean isValidCpf(String cpf) {
        if (cpf.matches("(\\d)\\1{10}")) return false;

        return validateDigit(cpf, new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2}, 9) &&
                validateDigit(cpf, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2}, 10);
    }

    private boolean isValidCnpj(String cnpj) {
        if (cnpj.matches("(\\w)\\1{13}")) return false;

        return validateDigit(cnpj, new int[]{5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, 12) &&
                validateDigit(cnpj, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, 13);
    }

    private boolean validateDigit(String doc, int[] weights, int digitPosition) {
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += ((int) doc.charAt(i) - (int) '0') * weights[i];
        }

        int remainder = sum % 11;
        int expected = remainder < 2 ? 0 : 11 - remainder;
        int actual = (int) doc.charAt(digitPosition) - (int) '0';

        return expected == actual;
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidDocumentException(message);
    }
}