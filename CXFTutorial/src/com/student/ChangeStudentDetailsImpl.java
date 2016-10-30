package com.student;

import javax.jws.WebService;

@WebService(endpointInterface = "com.student.ChangeStudentDetails")
public class ChangeStudentDetailsImpl implements ChangeStudentDetails {
    public Student changeName(Student student) {
      student.setName("Hello "+student.getName());
      return student;
    }
}