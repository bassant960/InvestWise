package org.example;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PortfolioManager manager = new PortfolioManager(1);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Portfolio Manager ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Remove Asset");
            System.out.println("3. View Assets");
            System.out.println("4. View Portfolio Summary");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Asset ID: ");
                    int assetID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter Asset Name: ");
                    String assetName = scanner.nextLine();

                    String[] allowedTypes = {"Stock", "RealEstate", "Gold", "Crypto"};
                    System.out.println("Select Asset Type:");
                    for (int i = 0; i < allowedTypes.length; i++) {
                        System.out.println((i + 1) + ". " + allowedTypes[i]);
                    }
                    System.out.print("Your choice: ");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (typeChoice < 1 || typeChoice > allowedTypes.length) {
                        System.out.println("Invalid type selected.");
                        break;
                    }

                    String assetType = allowedTypes[typeChoice - 1];

                    System.out.println("Enter Purchase Price: ");
                    float purchasePrice = scanner.nextFloat();

                    System.out.println("Enter Quantity: ");
                    float quantity = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.print("Enter Current Price: ");
                    float currentPrice = scanner.nextFloat();
                    scanner.nextLine();


                    Asset newAsset = new Asset(assetID, assetName, assetType, purchasePrice, quantity, currentPrice);
                    manager.addAsset(newAsset);
                    break;

                case 2:
                    System.out.print("Enter the ID of the asset to remove: ");
                    int removeID = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeAsset(removeID);
                    break;

                case 3:
                    List<String> assetNames = manager.getAssets();
                    if (assetNames.isEmpty()) {
                        System.out.println("No assets available.");
                    } else {
                        System.out.println("Assets:");
                        for (String assetNameStr : assetNames) {
                            System.out.println("- " + assetNameStr);
                        }
                    }
                    break;

                case 4:
                    if (manager.getAssets().isEmpty()) {
                        System.out.println("No assets in the portfolio to summarize.");
                    } else {
                        manager.printPortfolioSummary();
                    }
                    break;


                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
