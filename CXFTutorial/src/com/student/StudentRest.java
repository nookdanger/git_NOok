package com.student;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Student")
public class StudentRest {
	 private String name;

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
}
