package chess.move;

import chess.Position;
import chess.pieces.Piece;

/**
 * Represents a {@link Piece} move between absolute {@link Position}.
 */
public class PieceMove {

	private final Position from;

	private final Position to;

	private final boolean beat;

	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}

	public PieceMove(Position from, Position to) {
		this(from, to, false);
	}

	public PieceMove(Position from, Position to, boolean beat) {
		if (from == null || to == null) throw new IllegalArgumentException("'to' and 'from' cannot be null");

		this.from = from;
		this.to = to;
		this.beat = beat;
	}

	public static PieceMove valueOf(String moveEncoded) {
		if (moveEncoded == null) throw new IllegalArgumentException("'moveEncoded' cannot be null");

		String[] positions = moveEncoded.split(":");

		if (positions.length < 2) throw new IllegalArgumentException("Bad value of 'moveEncoded': " + moveEncoded);

		return new PieceMove(new Position(positions[0].trim()), new Position(positions[1].trim()));
	}

	@Override
	public String toString() {
		return "PieceMove{" + from + ":" + to + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PieceMove pieceMove = (PieceMove) o;

		if (!from.equals(pieceMove.from)) return false;
		return to.equals(pieceMove.to);
	}

	@Override
	public int hashCode() {
		int result = from.hashCode();
		result = 31 * result + to.hashCode();
		return result;
	}
}
