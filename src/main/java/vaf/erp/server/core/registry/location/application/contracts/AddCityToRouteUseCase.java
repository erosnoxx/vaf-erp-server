package vaf.erp.server.core.registry.location.application.contracts;

import vaf.erp.server.core.registry.location.application.schemas.request.AddCityToRouteRequest;
import vaf.erp.server.core.shared.application.schemas.UUIDResponse;

import java.util.UUID;

public interface AddCityToRouteUseCase {
    UUIDResponse execute(UUID id, AddCityToRouteRequest request);
}
