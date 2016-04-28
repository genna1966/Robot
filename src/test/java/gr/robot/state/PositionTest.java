package gr.robot.state;

import static org.junit.Assert.*;
import gr.robot.InvalidPositionException;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testGetInvalidPosition() {

		try {
			assertEquals("1,2", Position.getPosition(1, 2).asString());
		} catch (InvalidPositionException e) {
			fail("Valid position");
		}

	}

	@Test(expected = InvalidPositionException.class)
	public void testGetInvalidPositionYLessThenZero()
			throws InvalidPositionException {
		Position.getPosition(1, -1);
	}

	@Test(expected = InvalidPositionException.class)
	public void testGetInvalidPositionYGreaterThenMax()
			throws InvalidPositionException {
		Position.getPosition(1, 6);
	}

	@Test(expected = InvalidPositionException.class)
	public void testGetInvalidPositionXLessThenZero()
			throws InvalidPositionException {
		Position.getPosition(-3, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void testGetInvalidPositionXGreaterThenMax()
			throws InvalidPositionException {
		Position.getPosition(7, 0);
	}

	@Test(expected = InvalidPositionException.class)
	public void testGetInvalidPositionBothArgumentsWrong()
			throws InvalidPositionException {
		Position.getPosition(8, 9);
	}

	@Test
	public void testMoveNorth() {
		try {
			assertEquals(Position.getPosition(0, 0).moveNorth().asString(),
					"0,1");
		} catch (InvalidPositionException e) {
			fail("Valid position");
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveNorthOverTheBorder() throws InvalidPositionException {
		Position.getPosition(0, 4).moveNorth();
	}

	@Test
	public void testMoveSouth() {
		try {
			assertEquals("0,0", Position.getPosition(0, 1).moveSouth()
					.asString());
		} catch (InvalidPositionException e) {
			fail("Valid position");
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveSouthOverTheBorder() throws InvalidPositionException {
		Position.getPosition(0, 0).moveSouth();
	}

	@Test
	public void testMoveWest() {
		try {
			assertEquals("0,0", Position.getPosition(1, 0).moveWest()
					.asString());
		} catch (InvalidPositionException e) {
			fail("Valid position");
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveWestOverTheBorder() throws InvalidPositionException {
		Position.getPosition(0, 0).moveWest();
	}

	@Test
	public void testMoveEast() {
		try {
			assertEquals("1,0", Position.getPosition(0, 0).moveEast()
					.asString());
		} catch (InvalidPositionException e) {
			fail("Valid position");
		}
	}

	@Test(expected = InvalidPositionException.class)
	public void testMoveEastOverTheBorder() throws InvalidPositionException {
		Position.getPosition(4, 0).moveEast();
	}

}
