package codes;
import java.util.Scanner;

public class NumberGuessingGame 
{
	public static int errorTrap (int min, int max)
	{
		Scanner input = new Scanner(System.in); // Create scanner
		
		boolean inputSucess; // Boolean for error trap
		int number = 0;
	
		do
		{
			inputSucess = true; //Reset the boolean to assume the user isn't an idiot and will enter the correct data
		
			//Scanner input = new Scanner(System.in);
				
			try
			{
				number = input.nextInt();
			}
				
			catch (Exception e) //If an error occurs in the try statement, catch all exceptions
			{
				input.nextLine(); //Clear the stream of the user entered data
				inputSucess = false; //Set 'inputSucess' to false so the program will loop back to the prompt
			}
				
			if (number < min || number > max || inputSucess == false) //Test if the data is in the correct rang
			{
				System.out.println("Nope! Invalid data, try agin."); //Informs stupid user that they entered the wrong data
			}
				
		}while (number < min || number > max || inputSucess == false); //Loop back to the prompt if data is incorrect
		
		return number;
	}
	
	public static int randomNumberGenerator (int min, int max)
	{
		int number = (int) (Math.random() * (max - min + 1) + min); //Create random number
		return number;
	}
	
	public static boolean checkNumber (int guess, int number)
	{
		boolean sucess;
		
		if (number == guess)
			sucess = true;
		
		else
			sucess = false;
			
		return sucess;
	}
	
	public static String hwc (int guess, int number)
	{
		String hwc = ""; //hwc = hot, warm, cold
		
		if (guess - number == 1 || number - guess == 1) 
			hwc = "hot";
		
		if ((guess - number > 1 && guess - number < 4) || (number - guess > 1 && number - guess < 4))
			hwc = "warm";
		
		if (guess - number >= 4 || number - guess >= 4)
			hwc = "cold";
		
		return hwc;
	}
	
	public static void main(String[] args) 
	{
		
		System.out.println("Number Guessing Game!"); System.out.println(); //Print out game title and blank line
		int pa = 1; //pa = play aging
		int gamesWon = 0;
		int gamesLost = 0;
		int difficulty = 0;
		String hwc = ""; //hwc = hot, warm, cold
		
		Scanner input = new Scanner(System.in); // Create scanner
		
		do
		{
			
			System.out.print("Select difficulty (1 for Easy, 2 for Medium, 3 for Hard): ");
			difficulty = errorTrap(1,3);
				
			boolean sucess = false;
			
			int max = 10; 
			int min = 1; 
			
			if (difficulty == 1)
				max = 5;
			
			if (difficulty == 2)
				max = 10;
			
			if (difficulty == 3)
				max = 20;
			
			int trys = 3;
			int guess = 11; // start 'guess' at 11 because, if the user enters the wrong data, 
							//the integer won't get created and there will be an error in the do while loop
			
			int number = randomNumberGenerator(min,max); //Create random number
			//System.out.println("Number: " + number); //Show random number for testing purposes
	
			do
			{
				trys--; // Increases the number of tries so we can limit the user to 3 trys
				
				System.out.print("Enter a number between " + min + " and " + max +   ": "); //Prompt user for their guess
				guess = errorTrap(min,max);
				
				if (checkNumber(guess,number) == true)
				{
					System.out.println("You won!"); //Inform the user that they won the game
					sucess = true; //Set success to true so it won't say that they lost
					gamesWon++;
					break; //Exit the loop
				}
				
				else if (trys > 0)
				{
					hwc = hwc(guess, number);
					System.out.println("Nope! Try agin. You're " + hwc + ". You have " + trys + " try(s) remaining.");
				}
			
			}while (trys > 0); //Exit the loop when the user has had 3 tries to guess the number, and has failed
			
			if (sucess == false) //Inform the user that they lost the game
			{
				System.out.println("You lost! The number was " + number + ".");
				gamesLost++;
			}
			
			System.out.println("Games won: " + gamesWon);
			System.out.println("Games lost: " + gamesLost);
			
			System.out.print("Do you want to play again? (1 for Yes, 2 for No) ");
			pa = errorTrap(1,2);
			
		}while(pa == 1);

	}
}
