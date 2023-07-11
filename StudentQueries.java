
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mattg
 */
public class StudentQueries {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement addStudents;
    private static PreparedStatement getStudentList;
    private static ArrayList<StudentEntry> StudentEntry = new ArrayList<StudentEntry>();
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudentID;
    private static PreparedStatement getStudent;
    private static PreparedStatement getName;
    
    public static ArrayList<String> getName(String studentid){
        ArrayList<String> first_temp = new ArrayList<String>();
        ArrayList<String> Last_temp = new ArrayList<String>();
        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> studentid_temp = new ArrayList<String>();


        connection = DBConnection.getConnection();
        try
        {
            getName = connection.prepareStatement("select firstName from app.student order by studentid");
            resultSet = getName.executeQuery();
            while (resultSet.next()){
                first_temp.add(resultSet.getString(1));
            }
            getName = connection.prepareStatement("select lastName from app.student order by studentid");
            resultSet = getName.executeQuery();
            while (resultSet.next()){
                Last_temp.add(resultSet.getString(1));
            }
            getName = connection.prepareStatement("select studentid from app.student order by studentid");
            resultSet = getName.executeQuery();
            while (resultSet.next()){
                studentid_temp.add(resultSet.getString(1));
            }
            for(int i = 0; i<studentid_temp.size();i++){
                if(studentid_temp.get(i).equals(studentid)){
                    Name.add(first_temp.get(i)+","+Last_temp.get(i));
                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Name;
    }
    
    /*
    public static StudentEntry getStudent(String studentID){
        
        //StudentEntry student = null;
        connection = DBConnection.getConnection();
        try
        {
            getStudent = connection.prepareStatement("select  from app.semester where studentid=?");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();
            
               String x= resultSet.getString(1);
               String y= resultSet.getString(2);
               String z= resultSet.getString(3);
               StudentEntry student = new StudentEntry(x,y,z);
            
            
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    */
    public static void addStudents (String StudentID, String FirstName, String LastName)        
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudents = connection.prepareStatement("insert into app.student (studentid,firstname,lastname) values (?,?,?)");
            addStudents.setString(1, StudentID);
            addStudents.setString(2,FirstName);
            addStudents.setString(3, LastName);
            addStudents.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    
}
    public static  ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> Students = new ArrayList<StudentEntry>();
        ArrayList<String> Studentid_temp = new ArrayList<String>();
        ArrayList<String> StudentFirst_temp = new ArrayList<String>();
        ArrayList<String> StudentLast_temp = new ArrayList<String>();
        
        try
        {
            getAllStudents = connection.prepareStatement("select studentid from app.student order by studentid");
            resultSet = getAllStudents.executeQuery();
            while(resultSet.next())
            {
                Studentid_temp.add(resultSet.getString(1));
                
            }
            getAllStudents = connection.prepareStatement("select lastname from app.student order by studentid");
            resultSet = getAllStudents.executeQuery();
            while(resultSet.next())
            {
                StudentLast_temp.add(resultSet.getString(1));
                
            }
            getAllStudents = connection.prepareStatement("select firstName from app.student order by studentid");
            resultSet = getAllStudents.executeQuery();
            while(resultSet.next())
            {
                StudentFirst_temp.add(resultSet.getString(1));
                
            }
            for( int i=0; i<Studentid_temp.size(); i++)
            {
                StudentEntry value = new StudentEntry(Studentid_temp.get(i),StudentLast_temp.get(i),StudentFirst_temp.get(i));
                Students.add(value);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Students;
    }
    public static String getStudentID(String FirstName, String LastName){
    ArrayList<String> firstName_temp = new ArrayList<String>();
    ArrayList<String> lastName_temp = new ArrayList<String>();
    ArrayList<String>studentid_array = new ArrayList<String>();
    String studentid_temp= null;
    connection = DBConnection.getConnection();
    try
    {
        getStudentID = connection.prepareStatement("select lastname from app.student order by studentid");
        resultSet = getStudentID.executeQuery();
        while (resultSet.next()){
            lastName_temp.add(resultSet.getString(1));
        }
        getStudentID = connection.prepareStatement("select firstname from app.student order by studentid");
        resultSet = getStudentID.executeQuery();
        while (resultSet.next()){
           firstName_temp.add(resultSet.getString(1));
        }
        getStudentID = connection.prepareStatement("select studentid from app.student order by studentid");
        resultSet = getStudentID.executeQuery();
        while (resultSet.next()){
           studentid_array.add(resultSet.getString(1));
        }
        for(int i=0; i<firstName_temp.size();i++){
            if(firstName_temp.get(i).equals(FirstName) || lastName_temp.get(i).equals(LastName) )
            {
                    studentid_temp = studentid_array.get(i);
            }
        }
    }
    catch(SQLException sqlException)
    {
         sqlException.printStackTrace();
    }  
    return studentid_temp;
    }
    
    
    
    public static ArrayList<StudentEntry> getStudentList(){
        connection = DBConnection.getConnection();
        ArrayList<String> StudentFirst = new ArrayList<String>();
        ArrayList<String> StudentLast = new ArrayList<String>();
        ArrayList<String> StudentID = new ArrayList<String>();
        
        try
        {
            getStudentList = connection.prepareStatement("select studentid from app.student");
            resultSet = getStudentList.executeQuery();
            
            while(resultSet.next())
            {
                StudentID.add(resultSet.getString(1));
            }
            
            getStudentList = connection.prepareStatement("select firstname from app.student");
            resultSet = getStudentList.executeQuery();
            
            
            while(resultSet.next())
            {
                StudentFirst.add(resultSet.getString(1));
                
            }
            getStudentList = connection.prepareStatement("select lastname from app.student");
            resultSet = getStudentList.executeQuery();
            
            while (resultSet.next())
            {
                StudentLast.add(resultSet.getString(1));
            }
            for (int i = 0; i<StudentLast.size(); i++)
            {
                StudentEntry Stu_Ent = new StudentEntry(StudentID.get(i),StudentFirst.get(i),StudentLast.get(i));
                StudentEntry.add(Stu_Ent);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return StudentEntry;
    }
}
