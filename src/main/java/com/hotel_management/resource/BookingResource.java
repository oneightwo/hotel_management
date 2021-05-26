package com.hotel_management.resource;

import com.hotel_management.dto.BookingDto;
import com.hotel_management.dto.RoomDto;
import com.hotel_management.dto.UserDto;
import com.hotel_management.model.Booking;
import com.hotel_management.security.SecurityUtils;
import com.hotel_management.service.BookingService;
import com.hotel_management.service.RoomService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingResource {

    private final MapperFacade mapper;
    private final BookingService bookingService;
    private final RoomService roomService;

    @GetMapping("/rooms/{id}")
    private String getCreateBookingPage(@PathVariable("id") Long id, Model model) {
        if (Objects.isNull(SecurityUtils.getUserFromContext())) {
            return "redirect:/login";
        }
        model.addAttribute("room", mapper.map(roomService.getById(id), RoomDto.class));
        return "createBooking";
    }

    @GetMapping("/{id}")
    private String getEditBookingPage(@PathVariable("id") Long id, Model model) {
        if (Objects.isNull(SecurityUtils.getUserFromContext())) {
            return "redirect:/login";
        }
        model.addAttribute("booking", mapper.map(bookingService.getById(id), BookingDto.class));
        return "editBooking";
    }

    @PostMapping
    private String createBooking(@ModelAttribute("booking") BookingDto bookingDto) {
        bookingDto.setUser(new UserDto().setId(SecurityUtils.getUserFromContext().getId()));
        bookingService.save(mapper.map(bookingDto, Booking.class));
        return "redirect:/rooms";
    }

    @PostMapping("/{id}")
    private String updateBooking(@PathVariable("id") Long id, @ModelAttribute("booking") BookingDto bookingDto) {
        bookingDto.setId(id);
        bookingDto.setUser(new UserDto().setId(SecurityUtils.getUserFromContext().getId()));
        bookingService.update(mapper.map(bookingDto, Booking.class));
        return "redirect:/rooms";
    }

    @DeleteMapping("/{id}")
    private void deleteBooking(@PathVariable("id") Long id) {
        bookingService.delete(id);
    }
}
