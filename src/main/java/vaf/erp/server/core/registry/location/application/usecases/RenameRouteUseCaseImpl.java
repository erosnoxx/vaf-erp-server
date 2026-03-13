package vaf.erp.server.core.registry.location.application.usecases;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vaf.erp.server.core.registry.location.application.contracts.RenameRouteUseCase;
import vaf.erp.server.core.registry.location.application.schemas.request.RenameRouteRequest;
import vaf.erp.server.core.registry.location.domain.vos.RouteName;
import vaf.erp.server.core.registry.location.persistence.repository.RouteRepository;
import vaf.erp.server.core.shared.exceptions.NotFoundException;

import java.util.UUID;

@Service
public class RenameRouteUseCaseImpl implements RenameRouteUseCase {
    private final RouteRepository repository;

    public RenameRouteUseCaseImpl(
            RouteRepository repository) {
        this.repository = repository;
    }

    @Override @Transactional
    public void execute(UUID id, RenameRouteRequest request) {
        var route = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Route not found"));

        route.rename(RouteName.of(request.name()));
        repository.save(route);
    }
}
