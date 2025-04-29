package hus.oop.students;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentManager {
    // Singleton pattern
    private static StudentManager instance;

    private List<Student> studentList;

    private StudentManager() {
        studentList = new ArrayList<>();
    }

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void append(Student student) {
        studentList.add(student);
    }

    public void add(Student student, int index) {
        if (index >= 0 && index <= studentList.size()) {
            studentList.add(index, student);
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < studentList.size()) {
            studentList.remove(index);
        }
    }

    public Student studentAt(int index) {
        if (index >= 0 && index < studentList.size()) {
            return studentList.get(index);
        }
        return null;
    }

    public int numberOfStudents() {
        return studentList.size();
    }

    public List<Student> sortStudentsByName() {
        List<Student> sortedList = new ArrayList<>(studentList);
        Collections.sort(sortedList); // uses compareTo in Student
        return sortedList;
    }

    public List<Student> sortAverageGradeIncreasing() {
        List<Student> sortedList = new ArrayList<>(studentList);
        sortedList.sort(Comparator.comparingDouble(Student::getAverageGrade));
        return sortedList;
    }

    public List<Student> sortAverageGradeDecreasing() {
        List<Student> sortedList = new ArrayList<>(studentList);
        sortedList.sort(Comparator.comparingDouble(Student::getAverageGrade).reversed());
        return sortedList;
    }

    public List<Student> filterPassStudents() {
        List<Student> filteredList = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getMathsGrade() > 4.0 &&
                s.getPhysicsGrade() > 4.0 &&
                s.getChemistryGrade() > 4.0 &&
                s.getAverageGrade() > 5.0) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    public List<Student> filterFailureStudents(int howMany) {
        List<Student> filteredList = new ArrayList<>();
        for (Student s : studentList) {
            int failCount = 0;
            if (s.getMathsGrade() < 4.0) failCount++;
            if (s.getPhysicsGrade() < 4.0) failCount++;
            if (s.getChemistryGrade() < 4.0) failCount++;
            if (s.getAverageGrade() < 5.0) failCount++;
            if (failCount >= howMany) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

    public static String idOfStudentsToString(List<Student> studentList) {
        StringBuilder idOfStudents = new StringBuilder();
        idOfStudents.append("[");
        for (Student student : studentList) {
            idOfStudents.append(student.getId()).append(" ");
        }
        return idOfStudents.toString().trim() + "]";
    }

    public static void print(List<Student> studentList) {
        StringBuilder studentsString = new StringBuilder();
        studentsString.append("[\n");
        for (Student student : studentList) {
            studentsString.append(student.toString()).append("\n");
        }
        System.out.print(studentsString.toString().trim() + "\n]");
    }
}
