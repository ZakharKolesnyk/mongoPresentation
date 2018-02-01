package com.chisw.mongoPresentation.mongo.persistense.entity;


import com.chisw.mongoPresentation.mongo.persistense.dto.GroupDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "groups")
public class Group implements Serializable {

    @Id
    private String id;

    private String name;

    @JsonIgnore
    private List<Student> students;

    @JsonIgnore
    @DBRef(db = "teachers", lazy = true)
    private List<Teacher> teachers;

    public GroupDto dto() {
        return GroupDto.builder().id(id).name(name).students(students).build();
    }
}
