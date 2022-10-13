package com.mytrip.demo.application.persistance.trip.model.event;

import com.mytrip.demo.application.persistance.trip.model.TripJpa;
import com.mytrip.demo.application.persistance.trip.model.event.properties.TripEventTypePropertiesJpa;
import com.mytrip.demo.application.persistance.trip.model.event.type.TripEventTypeName;
import com.mytrip.demo.application.persistance.user.model.UserEventParticipantsJpa;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity(name = "trip_event")
public class TripEventJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("uuid()")
    private UUID uuid = UUID.randomUUID();
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripJpa trip;
    @Column(name = "creator_email")
    private String creator;


    @ManyToMany
    @JoinTable(name = "trip_event_participant",
            joinColumns = @JoinColumn(name = "trip_event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_email")
    )
    @ToString.Exclude
    private Set<UserEventParticipantsJpa> participants = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private TripEventTypeName tripType;
    @OneToMany(mappedBy = "tripEventJpa", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<TripEventTypePropertiesJpa> properties = new HashSet<>();

    public void addParticipants(UserEventParticipantsJpa user) {
        participants.add(user);
        Set<TripEventJpa> trips = user.getTripEventsParticipants();
        trips.add(this);
    }

    public void addProperty(String key, String value) {
        if (properties == null) {
            properties = new HashSet<>();
        }
        TripEventTypePropertiesJpa property = TripEventTypePropertiesJpa.builder().propertyKey(key).propertyValue(value).tripEventJpa(this).build();
        properties.add(property);
    }

    public void removeParticipants(UserEventParticipantsJpa user) {
        participants.remove(user);
        user.getTripEventsParticipants().remove(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TripEventJpa that = (TripEventJpa) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
