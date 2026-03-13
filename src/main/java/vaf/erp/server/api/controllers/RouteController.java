package vaf.erp.server.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vaf.erp.server.core.registry.location.application.contracts.AddCityToRouteUseCase;
import vaf.erp.server.core.registry.location.application.contracts.CreateRouteUseCase;
import vaf.erp.server.core.registry.location.application.contracts.DeactivateRouteUseCase;
import vaf.erp.server.core.registry.location.application.contracts.RenameRouteUseCase;
import vaf.erp.server.core.registry.location.application.schemas.request.AddCityToRouteRequest;
import vaf.erp.server.core.registry.location.application.schemas.request.CreateRouteRequest;
import vaf.erp.server.core.registry.location.application.schemas.request.RenameRouteRequest;
import vaf.erp.server.core.shared.application.schemas.UUIDResponse;
import java.util.UUID;

@RestController @RequestMapping("/routes")
public class RouteController {
    private final CreateRouteUseCase createRoute;
    private final AddCityToRouteUseCase addCity;
    private final DeactivateRouteUseCase deactivateRoute;
    private final RenameRouteUseCase renameRoute;

    public RouteController(
            CreateRouteUseCase createRoute,
            AddCityToRouteUseCase addCity,
            DeactivateRouteUseCase deactivateRoute,
            RenameRouteUseCase renameRoute) {
        this.createRoute = createRoute;
        this.addCity = addCity;
        this.deactivateRoute = deactivateRoute;
        this.renameRoute = renameRoute;
    }

    @PostMapping
    public ResponseEntity<UUIDResponse> create(@RequestBody @Valid CreateRouteRequest request) {
        return ResponseEntity.status(201).body(createRoute.execute(request));
    }

    @PostMapping("/{id}/cities")
    public ResponseEntity<UUIDResponse> addCity(
            @PathVariable UUID id,
            @RequestBody @Valid AddCityToRouteRequest request
    ) {
        return ResponseEntity.status(201).body(addCity.execute(id, request));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        deactivateRoute.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/rename")
    public ResponseEntity<Void> rename(
            @PathVariable UUID id,
            @RequestBody @Valid RenameRouteRequest request
    ) {
        renameRoute.execute(id, request);
        return ResponseEntity.noContent().build();
    }
}
