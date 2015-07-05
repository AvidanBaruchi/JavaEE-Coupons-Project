package il.ac.hit.project.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Implementation of inventory DAO interface with hibernate framework.
 * Implementing the singleton design pattern.
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class InventoryDAO implements IInventoryDAO
{
	/**
	 * factory of sessions with the hibernate framework
	 */
	private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
	/**
	 * represents singleton instance in coupon inventory
	 */
	private static InventoryDAO instance = null;

	/**
	 * creating instance of the object if not already exist
	 * 
	 * @return singleton instance in coupon inventory
	 */
	public static InventoryDAO getInstance()
	{
		if (instance == null)
		{
			instance = new InventoryDAO();
		}
		return instance;
	}

	/**
	 * parameterless private constructor
	 */
	private InventoryDAO()
	{
	}

	/**
	 * get session from the session factory
	 * 
	 * @return hibernate framework session
	 * @throws InventoryException
	 *             when creating session is failed
	 */
	private Session getSession() throws InventoryException
	{
		Session session = null;

		try
		{
			session = factory.openSession();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not open session" + e.getMessage());
		}

		return session;
	}

	/**
	 * using hibernate framework to add coupon to SQL data base
	 */
	@Override
	public void addCoupon(Coupon coupon) throws InventoryException
	{
		Session session = getSession();

		try
		{
			session.beginTransaction();
			session.save(coupon);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not add coupon: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	/**
	 * using hibernate framework to get coupons collection from SQL data base
	 */
	@Override
	public Collection<Coupon> getCoupons() throws InventoryException
	{
		Session session = getSession();
		List<Coupon> coupons;

		try
		{
			session.beginTransaction();
			Query query = session.createQuery("from Coupon");
			coupons = (List<Coupon>) query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not fetch coupons: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return coupons;
	}

	/**
	 * using hibernate framework to get coupon from SQL data base
	 */
	@Override
	public Coupon getCoupon(int id) throws InventoryException
	{
		Session session = getSession();
		Collection<Coupon> coupons;

		try
		{
			session.beginTransaction();
			Query query = session.createQuery("from Coupon WHERE id = :id");
			query.setInteger("id", id);
			coupons = (Collection<Coupon>) query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not get coupon: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return coupons.iterator().next();
	}

	/**
	 * using hibernate framework to delete coupon from SQL data base
	 */
	@Override
	public boolean deleteCoupon(int id) throws InventoryException
	{
		Session session = getSession();
		boolean answer = false;

		try
		{
			session.beginTransaction();
			Query query;

			query = session.createQuery("delete from Coupon where id = :id");
			query.setInteger("id", id);
			query.executeUpdate();
			session.getTransaction().commit();
			answer = true;
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not delete coupon: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return answer;
	}

	/**
	 * using hibernate framework to update coupon in SQL data base
	 */
	@Override
	public boolean updateCoupon(Coupon coupon) throws InventoryException
	{
		Session session = getSession();
		boolean answer = false;

		try
		{
			session.beginTransaction();
			session.update(coupon);
			session.getTransaction().commit();
			answer = true;
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not update coupon: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return answer;
	}

	/**
	 * using hibernate framework to add business to SQL data base
	 */
	@Override
	public void addBusiness(Business business) throws InventoryException
	{
		Session session = getSession();

		try
		{
			session.beginTransaction();
			session.save(business);
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not add business: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public Collection<Business> getBusinesses() throws InventoryException
	{
		Session session = getSession();
		List<Business> business;

		try
		{
			session.beginTransaction();
			Query query = session.createQuery("from Business");
			business = (List<Business>) query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not get Businesses: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return business;
	}

	/**
	 * using hibernate framework to get business from SQL data base
	 */
	@Override
	public Business getBusiness(int id) throws InventoryException
	{
		Session session = getSession();
		Collection<Business> businesses;

		try
		{
			session.beginTransaction();
			businesses = session.createQuery("from Business WHERE id = " + id).list();
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not get Business: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return businesses.iterator().next();
	}

	/**
	 * using hibernate framework to delete business from SQL data base
	 */
	@Override
	public boolean deleteBusiness(int id) throws InventoryException
	{
		Session session = getSession();
		boolean answer = false;

		try
		{
			session.beginTransaction();
			// Query query =
			// session.createQuery("delete from Business WHERE id = :id");
			session.delete(getBusiness(id));
			// query.setInteger("id", id);
			// query.executeUpdate();
			session.getTransaction().commit();
			answer = true;
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not delete business: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return answer;
	}

	/**
	 * using hibernate framework to updte business in SQL data base
	 */
	@Override
	public boolean updateBusiness(Business business) throws InventoryException
	{
		Session session = getSession();
		boolean answer = false;

		try
		{
			session.beginTransaction();
			session.update(business);
			session.getTransaction().commit();
			answer = true;
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not update business: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return answer;
	}

	/**
	 * using hibernate framework to authenticate admin existence in SQL data
	 * base
	 */
	@Override
	public boolean authenticate(String userName, String password) throws InventoryException
	{
		Session session = getSession();
		boolean answer = false;
		Collection<Admin> admins;
		byte[] bytesOfMessage = null;
		MessageDigest md = null;
		String passwordMD5 = null;

		try
		{
			// converting password string to md5 encryption
			bytesOfMessage = password.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			md.update(bytesOfMessage);
			bytesOfMessage = md.digest();
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < bytesOfMessage.length; i++)
			{
				String hex = Integer.toHexString(0xff & bytesOfMessage[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			passwordMD5 = hexString.toString();

			session.beginTransaction();
			Query query = session.createQuery("from Admin WHERE username = :username");
			query.setString("username", userName);
			admins = (Collection<Admin>) query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException | UnsupportedEncodingException | NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not authenticate user: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		if (admins != null)
		{
			for (Admin admin : admins)
			{
				if (admin.getPassword().equals(passwordMD5))
				{
					answer = true;
					break;
				}
			}
		}

		return answer;
	}

	/**
	 * using hibernate framework to get coupons by category from SQL data base
	 */
	public Collection<Coupon> getCouponsByCategory(String category) throws InventoryException
	{
		Session session = getSession();
		List<Coupon> coupons;

		try
		{
			session.beginTransaction();
			Query query = session.createQuery("from Coupon WHERE category = :category");
			query.setString("category", category);
			coupons = (List<Coupon>) query.list();
			session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
			e.printStackTrace();
			throw new InventoryException("Could not fetch coupons: " + e.getMessage());
		}
		finally
		{
			if (session != null)
			{
				session.close();
			}
		}

		return coupons;
	}
}
