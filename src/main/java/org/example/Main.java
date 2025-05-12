package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main  {
    public static void opensecondmenu(){}
    public static void usermenu() throws IOException{
        String name ;
        String pass ;
        String email ;
        String username;
        Scanner input = new Scanner(System.in);
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

        }else if(choice == 3){
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