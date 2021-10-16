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
                    displayVictor();
                }

            });

            add(buttons[i]);
        }
    }


    public void displayVictor() {

        if (checkForWinner()) {
            System.out.println("winner found");
            if (playerMark == 'x') playerMark = 'o';
            else playerMark = 'x';

            JOptionPane pane = new JOptionPane();
            int dialogResult = JOptionPane.showConfirmDialog(pane, "Game Over. " + playerMark + " wins. Would you like to play again?", "Game over.", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                resetTheButtons();
            } else {
                System.exit(0);
            }
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
        for (int i = 0; i <= 8; i++) {
            if (buttons[i].getText().charAt(0) == '-') {
                return false;
            }
        }
        return true;
    }

    public boolean checkForWinner() {
        // COLUMNS
        //for (int j = 0; j < 3; j++) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText()) && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
        }
        //}

        // ROWS
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText()) && buttons[i].getText().charAt(0) != '-') {
                return true;
            }
        }

        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && buttons[0].getText().charAt(0) != '-') {
            return true;
        }

        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && buttons[2].getText().charAt(0) != '-') {
            return true;
        }

        return false;
    }


    public static void main(String[] args) {
        JFrame window = new JFrame("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new tttGame());
        window.setBounds(500, 500, 500, 500);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
}
