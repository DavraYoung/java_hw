import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainWindow extends JFrame {
    private int n = 5;
    private int turn = 0;
    private final int PANEL_HEIGHT = 800;
    private final int PANEL_WIDTH = 1000;
    private KnightTour knightTour;
    private NQueens queens;
    private CustomPosition initialPosition;
    private JPanel board;
    private SideBar sideBar;
    private JSplitPane splitPane;
    private Game currentGame = Game.KnightTour;
    private enum Game {
        KnightTour,
        Queen
    }

    private void reInitFrame(Component component) {
        MainWindow frame = (MainWindow) SwingUtilities.getRoot(component);
        frame.getContentPane().removeAll();
        frame.initFrame();
        initialPosition = null;
        turn = 0;
    }

    private void disableAllPads(ActionListener listener) {
        for (int i = 0; i < n * n; i++)
            ((Pad) board.getComponent(i)).removeActionListener(listener);
    }

    private Pad getPadAtPosition(CustomPosition position) {
        int x = position.getX();
        int y = position.getY();
        Component component = board.getComponent(x * n + y);
        return (Pad) component;
    }

    private Dimension getSizeOfPad(){
        return getPadAtPosition(new CustomPosition(0,0)).getSize();
    }

    class NextAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (initialPosition == null) {
                MainWindow frame = (MainWindow) SwingUtilities.getRoot((JButton) actionEvent.getSource());
                JOptionPane.showMessageDialog(frame, "Click on eny pad to set initial position!");
            } else {
                switch (currentGame) {
                    case KnightTour:
                        if (turn + 1 != knightTour.solutionSequance.size())
                            getPadAtPosition(knightTour.solutionSequance.get(++turn)).visit(turn);
                    case Queen:
                        queens = new NQueens(n);
                        queens.solve(n);
                }
            }

        }
    }

    class GameChanged implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JComboBox box = (JComboBox) actionEvent.getSource();
            reInitFrame(box);
            switch ((String) Objects.requireNonNull(box.getSelectedItem())) {
                case "Board's Tour":
                    currentGame = Game.KnightTour;
                    break;
                case "Queen":
                    currentGame = Game.Queen;

                    break;
            }
        }
    }


    class SizeChanged implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            JComboBox box = (JComboBox) actionEvent.getSource();
            String size = (String) box.getSelectedItem();
            n = Integer.valueOf(size.split("x")[0].trim());
            reInitFrame(box);
        }
    }


    class InitialPositionAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Pad pad = (Pad) actionEvent.getSource();
            disableAllPads(this);
            initialPosition = pad.getIndex();
            pad.visit(0);
            switch (currentGame) {
                case KnightTour:
                    System.out.println("n = " + n);
                    knightTour = new KnightTour(n);
                    knightTour.solve(initialPosition);
                    if (knightTour.solutionSequance.size() != n * n) {
                        MainWindow frame = (MainWindow) SwingUtilities.getRoot((JButton) actionEvent.getSource());
                        JOptionPane.showMessageDialog(frame, "No solution for that position!");
                    }

            }
            System.out.println("Clicked on:" + initialPosition);


        }
    }



    void fillTheBoard() {
        InitialPositionAction action = new InitialPositionAction();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board.add(new Pad((i + j) % 2 == 1, i, j, action));
    }

    private void initBoard() {
        board = new JPanel(new GridLayout(n, n));
        fillTheBoard();
        board.setSize(getPadAtPosition(new CustomPosition(0,0)).getSize());
    }

    private void initSideBar() {
        sideBar = new SideBar(new SizeChanged(), new GameChanged(), new NextAction(), n, currentGame.ordinal());
        sideBar.setVisible(true);
    }

    private void initFrame() {

        initSideBar();
        initBoard();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, board, sideBar);
        splitPane.setVisible(true);
        this.add(splitPane);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setVisible(true);
        splitPane.setEnabled(false);
        splitPane.setDividerLocation(.8);
    }


    MainWindow(String title) throws HeadlessException {
        super(title);
        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            System.out.println("No such UI template!");
        }
        initFrame();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        CustomPosition position = new CustomPosition(positionX, positionY);
//        this.solutionSequance.add(position);
//        solve(position);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        System.out.println(solutionSequance.toString());
    }
}
