package chess;

import chess.move.PieceMove;
import chess.pieces.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;
import java.util.stream.Collectors;

import static chess.PieceMoveListTest.PiecePositionBean.of;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@RunWith(Parameterized.class)
public class PieceMoveListTest {

	@Parameters(name = "{index} - {0}")
	public static Collection<Object[]> data() {
		return asList(new Object[][]{
				{"Pawn moves on empty table", Player.White,
						singletonList(of(new Position("a1"), new Pawn(Player.White))), "a1:a2,a1:a3"},
				{"Top piece moves on empty table", Player.Black,
						singletonList(of(new Position("a8"), new Pawn(Player.Black))), "a8:a7,a8:a6"},
				{"Second Pawn move", Player.White,
						singletonList(of(new Position("a2"), new Pawn(Player.White, 1))), "a2:a3"},
				{"Rook moves on empty table", Player.White,
						singletonList(of(new Position("b2"), new Rook(Player.White))), "b2:b1, b2:c2, b2:d2, b2:e2, b2:f2, b2:g2, b2:h2, b2:b3, b2:b4, b2:b5, b2:b6, b2:b7, b2:b8, b2:a2"},
				{"Bishop moves on empty table", Player.White,
						singletonList(of(new Position("b2"), new Bishop(Player.White))), "b2:a1, b2:c3, b2:d4, b2:e5, b2:f6, b2:g7, b2:h8, b2:c1, b2:a3"},
				{"King moves on empty table", Player.White,
						singletonList(of(new Position("b2"), new King(Player.White))), "b2:c3, b2:a1, b2:b1, b2:c2, b2:c1, b2:a3, b2:b3, b2:a2"},
				{"Knight moves on empty table", Player.White,
						singletonList(of(new Position("b2"), new Knight(Player.White))), "b2:a4, b2:c4, b2:d3, b2:d1"},
				{"Queen moves on empty table", Player.White,
						singletonList(of(new Position("b2"), new Queen(Player.White))), "b2:b1, b2:c2, b2:d2, b2:e2, b2:f2, b2:g2, b2:h2, b2:b3, b2:b4, b2:b5, b2:b6, b2:b7, b2:b8, b2:a2, b2:a1, b2:c3, b2:d4, b2:e5, b2:f6, b2:g7, b2:h8, b2:c1, b2:a3"},
				{"Same color piece on a Pawn direction", Player.White,
						asList(of(new Position("a1"), new Pawn(Player.White)), of(new Position("a3"), new Pawn(Player.White))), "a1:a2, a3:a5, a3:a4"},
				{"Other color piece on a Pawn direction", Player.White,
						asList(of(new Position("a1"), new Pawn(Player.White)), of(new Position("a3"), new Pawn(Player.Black))), "a1:a2"},
				{"Pawn beats other piece", Player.White,
						asList(of(new Position("a1"), new Pawn(Player.White)), of(new Position("b2"), new Pawn(Player.Black))), "a1:a2, a1:a3, a1:b2"},
				{"Rook beats other piece", Player.White,
						asList(of(new Position("a1"), new Rook(Player.White)),
								of(new Position("a2"), new Pawn(Player.Black)),
								of(new Position("b1"), new King(Player.Black))), "a1:b1,a1:a2"}
		});
	}

	public PieceMoveListTest(String scenarioName, Player currentPlayed, Collection<PiecePositionBean> piecesList, String expectedMovesEncoded) {
		this.currentPlayed = currentPlayed;
		this.pieces = new HashMap<>();
		piecesList.forEach(bean -> pieces.put(bean.position, bean.piece));
		expectedMoves = Arrays.stream(expectedMovesEncoded.split(",")).map(PieceMove::valueOf).collect(Collectors.toSet());
	}

	private Player currentPlayed;

	private Map<Position, Piece> pieces;

	private Set<PieceMove> expectedMoves;

	private GameState gameState;

	@Before
	public void setup() {
		gameState = new GameState();
		gameState.setCurrentPlayer(currentPlayed);
		gameState.setPlayedOnTopOfBoard(Player.Black);
		gameState.resetWith(pieces);
	}

	@Test
	public void givenPieceOnTableThenCheckAllPossibleMoves() {
		Assert.assertEquals(expectedMoves, new HashSet<>(gameState.getAllPossibleMoves()));
	}

	static class PiecePositionBean {
		Position position;
		Piece piece;

		static PiecePositionBean of(Position pos, Piece piece) {
			PiecePositionBean bean = new PiecePositionBean();
			bean.piece = piece;
			bean.position = pos;
			return bean;
		}
	}

}
