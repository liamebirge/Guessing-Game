/********************************************************/
//	     Computer Run Recursive Guessing Game			//
//	 Author: Liam English-Birge		Date: 04/30/2019	//
/********************************************************/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class recursiveGuesser {
	public static void main(String[] args) {
		Random r = new Random();
		Scanner scan = new Scanner(System.in);
		int target, compGuess, guessCount = 0, upperBound, lowerBound, numberGames = 0, iterations;
		long scores[] = new long[1000];//create score board with 1000 slots
		//long startTime, endTime, elapsedTime;
		String tooLow = "too low", tooHigh = "too high", correct = "correct guess";
		target = r.nextInt(101 - 0);
		upperBound = 101;
		lowerBound = 0;
		
		System.out.print("How many times would you like to run the program? <1-1000>: ");
		iterations = scan.nextInt();//specify how many times the program should run and how big to make the score board
		System.out.println("Press enter to start game.");
		scan.nextLine();
		//startTime = System.currentTimeMillis();
		boolean guessing = true;//keeps game looping until correct number is guessed
		for (int j = 0; j < iterations; j++) {//loop the game for specified number of games
			BREAK_LOOP://break for the game loop/breaks here when correct number is guessed
			while (guessing) {
				if ((upperBound - lowerBound) <= 0) {//if bounds get too close together as to become negative, reset them
					upperBound = 101;
					lowerBound = 0;
				}
				compGuess = r.nextInt(upperBound-lowerBound);//choose a random number between the two bounds
				if(compGuess > target) {//if the number is greater than the target number:
					System.out.println(compGuess + " " + tooHigh);//tell that it was too high and what number was guessed
					guessCount++;//increase guess counter
					lowerBound++;//increase lower bound
				} else if(compGuess < target) {//if guess number is less than the target number:
					System.out.println(compGuess + " " + tooLow);//inform that it was too low and what number was guessed
					guessCount++;//increase guess counter
					upperBound--;//decrease lower bound
				} else if (compGuess == target) {//if the guess was correct
					//endTime = System.currentTimeMillis();
					System.out.println(compGuess + " " + correct + "\n");//display number and notify that it was correct
					guessCount++;//increase guess counter
					numberGames++;//increase score board game counter
					//elapsedTime = endTime - startTime;
					target = r.nextInt(101 - 0);//choose new random target number
					scores[numberGames] = guessCount;//add current game score to score list
					guessCount = 0;//reset guess counter
					upperBound = 101;//reset upper bound
					lowerBound = 0;//reset lower bound
					break BREAK_LOOP;//end the game iteration and break out of while loop
					//startTime = System.currentTimeMillis();
				} else {//catch statement for safekeeping
					System.out.println("Something went wrong...");
				}
			}
		}
		System.out.println("Operation complete");//title
		System.out.println("--Leaderboard--");//title
		for (int l = 1; l < scores.length; l++) {//loop through score list
			if (scores[l] == 0) {//if score equals zero (game not played)
				scores[l] = 888888888;//set score equal to a arbitrarily large place-holder
			}
		}
		Arrays.sort(scores);//sort score list in descending order
		for (int i = 1; i <= 10; i++) {//loop through top 10 scores
			if (i == 10) {//if the score's placement is tenth:
				System.out.println(i + ". " + scores[i]);//print line with only one space after period
			} else {//otherwise
				System.out.println(i + ".  " + scores[i]);//print line with 2 spaces after period
			}
		}
		wait4Enter();//wait for user to press enter/display prompt message (see bellow)
		System.out.println("Shutting down...");
	}
	
	public static void wait4Enter() {//new method
		Scanner s = new Scanner(System.in);//initialize scanner
		System.out.println("Press enter to quit.");//prompt user
		s.nextLine();//read for any input including enter key
	}
}
