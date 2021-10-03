import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tttGame extends JPanel {

    char playerMark = 'x';
    JButton[] buttons = new JButton[9];

    public tttGame() {
        setLayout(new GridLayout(3, 3));
        initializeButtons();
    }

    public void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);
            buttons[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton) e.getSource();
                    buttonClicked.setText(String.valueOf(playerMark));

                    if (playerMark == 'x') {
                        playerMark = 'o';
                        buttonClicked.setBackground(Color.CYAN);
                    } else {
                        playerMark = 'x';
                        buttonClicked.setBackground(Color.ORANGE);
                    }
                }

            });

            add(buttons[i]);
        }
    }


    public void displayVictor() {

        if (checkForWinner() == true) {
            if (playerMark == 'x') playerMark = 'o';
            else playerMark = 'x';

            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. " + playerMark + " wins. Would you like to play again?", "Game over.", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        } else if (checkDraw()) {

            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Draw. Play again?", "Game over.", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) resetTheButtons();
            else System.exit(0);
        }
    }

    private void resetTheButtons() {
        for (int i = 0; i < 9; i++) {
            playerMark = 'x';
            buttons[i].setText("-");
            buttons[i].setBackground(Color.white);
        }
    }

    public boolean checkDraw() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().charAt(0) == '-') {
                return false;
            }
        }
        return true;
    }

    public boolean checkForWinner() {
    }
}
