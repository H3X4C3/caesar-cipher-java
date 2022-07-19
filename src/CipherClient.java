import java.io.File;
import java.util.Scanner;

public class CipherClient {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String text, next, filePath, fileName;
		int shift, choice;
		
		boolean stop = false;
		while(stop == false) {
			System.out.println("Options:\n");
			System.out.println("1. Cipher\n2. Decipher\n0. Exit");
			System.out.print("Choice: ");
			choice = Integer.parseInt(input.nextLine()); // read nextLine() into integer since it keeps skipping user input
			System.out.println("\n==========================");
			
			switch(choice) {
			case 1:
				System.out.print("\n1. String\n2. File\nChoice: ");
				choice = Integer.parseInt(input.nextLine());
				
				if(choice == 1) {
					System.out.print("\nText to cipher: ");
					text = input.nextLine();
					System.out.print("Shift amount: ");
					shift = Integer.parseInt(input.nextLine());
					
					Cipher encode = new Cipher(text);
					System.out.println("\nEncoded Text: " + encode.cipherText(shift));
					System.out.print("Press enter to go back...");
					next = input.nextLine();
					System.out.println("\n==========================\n");
					
					break;
				}
				if(choice == 2) {
					System.out.print("\nFile path (excluding file name): ");
					filePath = input.nextLine();
					System.out.print("File name: ");
					fileName = input.nextLine();
					System.out.print("Shift amount: ");
					shift = Integer.parseInt(input.nextLine());
					
					File file = new File(filePath + "\\" + fileName);
					// check if file and/or directory exists
					if(!(file.exists())) {
						System.out.print("\nERROR: File/Directory not found. Please try again...");
						next = input.nextLine();
						break;
					}
					Cipher fileCipher = new Cipher(file, filePath);
					fileCipher.cipherFile(shift);
					System.out.println("\nCiphered " + file.getName());
					System.out.println("Press enter to go back...");
					next = input.nextLine();
					System.out.println("\n==========================\n");
					
					break;
				}
				
				System.out.println("Invalid choice, try again...");
				break;
			case 2:
				System.out.print("\n1. Brute-Force\n2. Use key\n3. File\nChoice: ");
				choice = Integer.parseInt(input.nextLine());
				
				if(choice == 1) {
					System.out.print("\nText to brute-force: ");
					text = input.nextLine();
					
					Cipher bruteforce = new Cipher(text);
					bruteforce.bruteforce();
					System.out.println("Press enter to go back...");
					next = input.nextLine();
					System.out.println("\n==========================\n");
					
					break;
				}
				if(choice == 2) {
					System.out.print("\nText to decipher: ");
					text = input.nextLine();
					System.out.print("Shift amount: ");
					shift = Integer.parseInt(input.nextLine());
					
					Cipher decode = new Cipher(text);
					System.out.println("\nDecoded Text: " + decode.decipherText(shift));
					System.out.print("Press enter to go back...");
					next = input.nextLine();
					System.out.println("\n==========================\n");
					
					break;
				}
				if(choice == 3) {
					System.out.print("\nFile path (excluding file name): ");
					filePath = input.nextLine();
					System.out.print("File name: ");
					fileName = input.nextLine();
					System.out.print("Shift amount: ");
					shift = Integer.parseInt(input.nextLine());
					
					File file = new File(filePath + "\\" + fileName);
					// check if file and/or directory exists
					if(!(file.exists())) {
						System.out.print("\nERROR: File/Directory not found. Please try again...");
						next = input.nextLine();
						break;
					}
					Cipher fileDecipher = new Cipher(file, filePath);
					fileDecipher.decipherFile(shift);
					System.out.println("\nDeciphered " + file.getName());
					System.out.println("Press enter to go back...");
					next = input.nextLine();
					System.out.println("\n==========================\n");
					
					break;
				}
				
				System.out.println("Invalid choice, try again...");
				break;
			case 0:
				stop = true;
				break;
			}
		}
		
		input.close();
	}
}