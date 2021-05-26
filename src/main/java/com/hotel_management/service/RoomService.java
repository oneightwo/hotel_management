package com.hotel_management.service;

import com.hotel_management.model.Room;

import java.util.List;

public interface RoomService extends CRUDService<Room, Long> {

    List<Room> getAll();
}
