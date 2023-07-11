
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Mattg
 */
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static ResultSet resultSet;
    private static PreparedStatement addSeats;
    private static PreparedStatement addCourseDescription;
    private static PreparedStatement addCourse;
    private static PreparedStatement addCourseSemester;
     private static PreparedStatement getAllCourses;
     private static PreparedStatement getAllCourseCodes;
     private static PreparedStatement getCourseSeats;
     private static PreparedStatement dropCourse;
     
         public static void dropCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            System.out.println(semester+courseCode);
            dropCourse = connection.prepareStatement("delete from app.course where coursecode =?  and semester=?");
            dropCourse.setString(1,courseCode);
            dropCourse.setString(2,semester);
            System.out.println("dropcourse trying");
            dropCourse.executeUpdate();
            System.out.println(("sucessful drop"));
            dropCourse = connection.prepareStatement("delete from app.schedule where coursecode = ? and semester=?");
            dropCourse.setString(1,courseCode);
            dropCourse.setString(2,semester);
            dropCourse.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
   }
    public static ArrayList<CourseEntry> getAllCourses(String currentsemester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> CourseSem = new ArrayList<String>();
        ArrayList<String> CourseCode = new ArrayList<String>();
        ArrayList<String> CourseDes = new ArrayList<String>();
        ArrayList<String> CourseSeat = new ArrayList<String>();
        ArrayList<CourseEntry> Course = new ArrayList<CourseEntry>();
        //CourseEntry Course[] =new CourseEntry[15];
        ArrayList<Object> guy = new ArrayList<Object>();
        ArrayList<String> AllCourseCodes = new ArrayList<String>();
        try
        {
            getAllCourses = connection.prepareStatement("select semester from app.course order by semester");
            resultSet = getAllCourses.executeQuery();
            
            
            while(resultSet.next())
            {
                CourseSem.add(resultSet.getString(1));
                
            }
            getAllCourses = connection.prepareStatement("select coursecode from app.course order by semester");
            resultSet = getAllCourses.executeQuery();
            while(resultSet.next())
            {
                CourseCode.add(resultSet.getString(1));
                
            }
            getAllCourses = connection.prepareStatement("select description from app.course order by semester");
            resultSet = getAllCourses.executeQuery();
            while(resultSet.next())
            {
                CourseDes.add(resultSet.getString(1));
                
            }
            getAllCourses = connection.prepareStatement("select seats from app.course order by semester");
            resultSet = getAllCourses.executeQuery();
            while(resultSet.next())
            {
                CourseSeat.add(resultSet.getString(1));
                
            }
                for(int i =0; i< CourseSeat.size() ; i++)
            
              {
                  
               if (CourseSem.get(i).equals(currentsemester))
               {                     
                    int y = Integer.parseInt(CourseSeat.get(i));
                    CourseEntry x = new CourseEntry(CourseSem.get(i),CourseCode.get(i), CourseDes.get(i), y);         
                    Course.add(x); 
               }
            }

        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return Course;
    }
        
    public static void addCourse(String code, String description, int seats, String semester)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (semester,coursecode,description,seats)  values (?,?,?,?)");
            
            addCourse.setString(1,semester);
           
            addCourse.setString(2,code);
            addCourse.setString(3,description);
            addCourse.setInt(4,seats);
            addCourse.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
                    sqlException.printStackTrace();
        }
    }
    public static int getCourseSeats(String semester, String courseCode){
        ArrayList<String> Semester_temp = new ArrayList<String>();
        ArrayList<String> Course_codeTemp = new ArrayList<String>();
        ArrayList<Integer> course_seats_temp = new ArrayList<Integer>();
        int temp = 0;
        connection = DBConnection.getConnection();
        
        try
        {
            getCourseSeats = connection.prepareStatement("select seats from app.course order by semester");
            resultSet = getCourseSeats.executeQuery();
            while (resultSet.next()){
                course_seats_temp.add(resultSet.getInt(1));
                
                }
            getCourseSeats = connection.prepareStatement("select coursecode from app.course order by semester");
            resultSet = getCourseSeats.executeQuery();
            while (resultSet.next()){
                Course_codeTemp.add(resultSet.getString(1));
            }
            getCourseSeats = connection.prepareStatement("select semester from app.course order by semester");
            resultSet = getCourseSeats.executeQuery();
            while (resultSet.next()){
                Semester_temp.add(resultSet.getString(1));
            }
            
            for(int i=0; i<Course_codeTemp.size();i++){
                if (Course_codeTemp.get(i).equals(courseCode) && Semester_temp.get(i).equals(semester))
                {
                        temp = course_seats_temp.get(i);
                    }
                
            }
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return temp;
    }
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        ArrayList<String> AllCourseCodes = new ArrayList<String>();
        ArrayList<String> Code_Sem = new ArrayList<String>();
        ArrayList<String> Code_Sem_temp = new ArrayList<String>();
        connection = DBConnection.getConnection();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.course order by semester");
            resultSet=getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                Code_Sem_temp.add(resultSet.getString(1));
            }
            getAllCourseCodes = connection.prepareStatement("select semester from app.course order by semester ");
            resultSet = getAllCourses.executeQuery();
            
            while(resultSet.next())
            {
                Code_Sem.add(resultSet.getString(1));
                
            }
            
            for(int i =0; i<=Code_Sem.size();i++)
            {
                if(Code_Sem.get(i).equals(semester))
                {
                    AllCourseCodes.add(Code_Sem_temp.get(i));
                }
            }
            
        }
                catch(SQLException sqlException)
        {
                    sqlException.printStackTrace();
        }
        return AllCourseCodes;        
    }
}
   
