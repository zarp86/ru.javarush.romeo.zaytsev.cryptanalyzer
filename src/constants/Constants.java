package constants;

import java.util.ArrayList;

public class Constants {
    private static final char[] chars = "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя .,”:-!?".toCharArray();
    public static final ArrayList<Character> ALPHABET = new ArrayList<>();
    static {
        for (char aChar : chars) {
            ALPHABET.add(aChar);
        }
    }
    public static final String FAIL_OF_BRUTE_FORCE_MESSAGE = "Sorry, but I could not find the key to your file :(. Maybe it's not encrypted? Try again please!";
}

