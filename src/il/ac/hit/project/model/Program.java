package il.ac.hit.project.model;

public class Program
{
	public static void main(String[] args)
	{
		/*
		IInventoryDAO dao = InventoryDAO.getInstance();

		//try
		//{
			long bla = new Timestamp().getDateTime();
			// addCouponTest(dao);
			// getCouponsTest(dao);
			//deleteCouponTest(dao);
			// getCouponTest(dao);
			
			System.out.println(bla);
			Timestamp jaa = new Timestamp(bla);
			System.out.println(new Date(jaa.getDateTime()));
			//System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(jaa));
		//}
		//catch (CouponException e)
		//{
			//e.printStackTrace();
		//}*/
	}
/*
	private static void addCouponTest(IInventoryDAO dao) throws InventoryException
	{
		Coupon temoCoupon1 = new Coupon(98765, "myCpoun", "blalblabla");
		Coupon temoCoupon2 = new Coupon(12543, "hit las falafelia", "get 2 falalfes for 10shekels");

		dao.addCoupon(temoCoupon1);
		dao.addCoupon(temoCoupon2);
	}

	private static void getCouponsTest(IInventoryDAO dao) throws HibernateException, InventoryException
	{
		Collection collection = dao.getCoupons();
		List<Coupon> coupons = new ArrayList(collection);

		System.out.println("There are " + coupons.size() + " coupon(s)");

		Iterator iterator = coupons.iterator();

		while (iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}

	private static void deleteCouponTest(IInventoryDAO dao) throws InventoryException
	{
		
		int id = 32132;
		dao.deleteCoupon(id);
		if (temp != null)
			System.out.println(temp.toString());

	}

	private static void getCouponTest(IInventoryDAO dao) throws InventoryException
	{
		Coupon coupon = dao.getCoupon(1234);
	}
	*/
}

