package com.hotel_management.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "rooms")
public class Room {

    private static final String ROOM_ID_SEQ = "room_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ROOM_ID_SEQ)
    @SequenceGenerator(name = ROOM_ID_SEQ, sequenceName = ROOM_ID_SEQ, allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer number;

    @Column
    private Integer floor;

    @Column
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "room")
    private List<Booking> bookings;

}