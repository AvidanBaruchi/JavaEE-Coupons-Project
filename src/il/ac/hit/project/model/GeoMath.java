package il.ac.hit.project.model;

/**
 * provides geoLocation calculations
 * 
 * @author Igor Shur, Avidan Baruchi. H.I.T
 *
 */
public class GeoMath
{

	/**
	 * calculates the distance in kilometers between two geoLocation points
	 * 
	 * @param lat1
	 *            is the latitude of the first geo-location point
	 * @param lon1
	 *            is the longitude of the first geo-location point
	 * @param lat2
	 *            is the latitude of the second geo-location point
	 * @param lon2
	 *            is the longitude of the second geo-location point
	 * @return the distance in kilometers
	 */
	public static double distance(double lat1, double lon1, double lat2, double lon2)
	{

		double theta = lon1 - lon2;

		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		return (dist);
	}

	/**
	 * convert radiant number to degrees
	 * 
	 * @param rad
	 *            the number in radiant
	 * @return the degree represented by the radiant number
	 */
	private static double rad2deg(double rad)
	{
		return (rad * 180 / Math.PI);
	}

	/**
	 * convert degrees number to radiant
	 * 
	 * @param deg
	 *            the number in degrees
	 * @return the radiant represented by the degrees number
	 */
	private static double deg2rad(double deg)
	{
		return (deg * Math.PI / 180.0);
	}
}
