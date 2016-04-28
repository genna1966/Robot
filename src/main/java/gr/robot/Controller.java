package gr.robot;

import gr.robot.state.Direction;

/**
 * @author genna
 * Controlled interface defines possible actions as per Robot spec
 */
public interface Controller {
	public void move() throws InvalidPositionException;

	public void left();

	public void right();

	public void place(int x, int y, Direction direction)
			throws InvalidPositionException;

	public void report();
}
