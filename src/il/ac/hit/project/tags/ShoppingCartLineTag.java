package il.ac.hit.project.tags;

import il.ac.hit.project.model.ShoppingCartLine;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Building shopping cart line in HTML table
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class ShoppingCartLineTag extends SimpleTagSupport
{
	private ShoppingCartLine line;

	/**
	 * Default constructor.
	 */
	public ShoppingCartLineTag()
	{
		super();
	}
	
	/**
	 * @return shopping cart of current line
	 */
	public ShoppingCartLine getLine()
	{
		return line;
	}

	/**
	 * set shopping cart to represent in table line
	 * @param shopping cart coupon to represent
	 */
	public void setLine(ShoppingCartLine line)
	{
		this.line = line;
	}
	
	/**
	 * crating line that represent shopping cart in shoping cart HTML table
	 */
	@Override
	public void doTag() throws JspException, IOException
	{
		JspWriter out = getJspContext().getOut();
		out.println("<tr>");
		out.println("<td>" + line.getCoupon().getName() + "</td>");
		out.println("<td>");
		out.println("<button class='btn btn-default btn-info btn-xs' type='button' onclick='addToCart(" + line.getCoupon().getId() + ")'>");
		out.println("<span class='glyphicon glyphicon-plus'></span>");
		out.println("</button> <span><strong>" + line.getQuantity() + "</strong></span>");
		out.println("<button class='btn btn-default btn-info btn-xs' type='button' onclick='dropFromCart(" + line.getCoupon().getId() + ")'>");
		out.println("<span class='glyphicon glyphicon-minus'></span>");
		out.println("</button>");
		out.println("</td>");
		out.println("</tr>");
	}
}
