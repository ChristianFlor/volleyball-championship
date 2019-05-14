package model;

import java.util.Date;

public class Espectator extends Assistant {

	private Espectator left;
	private Espectator right;
	
	public Espectator(String id, String firstName, String lastName, String email, String gender, String country,
			String pathForPhoto, Date birthDay) {
		super(id, firstName, lastName, email, gender, country, pathForPhoto, birthDay);
		
	}

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
