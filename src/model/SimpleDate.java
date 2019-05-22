package model;

import java.util.Date;

public class SimpleDate extends Date {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleDate(long date) {
		super(date);
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("deprecation")
	public SimpleDate(int month, int day, int year) {
		super(year, month, day);
	}
	@Override
	public String toString() {
		String[] date = super.toString().split(" ");
		return date[1] + " " + date[2] + " " + date[5];
	}
	
}
