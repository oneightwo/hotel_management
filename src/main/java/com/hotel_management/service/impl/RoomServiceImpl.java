package com.hotel_management.service.impl;

import com.hotel_management.model.Room;
import com.hotel_management.repository.RoomRepository;
import com.hotel_management.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    @Override
    public Room getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Комната не найдена");
        }
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Комната не найдена"));
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        getById(room.getId());
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.delete(getById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }
}
