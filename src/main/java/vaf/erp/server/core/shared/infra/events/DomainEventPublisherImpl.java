package vaf.erp.server.core.shared.infra.events;

import org.springframework.stereotype.Component;
import vaf.erp.server.core.shared.application.contracts.events.DomainEventListener;
import vaf.erp.server.core.shared.application.contracts.events.DomainEventPublisher;
import vaf.erp.server.core.shared.application.contracts.events.DomainEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final Map<Class<?>, List<DomainEventListener<?>>> listeners = new HashMap<>();

    public <T extends DomainEvent> void subscribe(Class<T> type, DomainEventListener<T> listener) {
        listeners.computeIfAbsent(type, k -> new ArrayList<>()).add(listener);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void publish(DomainEvent event) {
        var handlers = listeners.getOrDefault(event.getClass(), List.of());

        for (var handler : handlers) {
            ((DomainEventListener<DomainEvent>) handler).handle(event);
        }
    }
}
