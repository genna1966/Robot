package gr.robot.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import gr.robot.InvalidPositionException;

/**
 * @author genna
 *
 */
public abstract class Direction {
	protected abstract void move(State state) throws InvalidPositionException;

	protected abstract void turnLeft(State state);

	protected abstract void turnRight(State state);

	// protected abstract String name();

	public static Direction North = new Direction() {

		protected void move(State state) throws InvalidPositionException {
			state.moveNorth();
		}

		protected void turnLeft(State state) {
			state.setDirection(West);
		}

		protected void turnRight(State state) {
			state.setDirection(East);
		}

	};
	public static Direction South = new Direction() {

		protected void move(State state) throws InvalidPositionException {
			state.moveSouth();
		}

		protected void turnLeft(State state) {
			state.setDirection(East);
		}

		protected void turnRight(State state) {
			state.setDirection(West);
		}
	};
	public static Direction East = new Direction() {

		protected void move(State state) throws InvalidPositionException {
			state.moveEast();
		}

		protected void turnLeft(State state) {
			state.setDirection(North);
		}

		protected void turnRight(State state) {
			state.setDirection(South);
		}

	};
	public static Direction West = new Direction() {
		protected void move(State state) throws InvalidPositionException {
			state.moveWest();
		}

		protected void turnLeft(State state) {
			state.setDirection(South);
		}

		protected void turnRight(State state) {
			state.setDirection(North);
		}
	};

	public static Map<String, Direction> Directions;
	static {
		Directions = new HashMap<String, Direction>();
		Directions.put("NORTH", North);
		Directions.put("EAST", East);
		Directions.put("WEST", West);
		Directions.put("SOUTH", South);
	}

	/**
	 *  @return Direction name
	 *  Reverse lookup Directions map
	 */
	public String name() {
		for (Entry<String, Direction> entry : Directions.entrySet()) {
			if (this.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		//There is something wrong if we ever get here  
		return "An Unknown direction";
	}

}
