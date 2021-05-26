package com.hotel_management.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "booking")
public class Booking {

    private static final String BOOKING_ID_SEQ = "booking_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = BOOKING_ID_SEQ)
    @SequenceGenerator(name = BOOKING_ID_SEQ, sequenceName = BOOKING_ID_SEQ, allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "private_user_information_id", referencedColumnName = "id")
    private PrivateUserInformation privateUserInformation;

    private LocalDateTime fromDate;

    private LocalDateTime endDate;
}
