package chess.move;

import chess.Position;

/**
 * Represents predefined piece's move delta.
 */
public class MoveDelta {

	private final int v, h;

	public MoveDelta(int v, int h) {
		this.v = v;
		this.h = h;
	}

	/**
	 * Checks if this move is valid based on give position.
	 */
	public boolean isValidMove(Position pos) {
		int nextRow = pos.getRow() + this.v;
		char nextCol = (char) (pos.getColumn() + this.h);
		return Position.isValidPosition(nextCol, nextRow);
	}

	/**
	 * Derives next positions based on provided current position and this delta.
	 */
	public Position getNextPosition(Position pos) {
		int nextRow = pos.getRow() + this.v;
		char nextCol = (char) (pos.getColumn() + this.h);
		return new Position(nextCol, nextRow);
	}

	public MoveDelta getInvertedVertically() {
		return new MoveDelta(-v, h);
	}
}
