import java.util.Scanner;

// I/O
class Prompter {
    private Game game;

    // When we take game as a parameter, we don't need to create a setter
    public Prompter(Game game) {
        // this.game refers to the private variable game
        // game refers to the argument inside the constructor
        this.game = game;
    }

    public boolean promptForGuess() {
        Scanner scanner = new Scanner(System.in);
        boolean isHit = false;
        // To keep track of the state of the method ("Did we get an acceptable value?")
        boolean isAcceptable = false;

        do {
            System.out.print("Enter a letter: ");

            /*
             * Store input of user inside guessInput scanner.nextLine() is for Strings
             * scanner.nextInt() is for integers
             */

            String guessInput = scanner.nextLine();

            try {
                isHit = game.applyGuess(guessInput);
                // isAcceptable is true, because inside the try loop, the input is valid
                isAcceptable = true;
            } catch (IllegalArgumentException iae) {
                System.out.printf("%s. Please try again. %n ", iae.getMessage());
            }
        } while (!isAcceptable);
        return isHit;

    }

    public void displayProgress() {
        System.out.printf("You have %d tries left to solve: %s%n", game.getRemainingTries(), game.getCurrentProgress());
    }

    public void displayOutcome() {
        if (game.isWon()) {
            System.out.printf("Congrats! You won with %d tries remaining %n", game.getRemainingTries());
        } else {
            System.out.printf("Sorry! You lost the game. The answer was %s %n", game.getAnswer());
        }

    }
}
