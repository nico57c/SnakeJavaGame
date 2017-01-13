package snakeJavaGame.engine;

import java.util.Arrays;

public class Position implements Comparable<Position> {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position relativePosition(int rx, int ry) {
        return new Position(x + rx, y + ry);
    }

    public Position relativePosition(int rx, int ry, int maxX, int maxY) {
        ry = y + ry;
        rx = x + rx;
        return new Position(rx >= maxX ? 0 : rx < 0 ? maxX : rx, ry >= maxY ? 0 : ry < 0 ? maxY : ry);
    }

    public static Position valueOf(String position) {
        String[] xy = position.split(",");
        if (xy.length == 2) {
            return new Position(Integer.valueOf(xy[0]), Integer.valueOf(xy[1]));
        } else {
            return null;
        }
    }

    public static Position[] valuesOf(String positions) {
        String[] xys = positions.split(";");
        if (xys.length > 0) {
            return Arrays.asList(xys).stream().map(xy -> {
                return Position.valueOf(xy);
            }).toArray(size -> new Position[size]);
        } else {
            return null;
        }
    }

    @Override
    public int compareTo(Position position) {
        return position.getX() == getX() && position.getY() == getY() ? 0
                : position.getX() <= getX() || position.getY() <= getY() ? -1 : 1;
    }
}
