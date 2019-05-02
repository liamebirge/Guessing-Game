/********************************************************/
//	     		Computer-run Guessing Game				//
//	 Author: Liam English-Birge		Date: 04/30/2019	//
/********************************************************/

import java.util.*;

public class Guesser {
	public static void main(String[] args) {
		Random r = new Random();
		Scanner scan = new Scanner(System.in);
		int target, compGuess, guessCount = 0, upperBound, lowerBound, numberGames = 0;
		long startTime, endTime, elapsedTime;
		int[] scores = new int[100];//create score board with 100 slots
		double[] times = new double[100];//create time score board with 100 slots
		String tooLow = "too low", tooHigh = "too high", correct = "correct guess";
		target = r.nextInt(101 - 0);//get random integer between and including 1 and 100
		upperBound = 101;
		lowerBound = 0;
		
		System.out.println("Press enter to start game.");
		//System.out.println("Target is: " + target);
		scan.nextLine();//start game on user's command
		startTime = System.nanoTime();//get current time in miliseconds
		boolean guessing = true;//keep game looping until user chooses to break
		BREAK_LOOP:
		while(guessing) {
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
				endTime = System.nanoTime();//get end time of game
				System.out.println(compGuess + " " + correct);//display number and notify that it was correct
				guessCount++;//increase guess counter
				numberGames++;//increase score board game counter
				elapsedTime = endTime - startTime;//calculate how long the game took to complete
				System.out.println("The target number: " + target + " was guessed in: " + guessCount
						+ " tries in: " + elapsedTime + " miliseconds.");//display results of the game
				System.out.print("Run the game again? <enter 'y' to continue or any character to exit>: ");//ask if user would like to perform another game or exit
				char gameAgain = scan.next().charAt(0);//scan the first character entered
				if (gameAgain == 'y' || gameAgain == 'Y') {//if the user would like to continue:
					target = r.nextInt(101 - 0);//choose new random target number
					scores[numberGames] = guessCount;//add current game score to score column
					times[numberGames] = elapsedTime;//add game time to time column
					guessCount = 0;//reset guess counter
					upperBound = 101;//reset upper bound
					lowerBound = 0;//reset lower bound
					startTime = System.nanoTime();//restart game timer
				} else {//if user answers anything other than yes to continuing:
					scores[numberGames] = guessCount;//add current game score to score array
					times[numberGames] = elapsedTime;//add game time to time array
					System.out.println("--Leaderboard--");//title
					for (int i = 1; i < scores.length; i++) {//loop through score list
						if (scores[i] == 0) {//if score equals zero (game not played)
							scores[i] = 888888888;//set score equal to a arbitrarily large place-holder
						}
						if (times[i] == 0) {//if time equals zero (game not played)
							times[i] = 888888888;//set time equal to a arbitrarily large place-holder
						}else {
							times[i] = times[i] * 0.00000010;
						}
					}
					Arrays.sort(scores);
					Arrays.sort(times);
					/*This method of sorting only works if time values are representative of their score partner
					 * if for whatever reason the computer takes 20ms to calculate and complete 30 guesses while
					 * it was also able to complete a 19ms game of 150 guesses, the time will be separated from
					 * its paired score and lost in the data. Try to come up with a way to achieve this idea but
					 * through sorting a 2D array of 2 columns and 100+ rows "scores[99][1]"*/
					for (int i = 1; i <= 10; i++) {//loop through top 10 scores
						if (i == 10) {//if the score's placement is tenth:
							System.out.println(i + ". " + scores[i] + " guesses in " + times[i] + " nanoseconds");//print line with only one space after period
						} else {//otherwise
							System.out.println(i + ".  " + scores[i] + " guesses in " + times[i] + " nanoseconds");//print line with 2 spaces after period
						}
					}
					wait4Enter();//wait for user to press enter/display prompt message (see bellow)
					guessing = false;//stop main loop
					break BREAK_LOOP;
				}
			} else {//catch statement for safekeeping
				System.out.println("Something went wrong...");
			}
		}
		System.out.println("Shutting down...");
	}
	
	public static void wait4Enter() {//new method
		Scanner s = new Scanner(System.in);//initialize scanner
		System.out.println("Press enter to quit.");//prompt user
		s.nextLine();//read for any input including enter key
	}
}
