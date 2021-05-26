package com.hotel_management.dto;

import lombok.Data;

@Data
public class HistoryDto {

    private Long id;

    private BookingDto booking;

    private String comment;

}
