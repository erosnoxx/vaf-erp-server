package vaf.erp.server.core.registry.location.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vaf.erp.server.core.registry.location.domain.enums.State;
import vaf.erp.server.core.registry.location.domain.vos.CityCode;
import vaf.erp.server.core.registry.location.domain.vos.CityName;
import vaf.erp.server.core.shared.domain.entities.BaseEntity;

@Entity @Table(name = "cities") @NoArgsConstructor @Getter
public class City extends BaseEntity {

    @Column(nullable = false)
    private CityName name;

    @Column(nullable = false, unique = true)
    private CityCode code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    City(CityName name, CityCode code, State state, Route route) {
        this.name = name;
        this.code = code;
        this.state = state;
        this.route = route;
    }
}
