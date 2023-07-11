


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Mattg
 */
public class ScheduleEntry {
    private String Semester;
    private String CourseCode;
    private String StudentID;
    private String Status;
    private Timestamp Timestamp;
    
    public ScheduleEntry (String Semester, String CourseCode, String StudentID, String Status, Timestamp Timestamp){
        this.Semester= Semester;
        this.CourseCode = CourseCode;
        this.StudentID = StudentID;
        this.Status = Status;
        this.Timestamp = Timestamp;
    }
    public String getFirstName(){
        ArrayList<String> name = StudentQueries.getName(StudentID);
        ArrayList<String> firstName = new ArrayList<String>();
        for(int i=0 ; i<name.size();i++){
            String temp = name.get(i);
            String temp_[] = temp.split("[,]");
            firstName.add(temp_[0]);
        }
        
        return firstName.toString();
    }
    public String getLastName(){
        ArrayList<String> name_ = StudentQueries.getName(StudentID);
        ArrayList<String> lastName = new ArrayList<String>();
        for(int i=0 ; i<name_.size();i++){
            String temp = name_.get(i);
            String temp_[] = temp.split("[,]");
            lastName.add(temp_[1]);
        }
        return lastName.toString();
    }
    public Timestamp getTimestamp() {
        return Timestamp;
    }
    public String getSemester(){
        return Semester;
    }
    public String getCourseCode(){
        return CourseCode;
    }
    public String getStudentID(){
        return StudentID;
    }
    public String getStatus(){
        return Status;
    }
}
