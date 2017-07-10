package chess;

/**
 * Which side of the board is being played
 */
public enum Player {
    White, Black;

    public boolean is(Player other) {
        return this == other;
    }

}
