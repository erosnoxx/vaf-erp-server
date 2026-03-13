package vaf.erp.server.core.shared.application.contracts.events;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
