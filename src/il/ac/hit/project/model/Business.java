package il.ac.hit.project.model;

import java.util.Collection;

import javax.persistence.*;

/**
 * represents Business entity in the hibernate framework
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
@Entity
@Table(name = "businesses")
public class Business
{
	/**
	 * represents the business's id
	 */
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	/**
	 * represents the business's name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * represents the business's location
	 */
	@Column(name = "location")
	private String location;

	/**
	 * represents the business's geo-location latitude
	 */
	@Column(name = "latitude")
	private double latitude;

	/**
	 * represents the business's geo-location longitude
	 */
	@Column(name = "longitude")
	private double longitude;

	/**
	 * represents the business's offered coupons
	 */
	@OneToMany(mappedBy = "business", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Coupon> coupons;

	/**
	 * parameterless constructor
	 */
	public Business()
	{
	}

	/**
	 * constructor using some of the fields
	 * 
	 * @param name
	 *            sets the business name
	 * @param location
	 *            sets the business location
	 * @param latitude
	 *            sets the business geo-location latitude
	 * @param longitude
	 *            sets the business geo-location longitude
	 */
	public Business(String name, String location, double latitude, double longitude)
	{
		super();
		this.name = name;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * @return the business's id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the new id for the business
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the business's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the new name for the business
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the business's location
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * @param location
	 *            the new location name for the business
	 */
	public void setLocation(String location)
	{
		this.location = location;
	}

	/**
	 * @return the business's geo-location latitude
	 */
	public double getLatitude()
	{
		return latitude;
	}

	/**
	 * @param latitude
	 *            the new geo-location latitude for the business
	 */
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	/**
	 * @return the business's geo-location longitude
	 */
	public double getLongitude()
	{
		return longitude;
	}

	/**
	 * @param latitude
	 *            the new geo-location longitude for the business
	 */
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * 
	 * @return the collection of coupons offered by the business
	 */
	public Collection<Coupon> getCoupons()
	{
		return coupons;
	}

	/**
	 * @param coupons
	 *            sets the new collection of coupons offered by the business
	 */
	public void setCoupons(Collection<Coupon> coupons)
	{
		this.coupons = coupons;
	}

	/**
	 * String representation of the business
	 */
	@Override
	public String toString()
	{
		return "Business [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
}
