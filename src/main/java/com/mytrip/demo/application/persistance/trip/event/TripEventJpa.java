package com.mytrip.demo.application.persistance.trip.event;

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
    private LocalDate from;
    private LocalDate to;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
//    @ManyToOne
//    private TripJpa tripJpa;
    @ManyToOne
    private UserJpa userJpa;
    @ManyToMany
    private Set<UserJpa> participants = new HashSet<>();
    @ManyToOne
    private TripEventTypeJpa tripEventTypeJpa;
    @OneToMany
    private List<TripEventTypePropertiesJpa> properties;

    public void addParticipants(UserJpa user) {
//        Support jpa
        participants.add(user);
    }

    public void addProperty(String key, String value) {
        properties.add(TripEventTypePropertiesJpa.builder().propertyKey(key).propertyValue(value).tripEventJpa(this).build());
    }
}
