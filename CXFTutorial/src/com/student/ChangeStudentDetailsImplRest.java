package com.student;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Consumes("application/json")
@Produces("application/json")
public class ChangeStudentDetailsImplRest implements ChangeStudentDetails {

  @POST
  @Path("/changeName")
  public Student changeName(Student student) {
    student.setName("HELLO " + student.getName());
    return student;
  }

  @GET
  @Path("/getName")
  public Student getName() {
    Student student = new Student();
    student.setName("Rockey");
    return student;
  }
}