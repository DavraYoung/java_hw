import java.util.ArrayList;

public class Game {
    protected int n;
    protected boolean isSafe(CustomPosition position) {
        return position.getX() >= 0 && position.getY() >= 0
                && position.getX() < n && position.getY() < n;
    }
    public ArrayList<CustomPosition> solutionSequance = new ArrayList<>();

    public Game(int n) {
        this.n = n;
    }
}
