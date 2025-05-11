package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void opensecondmenu(){}
    public static void main(String[] args) throws IOException {
        String name ;
        String pass ;
        String email ;
        String username;
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("1. Sign In\n2. Sign Up\n3. Exit\nEnter your choice");
            int choice;
            choice = input.nextInt();
            if(choice == 1){
                System.out.print("enter your Username: ");
                username = input.next();
                System.out.print("enter your Password: ");
                pass = input.next();
                boolean success = User.signin(username, pass);
                if(success){
                    opensecondmenu();
                    break;
                }
            }
        }

    }

}
