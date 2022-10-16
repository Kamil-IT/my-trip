package com.mytrip.demo.application.port.out.impl;

import com.mytrip.demo.application.persistance.trip.model.event.TripEventJpa;
import com.mytrip.demo.application.port.in.trip.mapper.TripMapper;
import com.mytrip.demo.application.port.out.CreateAdditionalProps;
import com.mytrip.demo.application.port.out.EventAdditionPropsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventAdditionPropsService implements EventAdditionPropsServiceImpl {

    private final List<CreateAdditionalProps> createAdditionalProps;
    private final TripMapper tripMapper;

    @Override
    public void addAdditionalPropertiesToEvent(TripEventJpa eventCreated) {
        Set<Map.Entry<String, String>> additionalProps = createAdditionalProps.stream()
                .map(propService -> propService.createAdditionalPropertiesForEvent(tripMapper.toDomain(eventCreated)))
                .flatMap(props -> props.entrySet().stream())
                .collect(Collectors.toSet());

        additionalProps.forEach(prop -> eventCreated.addProperty(prop.getKey(), prop.getValue()));
    }
}
