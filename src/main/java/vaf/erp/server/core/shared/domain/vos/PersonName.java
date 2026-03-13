package vaf.erp.server.core.shared.domain.vos;

import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;
import vaf.erp.server.core.shared.exceptions.vos.InvalidPersonNameException;

public final class PersonName extends StringValueObject {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 100;

    private PersonName(String value) {
        super(value);
    }

    public static PersonName of(String value) {
        return new PersonName(value);
    }

    @Override
    protected String customValidate(String value) {
        checkLength(value, MIN_LENGTH, MAX_LENGTH);
        return value;
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidPersonNameException(message);
    }
}