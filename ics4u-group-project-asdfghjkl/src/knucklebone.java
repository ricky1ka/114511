import javax.swing.*;
import javax.swing.Timer;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class knucklebone extends JFrame {
    // Icons to display on the board buttons
    ImageIcon originDiceIcon0 = new ImageIcon("src/dice0.jpg");
    Image image0 = originDiceIcon0.getImage(); // Transform it
    Image diceIcon0Iamge = image0.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon0 = new ImageIcon(diceIcon0Iamge); // Convert back to an ImageIcon

    ImageIcon originDiceIcon1 = new ImageIcon("src/dice1.jpg");
    Image image1 = originDiceIcon1.getImage(); // Transform it
    Image diceIcon1Iamge = image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon1 = new ImageIcon(diceIcon1Iamge); // Convert back to an ImageIcon

    ImageIcon originDiceIcon2 = new ImageIcon("src/dice2.jpg");
    Image image2 = originDiceIcon2.getImage(); // Transform it
    Image diceIcon2Iamge = image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon2 = new ImageIcon(diceIcon2Iamge); // Convert back to an ImageIcon

    ImageIcon originDiceIcon3 = new ImageIcon("src/dice3.jpg");
    Image image3 = originDiceIcon3.getImage(); // Transform it
    Image diceIcon3Iamge = image3.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon3 = new ImageIcon(diceIcon3Iamge); // Convert back to an ImageIcon

    ImageIcon originDiceIcon4 = new ImageIcon("src/dice4.jpg");
    Image image4 = originDiceIcon4.getImage(); // Transform it
    Image diceIcon4Iamge = image4.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon4 = new ImageIcon(diceIcon4Iamge); // Convert back to an ImageIcon

    ImageIcon originDiceIcon5 = new ImageIcon("src/dice5.jpg");
    Image image5 = originDiceIcon5.getImage(); // Transform it
    Image diceIcon5Iamge = image5.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon5 = new ImageIcon(diceIcon5Iamge); // Convert back to an ImageIcon

    ImageIcon originDiceIcon6 = new ImageIcon("src/dice6.jpg");
    Image image6 = originDiceIcon6.getImage(); // Transform it
    Image diceIcon6Iamge = image6.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Scale it to the size you need
    ImageIcon diceIcon6 = new ImageIcon(diceIcon6Iamge); // Convert back to an ImageIcon
    ArrayList<ImageIcon> iconLists = new ArrayList<ImageIcon>(
            List.of(diceIcon1, diceIcon2, diceIcon3, diceIcon4, diceIcon5, diceIcon6));

    private JPanel background;
    private JButton button131;
    private JButton button111;
    private JButton button121;
    private JButton button213;
    private JButton button223;
    private JButton button233;
    private JButton button112;
    private JButton button123;
    private JButton button122;
    private JButton button132;
    private JButton button133;
    private JButton button113;
    private JButton button212;
    private JButton button211;
    private JButton button221;
    private JButton button231;
    private JButton button232;
    private JButton button222;
    private JButton dicebutton;
    private JLabel diceimage;
    private JLabel score1;
    private JLabel score2;
    // arrayList of buttons
    ArrayList<JButton> player1 = new ArrayList<JButton>(
            List.of(button111, button112, button113, button121, button122, button123, button131, button132, button133)
    );
    ArrayList<JButton> player2 = new ArrayList<JButton>(
             List.of(button211, button212, button213, button221, button222, button223, button231, button232, button233)
    );
    private int[][] player1Board;
    private int[][] player2Board;
    private int player1Score;
    private int player2Score;
    String playerRound;
    boolean gameStarted;
    // HELP

    public knucklebone() {
        this.player1Board = new int[3][3];
        this.player2Board = new int[3][3];
        this.player1Score = 0;
        this.player2Score = 0;
        this.gameStarted = false;
        this.playerRound = "";

         this.dicebutton.setText("Start");
		setTitle("Welcome"); // this is the main window
		setDefaultCloseOperation(EXIT_ON_CLOSE); // close window on exit
		setSize(1200, 800); // set window size
		setVisible(true); // show the window
		setContentPane(background);

		dicebutton.addActionListener(new ActionListener() {
//			private Timer timer;
//			private int rollCount = 0;

			public void actionPerformed(ActionEvent e) {
				if (!knucklebone.this.gameStarted) {
					knucklebone.this.startGame();
                    knucklebone.this.score1.setText(String.valueOf(knucklebone.this.player1Score));
                    knucklebone.this.score2.setText(String.valueOf(knucklebone.this.player2Score));
					knucklebone.this.player1Board(false);
					knucklebone.this.player2Board(false);
				} else {
					// if game starts: roll the dice
					int value = knucklebone.this.rollDice();
					if (knucklebone.this.playerRound == "Player 1") {
						// only allow Play 1 board buttons
						// clear listeners for board 1
						for (JButton cellButton: knucklebone.this.player1) {
							ActionListener[] listeners = cellButton.getActionListeners();
					        for (ActionListener listener : listeners) {
					        	cellButton.removeActionListener(listener);
					        }
						}
						knucklebone.this.player1Board(true);
						knucklebone.this.player2Board(false);
                        System.out.println("0");
						knucklebone.this.addPlay1ButtonListener(value);
					} else {
						// clear listeners for board 2
						for (JButton cellButton: knucklebone.this.player2) {
							ActionListener[] listeners = cellButton.getActionListeners();
					        for (ActionListener listener : listeners) {
					        	cellButton.removeActionListener(listener);
					        }
						}
						// only allow Play 2 board buttons
						knucklebone.this.player1Board(false);
						knucklebone.this.player2Board(true);
						knucklebone.this.addPlay2ButtonListener(value);
					}
				}
			}
		});
    }

    public int rollDice() {
        Random r = new Random();
        int roll = r.nextInt(1, 7);
        ImageIcon icon = new ImageIcon("src/dice" + roll + ".jpg");
        diceimage.setIcon(icon);
        return roll;
    }

    public void startGame() {
        this.dicebutton.setText("Shuffle");
        this.button131.setIcon(this.diceIcon0);
        this.button111.setIcon(this.diceIcon0);
        this.button121.setIcon(this.diceIcon0);
        this.button213.setIcon(this.diceIcon0);
        this.button223.setIcon(this.diceIcon0);
        this.button233.setIcon(this.diceIcon0);
        this.button112.setIcon(this.diceIcon0);
        this.button123.setIcon(this.diceIcon0);
        this.button122.setIcon(this.diceIcon0);
        this.button132.setIcon(this.diceIcon0);
        this.button133.setIcon(this.diceIcon0);
        this.button113.setIcon(this.diceIcon0);
        this.button212.setIcon(this.diceIcon0);
        this.button211.setIcon(this.diceIcon0);
        this.button221.setIcon(this.diceIcon0);
        this.button231.setIcon(this.diceIcon0);
        this.button232.setIcon(this.diceIcon0);
        this.button222.setIcon(this.diceIcon0);
        this.playerRound = "Player 1";
        this.gameStarted = true;
    }

    // Player 1 Board buttons
    public void player1Board(boolean enable) {
        for (JButton cellButton : this.player1) {
            cellButton.setEnabled(enable);
        }
    }

    public void player2Board(boolean enable) {
        for (JButton cellButton : this.player2) {
            cellButton.setEnabled(enable);
        }
    }

    public void placeDice(int DiceValue) {

    }

    public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new knucklebone();
			}
		});

