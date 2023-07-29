# Guess The Number Game

This is a simple Java command-line application that allows users to play a
number guessing game. The program generates a random number between 1 and 100,
and the user needs to guess that number within a limited number of attempts.

## How to Play

1. Clone the repository or download the source code.

2. Compile the `GuessNumber.java` file using any Java IDE or via the command
   line:

   ```
   javac GuessNumber.java
   ```

3. Run the compiled Java class:

   ```
   java GuessNumber
   ```

4. The game will start, and you will be prompted to guess the secret number.

5. Enter your guess and press Enter.

6. The program will provide feedback on whether your guess is correct, too high,
   or too low. It will also display the number of remaining attempts.

7. Continue guessing until you guess the correct number or run out of attempts.

8. If you guess the correct number, the program will congratulate you and
   display your current score and high score.

9. After each round, you will have the option to play again or quit.

## Game Rules

- The secret number is a random integer between 1 and 100.
- You start with 20 attempts to guess the number.
- For each incorrect guess, the number of attempts will decrease by 1.
- The game will inform you if your guess is too high or too low.
- If you guess the correct number within the given attempts, you win the round
  and your score is updated.
- Your score is based on the number of attempts remaining when you guess the
  correct number.
- The high score is the best score achieved across all rounds.
- You can play as many rounds as you like.

Enjoy playing the Guess The Number game! Feel free to modify the code or add new
features to enhance the gameplay...
