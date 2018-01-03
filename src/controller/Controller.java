
package controller;

import dbCom.DBConnection;
import dbCom.*;
import model.*;
import java.util.ArrayList;


public class Controller {
    private CollectionOfUsers users;
    private User user;
    private UserManager userManager;
    private DBSelect dbSelect;
    private DBInsert dbInsert;
    private DBConnection dbConnection;

    public Controller() {
        this.users = UserManager.getAllUsers();
    }

    public boolean tryLogin(String email, String password) {
        try {
            // TODO check user in db or serialize
            if (email.equals("Admin") && password.equals("Password")) {
                System.out.println("Controller know that really Admin is tryLogin");
                return true;
            }
            for (int i = 0; i < users.getUsers().size(); i++) {
                if (users.getUsers().get(i).getEmail().equals(email) && users.getUsers().get(i).getPassword().equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException n) {
            System.out.println("NullPointerException in Controller - tryLogin");
        }
        return false;
    }

    public boolean tryisTeacher(String userOrTeacher) {
        try {
            if (userOrTeacher.equals("Teacher")){
                return true;
            } else {
                return false;
            }
        }
        catch (NullPointerException n) {
            System.out.println("NullPointerException in controller tryisTeacher");
        }
        return false;
    }

    public boolean tryRegister(String name, String email, String password, String userOrTeacher) {
        if (email.equals("Admin") && password.equals("Password")) {
            System.out.println("Hi admin is already registered");
            return false;
        } else {
            System.out.println("Controller confirms a: " + userOrTeacher + " with the name: " + name + "  is trying to register");

            //TODO Register user...
            //users.addUser(name, email, password, userOrTeacher);
            User firstUser = new User(name,email,password);
            System.out.println(firstUser);
            return true;
        }
    }

    public String getNameByEmail(String email) {
        try {
            System.out.println(users.getUsers().size());
            System.out.println("Works");
        } catch (NullPointerException i) {
            System.out.println("Null");
            return "NULL found";
        }
        return "getNameByEmail end";
    }

    public ArrayList getCoursesAll() {
        try {
            ArrayList AllCourses;
            AllCourses = dbSelect.selectAllCourses();
            return AllCourses;
        } catch (Exception e) {
            return null;
        }
    }
    public String getCoursesMy(String email) {
        try {
            System.out.println("Trying to fetch my courses thru controller");
            return "Null of my courses found. Searching with email: " + email;
        } catch (Exception e) {
            System.out.println("EXCEPTION in controller getCoursesAll");
            return "Error";
        }
    }
    public String setCoursesCreateNew(String course) {
        System.out.println("Trying to register new course thru controller");
        System.out.println(course);
        DBInsert dbInsert = new DBInsert();
        dbInsert.insertCourse(course);
        return null;
        //return "Trying to register course: " + course + "";
    }

    public static void main(String[] args) {
        new Controller();
    }
}
    /*
        for(int i=0; i < users.getUsers().size();i++){
            if(users.getUsers().get(i).getEmail().equals(email)){
                return users.getUsers().get(i).getName();
            }
        }
        return "Name not found by email";

        NullPointerException
*/