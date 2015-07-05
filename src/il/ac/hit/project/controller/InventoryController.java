package il.ac.hit.project.controller;

import il.ac.hit.project.model.Business;
import il.ac.hit.project.model.Coupon;
import il.ac.hit.project.model.ECategory;
import il.ac.hit.project.model.GeoMath;
import il.ac.hit.project.model.InventoryDAO;
import il.ac.hit.project.model.InventoryException;
import il.ac.hit.project.model.ShoppingCart;
import il.ac.hit.project.model.ShoppingCartLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.*;

/**
 * serves as a controller in MVC pattern. Analyzes the requests of the client
 * and handle it. this class uses the @see
 * {@link il.ac.hit.project.model.InventoryDAO} class
 * Extends @see import javax.servlet.http.HttpServlet
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
@WebServlet("/controller/*")
public class InventoryController extends HttpServlet
{
	/**
	 * serial version ID used in serialization
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constant used in calculating one day in milliseconds
	 */
	private final long ONE_DAY_MILL_SEC = 1000 * 60 * 60 * 24;
	/**
	 * The logger class used to save log messages of the activity of the Servlet
	 * 
	 * @see org.apache.log4j.Logger
	 */
	private static final Logger logger = Logger.getLogger(InventoryController.class);

	/**
	 * parameterless constructor that uses the super's constructor
	 */
	public InventoryController()
	{
		super();
	}

	/**
	 * Analyzes the request from the client and handle it. this method uses the
	 * the Dao
	 * 
	 * @param request
	 * @see javax.servlet.http.HttpServletRequest
	 * @param response
	 * @see javax.servlet.http.HttpServletResponse
	 * @exception IOException
	 *                , ServletException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/plain; charset=UTF-8");
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		HttpSession session = null;

		logger.info("recieved GET request: " + path);
		// trying to auto log in the client from existing session
		if (path.endsWith("autologin"))
		{
			session = request.getSession();

			if (session.getAttribute("isAdmin") == null)
			{
				session.setAttribute("isAdmin", new Boolean(false));
			}

			if ((Boolean) session.getAttribute("isAdmin") == true)
			{
				String adminUsername = (String) session.getAttribute("adminUsername");
				dispatcher = getServletContext().getRequestDispatcher("/welcomeadmin.jsp");
				logger.info("Admin auto logedin : " + adminUsername);
			}
			else
			{
				dispatcher = getServletContext().getRequestDispatcher("/loginform.jsp");
			}

			dispatcher.forward(request, response);

		}
		// gets the coupons list from the InventoryDao class and forward to the
		// relevant jsp page
		else if (path.endsWith("coupons"))
		{
			try
			{
				session = request.getSession();
				Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

				if (isAdmin == null)
				{
					isAdmin = false;
				}

				if (isAdmin)
				{
					dispatcher = getServletContext().getRequestDispatcher("/admincouponcontrol.jsp");
				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				}

				request.setAttribute("coupons", InventoryDAO.getInstance().getCoupons());
				logger.info("coupons recieved");
				dispatcher.include(request, response);
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				e.printStackTrace();
			}

		}
		// adding coupon to the data base using the InventoryDAO class
		else if (path.endsWith("addcoupon"))
		{
			try
			{
				// extracting the variables for coupon creation from the request
				String name = request.getParameter("name");
				String description = request.getParameter("des");
				int category = Integer.parseInt(request.getParameter("category"));
				ECategory eCategory = ECategory.values()[category];
				long createdTime = System.currentTimeMillis();
				long daysToAdd = Long.parseLong(request.getParameter("daysToAdd"));
				long expirationTime = createdTime + (ONE_DAY_MILL_SEC * daysToAdd);
				int businessID = Integer.parseInt(request.getParameter("businessID"));
				Business business = InventoryDAO.getInstance().getBusiness(businessID);

				// creating coupon and adding it to the data base
				Coupon coupon = new Coupon(name, description, eCategory, createdTime, expirationTime);
				coupon.setBusiness(business);
				InventoryDAO.getInstance().addCoupon(coupon);

				logger.info("Coupon added :" + coupon.toString());
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// getting the add coupon view component in order to create new coupon
		// for the admin
		else if (path.endsWith("addcouponform"))
		{
			try
			{
				// getting businesses from the DAO to attach coupons to
				// businesses
				request.setAttribute("businesses", InventoryDAO.getInstance().getBusinesses());
				dispatcher = getServletContext().getRequestDispatcher("/addcouponform.jsp");
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				e.printStackTrace();
			}

			dispatcher.forward(request, response);
		}
		// getting the view component needed to edit a coupon from the data base
		else if (path.endsWith("editcouponform"))
		{
			try
			{
				// getting the coupon id from the request
				int couponId = Integer.parseInt(request.getParameter("id"));
				// asking the DAO for the coupon object (by its id)
				request.setAttribute("coupon", InventoryDAO.getInstance().getCoupon(couponId));
				dispatcher = getServletContext().getRequestDispatcher("/editcouponform.jsp");
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				e.printStackTrace();
			}

			dispatcher.forward(request, response);
		}
		// deleting coupon from the data base by its id
		else if (path.endsWith("deletecoupon"))
		{
			try
			{
				Boolean couponDeleted = null;
				int id = Integer.parseInt(request.getParameter("id"));
				couponDeleted = InventoryDAO.getInstance().deleteCoupon(id);

				if (couponDeleted)
				{
					logger.info("Coupon deleted : ID = " + id);
				}
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// edits the coupon that existing in the data base
		else if (path.endsWith("editcoupon"))
		{
			try
			{
				/*
				 * getting all parameters from the request for the edited coupon
				 * and editing the coupon object asked from the DAO then update
				 * the coupon with the DAO
				 */

				int id = Integer.parseInt(request.getParameter("id"));
				Coupon coupon = InventoryDAO.getInstance().getCoupon(id);
				String name = request.getParameter("name");
				String description = request.getParameter("des");
				int daysToAdd = Integer.parseInt(request.getParameter("daysToAdd"));
				long expirationTime = coupon.getExpirationTime() + (ONE_DAY_MILL_SEC * daysToAdd);

				coupon.setName(name);
				coupon.setDescription(description);
				coupon.setExpirationTime(expirationTime);

				InventoryDAO.getInstance().updateCoupon(coupon);
				logger.info("Coupon edited :" + coupon.toString());

			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// adding a chosen coupon to the shopping cart object saved in the
		// session
		else if (path.endsWith("addtocart"))
		{
			try
			{
				/*
				 * getting the coupon id to insert to the shopping cart. then
				 * checks if shopping cart object is existing(if not, creating
				 * one). asking the DAO for coupon object and insert it to the
				 * shopping cart object
				 */
				session = request.getSession();
				int couponId = Integer.parseInt(request.getParameter("id"));

				if (session.getAttribute("cart") == null)
				{
					session.setAttribute("cart", new ShoppingCart());
				}

				ShoppingCart cart = (ShoppingCart) (session.getAttribute("cart"));
				Coupon coupon = InventoryDAO.getInstance().getCoupon(couponId);
				cart.addShoppingCartLine(coupon);
				logger.info("Coupon added to shopping cart :" + coupon.toString());

			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}

		}
		// get current shopping cart from the session and forward it to the
		// relevant jsp
		else if (path.endsWith("shoppingcart"))
		{
			session = request.getSession();
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

			if (isAdmin == null)
			{
				isAdmin = false;
			}

			if (isAdmin)
			{
				request.setAttribute("statistic", ApplicationListener.getAtributesByID());
				dispatcher = getServletContext().getRequestDispatcher("/sessionstatistic.jsp");
			}
			else
			{
				if (session.getAttribute("cart") == null)
				{
					session.setAttribute("cart", new ShoppingCart());
				}

				ShoppingCart cart = (ShoppingCart) (session.getAttribute("cart"));
				Map<Coupon, ShoppingCartLine> couponsInCart = cart.getLines();

				request.setAttribute("cartlist", couponsInCart);
				dispatcher = getServletContext().getRequestDispatcher("/shoppingcart.jsp");
			}

			/*
			 * waits for the response to ends its commission (adding the
			 * attribute) then forward it to the relevant jsp
			 */
			if (!response.isCommitted())
			{
				dispatcher.include(request, response);
			}
		}
		// removes the requested coupon from the shopping cart object
		else if (path.endsWith("removefromcart"))
		{
			try
			{
				session = request.getSession();
				int couponId = Integer.parseInt(request.getParameter("id"));

				if (session.getAttribute("cart") == null)
				{
					session.setAttribute("cart", new ShoppingCart());
				}

				ShoppingCart cart = (ShoppingCart) (session.getAttribute("cart"));
				Coupon coupon = InventoryDAO.getInstance().getCoupon(couponId);
				cart.removeShoppingCartLine(coupon);
				logger.info("Coupon removed from shoping cart :" + coupon.toString());
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// getting the view component in charge of adding new business
		else if (path.endsWith("couponselection"))
		{
			dispatcher = getServletContext().getRequestDispatcher("/couponselection.jsp");
			dispatcher.forward(request, response);
		}
		// getting the view component of coupon selection
		else if (path.endsWith("addbusinessform"))
		{
			dispatcher = getServletContext().getRequestDispatcher("/addbusinessform.jsp");
			dispatcher.forward(request, response);
		}
		// getting the view component of admin functions (add coupon, add
		// business)
		else if (path.endsWith("adminfunctionsform"))
		{
			session = request.getSession();
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

			if (isAdmin == null)
			{
				isAdmin = false;
			}

			if (isAdmin)
			{
				dispatcher = getServletContext().getRequestDispatcher("/adminfunctionsform.jsp");
				dispatcher.forward(request, response);
			}
		}
		//get right form label depending on admin existence
		else if (path.endsWith("rightlabel"))
		{
			session = request.getSession();
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

			if (isAdmin == null)
			{
				isAdmin = false;
			}

			dispatcher = getServletContext().getRequestDispatcher("/rightlabel.jsp");
			dispatcher.forward(request, response);
		}
		// getting the view component of logIn
		else if (path.endsWith("loginform"))
		{
			dispatcher = getServletContext().getRequestDispatcher("/loginform.jsp");
			dispatcher.forward(request, response);
		}
		// getting the view component of session statistic
		else if (path.endsWith("sessionstatistic"))
		{
			session = request.getSession();
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
			request.setAttribute("statistic", ApplicationListener.getAtributesByID());

			if (isAdmin == null)
			{
				isAdmin = false;
			}

			if (isAdmin)
			{
				dispatcher = getServletContext().getRequestDispatcher("/sessionstatistic.jsp");
				dispatcher.include(request, response);
			}
		}
		// getting the view component of session key value pair by session id
		else if (path.endsWith("keyvaluepairs"))
		{
			session = request.getSession();
			Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
			String sessionID = request.getParameter("sessionID");
			List<String> keyValuePairs = ApplicationListener.getAtributesByID().get(sessionID);
			request.setAttribute("keys", keyValuePairs);

			if (isAdmin == null)
			{
				isAdmin = false;
			}

			if (isAdmin)
			{
				dispatcher = getServletContext().getRequestDispatcher("/keyvaluepairstable.jsp");
				dispatcher.forward(request, response);
			}
		}
		// adding new business to the data base using the DAO
		else if (path.endsWith("addbusiness"))
		{
			try
			{
				/*
				 * getting all variables from the request, creating new business
				 * object and inserts it to the data base using DAO
				 */
				String name = request.getParameter("name");
				String location = request.getParameter("location");
				double latitude = Double.parseDouble(request.getParameter("lat"));
				double longitude = Double.parseDouble(request.getParameter("lon"));

				Business business = new Business(name, location, latitude, longitude);
				InventoryDAO.getInstance().addBusiness(business);
				logger.info("Business added :" + business.toString());
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				e.printStackTrace();
				dispatcher.forward(request, response);
			}
		}
		// getting the view component and all businesses from the DAO for the
		// client
		if (path.endsWith("businesses"))
		{
			try
			{
				session = request.getSession();
				Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

				if (isAdmin == null)
				{
					isAdmin = false;
				}

				if (isAdmin)
				{
					dispatcher = getServletContext().getRequestDispatcher("/adminbusinesscontrol.jsp");
				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/businesses.jsp");
				}

				Collection<Business> businesses = InventoryDAO.getInstance().getBusinesses();
				request.setAttribute("businesses", businesses);
				logger.info("businesses recieved");
				dispatcher.include(request, response);
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// getting all coupons of some business from the DAO
		if (path.endsWith("getcouponsbybusiness"))
		{
			try
			{
				Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
				int businessId = Integer.parseInt(request.getParameter("businessId"));
				request.setAttribute("coupons", InventoryDAO.getInstance().getBusiness(businessId).getCoupons());

				if (isAdmin == null)
				{
					isAdmin = false;
				}

				if (isAdmin)
				{
					dispatcher = getServletContext().getRequestDispatcher("/admincouponcontrol.jsp");
				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				}

				if (!response.isCommitted())
				{
					logger.info("coupons from business " + businessId + " recieved");
					dispatcher.forward(request, response);
				}
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// getting all coupons of some category
		if (path.endsWith("getcouponsbycategory"))
		{
			try
			{
				Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
				int categoryID = Integer.parseInt(request.getParameter("category"));
				ECategory category = ECategory.values()[categoryID];
				Collection<Coupon> coupons = InventoryDAO.getInstance().getCouponsByCategory(category.name());
				request.setAttribute("coupons", coupons);

				if (isAdmin == null)
				{
					isAdmin = false;
				}

				if (isAdmin)
				{
					dispatcher = getServletContext().getRequestDispatcher("/admincouponcontrol.jsp");
				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				}

				if (!response.isCommitted())
				{
					logger.info("coupons selected by " + category.name() + " category");
					dispatcher.forward(request, response);
				}
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// delete business from the data base using DAO
		else if (path.endsWith("deletebusiness"))
		{
			try
			{
				Boolean businessDeleted = null;
				int businessId = Integer.parseInt(request.getParameter("businessId"));
				businessDeleted = InventoryDAO.getInstance().deleteBusiness(businessId);

				if (businessDeleted)
				{
					logger.info("Business deleted : ID = " + businessId);
				}
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}
		}
		// edits the business that existing in the data base
		else if (path.endsWith("editbusiness"))
		{
			try
			{
				/*
				 * getting business id from the request, then getting business
				 * object from DAO. editing the business object and updating it
				 * in the data base
				 */
				int id = Integer.parseInt(request.getParameter("id"));
				Business business = InventoryDAO.getInstance().getBusiness(id);
				String name = request.getParameter("name");
				String location = request.getParameter("location");
				double latitude = Double.parseDouble(request.getParameter("lat"));
				double longitude = Double.parseDouble(request.getParameter("lon"));

				business.setName(name);
				business.setLocation(location);
				business.setLatitude(latitude);
				business.setLongitude(longitude);

				InventoryDAO.getInstance().updateBusiness(business);
				logger.info("Business edited :" + business.toString());
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				e.printStackTrace();
				dispatcher.forward(request, response);
			}
		}
		// getting and setting the view component for the admin to edit a
		// business by its ID
		else if (path.endsWith("editbusinessform"))
		{
			try
			{
				int businessId = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("business", InventoryDAO.getInstance().getBusiness(businessId));
				dispatcher = getServletContext().getRequestDispatcher("/editbusinessform.jsp");
				dispatcher.forward(request, response);
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				e.printStackTrace();
				dispatcher.forward(request, response);
			}
		}
		// getting all coupons near current browser geoLocation
		else if (path.endsWith("nearme"))
		{
			try
			{
				session = request.getSession();
				// getting latitude, longitude and wanted radius from the
				// request
				double browserLongitude = Double.parseDouble(request.getParameter("lon"));
				double browserLatitude = Double.parseDouble(request.getParameter("lat"));
				double wantedRadius = Double.parseDouble(request.getParameter("wantedRadius"));

				// checking for admin in order to forward the relevant jsp
				Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");

				// getting all businesses from the DAO
				Collection<Coupon> coupons = new ArrayList<Coupon>();
				List<Business> businesses = (List<Business>) InventoryDAO.getInstance().getBusinesses();

				// creating business comparer by distance
				Comparator<Business> comparator = new Comparator<Business>()
				{
					public int compare(Business business1, Business business2)
					{
						double distance1 = GeoMath.distance(browserLatitude, browserLongitude, business1.getLatitude(), business1.getLongitude());
						double distance2 = GeoMath.distance(browserLatitude, browserLongitude, business2.getLatitude(), business2.getLongitude());
						return (int) (distance1 - distance2);
					}
				};

				Collections.sort(businesses, comparator);

				// creating relevant near me coupons
				for (Business business : businesses)
				{
					if (GeoMath.distance(browserLatitude, browserLongitude, business.getLatitude(), business.getLongitude()) <= wantedRadius)
					{
						coupons.addAll(business.getCoupons());
					}
					else
					{
						break;
					}
				}

				logger.error("Get coupons in the radius :" + wantedRadius);

				if (isAdmin == null)
				{
					isAdmin = false;
				}

				if (isAdmin)
				{
					dispatcher = getServletContext().getRequestDispatcher("/admincouponcontrol.jsp");
				}
				else
				{
					dispatcher = getServletContext().getRequestDispatcher("/coupons.jsp");
				}

				request.setAttribute("coupons", coupons);
				dispatcher.include(request, response);
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				e.printStackTrace();
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * Analyzes the request from the client and handle it. this method uses the
	 * the DAO. this method is in charge of the log in and log out
	 * 
	 * @param request
	 * @see javax.servlet.http.HttpServletRequest
	 * @param response
	 * @see javax.servlet.http.HttpServletResponse
	 * @exception IOException
	 *                , ServletException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = null;
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		boolean isAdmin = false;

		logger.info("recieved POST request: " + path);

		// getting the user name and password from the request and validates it
		// with the DAO
		if (path.endsWith("login"))
		{
			try
			{
				session = request.getSession();
				String username = request.getParameter("username");
				String password = request.getParameter("password");

				if (isAdmin = InventoryDAO.getInstance().authenticate(username, password))
				{
					session.setAttribute("adminUsername", username);
					session.setAttribute("isAdmin", isAdmin);
					logger.info("Admin logedin : " + username);
					dispatcher = request.getRequestDispatcher("/welcomeadmin.jsp");
				}
				else
				{
					dispatcher = request.getRequestDispatcher("/loginform.jsp");
				}

				dispatcher.forward(request, response);
			}
			catch (InventoryException e)
			{
				request.setAttribute("exception", e);
				dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
				logger.error("Exception of type Inventory thrown :" + e.getMessage());
				e.printStackTrace();
				dispatcher.forward(request, response);
			}
		}
		// sets the admin attribute in the session to false
		else if (path.endsWith("logout"))
		{
			session = request.getSession();
			String adminUsername = (String) session.getAttribute("adminUsername");
			session.setAttribute("isAdmin", false);
			logger.info("Admin logedout :" + adminUsername);
			dispatcher = request.getRequestDispatcher("/loginform.jsp");
			dispatcher.forward(request, response);
		}
	}
}
