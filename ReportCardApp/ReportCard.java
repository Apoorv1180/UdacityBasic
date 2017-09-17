//Class  ReportCard
public class ReportCard {
    private int mStudentID;
    private String mStudentName;
    private String mCourse;
    private int mMarks;
    private String mSemester;
//Constructor ReportCard
    public ReportCard(int studentID, String studentName, String course, int marks, String semester) {
        this.mStudentID = studentID;
        this.mStudentName = studentName;
        this.mCourse = course;
        this.mMarks = marks;
        this.mSemester = semester;
    }

//Setter Methods
    public void setStudentID(int studentId) {
        this.mStudentID = studentId;
    }

     public void setStudentName(String studentName) {
       this.mStudentName = studentName;
    }

    public void setCourse(String course) {
        this.mCourse = course;
    }

    public void setGrade(int marks) {
        this.mMarks = marks;
    }
    public void setSemester(String semester) {
        this.mSemester = semester;
    }

//getter Methods
    public int getStudentID() {
        return this.mStudentID;
    }

    public String getStudentName() {
        return this.mStudentName;
    }

    public String getCourse() {
        return this.mCourse;
    }

    public int getMarks() {
        return this.mMarks;
    }

    public String getSemester() {
        return mSemester;
    }

//METHOD TO DECIDE GRADES OF THE STUDENT
    public String getReportCardGrade(int grade){

        String mGrade;

        if(grade > 79 && grade < 101){
            mGrade = "A";
        } else if (grade > 69 && grade < 50){
            mGrade = "B";
        } else if (grade > 59 && grade < 70){
            mGrade = "C";
        }  else if (grade > 49 && grade < 60){
            mGrade = "D";
        } else {
            mGrade = "F";
        }
        return grade;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "studentID=" + mStudentID +
                ", studentName='" + mStudentName + '\'' +
                ", course='" + mCourse + '\'' +
                ", grade=" + mMarks +
                ", semester='" + mSemester + '\'' +
                '}';
    }
}
