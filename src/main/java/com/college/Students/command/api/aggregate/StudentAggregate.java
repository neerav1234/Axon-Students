package com.college.Students.command.api.aggregate;

import com.college.Students.StudentsApplication;
import com.college.Students.command.api.commands.AddStudentCommand;
import com.college.Students.command.api.commands.DeleteStudentByIdCommand;
import com.college.Students.command.api.events.StudentAddedEvent;
import com.college.Students.command.api.events.StudentDeletedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class StudentAggregate {
    @AggregateIdentifier
    private String sid;
    private String sname;
    private String course;

    public StudentAggregate() {
    }

    @CommandHandler
    public StudentAggregate(AddStudentCommand addStudentCommand) {
        //logix
        StudentAddedEvent studentAddedEvent = new StudentAddedEvent();
        BeanUtils.copyProperties(addStudentCommand, studentAddedEvent);
        AggregateLifecycle.apply(studentAddedEvent);
        System.out.println("Add Event Released and Add Command Handled");
    }

    @CommandHandler
    public void handle(DeleteStudentByIdCommand deleteStudentByIdCommand) {
        System.out.println("Entered Delete Command Handler");
        StudentDeletedEvent studentDeletedEvent  = new StudentDeletedEvent();
        BeanUtils.copyProperties(deleteStudentByIdCommand, studentDeletedEvent);
        AggregateLifecycle.apply(studentDeletedEvent);
        System.out.print(deleteStudentByIdCommand.getSid());
        System.out.println(" Delete Event Released and Delete Command Handled");
    }

    @EventSourcingHandler
    public void on(StudentAddedEvent studentAddedEvent) {
        this.sid = studentAddedEvent.getSid();
        this.sname = studentAddedEvent.getSname();
        this.course = studentAddedEvent.getCourse();
        System.out.println("Event Sourced");
    }

    @EventSourcingHandler
    public void on(StudentDeletedEvent studentDeletedEvent) {
        this.sid = studentDeletedEvent.getSid();
        System.out.print("Event Sourced ");
        System.out.println(studentDeletedEvent.getSid());
    }
}
