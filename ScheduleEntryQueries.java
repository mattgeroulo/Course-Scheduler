/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.Calendar;
/**
 *
 * @author Mattg
 */
public class ScheduleEntryQueries {
    private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getSchedule;
    private static PreparedStatement getCourses;
    private static PreparedStatement dropCourse;
    private static PreparedStatement getScheduledStudentsByCourse;
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode){
        ArrayList<String> studentid_temp = new ArrayList<String>();
        ArrayList<String> semester_temp = new ArrayList<String>();
        ArrayList<String> courseCode_temp = new ArrayList<String>();
        ArrayList<String> status_temp = new ArrayList<String>();
        ArrayList<Timestamp> TimeStamp = new ArrayList<Timestamp>();
        ArrayList<ScheduleEntry> Final = new ArrayList<ScheduleEntry>();
        connection = DBConnection.getConnection();
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select studentid from app.schedule order by studentid");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            while(resultSet.next())
            {
                studentid_temp.add(resultSet.getString(1));
            }
            getScheduledStudentsByCourse = connection.prepareStatement("select semester from app.schedule order by studentid");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            while(resultSet.next())
            {
                semester_temp.add(resultSet.getString(1));
            }
            getScheduledStudentsByCourse = connection.prepareStatement("select coursecode from app.schedule order by studentid");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            while(resultSet.next())
            {
                courseCode_temp.add(resultSet.getString(1));
            }
            getScheduledStudentsByCourse = connection.prepareStatement("select status from app.schedule order by studentid");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            while(resultSet.next())
            {
                status_temp.add(resultSet.getString(1));
            }
            getScheduledStudentsByCourse = connection.prepareStatement("select timestamp from app.schedule order by studentid");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            while(resultSet.next())
            {
                TimeStamp.add(resultSet.getTimestamp(1));
            }
            
            
            for(int i =0;i<courseCode_temp.size();i++){
                if( semester_temp.get(i).equals(semester))
                {
                    if(courseCode_temp.get(i).equals(courseCode)){
                        ScheduleEntry x = new ScheduleEntry(semester_temp.get(i),courseCode_temp.get(i),studentid_temp.get(i),status_temp.get(i),TimeStamp.get(i));
                        Final.add(x);
                    }
                    
                }
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Final;
    }
    
    public static void addScheduleEntry(String Semester, String CourseCode, String StudentID, String Status, Timestamp Timestamp){
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester,studentid,coursecode,status,timestamp) values(?,?,?,?,?)");
            addScheduleEntry.setString(1,Semester);
            addScheduleEntry.setString(2,StudentID);
            addScheduleEntry.setString(3,CourseCode);
            addScheduleEntry.setString(4,Status);
            addScheduleEntry.setTimestamp(5,Timestamp);
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static int getScheduledStudentCount(String semester, String CourseCode){
       connection = DBConnection.getConnection();
       int count = 0;
       try
       {
       getScheduledStudentCount = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and courseCode = ?");
       getScheduledStudentCount.setString(1,semester);
       getScheduledStudentCount.setString(2, CourseCode);
       resultSet =getScheduledStudentCount.executeQuery();
            while(resultSet.next())
            {
                count = resultSet.getInt(1);

            }    
       }
       catch(SQLException sqlException)
       {
           sqlException.printStackTrace();
       }
       return count;
    }
    public static ArrayList<String> getCourses(String semester, String StudentID){
        connection = DBConnection.getConnection();
        ArrayList<String> courses = new ArrayList<String>();
        try
        {
            getCourses = connection.prepareStatement("select coursecode from app.schedule where semester= ? and studentid = ?");
            getCourses.setString(1,semester);
            getCourses.setString(2,StudentID);
            resultSet = getCourses.executeQuery();
            while(resultSet.next()){
                courses.add(resultSet.getString(1));
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courses;
    }
    public static ArrayList<ScheduleEntry> getSchedule(String semester, String Studentid_){
        ArrayList<String> Semester = new ArrayList<String>();
        ArrayList<String> StudentID_temp = new ArrayList<String>();
        ArrayList<String> CourseCode = new ArrayList<String>();
        ArrayList<String> Status = new ArrayList<String>();
        ArrayList<Timestamp> TimeStamp = new ArrayList<Timestamp>();
        ArrayList<ScheduleEntry> Schedule = new ArrayList<ScheduleEntry>();
        connection = DBConnection.getConnection();
        try
        {
            getSchedule = connection.prepareStatement("select semester from app.schedule order by studentid");
            resultSet = getSchedule.executeQuery();
            while(resultSet.next())
            {
                Semester.add(resultSet.getString(1));
            }
            
            getSchedule = connection.prepareStatement("select studentid from app.schedule order by studentid");
            resultSet = getSchedule.executeQuery();
            while(resultSet.next())
            {
                StudentID_temp.add(resultSet.getString(1));
            }
            
            getSchedule = connection.prepareStatement("select coursecode from app.schedule order by studentid");
            resultSet = getSchedule.executeQuery();
            while(resultSet.next())
            {
                CourseCode.add(resultSet.getString(1));
            }
            
            getSchedule = connection.prepareStatement("select status from app.schedule order by studentid");
            resultSet = getSchedule.executeQuery();
            while(resultSet.next())
            {
                Status.add(resultSet.getString(1));
            }
            
            getSchedule = connection.prepareStatement("select timestamp from app.schedule order by studentid");
            resultSet = getSchedule.executeQuery();
            while(resultSet.next())
            {
                TimeStamp.add(resultSet.getTimestamp(1));
            }
            
            for(int i = 0 ;i<Status.size();i++){
                if(Semester.get(i).equals(semester) && StudentID_temp.get(i).equals(Studentid_)){
                    System.out.println(Semester.get(i)+ CourseCode.get(i)+ StudentID_temp.get(i)+Status.get(i));
                    ScheduleEntry x = new ScheduleEntry(Semester.get(i),CourseCode.get(i),StudentID_temp.get(i),Status.get(i),TimeStamp.get(i));
                    Schedule.add(x);
                    
                }
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Schedule;
    }

   }

