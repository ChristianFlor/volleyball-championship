package model;

public class Competitor extends Assistant {
	
	private Competitor next;
	private Competitor previous;
	
	public Competitor(String id, String firstName, String lastName, String email, String gender, String country,
			String pathForPhoto, SimpleDate birthDay) {
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
	
	public int size() {
		int s = 1;
		if(next != null) {
			s+= next.size();
		}
		return s;
	}

}
