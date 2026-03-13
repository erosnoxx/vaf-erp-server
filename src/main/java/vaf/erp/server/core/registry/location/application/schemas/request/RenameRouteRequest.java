package vaf.erp.server.core.registry.location.application.schemas.request;

import jakarta.validation.constraints.NotBlank;

public record RenameRouteRequest(@NotBlank String name) {
    public static RenameRouteRequest of(String name) {
        return new RenameRouteRequest(name);
    }
}
