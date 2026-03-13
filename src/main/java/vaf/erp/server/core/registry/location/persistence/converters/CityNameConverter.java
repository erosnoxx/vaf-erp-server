package vaf.erp.server.core.registry.location.persistence.converters;

import jakarta.persistence.Converter;
import vaf.erp.server.core.registry.location.domain.vos.CityName;
import vaf.erp.server.core.shared.domain.converters.GenericStringValueObjectConverter;

@Converter(autoApply = true)
public class CityNameConverter extends GenericStringValueObjectConverter<CityName> {
    protected CityNameConverter() {
        super(CityName.class);
    }
}
