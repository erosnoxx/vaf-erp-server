package vaf.erp.server.core.registry.location.domain.vos;

import vaf.erp.server.core.registry.location.domain.exceptions.InvalidCityCodeException;
import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;

public final class CityCode extends StringValueObject {

    private static final int MIN_LENGTH = 7;
    private static final int MAX_LENGTH = 7;

    private CityCode(String value) {
        super(value);
    }

    public static CityCode of(String value) {
        return new CityCode(value);
    }

    @Override
    protected String customValidate(String value) {
        checkLength(value, MIN_LENGTH, MAX_LENGTH);
        if (!value.matches("\\d{7}"))
            throw createException("city code must contain only digits");
        return value;
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidCityCodeException(message);
    }
}
