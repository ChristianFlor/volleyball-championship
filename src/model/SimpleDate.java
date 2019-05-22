package model;

import java.util.Date;

public class SimpleDate extends Date {
	
	// -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
	private static final long serialVersionUID = 1L;
	// -----------------------------------------------------------------
    // Builder
    // -----------------------------------------------------------------
	public SimpleDate(long date) {
		super(date);
		// TODO Auto-generated constructor stub
	}
	// -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	
	@Override
	public String toString() {
		String[] date = super.toString().split(" ");
		return date[1] + " " + date[2] + " " + date[5];
	}
	
}
