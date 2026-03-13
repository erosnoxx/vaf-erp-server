package vaf.erp.server.core.registry.location.domain.vos;

import vaf.erp.server.core.registry.location.domain.exceptions.InvalidCityNameException;
import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;

public final class CityName extends StringValueObject {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;

    private CityName(String value) {
        super(value);
    }

    public static CityName of(String value) {
        return new CityName(value);
    }

    @Override
    protected String customValidate(String value) {
        checkLength(value, MIN_LENGTH, MAX_LENGTH);
        return value;
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidCityNameException(message);
    }
}
