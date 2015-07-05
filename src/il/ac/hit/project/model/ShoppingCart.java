package il.ac.hit.project.model;

import java.util.*;

/**
 * represents shopping cart of coupons
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class ShoppingCart
{

	/**
	 * map of key value-pair of <Coupon, ShoppingCartLine>
	 */
	private Map<Coupon, ShoppingCartLine> lines = new Hashtable<Coupon, ShoppingCartLine>();

	/**
	 * add shopping cart line to shopping cart list
	 * 
	 * @param coupon
	 *            shopping cars line to add
	 */
	public void addShoppingCartLine(Coupon coupon)
	{
		if (lines.get(coupon) == null)
		{
			lines.put(coupon, new ShoppingCartLine(coupon, 1));
		}
		else
		{
			ShoppingCartLine line = lines.get(coupon);
			line.setQuantity(line.getQuantity() + 1);
		}
	}

	/**
	 * remove shopping cart line from shopping cart list
	 * 
	 * @param coupon
	 *            shopping cars line to remove
	 */
	public void removeShoppingCartLine(Coupon coupon)
	{
		ShoppingCartLine line = lines.get(coupon);

		if (line != null)
		{
			if (line.getQuantity() > 1)
			{
				line.setQuantity(line.getQuantity() - 1);
			}
			else
			{
				lines.remove(coupon);
			}
		}
	}

	/**
	 * @return key-value pair of <Coupon, ShoppingCartLine>
	 */
	public Map<Coupon, ShoppingCartLine> getLines()
	{
		return lines;
	}
}
