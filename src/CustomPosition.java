import java.util.Objects;

public class CustomPosition {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CustomPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x+":"+y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomPosition that = (CustomPosition) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
