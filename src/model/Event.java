package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event {
	
	//public static final String DATA = "data/assistants-data.csv";

	private Espectator root;
	private Competitor first;
	private double width;
	private double height;
	public static final double INCREASEMENT = 90.0;
	
	public Event() {
		
	}
	
	public void load(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		line = br.readLine();
		int limit = 0;
		while(line != null && limit < 20) {
			String[] info = line.split(",");
			Espectator a = new Espectator(info[0], info[1], info[2], info[3], info[4], info[5], info[6], new SimpleDate(Long.parseLong(info[7])));
			addEspectator(a);
			line = br.readLine();
			limit++;
		}
		br.close();
	}
	public void assignePositions() {
		assignePositions(root, 0, this.getWidth(), 0, this.getHeight() / this.getTreeHeight());
	}
	
	private void assignePositions(Espectator current, double xMin, double xMax, double yMin, double yMax) {
		current.setX((xMin + xMax) / 2);
		current.setY(yMin + yMax / 2);
		if(current.getLeft() != null) {
			assignePositions(current.getLeft(), xMin, (xMin + xMax) / 2, yMin + yMax, yMax);
		}
		if(current.getRight() != null) {
			assignePositions(current.getRight(), (xMin + xMax) / 2, xMax, yMin + yMax, yMax);
		}
	}
	public Espectator min(Espectator current) {
		if(current.getLeft() != null) {
			return min(current.getLeft());
		}
		return current;
	}
	
	public Espectator max(Espectator current) {
		if(current.getRight() != null) {
			return min(current.getRight());
		}
		return current;
	}
	@Override
	public String toString() {
		return printNodes(root);
	}
	
	public String printNodes(Espectator current) {
		String s = "";
		if(current != null) {
			s += printNodes(current.getLeft());
			s += "Node: " + current + " Left: " + current.getLeft() 
					+ " Right: " + current.getRight() + "\n~~~~~~~~~~~~~~~~~~~~~~~~\n";
			s += printNodes(current.getRight());
		}
		return s;
	}
	
	public void increaseBounds() {
		double x = increaseBoundsX(root);
		if(x >= width) {
			this.width = x+20;
		}
	}
	
	private double increaseBoundsX(Espectator current) {
		if(current != null) {
			double l = increaseBoundsX(current.getLeft());
			double r = increaseBoundsX(current.getRight());
			if(l > current.getX()) {
				if(l < r) {
					return r;
				}
				return l;
			} else if (r > current.getX()){
				if(r < l) {
					return l;
				}
				return r;
			} else {
				return current.getX();
			}
		}
		return 0;
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
	
	public Espectator searchEspectator(String id) {
		return searchEspectator(id, root);
	}
	
	private Espectator searchEspectator(String id, Espectator current) {
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
	
	public Competitor searchCompetitor(String id) {
		return searchCompetitor(id, first);
	}
	
	private Competitor searchCompetitor(String id, Competitor current) {
		if(current != null) {
			if(current.getId().compareTo(id) == 0) {
				return current;
			} else {
				return searchCompetitor(id, current.getNext());
			}
		}
		return null;
	}
	
	public List<Espectator> inorderListOfEspectators() {
		return inorderListOfEspectators(root);
	}
	
	private List<Espectator> inorderListOfEspectators(Espectator current){
		List<Espectator> l = new ArrayList<Espectator>();
		if(current != null) {
			l.addAll(inorderListOfEspectators(current.getLeft()));
			l.add(current);
			l.addAll(inorderListOfEspectators(current.getRight()));
		}
		return l;
	}
	
	public List<Espectator> preorderListOfEspectators(){
		List<Espectator> lis= new ArrayList<>();
		preorderListOfEspectators(root,lis);
		return lis;
	}
	private void preorderListOfEspectators(Espectator current,List<Espectator> lis){
		if(current != null) {
			lis.add(current);
			preorderListOfEspectators(current.getLeft(),lis);
			preorderListOfEspectators(current.getRight(),lis);
			
		}
	}
	
	public void assignePositionsList() {
		double x = this.width/this.first.size();
		double y = this.height/2;
		assignePositionsList(first, x, y);
	}
	
	
	
	private void assignePositionsList(Competitor current, double x, double y) {
		if(current != null) {
			current.setX(x);
			current.setY(y);
			assignePositionsList(current.getNext(), x + 80, y);
		}
	}

	public void selectCompetitors() {
		selectCompetitors(root, root.getWeight()/2-1);
	}
	
	private void selectCompetitors(Espectator current, int limit) {
		if(limit > 0) {
			if(current != null) {
				Random r = new Random();
				if(r.nextBoolean()) {
					addCompetitor(Assistant.cast(current));
					delete(root, current);
					limit--;
				}
				selectCompetitors(current.getLeft(), limit);
				selectCompetitors(current.getRight(), limit);
			}
		}
	}
	
	public Espectator delete(Espectator current, Espectator esp) {
		if (current == null) {
			return null;
		} else if (current.compareTo(esp) > 0) { // find the node
			current.setLeft(delete(current.getLeft(), esp));
		} else if (current.compareTo(esp) < 0) {
			current.setRight(delete(current.getRight(), esp));
		} else {            
			if (current.getRight() == null) {
				return current.getLeft();    //replace with left child
			} else if (current.getLeft() == null) {
				return current.getRight();   // replace with right child
			} else {                // replace with min from right subtree
				current.replaceData(getMin(current.getRight()));
				current.setRight(delete(current.getRight(), current));
			}
		}
		return current; 
	}
	
	private Espectator getMin(Espectator current) {
		if(current.getLeft() != null) {
			return getMin(current.getLeft());
		}
		return current;
	}
	
	public void addCompetitor(Competitor c) {
		addCompetitor(c, first);
	}
	
	private void addCompetitor(Competitor c, Competitor current) {
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

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int getTreeHeight() {
		return getTreeHeight(root);
	}
	public int getTreeHeight(Espectator current) {
		if(current != null) {
			return Math.max(getTreeHeight(current.getLeft()), getTreeHeight(current.getRight())) + 1;
		}
		return 0;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
}
