package Utils;

import java.util.Objects;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param bottomleft The bottom left point of the boundary.
     * @param topRight The top right point of the bounday.
     * @param point The point to check for.
     * @return Whether or not point resides within the boundary created by topLeft and bottomRight.
     */
    public static boolean within(Point bottomleft, Point topRight, Point point) {
        boolean x = point.x >= bottomleft.x && point.x <= topRight.x;
        boolean y = point.y >= bottomleft.y && point.y <= topRight.y;
        return x && y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point point = (Point) obj;
        return point.x == x && point.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
