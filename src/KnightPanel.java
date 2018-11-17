import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KnightPanel extends JButton {
    private boolean colored;
    private boolean isVisited;
    private KnightPosition index;
    public KnightPanel(boolean colored,int x,int y) {
        this.colored = colored;
        index = new KnightPosition(x,y);
        initPanel();
    }
    public KnightPanel(boolean colored,int x,int y,ActionListener actionListener) {
        this.colored = colored;
        index = new KnightPosition(x,y);
        this.addActionListener(actionListener);
        initPanel();
    }
    public void visit(){
        setBackground(new Color(0xA4FF91));
        isVisited=true;
    }
    private void initPanel() {

        this.setBackground(colored?Color.BLACK:Color.WHITE);
        this.setVisible(true);
    }

    public boolean isColored() {
        return colored;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public KnightPosition getIndex() {
        return index;
    }
}
