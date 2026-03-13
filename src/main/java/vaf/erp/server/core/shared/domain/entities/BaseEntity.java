package vaf.erp.server.core.shared.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import vaf.erp.server.core.shared.application.contracts.events.DomainEvent;
import vaf.erp.server.core.shared.utils.TimeConfig;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MappedSuperclass @Getter
public abstract class BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @Transient
    private final List<DomainEvent> events = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {
        events.add(event);
    }

    public List<DomainEvent> pullEvents() {
        var pending = List.copyOf(events);
        events.clear();
        return pending;
    }

    @PrePersist
    private void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now(TimeConfig.DEFAULT_OFFSET);
        }
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = OffsetDateTime.now(TimeConfig.DEFAULT_OFFSET);
    }
}
