package com.roborescuemod.commons;

public class Point3D {

	public int x;
	public int y;
	public int z;

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public int hashCode() {
		long bits = java.lang.Double.doubleToLongBits(getX());
		bits ^= java.lang.Double.doubleToLongBits(getY()) * 31;
		return (((int) bits) ^ ((int) (bits >> 32)));
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point3D) {
			Point3D pt = (Point3D) obj;
			return (x == pt.x) && (y == pt.y) && (z == pt.z);
		}
		return super.equals(obj);
	}

	public String toString() {
		return getClass().getName() + "[ " + x + ", " + y + ", " + z + " ]";
	}

	public void update(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}
