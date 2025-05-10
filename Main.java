package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PortfolioManager manager = new PortfolioManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Portfolio Manager ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Edit Asset");
            System.out.println("3. Remove Asset");
            System.out.println("4. View Assets");
            System.out.println("5. Clear Assets File");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    String[] allowedTypes = {"Stock", "RealEstate", "Gold","Crypto"};
                    System.out.println("Select Asset Type:");
                    for (int i = 0; i < allowedTypes.length; i++) {
                        System.out.println((i + 1) + ". " + allowedTypes[i]);
                    }
                    System.out.print("Your choice: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (typeChoice < 1 || typeChoice > allowedTypes.length) {
                        System.out.println("Invalid type selected.");
                        break;
                    }

                    String type = allowedTypes[typeChoice - 1];


                    System.out.print("How many fields for this asset? ");
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    String[] fields = new String[n];
                    for (int i = 0; i < n; i++) {
                        System.out.print("Field " + (i + 1) + ": ");
                        fields[i] = scanner.nextLine();
                    }

                    Asset newAsset = new Asset(type, fields);
                    manager.addAsset(newAsset);
                    break;

                case 2:
                    System.out.print("Enter the index of the asset to edit (starting from 1): ");
                    int editIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (editIndex < 0 || editIndex >= manager.getAssetCount()) {
                        System.out.println("Error: Invalid index. Asset not found.");
                    } else {
                        System.out.print("How many new fields? ");
                        int m = scanner.nextInt();
                        scanner.nextLine();
                        String[] newFields = new String[m];
                        for (int i = 0; i < m; i++) {
                            System.out.print("New Field " + (i + 1) + ": ");
                            newFields[i] = scanner.nextLine();
                        }

                        manager.editAsset(editIndex, newFields);
                    }

                    break;

                case 3:
                    System.out.print("Enter the index of the asset to remove (starting from 1): ");
                    int removeIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    manager.removeAsset(removeIndex);
                    break;

                case 4:
                    manager.printAssets();
                    break;

                case 5:
                    clearAssetFile();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void clearAssetFile() {
        try (java.io.Writer writer = new java.io.FileWriter("assets.json")) {
            writer.write("[]");
            System.out.println("Assets file has been cleared.");
        } catch (java.io.IOException e) {
            System.out.println("Error clearing asset file: " + e.getMessage());
        }
    }
}
