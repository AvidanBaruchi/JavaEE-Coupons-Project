package il.ac.hit.project.tags;

import il.ac.hit.project.model.Coupon;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Building coupon line in HTML table
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class CouponLine extends SimpleTagSupport
{
	private Coupon coupon;
	private boolean admin;
	private static final Date date = new Date();
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat();

	/**
	 * Default constructor.
	 */
	public CouponLine()
	{
		super();
	}

	/**
	 * @return if admin connected
	 */
	public boolean isAdmin()
	{
		return admin;
	}

	/**
	 * setting what tag to create by existent of a admin
	 * 
	 * @param admin
	 *            is admin loged in
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	/**
	 * @return coupon of current line
	 */
	public Coupon getCoupon()
	{
		return coupon;
	}

	/**
	 * set coupon to represent in table line
	 * 
	 * @param coupon
	 *            coupon to represent
	 */
	public void setCoupon(Coupon coupon)
	{
		this.coupon = coupon;
	}

	/**
	 * crating line that represent coupon in coupons HTML table
	 */
	@Override
	public void doTag() throws JspException, IOException
	{
		JspWriter out = getJspContext().getOut();
		dateFormat.applyPattern("dd-MM-yyyy EEE HH:mm");
		date.setTime(coupon.getExpirationTime());

		out.println("<tr>");
		out.println("<td>" + coupon.getName() + "</td>");
		out.println("<td>" + coupon.getDescription() + "</td>");
		out.println("<td>" + dateFormat.format(date) + "</td>");
		if (admin)
		{
			out.println("<td>" + coupon.isExpired() + "</td>");
		}
		else
		{
			out.println("<td>" + coupon.getBusiness().getLocation() + "</td>");
		}
		out.println("<td>" + coupon.getBusiness().getName() + "</td>");
		out.println("<td>");
		if (admin)
		{
			out.println("<button class='btn btn-default btn-info btn-xs' type='button' onclick='removeCuponClick(" + coupon.getId() + ")'>");
			out.println("<span class='glyphicon glyphicon-minus'></span>");
			out.println("</button>");
			out.println("<button class='btn btn-default btn-info btn-xs' type='button' onclick='editCuponClick(" + coupon.getId() + ")'>");
			out.println("<span class='glyphicon glyphicon-edit'></span>");
			out.println("</button>");

		}
		else
		{
			out.println("<button type='button' onclick='addToCart(" + coupon.getId() + ")'>add to cart</button>");
		}
		out.println("</td>");
		out.println("</tr>");
	}
}
