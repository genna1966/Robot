package gr.robot.state;

import static org.junit.Assert.*;
import gr.robot.InvalidPositionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class StateTest {
	private State state ;

	private void assertState(String expectedState) {

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			state.printOn(out);
			assertEquals(expectedState, out.toString().trim());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testPlace() throws InvalidPositionException {
		state = new State(1, 2, Direction.North);
		assertState("1,2,NORTH");
	}

	@Test(expected = InvalidPositionException.class)
	public void testPlaceInvalid() throws InvalidPositionException {
		state = new State(1, 5, Direction.North);
	}

	@Test
	public void testMove() throws InvalidPositionException {
		state = new State(1, 2, Direction.North);
		state.move();
		assertState("1,3,NORTH");

		state.place(1, 2, Direction.South);
		state.move();
		assertState("1,1,SOUTH");

		state.place(1, 2, Direction.West);
		state.move();
		assertState("0,2,WEST");

		state.place(1, 2, Direction.East);
		state.move();
		assertState("2,2,EAST");

	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveNorthOverTheBorder() throws InvalidPositionException {
		state = new State(0, 4, Direction.North);
		state.move();
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveSouthOverTheBorder() throws InvalidPositionException {
		state = new State(0, 0, Direction.South);
		state.move();
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveWestOverTheBorder() throws InvalidPositionException {
		state = new State(0, 0, Direction.West);
		state.move();
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveEastOverTheBorder() throws InvalidPositionException {
		state = new State(4, 0, Direction.East);
		state.move();

	}

	@Test
	public void testTurnLeft() throws InvalidPositionException {
		state = new State(1, 2, Direction.North);
		state.turnLeft();
		assertState("1,2,WEST");
		state.turnLeft();
		assertState("1,2,SOUTH");
		state.turnLeft();
		assertState("1,2,EAST");
		state.turnLeft();
		assertState("1,2,NORTH");

	}

	@Test
	public void testTurnRight() throws InvalidPositionException {
		state = new State(1, 2, Direction.North);
		state.turnRight();
		assertState("1,2,EAST");
		state.turnRight();
		assertState("1,2,SOUTH");
		state.turnRight();
		assertState("1,2,WEST");
		state.turnRight();
		assertState("1,2,NORTH");

	}

}
