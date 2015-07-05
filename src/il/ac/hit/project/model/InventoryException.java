package il.ac.hit.project.model;

/**
 * Extension of Exception class
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 */
public class InventoryException extends Exception
{
	/**
	 * serial version ID used in serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * one parameter contractor
	 * 
	 * @param str
	 *            InventoryException exception string to pass to the Exception
	 *            class
	 */
	public InventoryException(String str)
	{
		super(str);
	}
}
