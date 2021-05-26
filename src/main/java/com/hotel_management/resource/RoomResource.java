package com.hotel_management.resource;

import com.hotel_management.dto.BookingDto;
import com.hotel_management.dto.RoomDto;
import com.hotel_management.model.Booking;
import com.hotel_management.model.Room;
import com.hotel_management.security.SecurityUtils;
import com.hotel_management.service.BookingService;
import com.hotel_management.service.ReportService;
import com.hotel_management.service.RoomService;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomResource {

    private final MapperFacade mapper;
    private final RoomService roomService;
    private final BookingService bookingService;
    private final ReportService reportService;

    @GetMapping
    private String getRoomsPage(Model model) {
        if (Objects.isNull(SecurityUtils.getUserFromContext())) {
            return "redirect:/login";
        }
        List<RoomDto> roomDtos = mapper.mapAsList(roomService.getAll(), RoomDto.class).stream()
                .peek(roomDto -> {
                    Booking activeBooking = bookingService.getByRoomId(roomDto.getId());
                    roomDto.setIsBooking(Objects.nonNull(activeBooking));
                }).collect(Collectors.toList());
        model.addAttribute("rooms", roomDtos);
        return "rooms";
    }

    @DeleteMapping("/{id}")
    private void deleteRoom(@PathVariable("id") Long id) {
        roomService.delete(id);
    }

    @GetMapping("/{id}")
    private String getRoomByIdPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", mapper.map(roomService.getById(id), RoomDto.class));
        model.addAttribute("booking", mapper.map(bookingService.getByRoomId(id), BookingDto.class));
        return "viewRoom";
    }

    @GetMapping("/edit/{id}")
    private String getRoomEditByIdPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", mapper.map(roomService.getById(id), RoomDto.class));
        return "editRoom";
    }

    @GetMapping("/create")
    private String getCreateRoomPage() {
        return "createRoom";
    }

    @PostMapping
    private String createRoom(@ModelAttribute("room") RoomDto roomDto) {
        roomService.save(mapper.map(roomDto, Room.class));
        return "redirect:/rooms";
    }

    @PostMapping("/edit/{id}")
    private String editRoom(@PathVariable("id") Long id, @ModelAttribute("room") RoomDto roomDto) {
        roomDto.setId(id);
        roomService.update(mapper.map(roomDto, Room.class));
        return "redirect:/rooms";
    }

    @GetMapping("/reports")
    private ResponseEntity<?> getReport() {
        ByteArrayInputStream bis = reportService.getReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Отчет.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
