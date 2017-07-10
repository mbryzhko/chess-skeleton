package chess.pieces;

import chess.Player;
import chess.move.MoveDelta;
import chess.move.MoveDirection;
import chess.move.MoveType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * The Pawn
 */
public class Pawn extends Piece {

	private static final Collection<MoveDirection> DIRECTIONS;

	static {
		DIRECTIONS = Arrays.asList(
				new MoveDirection(Arrays.asList(new MoveDelta(1, 0), new MoveDelta(2, 0)), MoveType.ONLY_MOVE),
				new MoveDirection(Collections.singletonList(new MoveDelta(1, 1)), MoveType.ONLY_BEAT),
				new MoveDirection(Collections.singletonList(new MoveDelta(1, -1)), MoveType.ONLY_BEAT)
		);
	}


	public Pawn(Player owner) {
		super(owner);
	}

	public Pawn(Player owner, int movesCount) {
		super(owner, movesCount);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'p';
	}

	@Override
	public Collection<MoveDirection> getPossibleDirections() {
		return DIRECTIONS;
	}
}
