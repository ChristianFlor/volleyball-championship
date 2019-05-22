package model;

public class Espectator extends Assistant {
	// -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	private Espectator left;
	private Espectator right;
	// -----------------------------------------------------------------
    // Builder
    // -----------------------------------------------------------------
	public Espectator(String id, String firstName, String lastName, String email, String gender, String country,
			String pathForPhoto, SimpleDate birthDay) {
		super(id, firstName, lastName, email, gender, country, pathForPhoto, birthDay);
		
	}
	// -----------------------------------------------------------------
    // Methods Atributes
    // -----------------------------------------------------------------
	
	public Espectator getLeft() {
		return left;
	}

	public void setLeft(Espectator left) {
		this.left = left;
	}

	public Espectator getRight() {
		return right;
	}

	public void setRight(Espectator right) {
		this.right = right;
	}

}
