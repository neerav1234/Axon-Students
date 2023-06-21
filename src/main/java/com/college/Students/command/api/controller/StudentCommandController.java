package com.college.Students.command.api.controller;

import com.college.Students.command.api.commands.AddStudentCommand;
import com.college.Students.command.api.commands.DeleteStudentByIdCommand;
import com.college.Students.core.api.model.StudentRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentCommandController {
    private CommandGateway commandGateway;

    public StudentCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/")
    public String addStudent(@RequestBody StudentRestModel srm) {
        AddStudentCommand addStudentCommand = AddStudentCommand.builder()
                        .sid(UUID.randomUUID().toString())
                                .sname(srm.getSname())
                                        .course(srm.getCourse())
                                            .build();
        String result = commandGateway.sendAndWait(addStudentCommand);
        System.out.println("Student Added");
        return result;

    }

    @DeleteMapping("/{sid}")
    public String deleteStudentById(@PathVariable String sid) {
        System.out.print("Deleting Student with id: ");
        System.out.println(sid);
        DeleteStudentByIdCommand comm = new DeleteStudentByIdCommand(sid.toString());
        String result = commandGateway.sendAndWait(comm);
        System.out.println("Student Deleted");
        return result;
    }
}
