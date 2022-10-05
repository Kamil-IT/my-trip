package com.mytrip.demo.application.persistance.trip;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.user.UserTripParticipantsJpa;
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
@Entity(name = "trip")
public class TripJpa {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("uuid()")
    private UUID uuid = UUID.randomUUID();
    private String creator;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany()
    @JoinTable(
            name = "trip_participant",
            inverseJoinColumns = {@JoinColumn(name = "user_email")},
            joinColumns = @JoinColumn(name = "trip_id")
    )
    @ToString.Exclude
    private List<UserTripParticipantsJpa> participants = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<TripEventJpa> tripEvents = new HashSet<>();

    public void addParticipants(UserTripParticipantsJpa user) {
        participants.add(user);
        user.getTrips().add(this);
    }

    public void addEvent(TripEventJpa event) {
        tripEvents.add(event);
        event.setTrip(this);
    }

    public void removeParticipants(UserTripParticipantsJpa user) {
        participants.remove(user);
        user.getTrips().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TripJpa tripJpa = (TripJpa) o;
        return id != null && Objects.equals(id, tripJpa.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
