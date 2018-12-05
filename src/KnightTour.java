public class KnightTour extends Game {


    private final int[] iMovement = {-2, -1, 1, 2, 2, 1, -1, -2};
    private final int[] jMovement = {1, 2, 2, 1, -1, -2, -2, -1};


    private int getGradeOfCell(CustomPosition position) {
        int counter = 0;
        for (int i = 0; i < iMovement.length; i++) {
            CustomPosition newPosition = getChangedPosition(position, i);
            if (isSafe(newPosition) && !isPadVisited(newPosition)) {
                counter++;
            }
        }
        return counter;
    }


    private CustomPosition getChangedPosition(CustomPosition position, int moveIndex) {
        return new CustomPosition(
                position.getX() + iMovement[moveIndex],
                position.getY() + jMovement[moveIndex]);
    }

    public void solve(CustomPosition initialPosition) {
        solutionSequance.add(initialPosition);
        int minGrade;
        int minIndex;
        for (int j = 0; j < n * n; j++) {
            minGrade = Integer.MAX_VALUE;
            minIndex = -1;
            for (int i = 0; i < iMovement.length; i++) {
                CustomPosition newPosition = getChangedPosition(initialPosition, i);
                if (isSafe(newPosition) && !isPadVisited(newPosition)) {
                    int grade = getGradeOfCell(newPosition);
                    if ((minGrade > grade && grade != 0) || minIndex == -1) {
                        minGrade = grade;
                        minIndex = i;
                    }
                }
            }
            boolean hasSolution = minIndex != -1;
            if (hasSolution) {
                initialPosition = getChangedPosition(initialPosition, minIndex);
                solutionSequance.add(initialPosition);
            }
        }
    }

    public KnightTour(int n) {
        super(n);
    }

    private boolean isPadVisited(CustomPosition position) {
        return solutionSequance.contains(position);
    }

}
