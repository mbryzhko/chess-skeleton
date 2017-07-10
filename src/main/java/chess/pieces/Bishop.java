package chess.pieces;

import chess.Player;
import chess.move.MoveDelta;
import chess.move.MoveDirection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {

	private static final Collection<MoveDirection> DIRECTIONS;

    static {
	    MoveDirection toTopLeft = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(pos, -pos)).collect(Collectors.toList()));
	    MoveDirection toTopRight = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(pos, pos)).collect(Collectors.toList()));
	    MoveDirection toBottomRight = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(-pos, pos)).collect(Collectors.toList()));
	    MoveDirection toBottomLeft = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(-pos, -pos)).collect(Collectors.toList()));

        DIRECTIONS = Collections.unmodifiableCollection(Arrays.asList(toTopLeft, toTopRight, toBottomRight, toBottomLeft));
    }

    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

	@Override
	public Collection<MoveDirection> getPossibleDirections() {
		return DIRECTIONS;
	}
}
