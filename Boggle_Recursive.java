import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/*
* Name: Myles Parkhurst
* Class: COMP 3270
* Assignment: Exam II Makeup Boggle Project
* Compile Instructions: Compile in Java on Jgrasp machine using above imports
*/
public class Boggle_Recursive {
   // class variables
   int n;
   char[][] myArray;
   List<String> dictionary = new ArrayList<String>();
   List<String> words = new ArrayList<String>();
   //Collection<String> words;
   
   // constructor
   public Boggle_Recursive(char[][] bArray) throws IOException {
      myArray = bArray;
      n = myArray.length;
      createDictionary();
   }
   
   // create dictionary
   public void createDictionary() throws IOException {
      String file = "words_alpha.txt";
      
      dictionary = Files.readAllLines(Paths.get(file));
   }
   
   // check if word is valid
   public boolean dict_lookup(String s) {
      return dictionary.contains(s);
   }
   
   // get a column array from the 2d array
   public char[] getColumn(int c) {
      char[] returnArray = new char[n];
      for (int i = 0; i < n; i++) {
         returnArray[i] = myArray[i][c];
      }
      return returnArray;
   }
   
   // reverse a string
   public String reverseString(String s) {
      String rString = "";
      for (int i = s.length()-1; i >= 0; i--) {
         rString += s.charAt(i);
      }
      return rString;
   }
   
   //change later
   public void solve() {
      int len = recursiveSolve(1, 0, 0, false);
   }
   
   public int recursiveSolve(int l, int i, int j, boolean columns) {
      String s;
      if(!columns) {
         s = new String(myArray[i], j, j+l-1);
         if (dict_lookup(s)) { // add if its a word
            if (!words.contains(s)) {
               words.add(s);
            }
         }
         s = reverseString(s);
         if (dict_lookup(s)) { // add if its a word
            if (!words.contains(s)) {
               words.add(s);
            }
         }
         if (j+l-1 == n) { // if at end of array
            if (i != n-1) {// if not at bottom of array
               return recursiveSolve(1, i+1, 0, false);
            }
            return recursiveSolve(1, 0, 0, true); // at end start columns
         }   
         return recursiveSolve(l+1, i, j, false); // move over
      }
      // start columns
      s = new String(getColumn(j), i, i+l-1);
      if (dict_lookup(s)) { // add if its a word
            if (!words.contains(s)) {
               words.add(s);
            }
         }
         s = reverseString(s);
         if (dict_lookup(s)) { // add if its a word
            if (!words.contains(s)) {
               words.add(s);
            }
         }
         if (i+l-1 == n) { // if at end of array
            if (j != n-1) {// if not at far right
               return recursiveSolve(1, 0, j+1, true);
            }
            return 0; // end w/ base case
         }
         return recursiveSolve(l+1, i, j, true); // move over
   }
   
   // print list
   public void printList() {
      System.out.print(words);
   }
   
}