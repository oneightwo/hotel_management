package com.hotel_management.config;

import com.hotel_management.dto.BookingDto;
import com.hotel_management.dto.RoomDto;
import com.hotel_management.dto.UserDto;
import com.hotel_management.model.Booking;
import com.hotel_management.model.Room;
import com.hotel_management.model.User;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(Room.class, RoomDto.class)
                .byDefault()
                .register();

        mapperFactory.classMap(User.class, UserDto.class)
                .field("privateUserInformation.surname", "surname")
                .field("privateUserInformation.name", "name")
                .field("privateUserInformation.patronymic", "patronymic")
                .field("privateUserInformation.dateOfBirth", "dateOfBirth")
                .byDefault()
                .register();

        mapperFactory.classMap(Booking.class, BookingDto.class)
                .field("privateUserInformation.surname", "surname")
                .field("privateUserInformation.name", "name")
                .field("privateUserInformation.patronymic", "patronymic")
                .field("privateUserInformation.dateOfBirth", "dateOfBirth")
                .byDefault()
                .register();
    }
}
