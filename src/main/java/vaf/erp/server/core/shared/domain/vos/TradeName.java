package vaf.erp.server.core.shared.domain.vos;

import vaf.erp.server.core.shared.domain.vos.common.StringValueObject;
import vaf.erp.server.core.shared.exceptions.DomainException;
import vaf.erp.server.core.shared.exceptions.vos.InvalidTradeNameException;

public final class TradeName extends StringValueObject {

    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 150;

    private TradeName(String value) {
        super(value);
    }

    public static TradeName of(String value) {
        return new TradeName(value);
    }

    @Override
    protected String customValidate(String value) {
        checkLength(value, MIN_LENGTH, MAX_LENGTH);
        return value;
    }

    @Override
    protected DomainException createException(String message) {
        return new InvalidTradeNameException(message);
    }
}