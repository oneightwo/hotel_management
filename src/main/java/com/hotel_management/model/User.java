package com.hotel_management.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class User implements UserDetails {

    private static final String USER_ID_SEQ = "user_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_ID_SEQ)
    @SequenceGenerator(name = USER_ID_SEQ, sequenceName = USER_ID_SEQ, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "private_user_information_id", referencedColumnName = "id")
    private PrivateUserInformation privateUserInformation;

    @Column(columnDefinition = "varchar(4000)")
    private String description;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private User createdBy;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Booking> bookings;

    @Enumerated
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE;
    }
}
