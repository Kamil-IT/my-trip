package com.mytrip.demo.application.persistance.trip.event;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.properties.TripEventTypePropertiesJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeName;
import com.mytrip.demo.application.persistance.user.UserEventParticipantsJpa;
import lombok.*;
import org.hibernate.Hibernate;

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
//    Delete this field creator shoulde be administrator of that
//    private String creator;
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
    @OneToMany(mappedBy = "tripEventJpa")
    @ToString.Exclude
    private List<TripEventTypePropertiesJpa> properties = new ArrayList<>();

    public void addParticipants(UserEventParticipantsJpa user) {
        participants.add(user);
        List<TripEventJpa> trips = user.getTripEventsParticipants();
        trips.add(this);
    }

    public void addProperty(String key, String value) {
        TripEventTypePropertiesJpa property = TripEventTypePropertiesJpa.builder().propertyKey(key).propertyValue(value).tripEventJpa(this).build();
        properties.add(property);
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
