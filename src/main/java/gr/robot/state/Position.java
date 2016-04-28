package gr.robot.state;

import gr.robot.InvalidPositionException;

class Position {
	static final int UPPER_X = 4;
	static final int UPPER_Y = 4;
	private int x = 0;
	private int y = 0;

	private Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * validates position
	 * 
	 * @param pos_x
	 * @param pos_y
	 * @return false if a position outside a field
	 */
	private static boolean validatePosition(int pos_x, int pos_y) {
		return (pos_x >= 0 && pos_x <= UPPER_X)
				&& (pos_y >= 0 && pos_y <= UPPER_Y);
	}

	public static Position getPosition(int x, int y)
			throws InvalidPositionException {
		if (!validatePosition(x, y)) {
			throw new InvalidPositionException("Invalid position: "+x+","+y);
		}
		return new Position(x, y);
	}

	public Position moveNorth() throws InvalidPositionException {
		return getPosition(this.x, this.y + 1);
	}

	public Position moveSouth() throws InvalidPositionException {
		return getPosition(this.x, this.y - 1);
	}

	public Position moveWest() throws InvalidPositionException {
		return getPosition(this.x - 1, this.y);
	}

	public Position moveEast() throws InvalidPositionException {
		return getPosition(this.x + 1, this.y);
	}

	public String asString() {
		return this.x + "," + this.y;
	}

}
