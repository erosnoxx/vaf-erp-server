package vaf.erp.server.core.registry.location.application.usecases;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vaf.erp.server.core.registry.location.application.contracts.DeactivateRouteUseCase;
import vaf.erp.server.core.registry.location.persistence.repository.RouteRepository;
import vaf.erp.server.core.shared.application.contracts.events.DomainEventPublisher;
import vaf.erp.server.core.shared.exceptions.NotFoundException;

import java.util.UUID;

@Service
public class DeactivateRouteUseCaseImpl implements DeactivateRouteUseCase {
    private final RouteRepository repository;
    private final DomainEventPublisher publisher;

    public DeactivateRouteUseCaseImpl(
            RouteRepository repository,
            DomainEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Override @Transactional
    public void execute(UUID id) {
        var route = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("route not found"));

        route.deactivate();

        repository.save(route);
        route.pullEvents().forEach(publisher::publish);
    }
}
