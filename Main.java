package org.example;
import java.awt.*;
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
            System.out.println("5. Calculate Zakat");
            System.out.println("6. Connect Card");
            System.out.println("7. Exit");
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
                    return;

                case 2:
                    System.out.print("Enter the ID of the asset to remove: ");
                    int removeID = scanner.nextInt();
                    scanner.nextLine();
                    manager.removeAsset(removeID);
                    return;

                case 3:
                    List<Asset> assetNames = manager.getAssets();
                    if (assetNames.isEmpty()) {
                        System.out.println("No assets available.");
                    } else {
                        System.out.println("Assets:");
                        for (Asset assetNameStr : assetNames) {
                            System.out.println("- " + assetNameStr.getAssetName());
                        }
                    }
                    return;

                case 4:
                    if (manager.getAssets().isEmpty()) {
                        System.out.println("No assets in the portfolio to summarize.");
                    } else {
                        manager.printPortfolioSummary();
                    }
                    return;

                case 5:
                    System.out.println("Enter Asset Name:\n");
                    String name = scanner.nextLine();
                    boolean found = false;
                    Asset A = null;
                    List<Asset> Assets = manager.getAssets();
                    for(Asset asset: Assets) {
                        if(asset.getAssetName().equals(name)) {
                            A = asset;
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println("Asset not found.");
                        return;
                    }
                    int duration = scanner.nextInt();
                    while(duration < 0){
                        System.out.println("Please inter a positive number\n");
                        duration = scanner.nextInt();
                    }
                    ZakatCalculator zakat = new ZakatCalculator(A, duration);
                    zakat.Calulate();
                    System.out.println(zakat.getZakat());
                    return;

                case 6:
                    System.out.println("Enter CardId:");
                    int cardID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter your Password:");
                    String password = scanner.nextLine();
                    Card card = new Card(cardID, password);
                    if(!card.IsFound(card)){
                        System.out.println("Card is not found\n");
                        return;
                    }
                    BankAccount account = new BankAccount();
                    System.out.print("Enter OTP Code: ");
                    int code = scanner.nextInt();
                    account.ConnectAccount(manager, card, code);
                    return;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
