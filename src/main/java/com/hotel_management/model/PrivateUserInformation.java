package com.hotel_management.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "private_user_informations")
public class PrivateUserInformation {

    private static final String PRIVATE_USER_INFORMATION_ID_SEQ = "private_user_information_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PRIVATE_USER_INFORMATION_ID_SEQ)
    @SequenceGenerator(name = PRIVATE_USER_INFORMATION_ID_SEQ, sequenceName = PRIVATE_USER_INFORMATION_ID_SEQ, allocationSize = 1)
    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}
