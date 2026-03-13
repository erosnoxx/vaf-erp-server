package vaf.erp.server.core.registry.location.application.usecases;

import org.springframework.stereotype.Service;
import vaf.erp.server.core.registry.location.application.contracts.CreateRouteUseCase;
import vaf.erp.server.core.registry.location.application.schemas.request.CreateRouteRequest;
import vaf.erp.server.core.registry.location.domain.entities.Route;
import vaf.erp.server.core.registry.location.domain.vos.RouteName;
import vaf.erp.server.core.registry.location.persistence.repository.RouteRepository;
import vaf.erp.server.core.shared.application.contracts.events.DomainEventPublisher;
import vaf.erp.server.core.shared.application.schemas.UUIDResponse;

@Service
public class CreateRouteUseCaseImpl implements CreateRouteUseCase {
    private final RouteRepository repository;

    public CreateRouteUseCaseImpl(
            RouteRepository repository,
            DomainEventPublisher publisher) {
        this.repository = repository;
    }

    @Override
    public UUIDResponse execute(CreateRouteRequest request) {
        var saved = repository
                .save(Route.create(
                        RouteName.of(request.name())));
        return UUIDResponse.of(saved.getId());
    }
}
