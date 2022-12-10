package com.mytrip.demo.application.persistance.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity()
@Table(name = "user_app")
public class UserJpa implements GrantedAuthority {

    @Id
    private String email;
    private String password;
    private String authority = "USER";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserJpa userJpa = (UserJpa) o;
        return email != null && Objects.equals(email, userJpa.email);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
