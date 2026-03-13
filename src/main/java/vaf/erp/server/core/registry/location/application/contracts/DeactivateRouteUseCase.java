package vaf.erp.server.core.registry.location.application.contracts;

import java.util.UUID;

public interface DeactivateRouteUseCase {
    void execute(UUID id);
}
