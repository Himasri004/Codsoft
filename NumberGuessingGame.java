import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private int numberToGuess;
    private int attemptsLeft;
    private int score;
    private final int maxAttempts = 7;

    private JLabel instructionLabel;
    private JTextField guessInput;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JButton playAgainButton;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen

        initComponents();
        newRound();
    }

    private void initComponents() {
        setLayout(new GridLayout(7, 1));

        instructionLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(instructionLabel);

        guessInput = new JTextField();
        guessInput.setHorizontalAlignment(JTextField.CENTER);
        add(guessInput);

        guessButton = new JButton("Submit Guess");
        add(guessButton);

        feedbackLabel = new JLabel("", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts left: " + maxAttempts, SwingConstants.CENTER);
        add(attemptsLabel);

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        add(scoreLabel);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setEnabled(false);
        add(playAgainButton);

        // Button listeners
        guessButton.addActionListener(e -> processGuess());
        playAgainButton.addActionListener(e -> newRound());
    }

    private void newRound() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
        attemptsLeft = maxAttempts;

        instructionLabel.setText("Guess a number between 1 and 100");
        feedbackLabel.setText("");
        guessInput.setText("");
        guessInput.setEditable(true);
        attemptsLabel.setText("Attempts left: " + attemptsLeft);
        playAgainButton.setEnabled(false);
        guessButton.setEnabled(true);
    }

    private void processGuess() {
        String input = guessInput.getText();
        try {
            int guess = Integer.parseInt(input);
            attemptsLeft--;

            if (guess == numberToGuess) {
                feedbackLabel.setText("🎉 Correct! You guessed the number.");
                score++;
                endRound(true);
            } else if (guess < numberToGuess) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            attemptsLabel.setText("Attempts left: " + attemptsLeft);

            if (attemptsLeft <= 0 && guess != numberToGuess) {
                feedbackLabel.setText("❌ Out of attempts! The number was " + numberToGuess + ".");
                endRound(false);
            }

        } catch (NumberFormatException e) {
            feedbackLabel.setText("⚠ Please enter a valid number!");
        }

        guessInput.setText("");
    }

    private void endRound(boolean won) {
        guessInput.setEditable(false);
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        scoreLabel.setText("Score: " + score);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGuessingGameGUI().setVisible(true);
        });
    }
}
