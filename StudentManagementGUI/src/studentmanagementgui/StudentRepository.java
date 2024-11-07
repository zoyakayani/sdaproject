/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentmanagementgui;

/**
 *
 * @author CUI
 */
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }
}

