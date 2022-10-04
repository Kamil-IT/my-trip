package com.mytrip.demo.application.persistance.trip.event.properties;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity(name = "trip_event_properties")
public class TripEventTypePropertiesJpa {

    @Id
    private String propertyKey;
    private String propertyValue;

    @ManyToOne
    @JoinColumn(name = "trip_event_id")
    private TripEventJpa tripEventJpa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TripEventTypePropertiesJpa that = (TripEventTypePropertiesJpa) o;
        return propertyKey != null && Objects.equals(propertyKey, that.propertyKey);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
