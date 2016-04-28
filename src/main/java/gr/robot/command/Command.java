package gr.robot.command;

import gr.robot.Controller;
import gr.robot.RobotException;


/**
 * @author genna
 * Command interface
 * Also defines static basic commands MOVE, LEFT, RIGHT and REPORT. PLACE command will have to be
 * created dynamically as it needs to pass parameters 
 */
public interface Command {
	public void executeOn(Controller controller) throws RobotException;

	public static Command moveCommand = new Command() {
		public void executeOn(Controller controller) throws RobotException {
			controller.move();
		}
	};

	public static Command turnLeftCommand = new Command() {
		public void executeOn(Controller controller) throws RobotException {
			controller.left();
		}
	};

	public static Command turnRightCommand = new Command() {
		public void executeOn(Controller controller) throws RobotException {
			controller.right();
		}
	};
	
	public static Command reportCommand = new Command() {
		public void executeOn(Controller controller) throws RobotException {
			controller.report();
		}
	};
}