//        knucklebone k = new knucklebone();
//        int[][] board1 = {{3,0,0},{1,0,0},{3,0,0}};
//        k.player1Board = board1;
//        System.out.println(k.getPlayer1BoardScore());

    }

    public void addPlay1ButtonListener(int diceValue) {
        for(int i = 0; i < this.player1.size(); i++) {
            System.out.println("1.5");
            final int index = i;
            JButton cur = this.player1.get(i);
            cur.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // dealing with the 2D board array
                    int row = index % 3;
                    int column  = index / 3;
                    System.out.println("1");
                    if (knucklebone.this.checkBoard1CellAvailability(column, row)) {
                        cur.setIcon(knucklebone.this.getIconByDiceValue(diceValue));
                        knucklebone.this.player1Board[row][column] = diceValue;
                        System.out.println(row + " : " + column);
                        // reduce opponent's score if possible
                        knucklebone.this.removeDiceFromBoard2(column, diceValue);
                        // calculate both score based on current dices on the board
                        knucklebone.this.player1Score = knucklebone.this.getPlayer1BoardScore();
                        knucklebone.this.score1.setText(String.valueOf(knucklebone.this.player1Score));
                        knucklebone.this.player2Score =  knucklebone.this.getPlayer2BoardScore();
                        knucklebone.this.score2.setText(String.valueOf(knucklebone.this.player2Score));
                        System.out.println(knucklebone.this.player1Score + knucklebone.this.player2Score);
                        if (knucklebone.this.checkBoard1AllFilled()) {
                            knucklebone.this.endGame();
                        } else {
                            // disabled the buttons on board 1
                            knucklebone.this.player1Board(false);
                            knucklebone.this.playerRound = "Player 2";
                        }
                    } else {
                        // JOptionPane.showMessageDialog("Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }

    public void addPlay2ButtonListener(int diceValue) {
        for(int i = 0; i < this.player2.size(); i++) {
            final int index = i;
            JButton cur = this.player2.get(i);
            cur.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // dealing with the 2D board array
                    int row = index % 3;
                    int column  = index / 3;
                    if (knucklebone.this.checkBoard2CellAvailability(column, row)) {
                        System.out.println("6");
                        cur.setIcon(knucklebone.this.getIconByDiceValue(diceValue));
                        knucklebone.this.player2Board[row][column] = diceValue;
                        System.out.println(row + " : " + column);
                        // reduce opponent's score if possible
                        knucklebone.this.removeDiceFromBoard1(column, diceValue);
                        // calculate both score based on current dices on the board
                        knucklebone.this.player1Score = knucklebone.this.getPlayer1BoardScore();
                        knucklebone.this.score1.setText(String.valueOf(knucklebone.this.player1Score));
                        knucklebone.this.player2Score =  knucklebone.this.getPlayer2BoardScore();
                        knucklebone.this.score2.setText(String.valueOf(knucklebone.this.player2Score));
                        if (knucklebone.this.checkBoard2AllFilled()) {
                            knucklebone.this.endGame();
                        } else {
                            // disabled the buttons on board 1
                            System.out.println("7");
                            knucklebone.this.player2Board(false);

                            knucklebone.this.playerRound = "Player 1";
                        }
                    } else {
                        // JOptionPane.showMessageDialog("Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }

    public ImageIcon getIconByDiceValue(int diceValue) {
        return this.iconLists.get(diceValue - 1);
    }

    public int getPlayer1BoardScore() {
        int score = 0;
        // go over each column

        /*
         * [3] [] []
         * [1] [] []
         * [3] [] []
         */
        for(int j = 0; j < this.player1Board[0].length; j++) {
            // go over each cell on the same column
            ArrayList<Integer> duplicateList = new ArrayList<Integer>();
            for(int i = 0; i < this.player1Board.length; i++) { // j = 0; i = 0
                if (this.player1Board[i][j] == 0) {
                    break;
                } else {
                    System.out.println(score + " = going over: " + this.player1Board[i][j]);
                    // check the number of same-number-dice
                    int cur = i; // 0
                    int number = 1;
                    while(cur + 1 < this.player1Board.length) {
                        cur += 1; // Move to the next row
                        // compare the current with the selected
                        if (this.player1Board[cur][j] == this.player1Board[i][j]) {
                            System.out.println("Found Duplicate");
                            number++;
                        }
                    }
                    // special case for the last element
                    if (i == 2 && duplicateList.contains(this.player1Board[i][j])) {
                        break;
                    }
                    // add score based on
                    if (number == 3) {
                        System.out.println("1");
                        score += 3 * this.player1Board[i][j] * 3;
                        break;
                        // when running the first number that has another duplicate after, we calcuate and add
                    } else if ((!duplicateList.contains(this.player1Board[i][j])) && number == 2) {
                        score += 2 * this.player1Board[i][j] * 2;
                        duplicateList.add(this.player1Board[i][j]);
                    } else {
                        System.out.println(duplicateList);
                        System.out.println(number+":" +!duplicateList.contains(this.player1Board[i][j]) + ":" + (number == 2));
                        System.out.println("2");
                        score += this.player1Board[i][j];
                    }
                }
            }
        }
        return score;
    }

    // HOMEWORK:

    // check dice existence
    public boolean checkBoard1CellAvailability(int column, int row) {
        return this.player1Board[row][column] == 0;
    }

    // check dice existence
    public boolean checkBoard2CellAvailability(int column, int row) {
        return this.player2Board[row][column] == 0;
    }

    // remove the existence of dice on board 1 by position
    public void removeDiceFromBoard1Pos(int column, int row) {

        this.player1Board[row][column] = 0;
        int position = column * 3 + row ;
        System.out.println(column + ":" + row +position);
        this.player1.get(position).setIcon(diceIcon0);
    }

    // remove the existence of dice on board 1 by position
    public void removeDiceFromBoard2Pos(int column, int row) {
        this.player2Board[row][column] = 0;
        int position = column * 3 + row ;
        this.player2.get(position).setIcon(diceIcon0);
    }

    // Remove potential Duplicate number in column
    public void removeDiceFromBoard1(int column, int diceNumber) {
        for (int i = 0; i < this.player1Board[0].length; i++) {
            if (this.player1Board[i][column] == diceNumber) {
                System.out.println("yes"+ this.player1Board[i][column]);
                this.removeDiceFromBoard1Pos(column, i);
            }
        }
    }

    // Remove potential Duplicate number in column
    public void removeDiceFromBoard2(int column, int diceNumber) {
        for (int i = 0; i < this.player2Board[0].length; i++) {
            if (this.player2Board[i][column] == diceNumber) {
                System.out.println("no"+ this.player2Board[i][column]);
                this.removeDiceFromBoard2Pos(column, i);
            }
        }
    }

    // check if the board1 is all filled
    public boolean checkBoard1AllFilled() {
        boolean result = true;
        for (int i = 0; i < this.player1Board.length; i++) {
            for (int j = 0; j < this.player1Board[i].length; j++) {
                if (this.player1Board[i][j] == 0) {
                    return false;
                }
            }
        }
        return result;
    }

    // check if the board2 is all filled
    public boolean checkBoard2AllFilled() {
        boolean result = true;
        for (int i = 0; i < this.player2Board.length; i++) {
            for (int j = 0; j < this.player2Board[i].length; j++) {
                if (this.player2Board[i][j] == 0) {
                    return false;
                }
            }
        }
        return result;
    }

    public void endGame() {
        // announce winner
        // clear the board data
        this.player1Board = new int[3][3];
        this.player2Board = new int[3][3];

        this.gameStarted = false;
        this.playerRound = "";
        // clear the board buttons
        for(JButton cur: this.player1) {
            cur.setIcon(null);
        }
        for(JButton cur: this.player2) {
            cur.setIcon(null);
        }
        // clear the scores
        this.player1Score = 0;
        this.player2Score = 0;
        // change the diceButton text to 'start'
        this.dicebutton.setText("Start");
        // change score label to empty
        this.diceimage.setIcon(null);
    }

    /*
     *  Step 1: Roll the dice => get number N
     *  Step 2: User pick one cell at postion (i, j)
     *  	2a: before placing, check dice existence
     *  Step 3: After placing the dice,
     *  	3a. Check opponent's board on column j to see any dice with number N exist
     *  	3b. If exist: remove the dice score from the board
     *  	3c. If exist: remove the dice Icon on that button
     *  Step 4a: Calculate player board score
     *  	 4a1: Calculate each row by counting the number of same-number existence
     *  	 4ac: update player score label
     *  	 4b. Calculate opponent board score
     *  		 update opponent score label
     *  Step 5a: Check If player board is full or not
     *  	 5b: If so, compare the scores
     *  	 5c: announce the winner and ends the game
     */

    public int getPlayer2BoardScore() {
        int score = 0;
        // go over each column

        /*
         * [3] [] []
         * [1] [] []
         * [3] [] []
         */
        for(int j = 0; j < this.player2Board[0].length; j++) {
            // go over each cell on the same column
            ArrayList<Integer> duplicateList = new ArrayList<Integer>();
            for(int i = 0; i < this.player2Board.length; i++) { // j = 0; i = 0
                if (this.player2Board[i][j] == 0) {
                    break;
                } else {
                    System.out.println(score + " = going over: " + this.player2Board[i][j]);
                    // check the number of same-number-dice
                    int cur = i; // 0
                    int number = 1;
                    while(cur + 1 < this.player2Board.length) {
                        cur += 1; // Move to the next row
                        // compare the current with the selected
                        if (this.player2Board[cur][j] == this.player2Board[i][j]) {
                            System.out.println("Found Duplicate");
                            number++;
                        }
                    }
                    // special case for the last element
                    System.out.println((i == 2) + "---" + duplicateList.contains(this.player2Board[i][j]));
                    if (i == 2 && duplicateList.contains(this.player2Board[i][j])) {
                        break;
                    }
                    // add score based on
                    if (number == 3) {
                        System.out.println("1");
                        score += 3 * this.player2Board[i][j] * 3;
                        break;
                        // when running the first number that has another duplicate after, we calcuate and add
                    } else if ((!duplicateList.contains(this.player2Board[i][j])) && number == 2) {
                        System.out.println("Adding");
                        score += 2 * this.player2Board[i][j] * 2;
                        duplicateList.add(this.player2Board[i][j]);
                    } else {
                        System.out.println(duplicateList);
                        System.out.println(number+":" +!duplicateList.contains(this.player2Board[i][j]) + ":" + (number == 2));
                        System.out.println("2");
                        score += this.player2Board[i][j];
                    }
                }
            }
        }
        return score;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}