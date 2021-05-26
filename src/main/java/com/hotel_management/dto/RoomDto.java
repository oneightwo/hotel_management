package com.hotel_management.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDto {

    private Long id;

    private String name;

    private String description;

    private Integer number;

    private Integer floor;

    private BigDecimal price;

    private Boolean isBooking;

}