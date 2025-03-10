import java.util.Random;
import java.util.Scanner;

public class GameRPS {
    public static final int PAPER = 0;
    public static final int SCISSORS = 1;
    public static final int ROCK = 2;

    public static int[] outcomes = new int[3];
    public static int[] moveHistory = new int[3];

    public static int getComputerChoice(Random random) {
        int frequentChoice = getFrequentPlayerMove();
        return (frequentChoice + 2) % 3; 
    }

    
    public static int getFrequentPlayerMove() {
        int maxIndex = 0;
        for (int i = 1; i < 3; i++) {
            if (moveHistory[i] > moveHistory[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

  
    public static int getResult(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) 
        return 2; 
        if ((playerChoice == ROCK && computerChoice == SCISSORS) ||
            (playerChoice == PAPER && computerChoice == ROCK) ||
            (playerChoice == SCISSORS && computerChoice == PAPER)) {
            return 0; 
        }
        return 1; 
    }

    
    public static void updateOutcomes(int result)
     {
        outcomes[result]++;
    }

    
    public static void updateMoveHistory(int playerChoice) 
    {
        moveHistory[playerChoice]++;
    }

    public static void displayResult(int result)
     {
        switch (result) {
             case 0 -> System.out.println("Congrats, You Won!");
              case 1 -> System.out.println("Ohh!! You Lost.");
            case 2 -> System.out.println("Oopss!! It's a Draw!");
        }
    }

    
    public static void displayStatistics()
     {
        System.out.println("\nGame Statistics:");
          System.out.println("Wins: " +outcomes[0]);
        System.out.println("Losses: " +outcomes[1]);
        System.out.println("Draws: " +outcomes[2]);
        System.out.println("Your move history:");
           System.out.println("Rock: " + moveHistory[ROCK] + ", Paper: " + moveHistory[PAPER] + ", Scissors: " + moveHistory[SCISSORS]);
    }


    public static String choiceToString(int choice) 
    {
        return switch (choice) {
            case ROCK -> "Rock";
            case PAPER -> "Paper";
            case SCISSORS -> "Scissors";
            default -> "Unknown";
        };
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        while (playAgain) {
            
            System.out.println("Enter your choice:");
            System.out.println("0 for Paper, 1 for Scissors, 2 for Rock");
            int playerChoice = sc.nextInt();

            
            if (playerChoice < 0 || playerChoice > 2) {
                System.out.println("Invalid choice! Please enter 0, 1, or 2.");
                continue;
            }

    
            int computerChoice = getComputerChoice(random);

            
            System.out.println("Your choice: " + choiceToString(playerChoice));
            System.out.println("Computer's choice: " + choiceToString(computerChoice));

        
            int result = getResult(playerChoice, computerChoice);
            updateOutcomes(result);
            updateMoveHistory(playerChoice);

            
            displayResult(result);

            
            System.out.println("Do you want to play again? (yes or no)");
            playAgain = sc.next().equalsIgnoreCase("yes");
        }

        
        displayStatistics();
    
    }
}
