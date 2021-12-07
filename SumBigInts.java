// input file sum.txt

package codingAssignment7;
import java.io.*;
import java.util.*;

public class SumBigInts {
	public static final int MAX_DIGITS = 50;

	// main method that inputs the txt file to the program
	// and passes it to the method where the file is processed
	public static void main(String[] args) 
			throws FileNotFoundException {
		Scanner input = new Scanner(new File("sum.txt"));
		processFile(input);		
	}

	// this method will process the entire file line by line.
	public static void processFile(Scanner file) {
		int lineCount = 0;

			// while the entire file has lines
			while (file.hasNextLine()) {
				lineCount++;
				processBigLine(file.nextLine());
			} 
			System.out.println("\nTotal lines = " + lineCount);
		}

	// method for declaring arrays, inputting each number in the array,
	// printing the array, and handling the index addition
	public static void processBigLine(String string) {
		Scanner line = new Scanner(string);

		// declaring the first array that will hold
		// the first number and take index addition
		int[] array1 = new int[MAX_DIGITS];
		String num = line.next();
		
		// starting from the end of the array and moving to the left index by index
		// put the first number of the line into the array
		// changing the char value of the number to actual number by subtracting 48
		for (int i = 0; i < num.length(); i++) {
			array1[array1.length - 1 - i] = num.charAt(num.length() - 1 - i) - 48; 
		}
		
		// printing out the first number of the line
		printArray(array1);

		// while there are more numbers on the line
		// create arrays, input the numbers into the array,
		// and print them out
		while (line.hasNext()) {
			System.out.print(" + ");
			
			int[] array2 = new int[MAX_DIGITS];
			String num2 = line.next();

			for (int i = 0; i < num2.length(); i++) {
				array2[array2.length - 1 - i] = num2.charAt(num2.length() - 1 - i) - 48; 
			}

			// calling print method to print out each successive number in the line
			printArray(array2);

			// the next segment is to add the same indexes to one another
			for (int i = 0; i < array1.length -1; i++) {
				array1[array1.length - 1 - i] += array2[array2.length - 1 -i];

				// carrying over 1 if the index value is >= 10
				// carry the 1 one index to the left and subtract 10
				if (array1[array1.length - 1 - i] >= 10) {
					array1[array1.length - 1 - i] -= 10;
					array1[array1.length - 1 - i - 1] += 1;
				}
			}
		}
		
		// printing out the final result
		System.out.print(" = ");
		printArray(array1);
		System.out.println("");
	}
	
	// printing out the array without leading zeroes
	// while still keeping zeroes included in the number
	public static void printArray(int[] array) {
		int z = MAX_DIGITS - 1;
		for (int i = 0; i < MAX_DIGITS; i++) {
			if (Integer.valueOf(array[i]) > 0) {
				z = i;
				break;
			}
		}
		for (int i = z; i < MAX_DIGITS; i++) {
			System.out.print(Integer.valueOf(array[i]));
		}
	}
}
