package vaf.erp.server.core.registry.location.application.contracts;

import vaf.erp.server.core.registry.location.application.schemas.request.CreateRouteRequest;
import vaf.erp.server.core.shared.application.schemas.UUIDResponse;

public interface CreateRouteUseCase {
    UUIDResponse execute(CreateRouteRequest request);
}
