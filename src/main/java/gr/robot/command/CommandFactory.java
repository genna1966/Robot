package gr.robot.command;

import gr.robot.Controller;
import gr.robot.InvalidInputException;
import gr.robot.RobotException;
import gr.robot.state.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CommandFactory {
	private static final Map<String, Command> simpleCommands;
	static {
		simpleCommands = new HashMap<String, Command>();
		simpleCommands.put("MOVE", Command.moveCommand);
		simpleCommands.put("LEFT", Command.turnLeftCommand);
		simpleCommands.put("RIGHT", Command.turnRightCommand);
		simpleCommands.put("REPORT", Command.reportCommand);
	}

	public Command buildCommand(String input) throws InvalidInputException {
		// Trim whitespaces
		String str = input.trim();

		// Check if input matches simple command
		if (simpleCommands.containsKey(str)) {
			// This is simple command
			return simpleCommands.get(str);
		} else if (str.startsWith("PLACE ")) {
			return buildPlaceCommand(str);
		} else {
			throw new InvalidInputException("Invalid command " +input);
		}
	}

	private Command buildPlaceCommand(String str) throws InvalidInputException {
		StringTokenizer params = new StringTokenizer(str.substring(6), ",");
		int x = 0;
		int y = 0;
		Direction direction = Direction.North;

		if (params.countTokens() != 3) {
			throw new InvalidInputException("PLACE Command expects 3 parameters");
		} else {
			// First parameter
			try {
				x = Integer.parseInt(params.nextToken().trim());
			} catch (Exception e) {
				throw new InvalidInputException("First parameter must be integer");
			}

			// Second parameter
			try {
				y = Integer.parseInt(params.nextToken().trim());
			} catch (Exception e) {
				throw new InvalidInputException("Second parameter must be integer");
			}

			// Third parameter
			direction = Direction.Directions.get(params.nextToken().trim());
			if (direction == null) {
				throw new InvalidInputException("Third Parameter must be one of [ NORTH, SOUTH, WEST, EAST ] ");
			}

			final int param_x = x;
			final int param_y = y;
			final Direction param_direction = direction;

			return new Command() {

				public void executeOn(Controller controller)
						throws RobotException {
					controller.place(param_x, param_y, param_direction);
				}
			};
		}

	}
}
