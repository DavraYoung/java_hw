import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
        if (previous != null)
            previous.setIcon(null);
        this.setBackground(Color.YELLOW);
        try {
            this.setIcon(getScaledImage("knight.png",getWidth(),getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        isVisited = true;
        this.setText(String.valueOf(turn));
        previous = this;
    }

    private void initPanel() {
        this.setBackground(colored ? new Color(210,150,20) : new Color(245,242,136));
        this.setVisible(true);
    }

    public boolean isColored() {
        return colored;
    }

    public void queen() {
        try {
            this.setIcon(getScaledImage("queen.png",getWidth(),getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.setText(String.valueOf(turn));

    }

    private ImageIcon getScaledImage(String path, int width, int height) throws IOException {
        Image img;
        img = (new ImageIcon(
                ImageIO.read(
                        getClass().getResource(path)))).getImage();
        Image newimg = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public CustomPosition getIndex() {
        return index;
    }
}
