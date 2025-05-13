package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Manages a collection of investment assets in a portfolio.
 * Provides functionality to add, remove, list, and calculate values for assets,
 * and saves the portfolio data to a JSON file.
 *
 * @author
 * @version 1.0
 */
public class PortfolioManager {
    private static final String FILE_NAME = "assets.json";
    private int managerID;
    private List<Asset> assets;
    private List<Card> cards;

    /**
     * Constructs a PortfolioManager with a specified manager ID.
     *
     * @param managerID The ID of the manager responsible for the portfolio.
     */
    public PortfolioManager(int managerID) {
        this.managerID = managerID;
        this.assets = new ArrayList<>();
    }

    /**
     * Gets a list of asset names currently in the portfolio.
     *
     * @return A list of asset names.
     */
    public List<Asset> getAssets() {
        List<Asset> assetNames = new ArrayList<>();
        for (Asset asset : assets) {
            assetNames.add(asset);
        }
        return assetNames;
    }

    /**
     * Adds an asset to the portfolio and saves the updated list to a file.
     *
     * @param asset The asset to add.
     */
    public void addAsset(Asset asset) {
        assets.add(asset);

        // Add to current user's personal asset list
        User currentUser = User.getCurrentUser();
        if (currentUser != null) {
            currentUser.addAsset(asset);
            System.out.println("Asset added to current user's profile: " + asset.getAssetName());
        } else {
            System.out.println("No user is currently signed in.");
        }

        saveAssetsToFile();
        System.out.println("Asset added: " + asset.getAssetName());
    }
    /**
     * Adds an asset to the portfolio and saves the updated list to a file.
     *
     * @param card The asset to add.
     */
    public void connectCard(Card card) {
        cards.add(card);

        // Add to current user's personal asset list
        User currentUser = User.getCurrentUser();
        if (currentUser != null) {
            currentUser.addCard(card);
            System.out.println("Card is added to current user's profile");
        } else {
            System.out.println("No user is currently signed in.");
        }

        saveCardsToFile();
        System.out.println("Card is connected");
    }

    /**
     * Removes an asset from the portfolio based on user input for the asset ID.
     * Prompts the user to enter the ID, removes the asset if found, and updates the file.
     * If the asset is not found, displays a message to the user.
     */
    public void removeAsset() {
        displayAssetNamesAndIDs();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the asset to remove: ");
        int assetID = scanner.nextInt();
        scanner.nextLine();

        boolean removed = false;
        for (Asset asset : assets) {
            if (asset.getAssetID() == assetID) {
                assets.remove(asset);
                removed = true;
                break;
            }
        }


        if (removed) {
            saveAssetsToFile();
            System.out.println("Asset with ID " + assetID + " removed.");
        } else {
            System.out.println("Asset not found.");
        }
    }
    /**
     * Displays each asset's name and ID in the format "Asset Name, Asset ID".
     * Useful for identifying assets before performing actions like edit or delete.
     */
    public void displayAssetNamesAndIDs() {
        System.out.println("Asset List:");
        for (Asset asset : assets) {
            System.out.println(asset.getAssetName() + ", " + asset.getAssetID());
        }
    }
    /**
     * Edits an existing asset in the portfolio by ID.
     * Prompts the user to select the asset by ID, then enter new values for quantity, date, and price.
     * Updates the selected asset and saves the updated list to the file.
     */
    public void editAssetByID() {

        displayAssetNamesAndIDs();

        System.out.print("Enter the asset ID you want to edit: ");
        int assetID = new Scanner(System.in).nextInt();

        Asset selectedAsset = null;
        for (Asset asset : assets) {
            if (asset.getAssetID() == assetID) {
                selectedAsset = asset;
                break;
            }
        }

        if (selectedAsset != null) {

            System.out.println("Selected Asset Details:");
            System.out.println("Asset Name: " + selectedAsset.getAssetName());
            System.out.println("Purchase Date: " + selectedAsset.getPurchaseDate());
            System.out.println("Quantity: " + selectedAsset.getQuantity());
            System.out.println("Purchase Price: " + selectedAsset.getPurchasePrice());

            System.out.print("Enter new quantity: ");
            int newQuantity = new Scanner(System.in).nextInt();
            System.out.print("Enter new purchase date: ");
            String newPurchaseDate = new Scanner(System.in).nextLine();
            System.out.print("Enter new purchase price: ");
            float newPurchasePrice = new Scanner(System.in).nextFloat();

            selectedAsset.setQuantity(newQuantity);
            selectedAsset.setPurchaseDate(newPurchaseDate);
            selectedAsset.setPurchasePrice(newPurchasePrice);

            System.out.println("Asset updated: " + selectedAsset.getAssetName());
            saveAssetsToFile();
        } else {
            System.out.println("Asset with ID " + assetID + " not found.");
        }
    }

    /**
     * Calculates the total current value of all assets in the portfolio.
     *
     * @return The total current value.
     */
    public float getTotalCurrentValue() {
        float total = 0;
        for (Asset asset : assets) {
            total += asset.calculateCurrentValue();
        }
        return total;
    }

    /**
     * Prints a summary of the portfolio including each asset's current value and ROI,
     * as well as the total portfolio value.
     */
    public void printPortfolioSummary() {
        System.out.println("Portfolio Summary:");
        for (Asset asset : assets) {
            float currentValue = asset.calculateCurrentValue();
            float roi = Asset.calculateROI(asset.getPurchasePrice(), asset.getQuantity(), asset.getCurrentMarketPrice());
            System.out.printf("%s | Current Value: %.2f | ROI: %.2f%%\n", asset, currentValue, roi);
        }
        System.out.println("Total Portfolio Value: " + getTotalCurrentValue());
    }


    /**
     * Saves the current list of assets to a JSON file using Gson for serialization.
     * The file is saved under the name specified by FILE_NAME.
     */
    private void saveAssetsToFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(assets, writer);
        } catch (IOException e) {
            System.out.println("Error saving assets to file: " + e.getMessage());
        }
    }

    private void saveCardsToFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(cards, writer);
        } catch (IOException e) {
            System.out.println("Error saving cards to file: " + e.getMessage());
        }
    }
}
