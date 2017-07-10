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
 * The 'Rook' class
 */
public class Rook extends Piece {

	private static final Collection<MoveDirection> DIRECTIONS;

	static {
		MoveDirection toTop = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(pos, 0)).collect(Collectors.toList()));
		MoveDirection toBottom = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(-pos, 0)).collect(Collectors.toList()));
		MoveDirection toRight = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(0, pos)).collect(Collectors.toList()));
		MoveDirection toLeft = new MoveDirection(IntStream.range(1, 8).mapToObj(pos -> new MoveDelta(0, -pos)).collect(Collectors.toList()));

		DIRECTIONS = Collections.unmodifiableCollection(Arrays.asList(toTop, toBottom, toRight, toLeft));
	}

	public Rook(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'r';
	}

	@Override
	public Collection<MoveDirection> getPossibleDirections() {
		return DIRECTIONS;
	}
}
