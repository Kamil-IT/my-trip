package com.mytrip.demo.application.persistance.trip;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Data
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

    @ManyToMany
    @JoinTable(name = "trip_participant", inverseJoinColumns = {@JoinColumn(name = "user_email")})
    private List<UserJpa> participants;

    @OneToMany(mappedBy = "trip")
    private List<TripEventJpa> tripEvents;

    public void addParticipants(UserJpa user) {
        participants.add(user);
        user.getTrips().add(this);
    }

    public void removeParticipants(UserJpa user) {
        participants.remove(user);
        user.getTrips().remove(this);
    }
}
