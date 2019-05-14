package model;

import java.util.Date;

public class Competitor extends Assistant {
	
	private Competitor next;
	private Competitor previous;
	
	public Competitor(String id, String firstName, String lastName, String email, String gender, String country,
			String pathForPhoto, Date birthDay) {
		super(id, firstName, lastName, email, gender, country, pathForPhoto, birthDay);
		
	}
	
	public Competitor getNext() {
		return next;
	}

	public void setNext(Competitor next) {
		this.next = next;
	}

	public Competitor getPrevious() {
		return previous;
	}

	public void setPrevious(Competitor previous) {
		this.previous = previous;
	}


}
