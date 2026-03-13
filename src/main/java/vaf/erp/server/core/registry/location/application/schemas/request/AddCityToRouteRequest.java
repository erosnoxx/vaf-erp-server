package vaf.erp.server.core.registry.location.application.schemas.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import vaf.erp.server.core.registry.location.domain.enums.State;

public record AddCityToRouteRequest(
        @NotBlank String name,
        @NotBlank String code,
        @NotNull State state
) {
    public static AddCityToRouteRequest of(String name, String code, State state) {
        return new AddCityToRouteRequest(name, code, state);
    }
}
