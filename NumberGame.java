/* Task 1 : Number Game */
/* Project Overveiw
1. Generate a random number within a specified range, such as 1 to 100.
2. Prompt the user to enter their guess for the generated number.
3. Compare the user's guess with the generated number and provide feedback on whether the guess
is correct, too high, or too low.
4. Repeat steps 2 and 3 until the user guesses the correct number.
5. Limit the number of attempts the user has to guess the number.
6. Add the option for multiple rounds, allowing the user to play again.
7. Display the user's score, which can be based on the number of attempts taken or rounds won.*/
import java.util.Random;
import java.util.Scanner;
public class NumberGame
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerlimit = 1;
        int upperlimit = 100;
        int maxAttempts = 3;
        int rounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(upperlimit - lowerlimit + 1) + lowerlimit;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nNew round! Guess the number between " + lowerlimit + " and " + upperlimit);

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber)
                {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                }
                else if (userGuess < targetNumber)
                {
                    System.out.println("Too low! Try again.");
                }
                else
                {
                    System.out.println("Too high! Try again.");
                }
            }

            totalAttempts += attempts;
            rounds++;

            System.out.println("\nThe correct number was: " + targetNumber);
            System.out.println("Round summary - Attempts: " + attempts);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
        }

        System.out.println("\nGame Over! You played " + rounds + " rounds with a total of " + totalAttempts + " attempts.");
        scanner.close();
    }
}
