package gr.robot.command;

import gr.robot.InvalidInputException;

import java.io.InputStream;
import java.util.Scanner;

public class InputReceiver {
	private Scanner scanner;
	private InputStream in;
	private CommandFactory commandFactory;

	public InputReceiver(InputStream in) {
		this.in = in;
		this.scanner = new Scanner(this.in);
		this.commandFactory = new CommandFactory();
	}

	public InputReceiver() {
		this(System.in);
	}
	
	
	public Command receiveCommand() throws InvalidInputException {
		String inputLine = scanner.nextLine();
		return this.commandFactory.buildCommand(inputLine);
	}

	/**
	 * @return
	 * @see java.util.Scanner#hasNextLine()
	 */
	public boolean hasNextLine() {
		return scanner.hasNextLine();
	}
}
