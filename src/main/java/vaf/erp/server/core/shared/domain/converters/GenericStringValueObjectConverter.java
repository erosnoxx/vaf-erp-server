package vaf.erp.server.core.shared.domain.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vaf.erp.server.core.shared.domain.vos.common.ValueObject;

import java.lang.reflect.Constructor;

@Converter
public abstract class GenericStringValueObjectConverter<T extends ValueObject<String>>
        implements AttributeConverter<T, String> {

    private final Class<T> type;

    protected GenericStringValueObjectConverter(Class<T> type) {
        this.type = type;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        try {
            Constructor<T> ctor = type.getDeclaredConstructor(String.class);
            ctor.setAccessible(true);
            return ctor.newInstance(dbData);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to instantiate ValueObject " + type.getSimpleName() + " from DB value: " + dbData, e
            );
        }
    }
}
