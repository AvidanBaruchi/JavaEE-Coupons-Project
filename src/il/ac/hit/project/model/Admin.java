package il.ac.hit.project.model;

import javax.persistence.*;

/**
 * represents Admin entity in the hibernate framework
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 *
 */
@Entity
@Table(name = "admins")
public class Admin
{
	/**
	 * represents the admin's id in the data base
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	/**
	 * represents the admin's user name in the data base
	 */
	@Column(name = "username")
	private String username;

	/**
	 * represents the admin's password in the data base
	 */
	@Column(name = "password")
	private String password;

	/**
	 * parameterless constructor
	 */
	public Admin()
	{
	}

	/**
	 * @return the admin's id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * setting the admin's id
	 * 
	 * @param id
	 *            the new id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the admin's user name
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * setting the admin's user name
	 * 
	 * @param username
	 *            the new user name to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the admin's password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * setting the admin's password
	 * 
	 * @param password
	 *            the new password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
}
