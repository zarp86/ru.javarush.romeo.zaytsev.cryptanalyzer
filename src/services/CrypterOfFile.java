package services;

import java.io.*;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

public class CrypterOfFile {
    private final int key;
    /**
     * false - encrypt;
     * true - decrypt;
     */
    private final boolean typeOfCrypt;
    private final Path sourcePath;
    private final Path destPath;

    public CrypterOfFile(Path filePath, int key, boolean typeOfCrypt) {
        this.key = key;
        this.typeOfCrypt = typeOfCrypt;
        this.sourcePath = filePath;
        this.destPath = createDestPath();
    }

    public CrypterOfFile(Path filePath, int key, boolean typeOfCrypt, String customString) {
        this.key = key;
        this.typeOfCrypt = typeOfCrypt;
        this.sourcePath = filePath;
        this.destPath = createDestPath(customString);
    }

    private Path createDestPath() {
        Path path = sourcePath;
        Path destPath;
        String name;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        if (typeOfCrypt) {
            name = path.getFileName().toString().replaceAll(".txt", "_decrypted_" + timeStamp + ".txt");
        } else {
            name = path.getFileName().toString().replaceAll(".txt", "_encrypted_" + timeStamp + ".txt");
        }
        destPath = Path.of(path.getParent().toString(), name);
        return destPath;
    }

    private Path createDestPath(String customString) {
        Path path = sourcePath;
        Path destPath;
        String name;
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        name = path.getFileName().toString().replaceAll(".txt", customString + timeStamp + ".txt");
        destPath = Path.of(path.getParent().toString(), name);
        return destPath;
    }

    public void crypt() {
        CrypterOfString crypterOfString = new CrypterOfString();
        try (BufferedReader reader = new BufferedReader(new FileReader(sourcePath.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destPath.toFile()))) {
//            writer.write("");
            while (reader.ready()) {
                StringBuilder stringBuilder = new StringBuilder(reader.readLine());
                if (typeOfCrypt) {
                    writer.write(crypterOfString.decrypt(stringBuilder.toString(), key));
                } else {
                    writer.write(crypterOfString.encrypt(stringBuilder.toString(), key));
                }
                if (reader.ready()) {
                    writer.write("\n");
                }
            }
            System.out.println("Done! File \n"
                    + destPath.toString()
                    + "\nwas created! Check it, please!");
        } catch (IOException e) {
            e.printStackTrace();/////!!!!!!
        }
    }

}