package com.college.Students.command.api.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteStudentByIdCommand {
    @TargetAggregateIdentifier
    private String sid;

    public DeleteStudentByIdCommand(String sid) {
        this.sid = sid;
    }
}
