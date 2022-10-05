package com.mytrip.demo.application.persistance.user;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity()
@Table(name = "user_app")
public class UserTripParticipantsJpa {

    @Id
    private String email;
    private String password;

    @ManyToMany(mappedBy = "participants")
    @ToString.Exclude
    private List<TripJpa> trips = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserTripParticipantsJpa that = (UserTripParticipantsJpa) o;
        return email != null && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
