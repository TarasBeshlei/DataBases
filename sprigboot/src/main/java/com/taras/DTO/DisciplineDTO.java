package com.taras.DTO;

import com.taras.controller.StudentController;
import com.taras.domain.Discipline;
import com.taras.exceptions.NoSuchDisciplineException;
import com.taras.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DisciplineDTO extends ResourceSupport {

    Discipline discipline;
    public DisciplineDTO(Discipline discipline, Link selflink) throws NoSuchDisciplineException, NoSuchStudentException{
        this.discipline = discipline;
        add(selflink);
        add(linkTo(methodOn(StudentController.class).getStudentsByDisciplineID(discipline.getId())).withRel("students"));
    }

    public Long getDisciplineId(){
        return discipline.getId();
    }

    public String getDisciplineName(){
        return discipline.getDisciplineName();
    }
}
