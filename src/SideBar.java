import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBar extends JPanel {

    private ActionListener onSubmitAction;

    private String[] generateSizeList() {
        String[] result = new String[95];
        for (int i = 0; i < 95; i++)
            result[i] = String.format("%s x %s", String.valueOf(i+5), String.valueOf(i+5));
        return result;
    }

    JComboBox sizeList;
    JComboBox gameList;
    private JButton submitButton;
    private JButton exitButton;
    private JLabel inputLabel;
    private JButton nextBtn;

    public SideBar(ActionListener submitAction,ActionListener changeGameAction,ActionListener nextAction,int size,int gameIndex) {

        sizeList = new JComboBox<>(generateSizeList());
        gameList = new JComboBox<>(new String[]{"Board's Tour", "Queen"});
        nextBtn = new JButton("Next");
        exitButton = new JButton("Exit");
        inputLabel = new JLabel("Choose size N:");

        JPanel comboBoxes = new JPanel(new BorderLayout());
        comboBoxes.add(sizeList,BorderLayout.NORTH);
        comboBoxes.add(gameList,BorderLayout.CENTER);

        JPanel buttonBoxes = new JPanel(new BorderLayout());
        buttonBoxes.add(exitButton,BorderLayout.EAST);
        buttonBoxes.add(nextBtn,BorderLayout.WEST);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(100,500);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(inputLabel,BorderLayout.NORTH);
        mainPanel.add(comboBoxes,BorderLayout.CENTER);
        mainPanel.add(buttonBoxes,BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
        nextBtn.addActionListener(nextAction);
        sizeList.setSelectedIndex(size-5);
        gameList.setSelectedIndex(gameIndex);
        sizeList.addActionListener(submitAction);
        gameList.addActionListener(changeGameAction);

    }

}
