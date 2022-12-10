package com.mytrip.demo.application.port.in.booking;

import com.mytrip.demo.application.port.in.booking.model.FindHotel;
import com.mytrip.demo.infrastructure.booking.BookingClient;
import com.mytrip.demo.infrastructure.booking.model.BookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class BookingController {

    private final BookingClient bookingClient;

    @PostMapping("hotel")
    public BookingResponse findHotel(@Valid @RequestBody FindHotel findHotel){
        return bookingClient.findHotels(findHotel);
    }
}
