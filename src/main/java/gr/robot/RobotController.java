package gr.robot;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import gr.robot.command.Command;
import gr.robot.command.InputReceiver;
import gr.robot.state.Direction;
import gr.robot.state.State;

/**
 * @author genna
 * Concrete implementation of Controller interface
 * A main class that controls movement of a robot by reading input in form of a command and 
 * manipulating its state in the response to that command.
 */
public class RobotController implements Controller {
	// Make sure there is only one robot
	public static final String NOT_INITIALIZED = "Robot is not on the table";
	private static RobotController myRobot;
	private InputReceiver inputReceiver;
	private State state;
	OutputStream out;
	InputStream in;

	private RobotController() {
		// this.inputReceiver = new InputReceiver();
		reset();
	}

	// Make set methods package visibility so it can only be used in tests
	// In real life use StdIn/StdOut
	void setOutput(OutputStream out) {
		this.out = out;
	}

	void setInput(InputStream in) {
		inputReceiver = null;
		this.in = in;
	}

	public static RobotController getRobot() {
		if (myRobot == null) {
			myRobot = new RobotController();
		}
		return myRobot;
	}

	public void reset() {
		this.state = null;
		this.out = System.out;
		this.in = System.in;
	}

	
	/**
	 *  Main body - reads a command from input and manipulates 
	 * the Robot by executing the command 
	 */
	public void start() {
		if (inputReceiver == null) {
			inputReceiver = new InputReceiver(in);
		}

		while (inputReceiver.hasNextLine()) {
			try {
				Command command = inputReceiver.receiveCommand();
				command.executeOn(this);
			} catch (InvalidInputException invalidInput) {
				invalidInput.printStackTrace(new PrintStream(out)); 
			} catch (InvalidPositionException e) {
				// Ignore command and do nothing as per spec.
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	@Override
	public void move() throws InvalidPositionException {
		if (this.state != null) {
			this.state.move();
		}
	}

	@Override
	public void left() {
		if (this.state != null) {
			this.state.turnLeft();
		}

	}

	@Override
	public void right() {
		if (this.state != null) {
			this.state.turnRight();
		}

	}

	@Override
	public void place(int x, int y, Direction direction)
			throws InvalidPositionException {
		if (this.state != null) {
			this.state.place(x, y, direction);
		} else {
			this.state = new State(x, y, direction);
		}

	}

	@Override
	public void report() {
		try {
			if (this.state != null) {
				this.state.printOn(out);
			} else {
				new PrintStream(out).println(NOT_INITIALIZED);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RobotController.getRobot().start();
	}
}
