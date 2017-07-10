package chess.pieces;

import chess.Player;
import chess.move.MoveDelta;
import chess.move.MoveDirection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.unmodifiableCollection;

/**
 * The Knight class
 */
public class Knight extends Piece {

	private static final Collection<MoveDirection> DIRECTIONS = unmodifiableCollection(Arrays.asList(
			new MoveDirection(Collections.singletonList(new MoveDelta(2, -1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(2, 1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-1, 2))),
			new MoveDirection(Collections.singletonList(new MoveDelta(1, 2))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-2, 1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-2, -1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(1, -2))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-1, -2)))
	));

	public Knight(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'n';
	}

	@Override
	public Collection<MoveDirection> getPossibleDirections() {
		return DIRECTIONS;
	}
}
