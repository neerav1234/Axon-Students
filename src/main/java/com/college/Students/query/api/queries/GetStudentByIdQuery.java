package com.college.Students.query.api.queries;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetStudentByIdQuery {

    private String sid;

    public GetStudentByIdQuery(String sid) {
        this.sid = sid;
    }
}
