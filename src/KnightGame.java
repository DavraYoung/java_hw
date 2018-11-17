import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class KnightGame extends JFrame {
    private int xAxis;
    private int yAxis;
    private int positionX;
    private int getPositionY;
    private boolean isGameStarted;
    private final int PANEL_HEIGHT = 600;
    private final int PANEL_WIDTH = 600;
    private final int[] iMovement = {-2, -1, 1, 2, 2, 1, -1, -2};
    private final int[] jMovement = {1, 2, 2, 1, -1, -2, -2, -1};
    private int currentIndex;
    private ArrayList<KnightPosition> moveSequance = new ArrayList<>();
    JPanel board;

    private KnightPanel getPanelAtPosition(KnightPosition position) {
        int x = position.getX();
        int y = position.getY();
        Component component = board.getComponent(x * xAxis + y);
        return (KnightPanel) component;
    }

    private int[] input(String question, String splitCharacter) {
        int[] result;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(question);
                if (input == null)
                    return null;
                String[] dimenstions = input.split(splitCharacter);
                result = new int[]{
                        Integer.parseInt(dimenstions[0]),
                        Integer.parseInt(dimenstions[1])};
                break;
            } catch (
                    Exception ex) {
                continue;
            }

        }
        return result;
    }

    private int getGradeOfCell(KnightPosition position) {
        int counter = 0;
        for (int i = 0; i < iMovement.length; i++) {
            KnightPosition newPosition = getChangedPosition(position, i);
            if (isSafe(newPosition) && !isPanelVisited(newPosition)) {
                counter++;
            }
        }
        return counter;
    }

    private KnightPosition getChangedPosition(KnightPosition position, int moveIndex) {
        return new KnightPosition(
                position.getX() + iMovement[moveIndex],
                position.getY() + jMovement[moveIndex]);
    }

    private void calculateSolution(KnightPosition initialPosition) {
        int minGrade;
        int minIndex;
        boolean isLastStep = false;
        boolean hasSolution;
        for (int j = 0; j < xAxis * yAxis; j++) {
            minGrade = Integer.MAX_VALUE;
            minIndex = -1;
            isLastStep = j == (xAxis * yAxis) - 1;
            for (int i = 0; i < iMovement.length; i++) {
                KnightPosition newPosition = getChangedPosition(initialPosition, i);
                if (isSafe(newPosition) && !isPanelVisited(newPosition)) {
                    int grade = getGradeOfCell(newPosition);
                    if (minGrade > grade && grade != 0||isLastStep) {
                        minGrade = grade;
                        minIndex = i;
                    }
                }
            }
            hasSolution = minIndex != -1;
            if (hasSolution || isLastStep) {

                initialPosition = getChangedPosition(initialPosition, minIndex);
                moveSequance.add(initialPosition);
            }
        }

    }

    private boolean isPanelVisited(KnightPosition position) {
        boolean isVisited = moveSequance.contains(position);
        return isVisited;
    }

    private boolean isSafe(KnightPosition position) {
        return position.getX() >= 0 && position.getY() >= 0
                && position.getX() < xAxis && position.getY() < yAxis;
    }

    private ActionListener onButtonClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            KnightPanel source = (KnightPanel) e.getSource();
            isGameStarted = true;
            source.visit();
            System.out.println("CLICKED ON " + source.getIndex());
            System.out.println(getPanelAtPosition(source.getIndex()).getIndex());
        }
    };

    public KnightGame(String title) throws HeadlessException {
        super(title);
        int[] arr = input("Enter the dimensions", "x");
        if (arr == null)
            return;
        xAxis = arr[0];
        yAxis = arr[1];
        board = new JPanel(new GridLayout(xAxis, yAxis));

        for (int i = 0; i < xAxis; i++)
            for (int j = 0; j < yAxis; j++)
                board.add(new KnightPanel((i + j) % 2 == 1, i, j, onButtonClick));

        this.setLayout(new BorderLayout());
        this.add(board, BorderLayout.CENTER);
        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KnightPosition position = moveSequance.get(currentIndex++);
                getPanelAtPosition(new KnightPosition(position.getX(), position.getY())).visit();
            }
        });
        this.add(nextBtn, BorderLayout.PAGE_END);

        this.setResizable(false);
        this.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setVisible(true);
        this.moveSequance.add(new KnightPosition(xAxis - 1, yAxis - 1));
        calculateSolution(new KnightPosition(xAxis - 1, yAxis - 1));
        System.out.println(moveSequance.toString());
    }
}
