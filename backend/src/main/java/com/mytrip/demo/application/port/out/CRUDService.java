package com.mytrip.demo.application.port.out;

import javax.transaction.Transactional;

public interface CRUDService<T, ID, CREATE_DTO, UPDATE_DTO> {
    T get(ID uuid);

    T create(CREATE_DTO event);

    @Transactional
    void delete(ID id);

    void update(ID id, UPDATE_DTO updateEventDto);
}
