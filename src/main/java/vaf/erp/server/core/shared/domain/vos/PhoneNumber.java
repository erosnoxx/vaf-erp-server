package vaf.erp.server.core.shared.domain.vos;

import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;
import vaf.erp.server.core.shared.exceptions.vos.InvalidPhoneNumberException;

public final class PhoneNumber extends StringValueObject {

    private static final int MOBILE_LENGTH = 11;
    private static final int LANDLINE_LENGTH = 10;

    private PhoneNumber(String value) {
        super(value);
    }

    public static PhoneNumber of(String value) {
        return new PhoneNumber(value);
    }

    @Override
    protected String customValidate(String value) {
        var numeric = clean(value);

        if (numeric.length() != MOBILE_LENGTH && numeric.length() != LANDLINE_LENGTH)
            throw createException("invalid phone number format");

        return mask(numeric);
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidPhoneNumberException(message);
    }

    private String clean(String value) {
        return value.replaceAll("\\D", "");
    }

    private String mask(String numeric) {
        if (numeric.length() == MOBILE_LENGTH)
            return "(%s) %s-%s".formatted(
                    numeric.substring(0, 2),
                    numeric.substring(2, 7),
                    numeric.substring(7)
            );

        return "(%s) %s-%s".formatted(
                numeric.substring(0, 2),
                numeric.substring(2, 6),
                numeric.substring(6)
        );
    }

    public boolean isMobile() {
        return clean(value).length() == MOBILE_LENGTH;
    }
}