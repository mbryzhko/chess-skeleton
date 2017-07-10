package chess.pieces;

import chess.Player;
import chess.move.MoveDelta;
import chess.move.MoveDirection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.unmodifiableCollection;

/**
 * The King class
 */
public class King extends Piece {

	private static final Collection<MoveDirection> DIRECTIONS = unmodifiableCollection(Arrays.asList(
			new MoveDirection(Collections.singletonList(new MoveDelta(1, 0))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-1, 0))),
			new MoveDirection(Collections.singletonList(new MoveDelta(0, 1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(0, -1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(1, 1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-1, -1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(1, -1))),
			new MoveDirection(Collections.singletonList(new MoveDelta(-1, 1)))
	));


    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

	@Override
	public Collection<MoveDirection> getPossibleDirections() {
		return DIRECTIONS;
	}
}
