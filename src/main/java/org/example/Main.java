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
