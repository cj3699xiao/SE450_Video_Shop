package shop.data;

import java.util.WeakHashMap;

//import VideoObj.ThreeTuple;

/**
 * Implementation of Video interface.
 * 
 * @see Data
 */
final class VideoObj implements Video {
	private final String _title;
	private final int _year;
	private final String _director;
//	private static final WeakHashMap<ThreeTuple<String,Integer,String>, VideoObj> CACHE = new WeakHashMap<>();
////
//	public class ThreeTuple<A, B, C>{
//		public final String first;
//	    public final B second;
//	    public final String third;
//	    
//	     
//	    public ThreeTuple(String a, B b, String c) {
//	    	this.first = a;
//	        this.second = b;
//	        this.third = c;
//	    }
//		
//	}
	/**
	 * Initialize all object attributes. Title and director are "trimmed" to remove
	 * leading and final space.
	 * 
	 * @throws IllegalArgumentException if object invariant violated.
	 */
	VideoObj(String title, int year, String director) {
		// not sure if we still need to check the illegal inputs or not, here has no
		// ToDo
		try {
			title = checkString(title);
			director = checkString(director);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}

		if (year <= 1800 || year >= 5000)
			throw new IllegalArgumentException();

		_title = title;
		_year = year;
		_director = director;
	}
	
//	private VideoObj(ThreeTuple a) {  // try to make it work, but it fails here
//		try {
//			title = checkString(title);
//			director = checkString(director);
//		} catch (IllegalArgumentException e) {
//			throw new IllegalArgumentException();
//		}
//
//		if (year <= 1800 || year >= 5000)
//			throw new IllegalArgumentException();
//
//		_title = title;
//		_year = year;
//		_director = director;
//		//
////		d = id;
////		id ++;
//		
//	}
//	
//	public static VideoObj intern(String title,int year, String director) {
//		
//		synchronized(CACHE) { 
//			return CACHE.computeIfAbsent(a,VideoObj::new);
//		}
//	}

	private String checkString(String stringIn) {
		// if illegal inputs, throw exception
		if (stringIn == null || stringIn.isEmpty())
			throw new IllegalArgumentException();

		if (stringIn.charAt(0) == ' ')
			stringIn = stringIn.substring(1, stringIn.length());

		if (stringIn.isEmpty()) // if after trim becomes illegal
			throw new IllegalArgumentException();

		if (stringIn.charAt(stringIn.length() - 1) == ' ')
			stringIn = stringIn.substring(0, stringIn.length() - 1);

		if (stringIn.isEmpty()) // same as before
			throw new IllegalArgumentException();

		return stringIn;
	}

	public String director() {
		return _director;
	}

	public String title() {
		return _title;
	}

	public int year() {
		return _year;
	}

	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof VideoObj)) {// check the type
			return false;
		}
		VideoObj that = (VideoObj) thatObject;

		return (this.year() == that.year() && this.director().equals(that.director())
				&& this.title().equals(that.title())); // if any not equal, then false
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + _title.hashCode();
		result = 37 * result + _year;
		result = 37 * result + _director.hashCode();

		return result;
	}

	public int compareTo(Video that) {

		int compare = _title.compareTo(that.title());

		if (compare != 0) { // if same compare next field, else return
			return compare;
		}
		compare = _year - that.year();

		if (compare != 0) {
			return compare;
		}
		compare = _director.compareTo(that.director());
		return compare;
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(title());
		s.append(" (");
		s.append(year());
		s.append(") : ");
		s.append(director());

		return s.toString();
	}
}