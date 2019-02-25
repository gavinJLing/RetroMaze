package uk.gov.dwp.maze.utils;

public enum Direction {
    NORTH(0), EAST(1), SOUTH(2), WEST(3);

    private final int index;

    private Direction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Direction valueOfIndex(int i) {
        for (Direction d : values()) {
            if (d.getIndex() == i)
                return d;
        }
        throw new IllegalArgumentException(String.format("Unrecognised Index value %s", i));
    }

    public static Direction valueOfIndex(String s) {
        return valueOfIndex(Integer.parseInt(s));
    }

}
