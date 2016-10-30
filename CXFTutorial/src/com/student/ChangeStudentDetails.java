package com.student;

import javax.jws.WebService;

@WebService
public interface ChangeStudentDetails {
  Student changeName(Student student);
}