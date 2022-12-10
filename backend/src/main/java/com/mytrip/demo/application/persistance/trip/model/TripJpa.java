package com.mytrip.demo.application.persistance.trip.model;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import com.mytrip.demo.application.persistance.user.model.UserTripParticipantsJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

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
