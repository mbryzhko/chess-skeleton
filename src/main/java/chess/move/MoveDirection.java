package chess.move;

import java.util.Collections;
import java.util.List;

/**
 * Represents piece's move direction. Contains list of deltas.
 */
public class MoveDirection {
	private final List<MoveDelta> moveDeltas;
	private final MoveType type;

	public MoveDirection(List<MoveDelta> moveDeltas) {
		this.moveDeltas = Collections.unmodifiableList(moveDeltas);
		this.type = MoveType.ANY;
	}

	public MoveDirection(List<MoveDelta> moveDeltas, MoveType moveType) {
		this.moveDeltas = Collections.unmodifiableList(moveDeltas);
		this.type = moveType;
	}

	public List<MoveDelta> getMoveDeltas() {
		return moveDeltas;
	}

	public MoveType getType() {
		return type;
	}

	public boolean canMove() {
		return type == MoveType.ANY || type == MoveType.ONLY_MOVE;
	}

	public boolean canBeat() {
		return type == MoveType.ANY || type == MoveType.ONLY_BEAT;
	}

}