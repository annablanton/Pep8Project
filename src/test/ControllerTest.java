package test;

import controller.Pep8Sim;
import model.ALU;
import model.Register;
import org.junit.Before;
import org.junit.Test;
import view.GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;

/**
 * JUnit4 tests for the ALU class. All tests should pass.
 * 
 * @author Group 6: Anna Blanton
 * @version 10/18/2020
 */
public class ControllerTest {


	GUI view;
	Pep8Sim p;

	@Before
	public void setUp() {
		view = new GUI(new JPanel(), new JPanel(), new JTabbedPane(), new JMenuBar());
		p = new Pep8Sim();


	} //Do I need any setup for static void?

	@Test
	public void testMain() {
		Pep8Sim.main(new String[]{});
	}

	@Test
	public void testSetBatchInput() {
		p.setBatchInput("abc");
		assertEquals('a', p.getBatchInput());
	}

	@Test
	public void testReset() {
		p.reset();
	}



}
