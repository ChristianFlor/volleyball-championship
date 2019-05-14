package ui;

import java.io.IOException;
import java.util.Date;

import model.Espectator;
import model.Event;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Event e = new Event();
		e.load();
		System.out.println(e.traverse(e.getRoot()));
		Espectator a = new Espectator("123ABCC","Carl", "J.", "cj@gmail.com", "male", "USA", "NoPath", new Date(1557728263));
		e.addEspectator(a);
		System.out.println(e.traverse(e.getRoot()));
		System.out.println("FOUND: " + e.searchEspectator("123ABCC", e.getRoot()));
		System.out.println(e.inorderListOfEspectators(e.getRoot()));
		e.selectCompetitors();
		System.out.println(e.listOfCompetitors());
	}

}
