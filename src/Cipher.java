import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Cipher {
	private File file;
	private String text;
	private String dir;
	private char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private String specialChars = "`~!@#$%^&*()-_=+[{}]|\\<>.,/?:;'\""; // to check if char is special character
	
	public Cipher(String plainText) {
		this.text = plainText;
	}
	
	public Cipher(File file, String dir) {
		this.file = file;
		this.dir = dir;
		
		// if path has a '\' at the end then remove it
		if(dir.charAt(dir.length()-1) == '\\') {
			dir = dir.substring(0, dir.length()-1);
		}
	}
	
	public String cipherText(int shift) {
		String encodedText = "";
		char characters[] = text.toCharArray();
		char temp;
		
		for(char ch: characters) {
			if(ch != ' ') {
				if(Character.isUpperCase(ch)) {
					temp = (char) (ch + shift); // set temp to value after adding shift
					
					// check if temp exceeds the value of Z (values after Z aren't letters)
					if(temp > 'Z') {
						temp = (char) (temp - 'Z' + 'A' - 1); // make temp go back to beginning of alphabet and shift from there
					}
					
					encodedText += temp;
				}
				else if(Character.isLowerCase(ch)) { // same as above, but with lowercase letters
					temp = (char) (ch + shift);
					
					if(temp > 'z') {
						temp = (char) (temp - 'z' + 'a' - 1);
					}
					
					encodedText += temp;
				}
				else if(specialChars.contains(String.valueOf(ch))) { // convert ch to string so we can compare
					encodedText += ch;
				}
			} else encodedText += ch;
		}
		
		return encodedText;
	}
	
	// decipher by doing reverse of cipherText()
	public String decipherText(int shift) {
		String decodedText = "";
		char characters[] = text.toCharArray();
		char temp;
		
		for(char ch: characters) {
			if(ch != ' ') {
				if(Character.isUpperCase(ch)) {
					temp = (char) (ch - shift);
					
					if(temp < 'A') {
						temp = (char) (temp + 'Z' - 'A' + 1);
					}
					
					decodedText += temp;
				}
				else if(Character.isLowerCase(ch)) {
					temp = (char) (ch - shift);
					
					if(temp < 'a') {
						temp = (char) (temp + 'z' - 'a' + 1);
					}
					
					decodedText += temp;
				}
				else if(specialChars.contains(String.valueOf(ch))) {
					decodedText += ch;
				}
			} else decodedText += ch;
		}
		
		return decodedText;
	}
	
	// brute-forcing caesar cipher
	public void bruteforce() {
		String decodedText;
		
		for(int i = 1; i <= letters.length; i++) {
			decodedText = decipherText(i);
			System.out.println("Possible output " + i + ": " + decodedText);
		}
	}
	
	// cipher strings in file
	public boolean cipherFile(int shift) {
		String encodedText = "";
		String line = ""; // hold plain text line in file
		char temp;
		
		try {
			File fileCopy = new File(dir + "\\temp");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileCopy));
			
			while((line = reader.readLine()) != null) {
				char letters[] = line.toCharArray();
				
				// using cipherText() algorithm
				for(char ch: letters) {
					if(ch != ' ') {
						if(Character.isUpperCase(ch)) {
							temp = (char) (ch + shift);
							
							if(temp > 'Z') {
								temp = (char) (temp - 'Z' + 'A' - 1);
							}
							
							encodedText += temp;
						}
						else if(Character.isLowerCase(ch)) {
							temp = (char) (ch + shift);
							
							if(temp > 'z') {
								temp = (char) (temp - 'z' + 'a' - 1);
							}
							
							encodedText += temp;
						}
					} else encodedText += ch;;
				}
				writer.write(encodedText);
				writer.newLine();
				encodedText = "";
			}
			reader.close();
			writer.close();
			file.delete();
			fileCopy.renameTo(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	// decipher strings in file
	public boolean decipherFile(int shift) {
		String decodedText = "";
		String line = "";
		char temp;
		
		try {
			File fileCopy = new File(dir + "\\temp");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileCopy));
			
			while((line = reader.readLine()) != null) {
				char letters[] = line.toCharArray();
				
				// using decipherText() algorithm
				for(char ch: letters) {
					if(ch != ' ') {
						if(Character.isUpperCase(ch)) {
							temp = (char) (ch - shift);
							
							if(temp < 'A') {
								temp = (char) (temp + 'Z' - 'A' + 1);
							}
							
							decodedText += temp;
						}
						else if(Character.isLowerCase(ch)) {
							temp = (char) (ch - shift);
							
							if(temp < 'a') {
								temp = (char) (temp + 'z' - 'a' + 1);
							}
							
							decodedText += temp;
						}
					} else decodedText += ch;
				}
				writer.write(decodedText);
				writer.newLine();
				decodedText = "";
			}
			reader.close();
			writer.close();
			file.delete();
			fileCopy.renameTo(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}