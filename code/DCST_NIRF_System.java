/*
Author: Naveen Pattihal
Roll no : 2629
Description : This program is a simple OOP system for DCST Goa University NIRF reporting. It manages student and department details including academic activities like ISA, SEA, placements and non-academic activities like InfoFest and Plateaunia. It demonstrates basic OOP concepts like constructors, object referencing, static variables, multiple inheritance with interfaces, abstract classes, method overloading, overriding, exception handling, collections, reflection, file writing, and cloning.
*/

import java.util.*;
import java.io.*;

enum Type {
    STUDENT, FACULTY
}

interface Academic {
    void showAcad();
}

interface NonAcademic {
    void showNonAcad();
}

class Department implements Academic, NonAcademic {
    public void showAcad() {
        System.out.println("Academic: ISA, SEA, Midpoint, Placements");
    }
    public void showNonAcad() {
        System.out.println("Non-Acad: InfoFest, Plateaunia");
    }
}

class DemoAccess {
    public String pub = "public data";
    protected String pro = "protected data";
    private String pri = "private data";

    void printAll() {
        System.out.println(pub + ", " + pro + ", " + pri);
    }
}

abstract class Person {
    String name;
    Person(String n) {
        name = n;
    }
    abstract void display();
}

interface Notifier {
    void notifyUser();
}

class NIRFException extends Exception {
    NIRFException(String s) {
        super(s);
    }
}

class MyData<T> {
    T obj;
    MyData(T obj) {
        this.obj = obj;
    }
}

class Student extends Person implements Notifier, Cloneable, Serializable {
    String roll;
    String city;
    static int count = 0;

    Student(String name, String roll, String city) {
        super(name);
        this.roll = roll;
        this.city = city;
        count++;
    }

    static void getCount() {
        System.out.println("Count: " + count);
    }

    void display() {
        System.out.println("Student: " + name + " Roll: " + roll);
    }

    void addMarks(int m) {
        System.out.println("Marks: " + m);
    }

    void addMarks(int m, int practical) {
        System.out.println("Total: " + (m + practical));
    }

    public void notifyUser() {
        System.out.println("Alert sent to: " + name);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

public class DCST_NIRF_System {

    static void sendAlert(Notifier n) {
        n.notifyUser();
    }

    static <E> void printVal(E val) {
        System.out.println("Value: " + val);
    }

    public static void main(String[] args) {
        System.out.println("DCST NIRF System");

        Student s1 = new Student("Rahul", "01", "Panaji");
        Student s2 = s1;
        s2.name = "Rahul N"; 
        System.out.println("s1 name changed: " + s1.name);

        Student.getCount();
        Type t = Type.STUDENT;
        System.out.println("Type: " + t);

        Department d = new Department();
        d.showAcad();
        d.showNonAcad();

        DemoAccess da = new DemoAccess();
        da.printAll();
        System.out.println("Outside: " + da.pub);

        Person p = new Student("Pooja", "02", "Margao");
        p.display();

        sendAlert(s1);

        s1.addMarks(40);
        s1.addMarks(40, 15);

        MyData<String> md = new MyData<>("MCA Course");
        printVal(md.obj);

        ArrayList<String> courses = new ArrayList<>();
        courses.add("MCA");
        courses.add("MSc DS");
        courses.add("OOT");
        System.out.println("Courses: " + courses);

        HashMap<String, String> map = new HashMap<>();
        map.put("TechFest", "InfoFest");
        System.out.println("Fest: " + map.get("TechFest"));

        try {
            int x = 2020;
            if (x < 2023) {
                throw new NIRFException("Invalid year");
            }
        } catch (NIRFException e) {
            System.out.println("Caught: " + e.getMessage());
        } catch (Exception e) {
        } finally {
            System.out.println("Finally done");
        }

        try {
            int a = 10 / 0;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        try {
            Class c = Class.forName("Student");
            System.out.println("Class loaded: " + c.getName());

            FileWriter fw = new FileWriter("test.txt");
            fw.write("Rahul, MCA");
            fw.close();
            System.out.println("File saved");

            Student s3 = (Student) s1.clone();
            System.out.println("Cloned student: " + s3.name);
        } catch (Exception e) {
            System.out.println("Exception in part 10");
        }
    }
}
