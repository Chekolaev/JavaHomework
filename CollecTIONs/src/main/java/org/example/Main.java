package org.example;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {
     public static void main(String[] args) {

        ArrayList<Student> ArrayOfStudents = new ArrayList<>();

        LocalDate _date1 = LocalDate.of(2003,01,5);
        LocalDate _date2 = LocalDate.of(2002, 03, 7);
        LocalDate _date3 = LocalDate.of(2006, 6, 6);

        Student First = new Student(0,"Ksenia", "Chekolaeva", "Olegovns", _date1, 5.0f);
        Student Second = new Student(1,"Dmitry", "Chekolaev", "Alekseevich", _date2, 5.0f);
        Student Third = new Student(2,"Hudyakov", "Roman", "LOQIEMEAN", _date3, 4.5f);

        ArrayOfStudents.add(First);
        ArrayOfStudents.add(Second);
        ArrayOfStudents.add(Third);


        System.out.println("ARR_____________________________");
        for (Student Student : ArrayOfStudents) {
            System.out.println(Student.PrintInfo());
        }
        System.out.println("________________________________");

        Third._SetID(4); // Изменение ID
        ArrayOfStudents.get(2)._SetID(4); // Изменение ID

        ArrayOfStudents.remove(ArrayOfStudents.size()-1); // Удаление

        ArrayOfStudents.clear(); // Очистка

        System.out.println("ARR_____________________________");
        for (Student Student : ArrayOfStudents) {
            System.out.println(Student.PrintInfo());
        }
        System.out.println("________________________________");

        System.out.println(First._GetAge());
    }




}

