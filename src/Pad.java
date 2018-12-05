import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Pad extends JButton {
    static Pad previous;
    private boolean colored;
    private boolean isVisited;
    private CustomPosition index;
    public Pad(boolean colored, int x, int y) {
        this.colored = colored;
        index = new CustomPosition(x, y);
        initPanel();
    }

    public Pad(boolean colored, int x, int y, ActionListener actionListener) {
        this.colored = colored;
        index = new CustomPosition(x, y);
        this.addActionListener(actionListener);
        initPanel();
    }

    public void visit(int turn) {
        if(previous!=null)
            previous.setIcon(null);
        this.setBackground(Color.YELLOW);
        try {
            this.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("knight.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        isVisited = true;
        this.setText(String.valueOf(turn));
        previous = this;
    }

    private void initPanel() {

        this.setBackground(colored ? Color.BLACK : Color.WHITE);
        this.setVisible(true);
    }

    public boolean isColored() {
        return colored;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public CustomPosition getIndex() {
        return index;
    }
}
