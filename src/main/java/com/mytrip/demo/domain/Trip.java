package com.mytrip.demo.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Value
public class Trip {
    UUID uuid;
    String title;
    String creatorEmail;
    LocalDate from;
    LocalDate to;
    List<String> participantsEmails;
    List<Trip> trips;
}
