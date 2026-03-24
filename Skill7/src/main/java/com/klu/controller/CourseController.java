package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.entity.Course;
import com.klu.service.CourseManager;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseManager cm;

    @PostMapping("/insert")
    public String insert(@RequestBody Course c1)
    {
        return cm.insertData(c1);
    }

    @GetMapping   // ADD THIS
    public List<Course> getAllCourses()
    {
        return cm.getAllData();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        return cm.deleteData(id);
    }

    @GetMapping("/search/{title}")
    public List<Course> search(@PathVariable String title)
    {
        return cm.searchByTitle(title);
    }
}