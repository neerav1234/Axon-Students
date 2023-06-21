package com.college.Students.command.api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    private String sid;
    private String sname;
    private String course;
}
