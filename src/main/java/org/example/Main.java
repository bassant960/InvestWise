//package org.example;
//import java.awt.*;
//import java.util.List;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        PortfolioManager manager = new PortfolioManager(1);
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n--- Portfolio Manager ---");
//            System.out.println("1. Add Asset");
//            System.out.println("2. Remove Asset");
//            System.out.println("3. View Assets");
//            System.out.println("4. View Portfolio Summary");
//            System.out.println("5. Calculate Zakat");
//            System.out.println("6. Connect Card");
//            System.out.println("7. Exit");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter Asset ID: ");
//                    int assetID = scanner.nextInt();
//                    scanner.nextLine();
//
//                    System.out.println("Enter Asset Name: ");
//                    String assetName = scanner.nextLine();
//
//                    String[] allowedTypes = {"Stock", "RealEstate", "Gold", "Crypto"};
//                    System.out.println("Select Asset Type:");
//                    for (int i = 0; i < allowedTypes.length; i++) {
//                        System.out.println((i + 1) + ". " + allowedTypes[i]);
//                    }
//                    System.out.print("Your choice: ");
//                    int typeChoice = scanner.nextInt();
//                    scanner.nextLine();
//
//                    if (typeChoice < 1 || typeChoice > allowedTypes.length) {
//                        System.out.println("Invalid type selected.");
//                        break;
//                    }
//
//                    String assetType = allowedTypes[typeChoice - 1];
//
//                    System.out.println("Enter Purchase Price: ");
//                    float purchasePrice = scanner.nextFloat();
//
//                    System.out.println("Enter Quantity: ");
//                    float quantity = scanner.nextFloat();
//                    scanner.nextLine();
//                    System.out.print("Enter Current Price: ");
//                    float currentPrice = scanner.nextFloat();
//                    scanner.nextLine();
//
//
//                    Asset newAsset = new Asset(assetID, assetName, assetType, purchasePrice, quantity, currentPrice);
//                    manager.addAsset(newAsset);
//                    return;
//
//                case 2:
//                    System.out.print("Enter the ID of the asset to remove: ");
//                    int removeID = scanner.nextInt();
//                    scanner.nextLine();
//                    manager.removeAsset(removeID);
//                    return;
//
//                case 3:
//                    List<Asset> assetNames = manager.getAssets();
//                    if (assetNames.isEmpty()) {
//                        System.out.println("No assets available.");
//                    } else {
//                        System.out.println("Assets:");
//                        for (Asset assetNameStr : assetNames) {
//                            System.out.println("- " + assetNameStr.getAssetName());
//                        }
//                    }
//                    return;
//
//                case 4:
//                    if (manager.getAssets().isEmpty()) {
//                        System.out.println("No assets in the portfolio to summarize.");
//                    } else {
//                        manager.printPortfolioSummary();
//                    }
//                    return;
//
//                case 5:
//                    System.out.println("Enter Asset Name:\n");
//                    String name = scanner.nextLine();
//                    boolean found = false;
//                    Asset A = null;
//                    List<Asset> Assets = manager.getAssets();
//                    for(Asset asset: Assets) {
//                        if(asset.getAssetName().equals(name)) {
//                            A = asset;
//                            found = true;
//                            break;
//                        }
//                    }
//                    if(!found) {
//                        System.out.println("Asset not found.");
//                        return;
//                    }
//                    int duration = scanner.nextInt();
//                    while(duration < 0){
//                        System.out.println("Please inter a positive number\n");
//                        duration = scanner.nextInt();
//                    }
//                    ZakatCalculator zakat = new ZakatCalculator(A, duration);
//                    zakat.Calulate();
//                    System.out.println(zakat.getZakat());
//                    return;
//
//                case 6:
//                    System.out.println("Enter CardId:");
//                    int cardID = scanner.nextInt();
//                    scanner.nextLine();
//                    System.out.println("Enter your Password:");
//                    String password = scanner.nextLine();
//                    Card card = new Card(cardID, password);
//                    if(!card.IsFound(card)){
//                        System.out.println("Card is not found\n");
//                        return;
//                    }
//                    BankAccount account = new BankAccount();
//                    System.out.print("Enter OTP Code: ");
//                    int code = scanner.nextInt();
//                    account.ConnectAccount(manager, card, code);
//                    return;
//
//                case 7:
//                    System.out.println("Exiting...");
//                    return;
//
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        }
//    }
//}
package org.example;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Main  {
    public static void opensecondmenu(){}
    public static void usermenu() throws IOException{
        PortfolioManager manager = new PortfolioManager(1);
        String name ;
        String pass ;
        String email ;
        String username;
        Scanner input = new Scanner(System.in);
        System.out.println("1. Sign In\n2. Sign Up\n3. Calculate Asset Zakat\n4. Connect account\n5. Exit\nEnter your choice");
        int choice;
        choice = input.nextInt();
        input.nextLine();
        if(choice == 1){
            System.out.print("enter your Username: ");
            username = input.next();
            System.out.print("enter your Password: ");
            pass = input.next();
            boolean success = User.signin(username, pass);
            if(success){
                opensecondmenu();
            }
        }else if(choice == 2){
            System.out.print("enter your name: ");
            name = input.next();
            System.out.print("enter your Username: ");
            username = input.next();
            System.out.print("enter your Password: ");
            pass = input.next();
            System.out.print("enter your Email: ");
            email = input.next();
            User user = new User(name, username, pass, email);
            boolean success = user.signup();
            if(success){
                opensecondmenu();
            }

        }
        else if(choice == 3){
            System.out.println("Enter Asset Name:");
                    String assetname = input.nextLine();
                    boolean found = false;
                    Asset A = null;
                    List<Asset> Assets = manager.getAssets();
                    for(Asset asset: Assets) {
                        if(asset.getAssetName().equals(assetname)) {
                            A = asset;
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println("Asset not found.");
                        return;
                    }
                    int duration = input.nextInt();
                    while(duration < 0){
                        System.out.println("Please inter a positive number\n");
                        duration = input.nextInt();
                    }
                    ZakatCalculator zakat = new ZakatCalculator(A, duration);
                    zakat.Calulate();
                    System.out.println(zakat.getZakat());
                    return;
            }else if(choice == 4){
                    System.out.println("Enter CNN:");
                    int cardCVV = input.nextInt();
                    input.nextLine();
                    while(Integer.toString(cardCVV).length()!=3){
                        System.out.println("CNN should be at 3 numbers:");
                        cardCVV = input.nextInt();
                        input.nextLine();
                    }
                    System.out.println("CNN should be 16 numbers:");
                    String CardNumber = input.nextLine();
                    //input.nextLine();
                    while(CardNumber.length()!=16){
                        System.out.println("CNN should be 16 numbers:");
                        CardNumber = input.nextLine();
                        input.nextLine();
                    }
                    System.out.println("Enter your Password:");
                    int PIN = input.nextInt();
                    input.nextLine();
                    while(Integer.toString(PIN).length()!=4){
                        System.out.println("password should be 4 numbers:");
                        PIN = input.nextInt();
                        input.nextLine();
                    }
                    BankAccount account = new BankAccount();
                    System.out.print("Enter OTP Code: ");
                    int code = input.nextInt();
                    Card card = new Card(cardCVV, CardNumber, PIN);
                    account.ConnectAccount(manager, card, code);
        }else if(choice == 5){
            System.out.print("Exiting.................. ");
            System.exit(0);
        }else{
            System.out.println("Invalid choice try again");
        }

    }

    public static void main(String[] args) throws IOException {

        while(true) {
            usermenu();
        }
    }
}
