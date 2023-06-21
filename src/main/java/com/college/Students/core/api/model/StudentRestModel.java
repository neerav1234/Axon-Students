package com.college.Students.core.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRestModel {
    private String sid;
    private String sname;
    private String course;

}