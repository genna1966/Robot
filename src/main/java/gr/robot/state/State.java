package gr.robot.state;

import gr.robot.InvalidPositionException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class State {

	private Position position;
	private Direction direction;

	protected void setDirection(Direction direction) {
		this.direction = direction;

	}

	public State(int x, int y, Direction direction)
			throws InvalidPositionException {
		this.place(x, y, direction);
	}

	public void move() throws InvalidPositionException {
		direction.move(this);
	}

	public void place(int x, int y, Direction direction)
			throws InvalidPositionException {
		this.position = Position.getPosition(x, y);
		this.direction = direction;
	}

	public void turnLeft() {
		this.direction.turnLeft(this);

	}

	public void turnRight() {
		this.direction.turnRight(this);

	}

	public void printOn(OutputStream out) throws IOException {
		PrintStream ps = new PrintStream(out);
		ps.println(this.position.asString() + "," + direction.name());
		ps.flush();
	}

	/**
	 * @return
	 * @throws InvalidPositionException
	 * @see gr.robot.state.Position#moveNorth()
	 */
	public void moveNorth() throws InvalidPositionException {
		this.position = position.moveNorth();
	}

	/**
	 * @return
	 * @throws InvalidPositionException
	 * @see gr.robot.state.Position#moveSouth()
	 */
	public void moveSouth() throws InvalidPositionException {
		this.position = position.moveSouth();
	}

	/**
	 * @return
	 * @throws InvalidPositionException
	 * @see gr.robot.state.Position#moveWest()
	 */
	public void moveWest() throws InvalidPositionException {
		this.position = position.moveWest();
	}

	/**
	 * @return
	 * @throws InvalidPositionException
	 * @see gr.robot.state.Position#moveEast()
	 */
	public void moveEast() throws InvalidPositionException {
		this.position = position.moveEast();
	}

}
