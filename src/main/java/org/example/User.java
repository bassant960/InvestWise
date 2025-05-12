package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a user who can sign up and sign in to the system.
 * Handles serialization and deserialization of user data using JSON and Gson.
 * This class manages user credentials, assets, and user authentication.
 */
public class User {
    private String name;
    private String username;
    private String email;
    private String password;
    private static User currentUser = null;
    private List<Asset> userAssets = new ArrayList<>();

    public List<Asset> getUserAssets() {
        return userAssets;
    }

    public void addAsset(Asset asset) {
        userAssets.add(asset);
    }
    /**
     * Constructs a new User with the given information.
     *
     * @param name     The full name of the user.
     * @param username The unique username of the user.
     * @param email    The user's email address.
     * @param password The user's password.
     */
    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    /**
     * Signs up the user by validating credentials and saving them to a JSON file.
     *
     * @return true if the signup is successful, false otherwise.
     * @throws IOException If an I/O error occurs while reading or writing the file.
     */
    public boolean signup() throws IOException {

        if(!AuthenticationService.isvalidpass(password)){
            System.out.println("Password must have uppercase, lowercase and special characters");
            return false;
        }
        if(!AuthenticationService.isvalidemail(email)){
            System.out.println("Email must have @ domain");
            return false;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("Users.json");
        List<User> users = new ArrayList<User>();
        if (file.exists()) {
            Reader reader = new FileReader(file);
            users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
            reader.close();

        }
        for(User user : users) {
            if(user.username.equals(this.username) || user.email.equals(this.email)){
                System.out.println("User already exists try to sign in");
                return false;
            }

        }
        users.add(this);
        Writer writer = new FileWriter(file);
        gson.toJson(users, writer);
        writer.close();
        currentUser = this;
        System.out.println("You registered successfully.");
        return true;
    }
    /**
     * Signs in the user by validating the username and password against stored data.
     *
     * @param us   The username entered by the user.
     * @param pass The password entered by the user.
     * @return true if sign-in is successful, false otherwise.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static boolean signin(String us, String pass) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("Users.json");
        List<User> users = new ArrayList<User>();
        if (file.exists()) {
            Reader reader = new FileReader(file);
            users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
        }
        for(User user : users) {
            if(user.username.equals(us)){
                if(user.password.equals(pass)) {
                    currentUser = user;
                    System.out.println("Sign in successfully");
                    return true;
                }else{
                    System.out.println("Invalid password");
                    return false;
                }
            }
        } System.out.println("User does not exist");
        return false;

    }
    /**
     * Gets the current signed-in user.
     *
     * @return The current user if signed in, or null if no user is signed in.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

}
/**
 * Utility class for validating user credentials such as password and email.
 * This class provides static methods for validating passwords and email formats.
 */
class AuthenticationService{
    /**
     * Validates the strength of the password.
     * The password must contain at least one uppercase letter and one special character.
     *
     * @param pass The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public static boolean isvalidpass(String pass){
        return pass.matches("^(?=.[A-Z])(?=.[!@#$%^&*(),.?\":{}|<>]).+$");
    }
    /**
     * Validates the email format.
     *
     * @param email The email address to validate.
     * @return true if the email is in a valid format, false otherwise.
     */
    public static boolean isvalidemail(String email){
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

}
