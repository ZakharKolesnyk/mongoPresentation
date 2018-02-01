package com.chisw.mongoPresentation.mongo.persistense.entity;

import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private String id;

    private String name;

    public StudentDto dto() {
        return StudentDto.builder().id(id).name(name).build();
    }
}
