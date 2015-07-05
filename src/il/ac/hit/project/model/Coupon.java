package il.ac.hit.project.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * represents Coupon entity in the hibernate framework
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
@Entity
@Table(name = "coupons")
public class Coupon implements Serializable
{
	/**
	 * serial version ID used in serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * represents the coupon's id
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	/**
	 * represents the coupon's name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * represents the coupon's description
	 */
	@Column(name = "description")
	private String description;

	/**
	 * represents the coupon's category
	 */
	@Column(name="category") 
	@Enumerated(EnumType.STRING)
	private ECategory category;

	/**
	 * represents the coupon's creation time
	 */
	@Column(name = "createdTime")
	private long createdTime;

	/**
	 * represents the coupon's expiration time
	 */
	@Column(name = "expirationTime")
	private long expirationTime;

	/**
	 * represents the coupon's business
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_id", referencedColumnName = "ID", nullable = false)
	private Business business;

	/**
	 * constructor using some of the fields
	 * 
	 * @param name
	 *            sets the coupon's id
	 * @param description
	 *            sets the coupon's description
	 * @param createdTime
	 *            sets the coupon's creation time
	 * @param expirationTime
	 *            sets the coupon's expiration time
	 */
	public Coupon(String name, String description, ECategory category, long createdTime, long expirationTime)
	{
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.createdTime = createdTime;
		this.expirationTime = expirationTime;
	}

	/**
	 * parameterless constructor
	 */
	public Coupon()
	{
	}

	/**
	 * @return boolean expiration status
	 */
	public boolean isExpired()
	{
		boolean answer = false;

		// checks the coupon's expiration time with the current system time
		if (System.currentTimeMillis() > expirationTime)
		{
			answer = true;
		}

		return answer;
	}

	/**
	 * @return the coupon's id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the new cupon's id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the coupon's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the new cupon's name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the coupon's description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the new coupon's description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return coupon's category
	 */
	public ECategory getCategory()
	{
		return category;
	}

	/**
	 * @param category the new coupon's category
	 */
	public void setCategory(ECategory category)
	{
		this.category = category;
	}

	/**
	 * @return coupon's creation time
	 */
	public long getCreatedTime()
	{
		return createdTime;
	}

	/**
	 * @param createdTime
	 *            the new coupon's creation time
	 */
	public void setCreatedTime(long createdTime)
	{
		this.createdTime = createdTime;
	}

	/**
	 * @return the coupon's expiration time
	 */
	public long getExpirationTime()
	{
		return expirationTime;
	}

	/**
	 * @param expirationTime
	 *            the new coupon's expiration time
	 */
	public void setExpirationTime(long expirationTime)
	{
		this.expirationTime = expirationTime;
	}

	/**
	 * @return the coupon's @see {@link il.ac.hit.project.model.Business} object
	 */
	public Business getBusiness()
	{
		return business;
	}

	/**
	 * @param business
	 *            the new coupon's business object
	 */
	public void setBusiness(Business business)
	{
		this.business = business;
	}

	/**
	 * String representation of the coupon
	 */
	@Override
	public String toString()
	{
		return "Coupon [id=" + id + ", name=" + name + ", description=" + description + ", createdTime=" + createdTime + ", expirationTime=" + expirationTime + "]";
	}

	/**
	 * generates coupon's hash code
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(id);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * checks for coupons equality
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (id != other.id)
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(id) != Double.doubleToLongBits(other.id))
			return false;
		return true;
	}
}
