package com.hotel_management.service.impl;

import com.hotel_management.model.Booking;
import com.hotel_management.repository.BookingRepository;
import com.hotel_management.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    @Override
    public Booking getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Бронь не найдена");
        }
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Бронь не найдена"));
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        getById(booking.getId());
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(Long id) {
        bookingRepository.delete(getById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public Booking getByRoomId(Long id) {
        return bookingRepository.findByRoomId(id)
                .orElse(null);
    }
}
