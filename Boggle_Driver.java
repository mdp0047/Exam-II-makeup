import java.util.Scanner;
import java.util.Arrays;
import java.io.IOException;
import java.util.InputMismatchException;
/*
* Name: Myles Parkhurst
* Class: COMP 3270
* Assignment: Exam II Makeup Boggle Project
* Compile Instructions: Compile in Java on Jgrasp machine using above imports
*/
public class Boggle_Driver {
   public static void main(String args[]) {
   
      System.out.println("Hello. Welcome to Parkhurst's Boggle Game.");
      Scanner scan = new Scanner(System.in);
      System.out.println();
      
      try {
         int n = 0;
         while (n <= 0) { // game rows
            System.out.println("Please create your Game."
               + "\nHow many rows would you like to play with?(integer greater than 0)");
            n = scan.nextInt();
         }
      
         System.out.println("Please enter your letters for the board.\nThe board will fill left to right, top to bottom.(letters one at a time)");
         char[][] myArray = new char[n][n];
         for (int i = 0; i < n; i++) { // game letters
            for (int j = 0; j < n; j++) {
               myArray[i][j] = scan.next().charAt(0);
            }
         }
         System.out.println();
      
         // display board
         for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
               System.out.print("" + myArray[a][b] + " ");
            }
            System.out.println();
         }
         System.out.println();
      
         int style = -1;
         while (!(style == 0 || style == 1)) { // solve type 
            System.out.println("Almost done setting up!\nWould you like to solve Boggle Iteratively(0) or Recursively(1)?");
            style = scan.nextInt();
         }
         System.out.println();
         try {
            double t0 = System.currentTimeMillis();
            if (style == 0) {
               Boggle_Iterative iBoggle = new Boggle_Iterative(myArray);
               iBoggle.solve();
               iBoggle.printList();
            }
            else if (style == 1) {
               Boggle_Recursive iBoggle = new Boggle_Recursive(myArray);
               iBoggle.solve();
               iBoggle.printList();
            }
            double t1 = System.currentTimeMillis();
            double time = (t1 - t0)/1000;
            System.out.println("\nResult was found in " + time + " seconds.");
         }
         catch(IOException e) {
            System.out.println("Failed to create dictionary");
         }
      }
      catch(InputMismatchException a) { // catch invalid board size input
         System.out.println("Failed to input valid game board size.");
      }
   
   }
}