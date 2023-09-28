package com.codesmachine.springbootrestapi.controllers;

import com.codesmachine.springbootrestapi.domain.Student;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@ResponseBody
public class StudentController {


//    @GetMapping("student")
//    public Student getStudent(){
//        Student student = new Student();
//        student.setId(1);
//        student.setFirstName("John");
//        student.setLastName("Doe");
//
//        return student;
//    }

    @GetMapping("student")
    public ResponseEntity<Student> getStudentResponse(){
        Student student = new Student();
        student.setId(1);
        student.setFirstName("John");
        student.setLastName("Doe");


        return new ResponseEntity<>(student,HttpStatus.OK);
    }


//    @GetMapping("students")
//    public List<Student> getStudents(){
//      List<Student> students = new ArrayList<>();
//      Student st1 = new Student();
//      st1.setId(1);
//      st1.setFirstName("John");
//      st1.setLastName("Doe");
//
//      Student st2 = new Student();
//      st2.setId(2);
//      st2.setFirstName("Felix");
//      st2.setLastName("Mugisha");
//
//      students.add(st1);
//      students.add(st2);
//
//      return students;
//    }


    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudentDataResponse(){
        List<Student> students = new ArrayList<>();
        Student st1 = new Student();
        st1.setId(1);
        st1.setFirstName("John");
        st1.setLastName("Doe");

        Student st2 = new Student();
        st2.setId(2);
        st2.setFirstName("Felix");
        st2.setLastName("Mugisha");

        students.add(st1);
        students.add(st2);

        return new ResponseEntity<>(students,HttpStatus.OK);
    }


//    @GetMapping("students/{id}")
//    public Student getStudentById(@PathVariable("id") Integer id){
//        Student st1 = new Student();
//        st1.setId(id);
//        st1.setFirstName("John");
//        st1.setLastName("Doe");
//        return st1;
//    }

    @GetMapping("students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id){
        Student st1 = new Student();
        st1.setId(id);
        st1.setFirstName("John");
        st1.setLastName("Doe");
        return ResponseEntity.ok(st1);
    }

//    @PostMapping("students")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Student sendStudentData(
//    @RequestBody Student student
//    ){
//        System.out.println("id "+student.getId());
//        System.out.println("first name "+student.getFirstName());
//        System.out.println("last name "+student.getLastName());
//        return student;
//    }

    @PostMapping("students")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> sendStudentData(
            @RequestBody Student student
    ){
        System.out.println("id "+student.getId());
        System.out.println("first name "+student.getFirstName());
        System.out.println("last name "+student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

//    @PutMapping("students/{id}/update")
//    public Student updateStudent(
//            @PathVariable ("id") Integer studentId,
//            @RequestBody Student student
//    )
//    {
//        System.out.println("first name "+student.getFirstName());
//        System.out.println("last name "+student.getLastName());
//        return student;
//    }
    @PutMapping("students/{id}/update")
     public ResponseEntity<Student> updateStudent(
             @PathVariable ("id") Integer studentId,
             @RequestBody Student student
     )
     {
         System.out.println("first name "+student.getFirstName());
         System.out.println("last name "+student.getLastName());
         return ResponseEntity.ok(student);
     }


//    @DeleteMapping("students/{id}/delete")
//    public String delete(@PathVariable("id") Integer id)
//    {
//        System.out.println("Student Id "+id);
//        return "deleted successfully";
//    }

    @DeleteMapping("students/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id)
    {
        System.out.println("Student Id "+id);
        return ResponseEntity.ok("deleted successfully");
    }


}
