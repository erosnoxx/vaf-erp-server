package vaf.erp.server.core.registry.location.application.usecases;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vaf.erp.server.core.registry.location.application.contracts.AddCityToRouteUseCase;
import vaf.erp.server.core.registry.location.application.schemas.request.AddCityToRouteRequest;
import vaf.erp.server.core.registry.location.domain.vos.CityCode;
import vaf.erp.server.core.registry.location.domain.vos.CityName;
import vaf.erp.server.core.registry.location.persistence.repository.RouteRepository;
import vaf.erp.server.core.shared.application.contracts.events.DomainEventPublisher;
import vaf.erp.server.core.shared.application.schemas.UUIDResponse;
import vaf.erp.server.core.shared.exceptions.NotFoundException;

import java.util.UUID;

@Service
public class AddCityToRouteUseCaseImpl implements AddCityToRouteUseCase {
    private final RouteRepository repository;

    public AddCityToRouteUseCaseImpl(
            RouteRepository repository,
            DomainEventPublisher publisher) {
        this.repository = repository;
    }

    @Override @Transactional
    public UUIDResponse execute(UUID id, AddCityToRouteRequest request) {
        var route = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("route not found"));
        var city = route.addCity(
                CityName.of(request.name()),
                CityCode.of(request.code()),
                request.state());

        repository.save(route);

        return UUIDResponse.of(city.getId());
    }
}
