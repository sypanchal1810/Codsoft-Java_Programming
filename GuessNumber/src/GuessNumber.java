import java.util.*;

public class GuessNumber {
    // Variables
    static final int RANGE = 100;
    private int secretNumber = (int) (Math.random() * RANGE) + 1;
    private int score = 20;
    private int highscore = 0;
    private boolean play;
    private boolean isCorrectGuess;

    // Non-parametrized Constructor
    public GuessNumber() {
    }

    // Start the game
    public void startGame() {
        System.out.print("\nGuess The Number Between 1 and 100 \t\t");
        System.out.print("\nAttempts: " + getScore() + " \t HighScore: " + getHighscore() + " \t\t");
        System.out.print("\nStart Guessing The Number...\t");
        setPlay(true);
        setIsCorrectGuess(false);
    }

    // Check the guessed number
    public void checkGuessedNumber(int guessedNumber) {
        // When guessedNumber matched
        if (guessedNumber == getSecretNumber()) {
            if (getHighscore() < getScore()) {
                setHighscore(getScore());
            }
            setIsCorrectGuess(true);
            System.out.print("\nCongratulations... Guessed Number is Correct!!");
            System.out.print("\nAttempts: " + getScore() + " \t HighScore: " + getHighscore() + " \t\t");
            return;
        }

        // When guessedNumber didn't match
        if (getScore() > 1) {
            if (guessedNumber > getSecretNumber()) {
                setScore(getScore() - 1);
                System.out.print("\nGuessed Number is Too High!!");
                System.out.print("\nAttempts: " + getScore() + " \t HighScore: " + getHighscore() + " \t\t");
                System.out.print("\nGuess Again...\t");
            } else {
                setScore(getScore() - 1);
                System.out.print("\nGuessed Number is Too Low!!");
                System.out.print("\nAttempts: " + getScore() + " \t HighScore: " + getHighscore() + " \t\t");
                System.out.print("\nGuess Again...\t");
            }
        } else {
            // When there is no attempt remaining
            setScore(0);
            setIsCorrectGuess(false);
            System.out.print("\nOops! You didn't guess the correct number. The correct number is " + getSecretNumber());
        }
    }

    // Play the game again
    public void playAgain() {
        setSecretNumber((int) (Math.random() * RANGE) + 1);
        setScore(20);
    }

    // Getters and Setters
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    public boolean getPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean getIsCorrectGuess() {
        return isCorrectGuess;
    }

    public void setIsCorrectGuess(boolean isCorrectGuess) {
        this.isCorrectGuess = isCorrectGuess;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        GuessNumber game = new GuessNumber();
        game.startGame();

        while (game.play) {
            while (!game.getIsCorrectGuess()) {
                int guessedNumber = scn.nextInt();
                game.checkGuessedNumber(guessedNumber);
            }

            // Ask user to play again or quit
            System.out.print("\n\nDo you want to play again? (yes/no): ");
            String playAgainInput = scn.next();

            if (!playAgainInput.equalsIgnoreCase("yes")) {
                game.setPlay(false);
            } else {
                game.playAgain();
                game.startGame();
            }
            System.out.println();
        }
        scn.close();
    }
}
