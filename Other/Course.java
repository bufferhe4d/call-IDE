/**
 * Desc:     This class will be used to store the information about courses
 * @author   Ataberk Gözkaya
 * @version  1.00 03/05/2017
 */
public class Course 
{
	//properties
	private int    id;
	private String name;
	private int    instructorID;
	private String classKey;
	
	
	/**
	 * @param id
	 * @param name
	 * @param instructorID
	 * @param classKey
	 */
	public Course( int id, String name, int instructorID, String classKey )
	{
		this.id           = id;
		this.name         = name;
		this.instructorID = instructorID;
		setClassKey(classKey);
	}
	
	/**
	 * @return id 
	 */
	public int getID()
	{
		return this.id;
	}
	
	/**
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * This method create a class key
	 * @param classKey
	 */
	public void setClassKey( String classKey )
	{
		this.classKey = classKey;
	}
	
	/**
	 * @return classKey
	 */
	public String getClassKey()
	{
		return this.classKey;
	}
	
	/**
	 * @return instructorID
	 */
	public int getInstructor()
	{
		return this.instructorID;
	}
}
