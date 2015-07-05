package il.ac.hit.project.tags;

import il.ac.hit.project.model.Business;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Building business line in HTML table
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class BusinessLine extends SimpleTagSupport
{
	private boolean admin;
	private Business business = new Business();

	/**
	 * Default constructor.
	 */
	public BusinessLine()
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
	 * @param admin is admin loged in
	 */
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}

	/**
	 * @return business of current line
	 */
	public Business getBusiness()
	{
		return business;
	}

	/**
	 * set business to represent in table line
	 * @param business coupon to represent
	 */
	public void setBusiness(Business business)
	{
		this.business = business;
	}

	/**
	 * crating line that represent business in businesses HTML table
	 */
	@Override
	public void doTag() throws JspException, IOException
	{
		JspWriter out = getJspContext().getOut();
		out.println("<tr>");
		out.println("<td>" + business.getName() + "</td>");
		out.println("<td>");
		if (admin)
		{
			out.println("<button type='button' onclick='showBusinessCoupons(" + business.getId() + ")'>Show</button>");
			out.println("<td>");
			out.println("<button class='btn btn-default btn-info btn-xs' type='button' onclick='removeBusinessClick(" + business.getId() + ")'>");
			out.println("<span class='glyphicon glyphicon-minus'></span>");
			out.println("</button>");
			out.println("<button class='btn btn-default btn-info btn-xs' type='button' onclick='editBusinessClick(" + business.getId() + ")'>");
			out.println("<span class='glyphicon glyphicon-edit'></span>");
			out.println("</button>");
		}
		else
		{
			out.println("<button type='button' onclick='showBusinessCoupons(" + business.getId() + ")'>Show</button>");
		}
		out.println("</td>");
		out.println("</tr>");
	}
}
