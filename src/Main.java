import java.util.*;
import java.io.*;
/*
 * This file reads in a text file and encodes it based on the Vortex Cipher description.
 * DATA10.txt is the plain english version of the text
 * DATA11.txt is the encoded version of the text.
 * Your task is to take this program and reverse it so that it decodes DATA11.txt properly into the regular words.
 * See the problem definition in the PDF file for more info.
 */
public class Main {

	public static void main(String[] args) throws IOException{
		// Open up a file reader
		BufferedReader in=new BufferedReader(new FileReader("DATA10.txt"));
		//Loop through five lines
		for (int x=0;x<5;x++) {
			//split the line into words
			StringTokenizer s=new StringTokenizer(in.readLine());
			//repeat while there are more words
			while(s.hasMoreTokens()) {
				//get the word and split it based on punctuation
				String word=s.nextToken();
				String[] vals=word.split("[\\W]");
				int pos=0;
				//loop through every "word".
				//Example: they'll counts as two words: "they" and "ll". The apostrophe has to be ignored, but added back at the end.
				for (String i: vals) {
					pos+=i.length();
					//Encode the message based on length. If it's one letter
					if (i.length()==1) {
						System.out.print(OneLetter(i));
					}else if (i.length()==2) { //if it's two letters
						System.out.print(TwoLetter(i));
					}else if(i.length()%2==0) { //If there are an even number of letters
						System.out.print(EvenLetter(i));
					}else { //If there are an odd number of letters
						System.out.print(OddLetter(i));
					}
					//If we haven't hit the full length of the word, there is punctuation. So put it back in.
					if (pos<word.length()) {
						System.out.print(word.charAt(i.length()));
						pos++;
					}
				}
				//print a space after every word
				System.out.print(" ");
			}
			//print a newline after every sentence
			System.out.println();
		}
		//close the file
		in.close();
	}
	
	
	public static String OneLetter(String x) {
		//Turn the word to an array of characters
		char[] word=x.toCharArray();
		//subtract one to go back a letter
		word[0]--;
		//if the letter is before 'a', add 26 to loop through the alphabet
		word[0]+=(word[0]<'a'?26:0);
		//get the string of the character
		x=String.valueOf(word[0]);
		//return it
		return x;
	}
	
	public static String TwoLetter(String x) {
		//turn to a character array
		char[] word=x.toCharArray();
		//swap the letters
		char temp=word[0];
		word[0]=word[1];
		word[1]=temp;
		//increase by one to go to the next letter
		word[0]++;
		word[1]++;
		//If you go past 'z', go back 26 to loop
		word[0]-=(word[0]>'z'?26:0);
		word[1]-=(word[1]>'z'?26:0);
		//Add the letters to our output string
		x="";
		for (char c:word) {
			x+=c;
		}
		//return it
		return x;
	}
	
	public static String OddLetter(String x) {
		//Start a variable for our output
		String output="";
		//Middle,
		//find our middle character
		char temp = x.charAt((x.length()-1)/2);
		//subtract one. If it's less than 'a', add 26
		temp--;
		temp+=(temp<'a'?26:0);
		//add it to the output
		output+=temp;
		//substring(1,middle-1)
		//add the letters from position 1 up to the middle
		output+=x.substring(1,(x.length()-1)/2);
		//add the first letter
		temp=x.charAt(0);
		temp--;
		temp+=(temp<'a'?26:0);
		output+=temp;
		//add the last character
		temp=x.charAt(x.length()-1);
		temp--;
		temp+=(temp<'a'?26:0);
		output+=temp;
		//substring middle to end
		output+=x.substring((x.length()+1)/2,x.length()-1);
		return output;
	}
	
	public static String EvenLetter(String x) {
		String output="";
		//1st middle
		char temp=x.charAt((x.length()/2)-1);
		temp++;
		temp-=(temp>'z'?26:0);
		output+=temp;
		//substring
		output+=x.substring(1,x.length()/2-1);
		//First letter
		temp=x.charAt(0);
		temp++;
		temp-=(temp>'z'?26:0);
		output+=temp;
		//Last letter
		temp=x.charAt(x.length()-1);
		temp++;
		temp-=(temp>'z'?26:0);
		output+=temp;
		//substring
		output+=x.substring(x.length()/2+1,x.length()-1);
		//2nd middle
		temp=x.charAt(x.length()/2);
		temp++;
		temp-=(temp>'z'?26:0);
		output+=temp;
		
		return output;
	}

}
