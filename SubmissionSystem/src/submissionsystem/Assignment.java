package submissionsystem;

import java.sql.Date;

/**
 * Desc:     This class will be used to store the information about assignments 
 * @author   Ataberk Gözkaya
 * @version  1.00  03/05/2017
 */
public class Assignment 
{
    //properties
    private String name;
    private String author;
    private Date duedate;
    private Date subdate;
    private String path;
    private int grade;



    /**
     * @param id
     * @param authorID
     * @param grade
     */
    public Assignment(String name, String author, Date duedate, Date subdate, String path, int grade) {
        this.name = name;
        this.author = author;
        this.duedate = duedate;
        this.subdate = subdate;
        this.path = path;
        this.grade = grade;
    }



    
    public String getName() {
        return name;
    }
    
    public String getAuthor()
    {
            return author;
    }

    public Date getDuedate() {
        return duedate;
    }

    public Date getSubdate() {
        return subdate;
    }

    public String getPath() {
        return path;
    }

    public int getGrade() {
        return grade;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }
    
    
    

        
}
