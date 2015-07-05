package il.ac.hit.project.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

/**
 * listening to session and session Attribute
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
@WebListener
public class ApplicationListener implements HttpSessionIdListener, HttpSessionAttributeListener, HttpSessionListener
{
	private static Map<String, List<String>> atributesByID = new Hashtable<String, List<String>>();
	

	public ApplicationListener()
	{
		super();
	}

	/**
	 * creating new key in the hash table
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent sessionEvent)
	{
		HttpSession session = sessionEvent.getSession();
		String sessionID = session.getId();
		Collection<String> atributesNames = Collections.list(session.getAttributeNames());
		atributesByID.put(sessionID, new ArrayList<String>(atributesNames));	
	}

	/**
	 * changing key id the session id been changed
	 * @see HttpSessionIdListener#sessionIdChanged(HttpSessionEvent, String)
	 */
	public void sessionIdChanged(HttpSessionEvent sessionEvent, String oldSessionID)
	{
		HttpSession session = sessionEvent.getSession();
		List<String> tempValueList = atributesByID.remove(oldSessionID);
		atributesByID.put(session.getId(), tempValueList);
	}

	/**
	 * session destroyed not implemented to save all attribute data
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0)
	{
	}

	/**
	 * adding attribute to existing session
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent sessionEvent)
	{
		String sessionID = sessionEvent.getSession().getId();
		List<String> keysList = atributesByID.get(sessionID);
		if (keysList != null)
		{
			keysList.add(sessionEvent.getName());
		}	
	}

	/**
	 * attribute removed not implemented to save all attribute data
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent sessionEvent)
	{
	}

	/**
	 * * attribute replaced not implemented to save all attribute data
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0)
	{
	}

	public static Map<String, List<String>> getAtributesByID()
	{
		return atributesByID;
	}
}
