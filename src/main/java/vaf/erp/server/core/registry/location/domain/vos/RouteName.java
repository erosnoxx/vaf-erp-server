package vaf.erp.server.core.registry.location.domain.vos;

import vaf.erp.server.core.registry.location.domain.exceptions.InvalidRouteNameException;
import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;

public final class RouteName extends StringValueObject {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;

    private RouteName(String value) {
        super(value);
    }

    public static RouteName of(String value) {
        return new RouteName(value);
    }

    @Override
    protected String customValidate(String value) {
        checkLength(value, MIN_LENGTH, MAX_LENGTH);
        return value.toUpperCase();
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidRouteNameException(message);
    }
}
