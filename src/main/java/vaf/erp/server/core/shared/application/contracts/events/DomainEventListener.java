package vaf.erp.server.core.shared.application.contracts.events;

public interface DomainEventListener<T extends DomainEvent> {
    void handle(T event);
}
