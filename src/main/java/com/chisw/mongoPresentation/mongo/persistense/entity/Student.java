package com.chisw.mongoPresentation.mongo.persistense.entity;

import com.chisw.mongoPresentation.mongo.persistense.dto.StudentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student implements Serializable {

    @Id
    private String id;

    private String name;

    @DBRef(db = "groups", lazy = true)
    private Group group;

    public StudentDto dto() {
        return StudentDto.builder().id(id).name(name).build();
    }
}
