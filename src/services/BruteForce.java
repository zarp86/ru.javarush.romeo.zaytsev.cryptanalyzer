package services;

import constants.Constants;
import utils.MyArraysUtil;

import java.io.*;
import java.nio.file.Path;

public class BruteForce {
    private final Path sourcePath;

    public BruteForce(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

    private int findNumberOfRightCombInString(String s) {
        int count1 = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ' ') {
                if (Character.isLetter(chars[i - 1]) || chars[i - 1] == '.' || chars[i - 1] == '!' || chars[i - 1] == '?') {
                    count1 = count1 + 1;
                }
            }
        }
        return count1;
    }

    private int findNumberOfRightCombsInFile(Path sourcePath, int key) {
        int count = 0;
        CrypterOfString crypterOfString = new CrypterOfString();
        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath.toFile()))) {
            while (reader.ready()) {
                String s = crypterOfString.decrypt(reader.readLine(), key);
                count = count + findNumberOfRightCombInString(s);
            }
        } catch (IOException e) {
            e.printStackTrace();//:(((
        }
        return count;
    }

    private int findTheKeyToFile(Path sourcePath) {
        int possibleKey = 0;
        int alphabetSize = Constants.ALPHABET.size();
        int[] arrayWithCountOfCombs = new int[alphabetSize];

        for (int i = 0; i < alphabetSize; i++) {
            arrayWithCountOfCombs[i] = findNumberOfRightCombsInFile(sourcePath, i);
        }
//        System.out.println(Arrays.toString(arrayWithCountOfCombs));/////////////////////////////////////////
        possibleKey = MyArraysUtil.findIndexOfMaxInIntArray(arrayWithCountOfCombs);
        System.out.println("Possible key = " + possibleKey + "!");
        return possibleKey;
    }

    public void run() {
        int possibleKey = findTheKeyToFile(sourcePath);
        if (possibleKey == 0) {
            System.out.println(Constants.FAIL_OF_BRUTE_FORCE_MESSAGE);
        } else {
            CrypterOfFile crypterOfFile = new CrypterOfFile(sourcePath, possibleKey, true, "_HACKED_with_key_" + possibleKey + "_");
            crypterOfFile.crypt();
        }
    }

}
