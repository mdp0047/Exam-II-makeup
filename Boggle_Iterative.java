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
public class Boggle_Iterative {
   // class variables
   int n;
   char[][] myArray;
   List<String> dictionary = new ArrayList<String>();
   List<String> words = new ArrayList<String>();
   //Collection<String> words;
   
   // constructor
   public Boggle_Iterative(char[][] bArray) throws IOException {
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
      String temp;
      for (int i = 0; i < n; i++) { // took out <=
         for (int j = 0; j < n; j++) { // took out <=
            temp = "" + myArray[i][j];
            if (dict_lookup(temp) && !words.contains(temp)) {
               words.add(temp);
            }
            for (int k = 1; k <= n-i; k++) { // took out <=
               temp = new String(getColumn(j), i, k); //might need to change to k+1 for substrings
               if (dict_lookup(temp) && !words.contains(temp)) {
                  words.add(temp);
               }
               temp = reverseString(temp);
               if (dict_lookup(temp) && !words.contains(temp)) {
                  words.add(temp);
               }
            }
            for (int l = 1; l <= n-j; l++) { // took out <=
               temp = new String(myArray[i], j, l); // see above warning
               if (dict_lookup(temp) && !words.contains(temp)) {
                  words.add(temp);
               }
               temp = reverseString(temp);
               if (dict_lookup(temp) && !words.contains(temp)) {
                  words.add(temp);
               }
            }
         }
      }
   }
   
   // print list
   public void printList() {
      System.out.print(words);
   }
}