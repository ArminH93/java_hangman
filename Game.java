// Game Logic
class Game {
    public static final int MAX_MISSES = 7;
    private String answer;
    private String hits;
    private String misses;

    // Constructor
    // Initializing empty variables that holds the letters
    public Game(String answer) {
        this.answer = answer.toLowerCase();
        hits = "";
        misses = "";
    }

    public String getAnswer() {
        return answer;
    }

    // Only accessible to the class itself and not by outside
    // The normalizeGuess method is for input validation
    private char normalizeGuess(char letter) {
        // Check if the input is a letter or something else like a number or a symbol
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("A letter is required");
        }
        // Transform the letter to a lowercase letter.
        letter = Character.toLowerCase(letter);
        // Check to see if the guessed letter has already been guessed
        if (misses.indexOf(letter) != -1 || hits.indexOf(letter) != -1) {
            throw new IllegalArgumentException(letter + " has already been guessed");
        }
        return letter;
    }

    public boolean applyGuess(String letters) {
        // Check if input is empty
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found");
        }
        // Calling the original method to get all the logic of the method without
        // duplicating it (method overloading)
        return applyGuess(letters.charAt(0));
    }

    // Method to check, if the guess was correct (hits) or not correct (misses)
    public boolean applyGuess(char letter) {
        letter = normalizeGuess(letter);
        // Check if a guess has already been made

        /*
         * As long as the index of the character is greater or equal to 0, it's in the
         * answer. If it is not in the answer, the index shows -1. isHit is true, when
         * the indexOf the letter is NOT -1
         */

        boolean isHit = answer.indexOf(letter) != -1;
        if (isHit) {
            hits += letter;
        } else {
            misses += letter;
        }
        return isHit;
    }

    public int getRemainingTries() {
        return MAX_MISSES - misses.length();

    }

    public String getCurrentProgress() {
        String progress = "";

        // For each letter in the answer 'treehouse' display a dash (-) to show how long
        // the answer is
        for (char letter : answer.toCharArray()) {
            char display = '-';

            /*
             * If hits is true, the letter at that index should show up in the progress and
             * replace the dash (-)
             * 
             * hit is not a boolean, for that reason we cannot write if(hits) {...}
             */

            if (hits.indexOf(letter) != -1) {
                display = letter;
            }
            progress += display;
        }
        return progress;
    }

    public boolean isWon() {
        // Returning a String and checking that string if there are dashes at index -1
        // (dashes at index -1 = no dashes at all = the game is won)
        return getCurrentProgress().indexOf('-') == -1;
    }
}