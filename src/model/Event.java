package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Event {
	
	public static final String DATA = "data/assistants-data.csv";

	private Espectator root;
	private Competitor first;
	
	public Event() {
		
	}
	
	public void load() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(DATA));
		String line = br.readLine();
		line = br.readLine();
		int counter = 0;
		while(line != null && counter < 10) {
			String[] info = line.split(",");
			Espectator a = new Espectator(info[0], info[1], info[2], info[3], info[4], info[5], info[6], new Date(Long.parseLong(info[7])));
			addEspectator(a);
			line = br.readLine();
			counter++;
		}
		br.close();
	}
	
	public void addEspectator(Espectator a) {
		addEspectator(a, root);
	}

	public void addEspectator(Espectator a, Espectator current) {
		if(root == null) {
			root = a;
		} else {
			if(a.compareTo(current) < 0) {
				if(current.getLeft() == null) {
					current.setLeft(a);
				} else {
					addEspectator(a, current.getLeft());
				}
			} else if(a.compareTo(current) > 0) {
				if(current.getRight() == null) {
					current.setRight(a);
				} else {
					addEspectator(a, current.getRight());
				}
			} else {
				throw new IllegalArgumentException("Two assistants cannot have the same id");
			}
		}
	}
	
	public Espectator searchEspectator(String id, Espectator current) {
		if(current != null) {
			if(current.getId().compareTo(id) > 0) {
				return searchEspectator(id, current.getLeft());
			} else if(current.getId().compareTo(id) < 0) {
				return searchEspectator(id, current.getRight());
			} else {
				return current;
			}
		}
		return null;
	}
	
	public String traverse(Espectator current) {
		String s = "";
		if(current != null) {
			if(current.getLeft() != null) {
				s += traverse(current.getLeft());
			} else {
				return current + traverse(current.getRight());
			}
			s += current;
			if(current.getRight() != null) {
				s += traverse(current.getRight());
			} else {
				return traverse(current.getLeft()) + current;
			}
		}
		return s;
	}
	
	public List<Espectator> inorderListOfEspectators(Espectator current){
		List<Espectator> l = new ArrayList<Espectator>();
		if(current != null) {
			l.addAll(inorderListOfEspectators(current.getLeft()));
			l.add(current);
			l.addAll(inorderListOfEspectators(current.getRight()));
		}
		return l;
	}
	
	public void selectCompetitors() {
		selectCompetitors(root);
	}
	
	public void selectCompetitors(Espectator current) {
		if(current != null) {
			Random r = new Random();
			selectCompetitors(current.getLeft());
			if(r.nextBoolean()) {
				addCompetitor(Assistant.cast(current));
			}
			selectCompetitors(current.getRight());
		}
	}
	
	public void addCompetitor(Competitor c) {
		addCompetitor(c, first);
	}
	
	public void addCompetitor(Competitor c, Competitor current) {
		if(first == null) {
			first = c;
		} else {
			if(current.getNext() == null) {
				current.setNext(c);
				c.setPrevious(current);
			} else {
				addCompetitor(c, current.getNext());
			}
		}
	}
	
	public List<Competitor> listOfCompetitors(){
		List<Competitor> l = new ArrayList<Competitor>();
		Competitor current = first;
		while(current != null) {
			l.add(current);
			current = current.getNext();
		}
		return l;
	}
	
	public Espectator getRoot() {
		return root;
	}

	public void setRoot(Espectator root) {
		this.root = root;
	}

	public Competitor getFirst() {
		return first;
	}

	public void setFirst(Competitor first) {
		this.first = first;
	}
	
}
