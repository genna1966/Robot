package gr.robot.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import gr.robot.RobotException;
import gr.robot.command.Command;
import gr.robot.command.CommandFactory;

public class CommandBuilderTest {
	MockController controller = new MockController();
	CommandFactory factory = new CommandFactory();

	private void executeAndTestValidCommand(String input, String output) {
		try {
			buildAndExecuteCommand(input, output);
		} catch (RobotException e) {
			fail("Valid Input " + input);
		}

	}

	private void testInvalidCommand(String input) {
		try {
			factory.buildCommand(input);
			fail("Built invalid command " + input);
		} catch (RobotException e) {
			// Expected result
		}

	}

	private void buildAndExecuteCommand(String input, String output)
			throws RobotException {
		Command command = factory.buildCommand(input);
		command.executeOn(controller);
		assertEquals("Must match", controller.getResult(), output);
	}

	@Test
	public void testValidCommands() {
		// Simple commands
		executeAndTestValidCommand("MOVE", "MOVE");
		executeAndTestValidCommand("LEFT", "LEFT");
		executeAndTestValidCommand("RIGHT", "RIGHT");
		executeAndTestValidCommand("REPORT", "REPORT");

		// Position commands
		executeAndTestValidCommand("PLACE 0,0,NORTH", "POSITION 0,0,NORTH");
		executeAndTestValidCommand("PLACE 0,0,SOUTH", "POSITION 0,0,SOUTH");
		executeAndTestValidCommand("PLACE 0,0,WEST", "POSITION 0,0,WEST");
		executeAndTestValidCommand("PLACE 0,0,EAST", "POSITION 0,0,EAST");

		// White spaces commands
		executeAndTestValidCommand("MOVE ", "MOVE");
		executeAndTestValidCommand(" LEFT", "LEFT");
		executeAndTestValidCommand("PLACE    0,0,NORTH", "POSITION 0,0,NORTH");
		executeAndTestValidCommand("PLACE  0, 0, NORTH", "POSITION 0,0,NORTH");

	}

	@Test
	public void testOnlyUpperCaseSupported() {
		// Simple commands
		testInvalidCommand("move");
		testInvalidCommand("Left");
		testInvalidCommand("Place 0,0,NORTH");
		testInvalidCommand("PLACE 0,0,North");
	}

	@Test
	public void testInvalidPlaceFormat() {
		// Simple commands
		testInvalidCommand("PLACE North");
		testInvalidCommand("PLACE 0,,NORTH");
		testInvalidCommand("PLACE 0,0,NORTHEAST");
	}

}
