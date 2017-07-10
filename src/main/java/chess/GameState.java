package chess;


import chess.move.MoveDelta;
import chess.move.MoveDirection;
import chess.move.PieceMove;
import chess.pieces.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

	private Player playedOnTopOfBoard = Player.Black;

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<Position, Piece>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    void setCurrentPlayer(Player nextPlayer) {
    	currentPlayer = nextPlayer;
    }

	public Player getPlayedOnTopOfBoard() {
		return playedOnTopOfBoard;
	}

	public void setPlayedOnTopOfBoard(Player playedOnTopOfBoard) {
		this.playedOnTopOfBoard = playedOnTopOfBoard;
	}

	public Map<Position, Piece> getPositionToPieceMap() {
		return positionToPieceMap;
	}

	public void setPositionToPieceMap(Map<Position, Piece> positionToPieceMap) {
		this.positionToPieceMap = positionToPieceMap;
	}

	/**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));
    }

    void resetWith(Map<Position, Piece> pieces) {
        positionToPieceMap.putAll(pieces);
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    /**
     * Calculates all possible moves the current player can do.
     * @return list of {@link PieceMove}
     */
    public Collection<PieceMove> getAllPossibleMoves() {
	    Collection<PieceMove> result = new LinkedList<>();

	    positionToPieceMap.entrySet().forEach(positionOfPiece -> {
		    Position currentPiecePos = positionOfPiece.getKey();
		    Piece currentPiece = positionOfPiece.getValue();
		    if (isCurrentUsersPiece(currentPiece)) {
			    currentPiece.getPossibleDirections().forEach(direction -> deriveMovesOfDirection(direction, currentPiecePos, currentPiece, result));
		    }
	    });

	    return result;
    }

	private void deriveMovesOfDirection(MoveDirection direction, Position currentPiecePos, Piece currentPiece, Collection<PieceMove> result) {
		List<Position> absoluteDirectionPositions = resolveMovePositions(direction.getMoveDeltas(), currentPiecePos);
		for (int moveIdx = 0; moveIdx < absoluteDirectionPositions.size(); moveIdx++) {
			Position nextPosition = absoluteDirectionPositions.get(moveIdx);
			Piece pieceAtNextPosition = getPieceAt(nextPosition);

			if (currentPiece instanceof Pawn && currentPiece.getMovesCount() > 0 && moveIdx > 0) { // Pawn moves 2 cells ahead only first time
				break;
			} else if (pieceAtNextPosition == null && direction.canMove()) {
				result.add(new PieceMove(currentPiecePos, nextPosition));
			} else if (pieceAtNextPosition != null && !pieceAtNextPosition.getOwner().is(currentPlayer) && direction.canBeat()) { // When other player piece and can beat then move
				result.add(new PieceMove(currentPiecePos, nextPosition, true));
				break;
			} else if (pieceAtNextPosition != null && pieceAtNextPosition.getOwner().is(currentPlayer)) { // When this played piece then stop
				break;
			}

		}

	}

	private boolean isCurrentUsersPiece(Piece currentPiece) {
		return currentPiece.getOwner().is(currentPlayer);
	}

	/**
     * Iterates over all move details of a piece and produce positions if their moves are valid on the table.
     */
    private List<Position> resolveMovePositions(List<MoveDelta> piece, Position currentPos) {
	    return piece.stream()
			    .map(delta -> isCurrentPlayerOnTopOfBoard() ? delta.getInvertedVertically() : delta)
			    .filter(delta -> delta.isValidMove(currentPos))
			    .map(delta -> delta.getNextPosition(currentPos))
			    .collect(Collectors.toList());
    }

	/**
	 * Derives a side of the current player. This is needed in order to know in which direction pieces can move.
	 *
	 * @return whether current played is on the top
	 */
	private boolean isCurrentPlayerOnTopOfBoard() {
    	return currentPlayer.is(playedOnTopOfBoard);
    }
}
