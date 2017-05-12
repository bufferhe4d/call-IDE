package submissionsystem;

import java.io.Serializable;
import java.sql.Date;

/**
 * Desc:     This class will be used to store the information about assignments 
 * @author   Ataberk Gozkaya
 * @version  1.00  03/05/2017
 */
public class Assignment implements Serializable
{
    //properties
    private String name;
    private String author;
    private Date duedate;
    private Date subdate;
    private String path;
    private int grade;

    /**
     * A constructor for initializing the variables for determining the features of 
     * @param name the name of the assignments
     * @param author the name of the author for the assignment
     * @param duedate the due date of the assignment
     * @param subdate the submission date for the assignment
     * @param path location of the file in the server
     * @param grade the grade for the assignment
     */
    public Assignment(String name, String author, Date duedate, Date subdate, String path, int grade) {
        this.name = name;
        this.author = author;
        this.duedate = duedate;
        this.subdate = subdate;
        this.path = path;
        this.grade = grade;
    }

    /**
     * A method to get the name of the assignments
     * @return the name of the assignment in String type
     */
    public String getName() {
        return name;
    }
    
    /**
     * A method to get the author name of the assignments
     * @return the author name of the assignment in String type
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * A method to get the due date of the assignments
     * @return the due date of the assignment in Date type
     */
    public Date getDuedate() {
        return duedate;
    }

    /**
     * A method to get the submission date of the assignments 
     * @return the submission of the assignment in Date type
     */
    public Date getSubdate() {
        return subdate;
    }

    /**
     * A method to get the path of the assignments in the location of server
     * @return the path of the assignment in the location of server in String type
     */
    public String getPath() {
        return path;
    }
    /**
     * A method to get the grade for the assignments
     * @return the grade for the assignment in integer type
     */
    public int getGrade() {
        return grade;
    }

    /**
     * A method to set the author for the assignment
     * @param author a parameter to take the author name to set in String type
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * A method to set the due date of the assignment
     * @param duedate a parameter to take the due date to set in Date type
     */
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }
    /**
     * A method to set a grade for the assignment
     * @param grade a parameter to take the grade to set in integer type
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * A method to set the name for the assignment
     * @param author a parameter to take the name to set in String type
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * A method to set the path for the assignment
     * @param author a parameter to take the path to set in String type
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * A method to set the submission date for the assignments
     * @param subdate a parameter to take the submission date 
     */
    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }
    
    /**
     * Check whether the Assignment objects are equal or not according to their names
     * @param other a parameter to take an Assignment object to compare names
     * @return true, if they are equal; false, if not
     */
    public boolean equals( Assignment other) {
        return name.equals(other.name);
    }
   
}
