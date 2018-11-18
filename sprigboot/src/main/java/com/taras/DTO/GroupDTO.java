package com.taras.DTO;

import com.taras.controller.StudentController;
import com.taras.domain.Group;
import com.taras.exceptions.NoSuchDisciplineException;
import com.taras.exceptions.NoSuchGroupException;
import com.taras.exceptions.NoSuchStudentException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GroupDTO extends ResourceSupport {
    Group group;
    public GroupDTO(Group group, Link selfLink) throws NoSuchStudentException, NoSuchDisciplineException, NoSuchGroupException{
        this.group=group;
        add(selfLink);
        add(linkTo(methodOn(StudentController.class).getStudentByGroupID(group.getId())).withRel("students"));
    }

    public Long getGroupId() { return group.getId(); }

    public String getGroup_name() {
        return group.getGroup_name();
    }
}
