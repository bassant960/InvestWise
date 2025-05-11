package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> getAssets() {
        List<String> assetNames = new ArrayList<>();
        for (Asset asset : assets) {
            assetNames.add(asset.getAssetName());
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
        saveAssetsToFile();
        System.out.println("Asset added: " + asset.getAssetName());
    }

    /**
     * Removes an asset from the portfolio based on the asset ID.
     *
     * @param assetID the ID of the asset to remove
     */
    public void removeAsset(int assetID) {
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
     * Saves the current list of assets to a JSON file.
     * Uses Gson to serialize the data.
     */
    private void saveAssetsToFile() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            Gson gson = new Gson();
            gson.toJson(assets, writer);
        } catch (IOException e) {
            System.out.println("Error saving assets to file: " + e.getMessage());
        }
    }
}
