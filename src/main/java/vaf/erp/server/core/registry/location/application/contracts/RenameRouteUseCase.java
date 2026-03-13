package vaf.erp.server.core.registry.location.application.contracts;

import vaf.erp.server.core.registry.location.application.schemas.request.RenameRouteRequest;

import java.util.UUID;

public interface RenameRouteUseCase {
    void execute(UUID id, RenameRouteRequest request);
}
