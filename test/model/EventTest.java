package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class EventTest {
	private Event e;
	private String path;
	private void setUpScenary1() {
		e=new Event();
		path="C:\\Users\\chris\\OneDrive\\Documentos\\projects\\volleyball-championship\\data\\assistants-data.csv";
	}
	private void setUpScenary2() {
		e=new Event();
		path="C:\\Users\\chris\\OneDrive\\Documentos\\projects\\volleyball-championship\\data\\assitants1-data.csv";
	}
	@Test
	public void testLoad() {
		setUpScenary1();
		try {
			long start = System.currentTimeMillis();
			e.load(path);
			long endTime = (System.currentTimeMillis() - start);
			System.out.println(endTime);
			assertTrue("The method load of 1000 assitants is delayed", endTime>10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testLoad2() {
		setUpScenary2();
		try {
			long start = System.currentTimeMillis();
			e.load(path);
			long endTime = (System.currentTimeMillis() - start);
			System.out.println(endTime);
			assertTrue("The method load of 100.000 assitants is delayed", endTime>20);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test() {
		setUpScenary1();
		try {
			long start = System.currentTimeMillis();
			e.load(path);
			long endTime = (System.currentTimeMillis() - start);
			System.out.println(endTime);
			assertTrue("The method load of 100.000 assitants is delayed", endTime>20);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Event getE() {
		return e;
	}
	public void setE(Event e) {
		this.e = e;
	}

}
