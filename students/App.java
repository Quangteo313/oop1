package hus.oop.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String COMMA_DELIMITER = ",";

    public static void readListData(String filePath) {
        BufferedReader dataReader = null;
        try {
            String line;
            dataReader = new BufferedReader(new FileReader(filePath));
    
            while ((line = dataReader.readLine()) != null) {
                List<String> dataList = parseDataLineToList(line);
                if (dataList.size() != 7) {
                    continue;
                }
    
                if (dataList.get(0).equalsIgnoreCase("id")) {
                    continue;
                }
    
                String id = dataList.get(0).trim();
                String lastname = dataList.get(1).trim();
                String firstname = dataList.get(2).trim();
                int yearOfBirth = Integer.parseInt(dataList.get(3).trim());
                double mathsGrade = Double.parseDouble(dataList.get(4).trim());
                double physicsGrade = Double.parseDouble(dataList.get(5).trim());
                double chemistryGrade = Double.parseDouble(dataList.get(6).trim());
    
                Student newStudent = new Student.StudentBuilder(id)
                    .withLastname(lastname)
                    .withFirstname(firstname)
                    .withYearOfBirth(yearOfBirth)
                    .withMathsGrade(mathsGrade)
                    .withPhysicsGrade(physicsGrade)
                    .withChemistryGrade(chemistryGrade)
                    .build();
    
                StudentManager.getInstance().append(newStudent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataReader != null)
                    dataReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

    public static void testOriginalData() {
        System.out.println("== ORIGINAL DATA ==");
        StudentManager.print(StudentManager.getInstance().sortStudentsByName()); // Or print full list
    }
    
    public static void testSortStudentsByName() {
        System.out.println("== SORTED BY NAME ==");
        List<Student> sorted = StudentManager.getInstance().sortStudentsByName();
        StudentManager.print(sorted);
    }
    
    public static void testSortAverageGradeIncreasing() {
        System.out.println("== SORTED BY AVERAGE GRADE (INCREASING) ==");
        List<Student> sorted = StudentManager.getInstance().sortAverageGradeIncreasing();
        StudentManager.print(sorted);
    }
    
    public static void testSortAverageGradeDecreasing() {
        System.out.println("== SORTED BY AVERAGE GRADE (DECREASING) ==");
        List<Student> sorted = StudentManager.getInstance().sortAverageGradeDecreasing();
        StudentManager.print(sorted);
    }
    
    public static void testFilterPassStudents() {
        System.out.println("== PASSED STUDENTS ==");
        List<Student> filtered = StudentManager.getInstance().filterPassStudents();
        StudentManager.print(filtered);
    }
    
    public static void testFilterFailureStudents() {
        System.out.println("== FAILED STUDENTS (at least 1 failure) ==");
        List<Student> filtered = StudentManager.getInstance().filterFailureStudents(1);
        StudentManager.print(filtered);
    }
    public static void main(String[] args) {
        init();
        testOriginalData();
        testSortStudentsByName();
        testSortAverageGradeIncreasing();
        testSortAverageGradeDecreasing();
        testFilterPassStudents();
        testFilterFailureStudents();
    }
}
        