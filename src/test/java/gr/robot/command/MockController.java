/**
 * 
 */
package gr.robot.command;

import gr.robot.Controller;
import gr.robot.InvalidPositionException;
import gr.robot.state.Direction;

/**
 * @author genna
 *
 */
public class MockController implements Controller {
	private String result;
	
	/* (non-Javadoc)
	 * @see gr.robot.Controller#move()
	 */
	@Override
	public void move() throws InvalidPositionException {
		result = "MOVE";
	}

	/* (non-Javadoc)
	 * @see gr.robot.Controller#left()
	 */
	@Override
	public void left() {
		result = "LEFT";
	}

	/* (non-Javadoc)
	 * @see gr.robot.Controller#right()
	 */
	@Override
	public void right() {
		result = "RIGHT";

	}

	/* (non-Javadoc)
	 * @see gr.robot.Controller#place(int, int, gr.robot.state.Direction)
	 */
	@Override
	public void place(int x, int y, Direction direction)
			throws InvalidPositionException {
		result = "POSITION "+x+","+y+","+direction.name();

	}

	/* (non-Javadoc)
	 * @see gr.robot.Controller#report()
	 */
	@Override
	public void report() {
		// TODO Auto-generated method stub
		result = "REPORT";
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

}
