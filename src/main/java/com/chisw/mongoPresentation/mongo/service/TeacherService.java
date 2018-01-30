package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> listDto();

    TeacherDto byIdDto(String id);

    TeacherDto createAndReturnDto(TeacherDto teacher);

    List<TeacherDto> createAndReturnDto(List<TeacherDto> teacher);

    void delete(String id);
}
