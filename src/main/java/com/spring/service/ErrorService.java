package com.spring.service;

import com.spring.dto.waitresDTO.ErrorDTO;
import com.spring.model.Error;

import java.util.List;

public interface ErrorService {

    void saveError(ErrorDTO errorDTO, Long id);

    List<Error> getErrors();

    void deleteError(Long id);

    Long checkActiveErrors();
}
