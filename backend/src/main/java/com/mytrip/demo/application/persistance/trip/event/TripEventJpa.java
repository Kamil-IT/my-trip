package com.mytrip.demo.application.persistance.trip.event;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.properties.TripEventTypePropertiesJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "trip_event")
public class TripEventJpa {

    @Id
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
    private TripJpa tripJpa;
    @ManyToOne
    private UserJpa creator;
    @ManyToMany
    @JoinTable(name = "trip_event_participant", joinColumns = @JoinColumn(name = "trip_event_id"),inverseJoinColumns = @JoinColumn(name = "user_email"))
    private Set<UserJpa> participants = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "trip_event_type_id")
    private TripEventTypeJpa tripType;
    @OneToMany
    private List<TripEventTypePropertiesJpa> properties;

    public void addParticipants(UserJpa user) {
        participants.add(user);
        List<TripEventJpa> trips = user.getTripEventsParticipants();
        trips.add(this);
    }

    public void addProperty(String key, String value) {
        TripEventTypePropertiesJpa property = TripEventTypePropertiesJpa.builder().propertyKey(key).propertyValue(value).tripEventJpa(this).build();
        properties.add(property);
    }
}
