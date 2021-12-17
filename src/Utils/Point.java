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
     * @param bottomLeft The bottom left point of the boundary.
     * @param topRight The top right point of the bounday.
     * @param point The point to check for.
     * @return Whether or not point resides within the boundary created by topLeft and bottomRight.
     */
    public static boolean within(Point bottomLeft, Point topRight, Point point) {
        boolean x = point.x >= bottomLeft.x && point.x <= topRight.x;
        boolean y = point.y >= bottomLeft.y && point.y <= topRight.y;
        return x && y;
    }

    /**
     * Checks if the point is past the right side of the boundary or the bottom of the boundary created by bottomLeft and topRight.
     * @param bottomLeft The bottom left point of the boundary.
     * @param topRight The top right point of the bounday.
     * @param point The point to check for.
     * @return Whether or not the point is beyond the boundaries bottomRight corner coming from the topLeft.
     */
    public static boolean beyondBottomRight(Point bottomLeft, Point topRight, Point point) {
        return point.x > topRight.x || point.y < bottomLeft.y;
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
