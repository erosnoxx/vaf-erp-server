package vaf.erp.server.core.registry.location.persistence.converters;

import jakarta.persistence.Converter;
import vaf.erp.server.core.registry.location.domain.vos.RouteName;
import vaf.erp.server.core.shared.domain.converters.GenericStringValueObjectConverter;

@Converter(autoApply = true)
public class RouteNameConverter extends GenericStringValueObjectConverter<RouteName> {
    protected RouteNameConverter() {
        super(RouteName.class);
    }
}
