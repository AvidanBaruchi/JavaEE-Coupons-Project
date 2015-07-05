package il.ac.hit.project.model;

import java.util.*;

/**
 * describes an inventory data access object
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public interface IInventoryDAO
{
	/**
	 * adding new coupon to the data base
	 * 
	 * @param coupon
	 *            the coupon to add
	 * @throws InventoryException
	 *             when adding coupon is failed
	 */
	public void addCoupon(Coupon coupon) throws InventoryException;

	/**
	 * gets all coupons from the data base
	 * 
	 * @return a collection of coupons
	 * @throws InventoryException
	 *             when getting coupons fails
	 */
	public Collection<Coupon> getCoupons() throws InventoryException;

	/**
	 * get coupon from the data base
	 * 
	 * @param id
	 *            of wanted coupon
	 * @return wanted coupon
	 * @throws InventoryException
	 *             when getting coupon fails
	 */
	public Coupon getCoupon(int id) throws InventoryException;

	/**
	 * delete coupon from the data base
	 * 
	 * @param id
	 *            of wanted coupon
	 * @return if operation succeeded
	 * @throws InventoryException
	 *             when deleting coupon fails
	 */
	public boolean deleteCoupon(int id) throws InventoryException;

	/**
	 * update coupon from the data base
	 * 
	 * @param coupon
	 *            to update
	 * @return if operation succeeded
	 * @throws InventoryException
	 *             when update coupon fails
	 */
	public boolean updateCoupon(Coupon coupon) throws InventoryException;

	/**
	 * add business to the data base
	 * 
	 * @param business
	 *            to add to data base
	 * @throws InventoryException
	 *             when getting business fails
	 */
	public void addBusiness(Business business) throws InventoryException;

	/**
	 * gets all businesses from the data base
	 * 
	 * @return businesses collection
	 * @throws InventoryException
	 *             when getting businesses fails
	 */
	public Collection<Business> getBusinesses() throws InventoryException;

	/**
	 * get business from the data base
	 * 
	 * @param id
	 *            of wanted business
	 * @return wanted business
	 * @throws InventoryException
	 *             when getting business fails
	 */
	public Business getBusiness(int id) throws InventoryException;

	/**
	 * delete business from the data base
	 * 
	 * @param id
	 *            of wanted business to delete
	 * @return if operation succeeded
	 * @throws InventoryException
	 *             when deleting business fails
	 */
	public boolean deleteBusiness(int id) throws InventoryException;

	/**
	 * delete business from the data base
	 * 
	 * @param business
	 *            of wanted business to update
	 * @return if operation succeeded
	 * @throws InventoryException
	 *             when updating business fails
	 */
	public boolean updateBusiness(Business business) throws InventoryException;

	/**
	 * try to log in as inventory admin
	 * 
	 * @param userName
	 *            admin's user name
	 * @param password
	 *            admin's password
	 * @return if logging in succeeded
	 * @throws InventoryException
	 */
	public boolean authenticate(String userName, String password) throws InventoryException;
}
