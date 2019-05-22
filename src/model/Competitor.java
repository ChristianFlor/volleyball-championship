package model;

public class Competitor extends Assistant {
	// -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
	private Competitor next;
	private Competitor previous;
	// -----------------------------------------------------------------
    // Builder
    // -----------------------------------------------------------------
	
	public Competitor(String id, String firstName, String lastName, String email, String gender, String country,
			String pathForPhoto, SimpleDate birthDay) {
		super(id, firstName, lastName, email, gender, country, pathForPhoto, birthDay);
		
	}
	// -----------------------------------------------------------------
    // Methods Atributes
    // -----------------------------------------------------------------
	
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
