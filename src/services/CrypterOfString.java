package services;

import constants.Constants;

import java.util.ArrayList;

public class CrypterOfString {
    public String encrypt(String s, int key) {
        return crypt(s, key);
    }

    public String decrypt(String s, int key) {
        return crypt(s, -key);
    }

    private String crypt(String s, int key) {
        StringBuilder stringBuilder = new StringBuilder();
        key = key % Constants.ALPHABET.size();
        if (key < 0) {
            key = Constants.ALPHABET.size() - Math.abs(key);
        }
        char[] text = s.toCharArray();
        for (int i = 0; i < text.length; i++) {
            char symbol = text[i];
            if (Constants.ALPHABET.contains(text[i])) {
                stringBuilder.append(Constants.ALPHABET.get(Math.abs(Constants.ALPHABET.indexOf(symbol) + key) % Constants.ALPHABET.size()));
            } else {
                stringBuilder.append(text[i]);
            }
        }
        return stringBuilder.toString();
    }

}