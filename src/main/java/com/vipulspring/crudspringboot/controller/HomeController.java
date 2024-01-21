package com.vipulspring.crudspringboot.controller;

import com.vipulspring.crudspringboot.entity.Student;
import com.vipulspring.crudspringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;


    //below is a handler for simply viewing the response on tomcat server
    @GetMapping("/")
    public String index(){
        return "This is my CRUD springboot application";


    }

    //below is handler for saving student data by providing required inputs as json during postamn testing
    @PostMapping("/saveStudent")
    public Student saveData(@RequestBody Student student)
    {   studentRepository.save(student);
        return student;
    }
    //below handler is used for getting details of a student by providing rollNo-primary key as a path variable
    @GetMapping("/getStudent/{rollNo}")
    public Student getStudentData(@PathVariable int rollNo){
        Optional<Student> student= studentRepository.findById(rollNo);
        Student student1=student.get();
        return student1;
    }
    //below handler is used for getting details of all students at the present moment in the database
    @GetMapping("/getAllStudent")
    public List<Student> getAll(){
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }
    //below handler is used to delete a student data from database by providing rollNo as input(path variable)
    @DeleteMapping("/deleteStudent/{rollNo}")
    public String deleteStudent(@PathVariable int rollNo){
        Student student=studentRepository.findById(rollNo).get();
        if(student!=null)
            studentRepository.delete(student);

        return "The requested student is successfully deleted";
    }

    //below handler is used for updating data of student, the request body is same like in savestudent handler above
    @PutMapping("/updateData")
    public Student updateStudentData(@RequestBody Student student){
        studentRepository.save(student);
        return student;

    }

}


