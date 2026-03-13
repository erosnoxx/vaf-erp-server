package vaf.erp.server.core.registry.location.domain.events;

import vaf.erp.server.core.shared.application.contracts.events.DomainEvent;

import java.util.UUID;

public record RouteDeactivatedEvent(UUID routeId) implements DomainEvent {
}
