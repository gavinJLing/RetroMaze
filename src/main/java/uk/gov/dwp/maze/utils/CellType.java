package uk.gov.dwp.maze.utils;

public enum CellType {
    START('S'), PATH(' '), WALL('X'), FINISH('F'), UNKNOWN('!');

    private final char type;

    private CellType(char type) {
        this.type = type;
    }

    public static CellType valueOfSymbol(char symbol) {

        for (CellType c : values()) {
            if (c.type == symbol)
                return c;
        }
        throw new IllegalArgumentException(String.format("Unrecognised symbol value %s", symbol));
    }

    public char getType() {
        return type;
    }
}
