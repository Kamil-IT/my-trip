package com.mytrip.demo.application.persistance.user.model;

import com.mytrip.demo.application.persistance.trip.model.TripJpa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
