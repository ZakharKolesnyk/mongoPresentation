package com.chisw.mongoPresentation.mongo.persistense.entity;

import com.chisw.mongoPresentation.mongo.persistense.dto.TeacherDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "teachers")
public class Teacher implements Serializable {

    @Id
    private String id;

    //    @TextIndexed
    private String name;

    @TextIndexed
    private String text;

    @DBRef(db = "groups", lazy = true)
    private List<Group> groups;

    public TeacherDto dto() {
        return TeacherDto.builder().id(id).name(name).text(text).build();
    }
}
