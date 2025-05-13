package org.example;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void opensecondmenu() {
        PortfolioManager manager = new PortfolioManager(1);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Portfolio Manager ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Remove Asset");
            System.out.println("3. View Assets");
            System.out.println("4. View Portfolio Summary");
            System.out.println("5. Edit Asset");
            System.out.println("6. Calculate Zakat");
            System.out.println("7. Connect Card");
            System.out.println("8. Log out");
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
                    manager.removeAsset();
                    break;

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
                    break;

                case 4:
                    if (manager.getAssets().isEmpty()) {
                        System.out.println("No assets in the portfolio to summarize.");
                    } else {
                        manager.printPortfolioSummary();
                    }
                    break;

                case 5:
                    System.out.println("Choose an asset to edit:");

                    manager.editAssetByID();
                    break;

                case 6:
                    PortfolioManager.displayAssetNamesAndIDs();
                    System.out.println("Enter Asset Name:");
                    String assetname = scanner.nextLine();
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
                        break;
                    }
                    int duration = scanner.nextInt();
                    while(duration < 0){
                        System.out.println("Please Enter a positive number\n");
                        duration = scanner.nextInt();
                    }
                    ZakatCalculator zakat = new ZakatCalculator(A, duration);
                    zakat.Calulate();
                    System.out.println(zakat.getZakat());
                    break;
                case 7:
                    System.out.println("Enter CNN:");
                    int cardCVV = scanner.nextInt();
                    scanner.nextLine();
                    while(Integer.toString(cardCVV).length()!=3){
                        System.out.println("CNN should be at 3 numbers:");
                        cardCVV = scanner.nextInt();
                        scanner.nextLine();
                    }
                    System.out.println("CNN should be 16 numbers:");
                    String CardNumber = scanner.nextLine();
                    //input.nextLine();
                    while(CardNumber.length()!=16){
                        System.out.println("CNN should be 16 numbers:");
                        CardNumber = scanner.nextLine();
                        scanner.nextLine();
                    }
                    System.out.println("Enter your Password:");
                    int PIN = scanner.nextInt();
                    scanner.nextLine();
                    while(Integer.toString(PIN).length()!=4){
                        System.out.println("password should be 4 numbers:");
                        PIN = scanner.nextInt();
                        scanner.nextLine();
                    }
                    BankAccount account = new BankAccount();
                    System.out.print("Enter OTP Code: ");
                    int code = scanner.nextInt();
                    Card card = new Card(cardCVV, CardNumber, PIN);
                    account.ConnectAccount(manager, card, code);

                case 8:
                    System.out.println("Logging out................");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void usermenu() throws IOException {

        String name, pass, email, username;
        Scanner input = new Scanner(System.in);

        System.out.println("1. Sign In\n2. Sign Up\n3. Exit\nEnter your choice:");
        int choice = input.nextInt();

        if (choice == 1) {
            System.out.print("Enter your Username: ");
            username = input.next();
            System.out.print("Enter your Password: ");
            pass = input.next();
            boolean success = User.signin(username, pass);
            if (success) {
                opensecondmenu();
            }
        } else if (choice == 2) {
            System.out.print("Enter your name: ");
            name = input.next();
            System.out.print("Enter your Username: ");
            username = input.next();
            System.out.print("Enter your Password: ");
            pass = input.next();
            if(!AuthenticationService.isValidPass(pass)){
                System.out.println("Password must have uppercase, lowercase and special characters");
                return ;
            }

            System.out.print("Enter your Email: ");
            email = input.next();

            if(!AuthenticationService.isvalidemail(email)){
                System.out.println("Email must have @ domain");
                return ;
            }
            User user = new User(name, username, email, pass);
            boolean success = user.signup();
            if (success) {
                opensecondmenu();
            }
        } else if (choice == 3) {
            System.out.print("Exiting.................. ");
            System.exit(0);
        } else {
            System.out.println("Invalid choice, try again");
        }
    }

    public static void main(String[] args) {
        while (true) {
            try {
                usermenu();
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

}
