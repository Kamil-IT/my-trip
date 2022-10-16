package com.mytrip.demo.application.port.out;

import java.util.UUID;

public interface ParticitableService {

    void addParticipant(UUID eventUuid, String email);

    void removeParticipant(UUID eventUuid, String email);

}
