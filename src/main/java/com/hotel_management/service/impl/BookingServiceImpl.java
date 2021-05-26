package com.hotel_management.service.impl;

import com.hotel_management.dto.BookingDto;
import com.hotel_management.model.Booking;
import com.hotel_management.model.History;
import com.hotel_management.repository.BookingRepository;
import com.hotel_management.repository.HistoryRepository;
import com.hotel_management.repository.RoomRepository;
import com.hotel_management.service.BookingService;
import com.hotel_management.service.RoomService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;
    private final MapperFacade mapperFacade;
    private final BookingRepository bookingRepository;
    private final HistoryRepository historyRepository;

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
        booking.setTotalPrice(calculatePrice(booking));
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        getById(booking.getId());
        booking.setTotalPrice(calculatePrice(booking));
        return bookingRepository.save(booking);
    }

    private BigDecimal calculatePrice(Booking booking) {
        long numberDays = DAYS.between(booking.getFromDate(), booking.getEndDate());
        return roomService.getById(booking.getRoom().getId()).getPrice().multiply(new BigDecimal(numberDays));
    }

    @Override
    public void delete(Long id) {
        Booking booking = getById(id);
        mapperFacade.map(booking, BookingDto.class);
        historyRepository.save(new History().setBooking(mapperFacade.map(booking, BookingDto.class)));
        bookingRepository.delete(booking);
    }

    @Transactional(readOnly = true)
    @Override
    public Booking getByRoomId(Long id) {
        return bookingRepository.findByRoomId(id)
                .orElse(null);
    }
}
