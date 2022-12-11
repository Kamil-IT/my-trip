package com.mytrip.demo.application.port.in.booking;

import com.mytrip.demo.application.port.in.booking.model.FindHotel;
import com.mytrip.demo.infrastructure.booking.model.BookingResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface BookingController {
    @PostMapping("hotel")
    BookingResponse findHotel(@Valid @RequestBody FindHotel findHotel);
}
