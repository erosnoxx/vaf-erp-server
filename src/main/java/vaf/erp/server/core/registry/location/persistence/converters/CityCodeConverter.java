package vaf.erp.server.core.registry.location.persistence.converters;

import jakarta.persistence.Converter;
import vaf.erp.server.core.registry.location.domain.vos.CityCode;
import vaf.erp.server.core.shared.domain.converters.GenericStringValueObjectConverter;

@Converter(autoApply = true)
public class CityCodeConverter extends GenericStringValueObjectConverter<CityCode> {
    protected CityCodeConverter() {
        super(CityCode.class);
    }
}
