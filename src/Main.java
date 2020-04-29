import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in=new BufferedReader(new FileReader("DATA10.txt"));
		//Loop through five lines
		for (int x=0;x<5;x++) {
			StringTokenizer s=new StringTokenizer(in.readLine());
			while(s.hasMoreTokens()) {
				String word=s.nextToken();
				String[] vals=word.split("[\\W]");
				int pos=0;
				for (String i: vals) {
					pos+=i.length();
					if (i.length()==1) {
						System.out.print(OneLetter(i));
					}else if (i.length()==2) {
						System.out.print(TwoLetter(i));
					}else if(i.length()%2==0) {
						System.out.print(EvenLetter(i));
					}else {
						System.out.print(OddLetter(i));
					}
					if (pos<word.length()) {
						System.out.print(word.charAt(i.length()));
						pos++;
					}
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		in.close();
	}
	
	
	public static String OneLetter(String x) {
		char[] word=x.toCharArray();
		word[0]--;
		word[0]+=(word[0]<'a'?26:0);
		x=String.valueOf(word[0]);
		return x;
	}
	
	public static String TwoLetter(String x) {
		char[] word=x.toCharArray();
		char temp=word[0];
		word[0]=word[1];
		word[1]=temp;
		word[0]++;
		word[1]++;
		word[0]-=(word[0]>'z'?26:0);
		word[1]-=(word[1]>'z'?26:0);
		x="";
		for (char c:word) {
			x+=c;
		}
		return x;
	}
	
	public static String OddLetter(String x) {
		String output="";
		//Middle,
		char temp = x.charAt((x.length()-1)/2);
		temp--;
		temp+=(temp<'a'?26:0);
		output+=temp;
		//substring(1,middle-1)
		output+=x.substring(1,(x.length()-1)/2);
		//first character
		temp=x.charAt(0);
		temp--;
		temp+=(temp<'a'?26:0);
		output+=temp;
		//last character
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
