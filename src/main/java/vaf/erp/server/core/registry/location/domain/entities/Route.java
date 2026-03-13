package vaf.erp.server.core.registry.location.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vaf.erp.server.core.registry.location.domain.enums.State;
import vaf.erp.server.core.registry.location.domain.events.*;
import vaf.erp.server.core.registry.location.domain.events.RouteDeactivatedEvent;
import vaf.erp.server.core.registry.location.domain.vos.CityCode;
import vaf.erp.server.core.registry.location.domain.vos.CityName;
import vaf.erp.server.core.registry.location.domain.vos.RouteName;
import vaf.erp.server.core.shared.domain.entities.BaseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity @Table(name = "routes") @NoArgsConstructor @Getter
public class Route extends BaseEntity {
    @Column(nullable = false, unique = true)
    private RouteName name;
    private boolean active;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<City> cities = new ArrayList<>();

    private Route(RouteName name) {
        this.name = name;
        this.active = true;
    }

    public static Route create(RouteName name) {
        return new Route(name);
    }

    public City addCity(CityName name, CityCode code, State state) {
        if (!active)
            throw new IllegalStateException("cannot add city to inactive route");

        var city = new City(name, code, state, this);
        cities.add(city);
        return city;
    }

    public void deactivate() {
        if (!active)
            throw new IllegalStateException("route is already inactive");

        this.active = false;
        registerEvent(new RouteDeactivatedEvent(getId()));
    }

    public void rename(RouteName name) {
        this.name = name;
    }

    public List<City> getCities() {
        return Collections.unmodifiableList(cities);
    }
}
