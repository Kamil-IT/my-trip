package com.mytrip.demo.application.persistance.user.model;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity()
@Table(name = "user_app")
public class UserEventParticipantsJpa {

    @Id
    private String email;
    private String password;

    @ManyToMany(mappedBy = "participants")
    @ToString.Exclude
    private Set<TripEventJpa> tripEventsParticipants = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEventParticipantsJpa that = (UserEventParticipantsJpa) o;
        return email != null && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
