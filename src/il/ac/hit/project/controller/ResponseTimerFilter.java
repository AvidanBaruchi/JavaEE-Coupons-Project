package il.ac.hit.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * filter relevant request from the client in order to calculate server response
 * time in milliseconds and print the result to the client
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
@WebFilter(filterName = "Timer", urlPatterns = { "/controller/coupons", "/controller/admincouponcontrol", "/controller/businesses", "/controller/adminbusinesscontrol", "/controller/nearme", "/controller/shoppingcart", "/controller/sessionstatistic" }, initParams = {})
public class ResponseTimerFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public ResponseTimerFilter()
	{
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
	}

	/**
	 * representing how much time passed from the moment the request arrived to
	 * the moment the response send back
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();
		long startTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis() - startTime;
		out.println("<br/>");
		out.println("<h6>Loaded in " + endTime + " milleseconds</h6>");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
	}

}
