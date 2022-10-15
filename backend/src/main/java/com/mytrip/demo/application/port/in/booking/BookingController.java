package com.mytrip.demo.application.port.in.booking;

import com.mytrip.demo.application.port.in.booking.model.FindHotel;
import com.mytrip.demo.infrastructure.booking.BookingClient;
import com.mytrip.demo.infrastructure.booking.model.BookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class BookingController {

    private final BookingClient bookingClient;

    @PostMapping("hotel")
    public BookingResponse findHotel(@RequestBody FindHotel findHotel){
        return bookingClient.findHotels(findHotel);
    }
}
