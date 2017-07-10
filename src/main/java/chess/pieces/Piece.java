package chess.pieces;

import chess.Player;
import chess.move.MoveDirection;

import java.util.Collection;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
	private final Player owner;
	private int movesCount = 0;

	protected Piece(Player owner) {
		this.owner = owner;
	}

	protected Piece(Player owner, int movesCount) {
		this.owner = owner;
		this.movesCount = movesCount;
	}

	public void incMoves() {
		movesCount++;
	}

	public int getMovesCount() {
		return movesCount;
	}

	public char getIdentifier() {
		char id = getIdentifyingCharacter();
		if (owner.equals(Player.White)) {
			return Character.toLowerCase(id);
		} else {
			return Character.toUpperCase(id);
		}
	}

	public Player getOwner() {
		return owner;
	}

	protected abstract char getIdentifyingCharacter();

	public abstract Collection<MoveDirection> getPossibleDirections();

}
