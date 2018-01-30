package com.chisw.mongoPresentation.mongo.service;

import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> listDto();

    StudentDto byIdDto(String id);

    StudentDto createAndReturnDto(StudentDto student);

    List<StudentDto> createAndReturnDto(List<StudentDto> student);

    void delete(String id);

    List<StudentDto> listDtoByTeacherIdAndGroupId(String teacherId, String groupId);

    StudentDto createAndReturnDtoByTeacherIdAndGroupId(String teacherId, String groupId, StudentDto student);
}
