package com.hotel_management.service;

import com.hotel_management.model.Booking;

public interface BookingService extends CRUDService<Booking, Long> {

    Booking getByRoomId(Long id);

}
