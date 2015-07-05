package il.ac.hit.project.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import il.ac.hit.project.model.*;

import org.junit.Test;

/**
 * test inventory DAO with hibernate framework functionality
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class InventoryDAOTest
{
	/**
	 * testing coupons functionality:
	 * 
	 * - adding a dummy coupon to the data base and verifying that the coupon
	 * added to inventory DAO
	 * 
	 * - edit a dummy coupon and verifying that the coupon updated in the
	 * inventory DAO
	 * 
	 * - delete a dummy coupon and verifying that the coupon deleted from the
	 * inventory DAO
	 * 
	 * while testing the add, edit, delete functionality we can verify the get
	 * all coupons functionality
	 */
	@Test
	public void testCouponFunctionality()
	{
		try
		{
			IInventoryDAO inventory = InventoryDAO.getInstance();
			ArrayList<Coupon> coupons = new ArrayList<Coupon>(inventory.getCoupons());

			// get coupon list size before adding new coupon
			int beforeAddingTestCouponLength = coupons.size();

			// create dummy coupon
			long time = System.currentTimeMillis();
			Coupon testCoupon = new Coupon("test", "JUnitTest", ECategory.JClothes, time, 0);
			Business business = inventory.getBusinesses().iterator().next();
			testCoupon.setBusiness(business);

			// add dummy coupon
			inventory.addCoupon(testCoupon);
			// get coupon list size after adding new coupon
			coupons = new ArrayList<Coupon>(inventory.getCoupons());
			int afterAddingTestCouponLength = coupons.size();

			// test if after adding coupon to the data base the size of coupon
			// is before adding test coupon + 1
			assertEquals(beforeAddingTestCouponLength + 1, afterAddingTestCouponLength);
			Coupon couponAfterAdding = coupons.get(coupons.size() - 1);

			// test if this coupon were added at the time we remembered
			assertEquals(time, couponAfterAdding.getCreatedTime());

			// edit coupon test
			Coupon couponBeforeUpdating = couponAfterAdding;
			couponBeforeUpdating.setName("test(AfterUpdate");
			couponBeforeUpdating.setDescription("JUnitTest(AfterUpdate");
			Coupon couponAfterUpdating = inventory.getCoupon(couponBeforeUpdating.getId());

			// test if the coupons are not equal after edit
			assertNotEquals(couponBeforeUpdating, couponAfterUpdating);

			// delete dummy coupon;
			// get coupon list size before deleting existing coupon
			int beforeDeletingTestCouponLength = coupons.size();
			inventory.deleteCoupon(couponAfterAdding.getId());
			coupons = new ArrayList<Coupon>(inventory.getCoupons());
			// get coupon list size after deleting coupon from the data base
			int afterDeletingTestCouponLength = coupons.size();

			// test if after deleting coupon from the data base the size of
			// coupons list is smaller by 1
			assertEquals(beforeDeletingTestCouponLength - 1, afterDeletingTestCouponLength);
		}
		catch (InventoryException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
