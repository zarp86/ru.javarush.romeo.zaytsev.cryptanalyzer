import services.BruteForce;
import services.CrypterOfFile;
import services.CrypterOfString;

import java.nio.file.Path;
import java.util.Scanner;

public class CryptAnalyzerRunner {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Please, enter the Absolute path to your *.txt File:");
        Path of = Path.of(console.nextLine());
        System.out.println("Please, enter what you want to do: 0 - encrypt File, 1-decrypt File, 2 - brute force File, 5 - EXIT");
        int choice = console.nextInt();
        if (choice == 0 || choice == 1 || choice == 2) {
            if (choice == 0 || choice == 1) {
                System.out.println("Please, enter the KEY for crypt:");
                int key = console.nextInt();
                CrypterOfFile crypterOfFile;
                if (choice == 0) {
                    crypterOfFile = new CrypterOfFile(of, key, false);
                } else {
                    crypterOfFile = new CrypterOfFile(of, key, true);
                }
                crypterOfFile.crypt();
            } else {
                System.out.println("You are in brute force branch...");
                BruteForce bruteForce = new BruteForce(of);
                bruteForce.run();
            }
        }
        System.out.println("Goodbye! See you again!");
    }
}
