package il.ac.hit.project.model;

/**
 * represents shopping cart line in shopping cart list
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class ShoppingCartLine
{
	/**
	 * represents coupon in shopping cart line
	 */
	private Coupon coupon;
	/**
	 * represents quantity in shopping cart line
	 */
	private int Quantity;

	/**
	 * full parameters constructor
	 * @param coupon sets shopping cart line coupon
	 * @param quantity shopping cart line quantity
	 */
	public ShoppingCartLine(Coupon coupon, int quantity)
	{
		this.coupon = coupon;
		Quantity = quantity;
	}

	/**
	 * @return shopping cart's coupon
	 */
	public Coupon getCoupon()
	{
		return coupon;
	}

	/**
	 * @param coupon sets shopping cart line coupon
	 */
	public void setCoupon(Coupon coupon)
	{
		this.coupon = coupon;
	}

	/**
	 * @return shopping cart's quantity
	 */
	public int getQuantity()
	{
		return Quantity;
	}

	/**
	 * @param quantity sets shopping cart line quantity
	 */
	public void setQuantity(int quantity)
	{
		Quantity = quantity;
	}

}
