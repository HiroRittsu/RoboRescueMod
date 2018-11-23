package commons;

public class Point3Df {

	public double x;
	public double y;
	public double z;

	public Point3Df(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void resetPoint(String s) {
		if (s == "MAX") {
			x = Double.MAX_VALUE;
			y = Double.MAX_VALUE;
			z = Double.MAX_VALUE;
		} else if (s == "MIN") {
			x = Double.MIN_VALUE;
			y = Double.MIN_VALUE;
			z = Double.MIN_VALUE;
		}
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	public int hashCode() {
		long bits = java.lang.Double.doubleToLongBits(getX());
		bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
		return (((int) bits) ^ ((int) (bits >> 32)));
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point3Df) {
			Point3Df pt = (Point3Df) obj;
			return (x == pt.x) && (y == pt.y) && (z == pt.z);
		}
		return super.equals(obj);
	}

	public String toString() {
		return getClass().getName() + "[ " + x + ", " + y + ", " + z + " ]";
	}

	public void update(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
