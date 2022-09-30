package com.mytrip.demo.application.port.in.trip.mapper;

import com.mytrip.demo.application.persistance.trip.event.TripEventJpa;
import com.mytrip.demo.application.persistance.trip.event.properties.TripEventTypePropertiesJpa;
import com.mytrip.demo.domain.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "location", ignore = true)
//    @Mapping(target = "LocationDetails.latitude", source = "latitude")
//    @Mapping(target = "LocationDetails.longitude", source = "longitude")
//    @Mapping(target = "LocationDetails.locationDescription", source = "locationDescription")
    @Mapping(target = "from", source = "startDate")
    @Mapping(target = "to", source = "endDate")
    @Mapping(target = "creatorEmail", source = "creator.email")
    @Mapping(target = "eventType", source = "tripType.name")
    Event toDomain(TripEventJpa event);

    List<Event> toDomain(List<TripEventJpa> event);

    default Map<String, String> toDomainProperties(List<TripEventTypePropertiesJpa> properties){
        return properties.stream().collect(Collectors.toMap(TripEventTypePropertiesJpa::getPropertyKey, TripEventTypePropertiesJpa::getPropertyValue));
    }
}
