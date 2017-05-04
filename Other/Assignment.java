/**
 * This class will be used to store the information about assignments 
 * @author   Ataberk Gözkaya
 * @version  1.00  03/05/2017
 */
public class Assignment 
{
	//properties
	private int id;
	private int authorID;
	private int grade;
	
	/**
	 * @param id
	 * @param authorID
	 * @param grade
	 */
	public Assignment( int id, int authorID, int grade )
	{
		this.id       = id;
		this.authorID = authorID;
		setGrade( grade );
	}
	
	/**
	 * @param id
	 * @param authorID
	 */
	public Assignment( int id, int authorID )
	{
		this.id       = id;
		this.authorID = authorID;
	}
	
	/**
	 * @return id
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * @return authorID
	 */
	public int getAuthor()
	{
		return this.authorID;
	}
	
	/**
	 * @return grade
	 */
	public int getGrade()
	{
		return this.grade;
	}
	
	/**
	 * @param grade
	 */
	public void setGrade( int grade )
	{
		this.grade = grade;
	}	
}
