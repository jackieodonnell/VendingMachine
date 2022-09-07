package com.ododev.reader;

import com.ododev.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRead {

    public static List<Item> readFile(String filePath) {
        File file = new File(filePath);
        List<Item> inventory = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineArray = line.split(",");
                if (lineArray[3].equals("Munchy")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Munchy(lineArray[0], lineArray[1], price));
                } else if (lineArray[3].equals("Candy")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Candy(lineArray[0], lineArray[1], price));
                } else if (lineArray[3].equals("Drink")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Drink(lineArray[0], lineArray[1], price));
                } else if (lineArray[3].equals("Gum")) {
                    BigDecimal price = new BigDecimal(lineArray[2]);
                    inventory.add(new Gum(lineArray[0], lineArray[1], price));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return inventory;
    }
}
