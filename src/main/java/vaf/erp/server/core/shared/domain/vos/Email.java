package vaf.erp.server.core.shared.domain.vos;

import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;
import vaf.erp.server.core.shared.exceptions.vos.InvalidEmailException;

import java.util.regex.Pattern;

public final class Email extends StringValueObject {

    private static final int MAX_LENGTH = 254;
    private static final Pattern FORMAT = Pattern.compile(
            "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$"
    );

    private Email(String value) {
        super(value);
    }

    public static Email of(String value) {
        return new Email(value);
    }

    @Override
    protected String customValidate(String value) {
        checkLength(value, 1, MAX_LENGTH);

        if (!FORMAT.matcher(value).matches())
            throw createException("invalid email format");

        return value.toLowerCase();
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidEmailException(message);
    }
}