package com.mytrip.demo.application.port.in.ui;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.type.TripEventTypeName;
import com.mytrip.demo.application.port.out.TripService;
import com.mytrip.demo.domain.Event;
import com.mytrip.demo.domain.Trip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.*;

//@RequiredArgsConstructor
//@Controller
public class TripUiController {

//    private final TripService tripService;
//
//    @GetMapping(path = {"/index", "/", "", "/trip"})
//    public ModelAndView trips() {
//        List<TripJpa> tripsFromDb = tripService.getAll();
//
//        List<Trip> trips = Arrays.asList(getTripToBarcelona(), getTripToBarcelona(), getTripToBarcelona());
//
//        ModelAndView mav = new ModelAndView("triplist");
//        mav.addObject("trips", trips);
//
//        return mav;
//    }
//
//    @GetMapping(path = {"/trip/{id}"})
//    public ModelAndView trip(@PathVariable UUID id) {
////        TripJpa tripFromDb = tripService.getById(id);
//
//        ModelAndView mav = new ModelAndView("tripedit");
//        mav.addObject("trip", getTripToBarcelona());
//
//        return mav;
//    }
//
//    public static Trip getTripToBarcelona() {
//        List<Event> events = Arrays.asList(Event.builder().title("Accomodation").from(LocalDate.now()
//                .plusDays(2)).to(LocalDate.now().plusDays(3))
//                .location(Event.LocationDetails.builder().latitude(23d).longitude(45d)
//                        .locationDescription("Barcelona").build())
//                .creatorEmail("admin@admin.com").eventType(TripEventTypeName.ACCOMMODATION)
//                .properties(Map.of("test", "test")).build());
//        return Trip.builder().uuid(UUID.randomUUID()).title("Trip to barcelona")
//                .creatorEmail("ex@emil.com")
//                .from(LocalDate.now().plusDays(2)).to(LocalDate.now().plusDays(4))
//                .participantsEmails(Arrays.asList("rand@email.com", "nice@mail.com"))
//                .events(events).build();
//    }

}
