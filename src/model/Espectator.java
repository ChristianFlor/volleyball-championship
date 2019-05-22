package model;

public class Espectator extends Assistant {

	private Espectator left;
	private Espectator right;
	
	public Espectator(String id, String firstName, String lastName, String email, String gender, String country,
			String pathForPhoto, SimpleDate birthDay) {
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
	
	public void replaceData(Espectator esp) {
		super.setFirstName(esp.getFirstName());
		super.setBirthDay(esp.getBirthDay());
		super.setCountry(esp.getCountry());
		super.setEmail(esp.getEmail());
		super.setGender(esp.getGender());
		super.setId(esp.getId());
		super.setLastName(esp.getLastName());
		super.setPathForPhoto(esp.getPathForPhoto());
		super.setX(esp.getX());
		super.setY(esp.getY());
	}
	
	public int getWeight() {
		int weight = 1;
		if(left != null) {
			weight += left.getWeight();
		}
		if(right != null) {
			weight += right.getWeight();
		}
		return weight;
	}

}
