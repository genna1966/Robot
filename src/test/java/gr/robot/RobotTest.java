package gr.robot;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

/**
 * @author genna
 *  This class tests full system. Tests A B C as per example in
 *  the requirement document. Additional tests to ignore invalid
 *  moves/positions
 */
public class RobotTest {

	protected void executeTest(String input, String expectedOutput) {
		InputStream in = new ByteArrayInputStream(input.getBytes());
		OutputStream out = new ByteArrayOutputStream();
		RobotController controller = RobotController.getRobot();
		controller.reset();
		controller.setInput(in);
		controller.setOutput(out);
		controller.start();
		String output = out.toString().trim();
		assertEquals(expectedOutput, output);
	}

	@Test
	public void testA() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("PLACE 0,0,NORTH\n");
		strBuilder.append("MOVE\n");
		strBuilder.append("REPORT\n");
		executeTest(strBuilder.toString(), "0,1,NORTH");
	}

	@Test
	public void testB() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("PLACE 0,0,NORTH\n");
		strBuilder.append("LEFT\n");
		strBuilder.append("REPORT\n");
		executeTest(strBuilder.toString(), "0,0,WEST");
	}

	@Test
	public void testC() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("PLACE 1,2,EAST\n");
		strBuilder.append("MOVE\n");
		strBuilder.append("MOVE\n");
		strBuilder.append("LEFT\n");
		strBuilder.append("MOVE\n");
		strBuilder.append("REPORT\n");
		executeTest(strBuilder.toString(), "3,3,NORTH");
	}
	
	@Test
	public void testIgnoreInvalidMoves() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("PLACE 0,0,WEST\n");
		//This should be ignored 
		strBuilder.append("MOVE\n");
		// Should be at the same spot
		strBuilder.append("REPORT\n");
		executeTest(strBuilder.toString(), "0,0,WEST");
	}

	@Test
	public void testIgnoreInvalidPlace() {
		StringBuilder strBuilder = new StringBuilder();
		// Should be ignored 
		strBuilder.append("PLACE 0,6,WEST\n");
		strBuilder.append("MOVE\n");
		strBuilder.append("LEFT\n");
		strBuilder.append("REPORT\n");
		executeTest(strBuilder.toString(), RobotController.NOT_INITIALIZED);
	}

	
}
