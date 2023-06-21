package com.college.Students.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class AddStudentCommand {
    @TargetAggregateIdentifier
    private String sid;
    private String sname;
    private String course;
}
