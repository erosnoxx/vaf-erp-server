package vaf.erp.server.core.registry.location.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vaf.erp.server.core.registry.location.domain.entities.Route;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
}
