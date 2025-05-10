package org.example;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages a collection of assets, allowing operations such as adding, editing,
 * removing, and printing assets. Assets are persisted to a JSON file.
 */
public class PortfolioManager {
    private List<Asset> assets;
    private static final String FILE_NAME = "assets.json";
    /**
     * Constructs a new PortfolioManager and clears any existing asset data in the file.
     */
    public PortfolioManager() {
        assets = new ArrayList<>();
            clearAssetFile();
        }

    /**
     * Returns the total number of assets in the portfolio.
     *
     * @return The number of assets.
     */
    public int getAssetCount() {
        return assets.size();
    }
    /**
     * Adds a verified asset to the portfolio and saves the updated list to file.
     *
     * @param asset The asset to be added.
     * @return true if the asset is valid and was added; false otherwise.
     */

    public boolean addAsset(Asset asset) {
        boolean verified = Verification.checkFields(asset.getFields());
        if (verified) {
            assets.add(asset);
            System.out.println("Asset has been added successfully.");
            saveAssetsToFile();
            return true;
        } else {
            System.out.println("Error: Asset fields are not valid.");
            return false;
        }
    }
    /**
     * Edits the fields of an existing asset by index and saves changes to file.
     *
     * @param index     The index of the asset to edit.
     * @param newFields The new field values to set.
     * @return true if the asset exists and was updated; false otherwise.
     */
    public boolean editAsset(int index, String[] newFields) {
        if (index >= 0 && index < assets.size()) {
            Asset asset = assets.get(index);
            asset.setFields(newFields);
            System.out.println("Asset has been updated successfully.");
            saveAssetsToFile();
            return true;
        } else {
            System.out.println("Error: Asset not found.");
            return false;
        }
    }
    /**
     * Removes an asset from the portfolio by index and saves changes to file.
     *
     * @param index The index of the asset to remove.
     * @return true if the asset exists and was removed; false otherwise.
     */
    public boolean removeAsset(int index) {
        if (index >= 0 && index < assets.size()) {
            assets.remove(index);
            System.out.println("Asset has been removed successfully.");
            saveAssetsToFile();
            return true;
        } else {
            System.out.println("Error: Asset not found.");
            return false;
        }
    }
    /**
     * Prints a summary of all assets in the portfolio.
     */
    public void printAssets() {
        if (assets.isEmpty()) {
            System.out.println("No assets in the portfolio.");
        } else {
            for (int i = 0; i < assets.size(); i++) {
                System.out.println("Asset " + (i + 1) + ": Type = " + assets.get(i).getType());
            }
        }
    }
    /**
     * Saves the current list of assets to a JSON file.
     */
    private void saveAssetsToFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(assets, writer);
        } catch (IOException e) {
            System.out.println("Error saving assets to file: " + e.getMessage());
        }
    }
    /**
     * Clears the contents of the asset file by writing an empty JSON array.
     */
    private void clearAssetFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            writer.write("[]");
        } catch (IOException e) {
            System.out.println("Error clearing asset file: " + e.getMessage());
        }
    }
    /**
     * Provides utility methods for verifying asset data fields.
     */
    private class Verification {
        /**
         * Checks whether all fields in the array are non-null and non-empty.
         *
         * @param fields An array of strings representing the fields to check.
         * @return true if all fields are non-null and not empty; false otherwise.
         */
        public static boolean checkFields(String[] fields) {
            for (String field : fields) {
                if (field == null || field.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }

}
