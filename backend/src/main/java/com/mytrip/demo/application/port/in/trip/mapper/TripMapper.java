package com.mytrip.demo.application.port.in.trip.mapper;

import com.mytrip.demo.application.persistance.trip.TripJpa;
import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.event.properties.TripEventTypePropertiesJpa;
import com.mytrip.demo.application.persistance.user.UserEventParticipantsJpa;
import com.mytrip.demo.application.persistance.user.UserJpa;
import com.mytrip.demo.application.persistance.user.UserTripParticipantsJpa;
import com.mytrip.demo.domain.Event;
import com.mytrip.demo.domain.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(imports = EventMapper.class, componentModel = "spring")
public interface TripMapper {

    @Mapping(source = "creator", target = "creatorEmail")
    @Mapping(source = "startDate", target = "from")
    @Mapping(source = "endDate", target = "to")
    @Mapping(source = "participants", target = "participantsEmails")
    @Mapping(source = "tripEvents", target = "events")
    Trip toDomain(TripJpa trip);

    List<Trip> toDomain(List<TripJpa> trip);

    default List<String> toEmailTripList(List<UserTripParticipantsJpa> users) {
        return users.stream().map(UserTripParticipantsJpa::getEmail).collect(Collectors.toList());
    }

    default List<String> toEmailEventList(Set<UserEventParticipantsJpa> users) {
        return users.stream().map(UserEventParticipantsJpa::getEmail).collect(Collectors.toList());
    }

    default Map<String, String> toDomainProperties(List<TripEventTypePropertiesJpa> properties) {
        return properties.stream()
                .collect(Collectors.toMap(TripEventTypePropertiesJpa::getPropertyKey, TripEventTypePropertiesJpa::getPropertyValue));
    }

    //    @Mapping(target = "LocationDetails.latitude", source = "latitude")
//    @Mapping(target = "LocationDetails.longitude", source = "longitude")
//    @Mapping(target = "LocationDetails.locationDescription", source = "locationDescription")
    @Mapping(target = "from", source = "startDate")
    @Mapping(target = "to", source = "endDate")
    @Mapping(target = "creatorEmail", source = "creator")
    @Mapping(target = "eventType", source = "tripType")
    @Mapping(target = "location", source = "locationDescription")
    @Mapping(target = "properties", source = "properties")
    Event toDomain(TripEventJpa event);

    default Set<Event.Property> toDomain(Set<TripEventTypePropertiesJpa> properties) {
        return properties.stream()
                .map(property -> Event.Property.builder()
                        .key(property.getPropertyKey())
                        .value(property.getPropertyValue())
                        .build())
                .collect(Collectors.toSet());
    }

    default Event.LocationDetails toDomain(String locationDescription) {
        return Event.LocationDetails.builder().locationDescription(locationDescription).build();
    }

    default Event.Property toDomain(TripEventTypePropertiesJpa propertiesJpa) {
        return Event.Property.builder()
                .key(propertiesJpa.getPropertyKey())
                .value(propertiesJpa.getPropertyValue())
                .build();
    }
}
