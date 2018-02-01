package com.chisw.mongoPresentation.mongo.persistense.dto;


import com.chisw.mongoPresentation.mongo.persistense.entity.Student;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto implements Serializable {

    private String id;

    private String name;

    private List<Student> students;
}
