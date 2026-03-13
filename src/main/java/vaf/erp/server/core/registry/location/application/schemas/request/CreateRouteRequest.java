package vaf.erp.server.core.registry.location.application.schemas.request;

import jakarta.validation.constraints.NotBlank;

public record CreateRouteRequest(
        @NotBlank String name
) {
    public static CreateRouteRequest of(String name) {
        return new CreateRouteRequest(name);
    }
}
