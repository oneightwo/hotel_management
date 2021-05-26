package com.hotel_management.model;

import com.hotel_management.dto.BookingDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "histories")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class History {

    private static final String HISTORY_ID_SEQ = "history_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = HISTORY_ID_SEQ)
    @SequenceGenerator(name = HISTORY_ID_SEQ, sequenceName = HISTORY_ID_SEQ, allocationSize = 1)
    private Long id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private BookingDto booking;

}