package com.mytrip.demo.application.port.out;

import com.mytrip.demo.domain.Event;

import java.util.Map;

public interface CreateAdditionalProps {

    Map<String, String> createAdditionalPropertiesForEvent(Event event);
}
