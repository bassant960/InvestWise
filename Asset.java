package org.example;

/**
 * Represents an investment asset with details such as ID, name, type,
 * purchase price, quantity, and current market price.
 * Provides methods to calculate current value and return on investment (ROI).
 *
 * @author
 * @version 1.0
 */
public class Asset {
    private int assetID;
    private String assetName;
    private String assetType;
    private float purchasePrice;
    private float quantity;
    private float currentMarketPrice;

    /**
     * Constructs an Asset with the specified details.
     *
     * @param assetID             The unique identifier for the asset.
     * @param assetName           The name of the asset.
     * @param assetType           The type/category of the asset (e.g., stock, bond).
     * @param purchasePrice       The price at which the asset was purchased.
     * @param quantity            The amount/number of units owned.
     * @param currentMarketPrice  The current market price of the asset.
     */
    public Asset(int assetID, String assetName, String assetType,
                 float purchasePrice, float quantity, float currentMarketPrice) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetType = assetType;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.currentMarketPrice = currentMarketPrice;
    }

    /**
     * Calculates the current total value of the asset.
     *
     * @return The current value (quantity × current market price).
     */
    public float calculateCurrentValue() {
        return quantity * currentMarketPrice;
    }

    /**
     * Calculates the Return on Investment (ROI) based on the given values.
     *
     * @param purchasePrice        The purchase price of one unit of the asset.
     * @param quantity             The number of units owned.
     * @param currentMarketPrice   The current market price of one unit.
     * @return The ROI percentage.
     */
    public static float calculateROI(float purchasePrice, float quantity, float currentMarketPrice) {
        float initialInvestment = quantity * purchasePrice;
        float currentValue = quantity * currentMarketPrice;
        return ((currentValue - initialInvestment) / initialInvestment) * 100;
    }


    /**
     * Gets the unique ID of the asset.
     *
     * @return The asset ID.
     */
    public int getAssetID() {
        return assetID;
    }

    /**
     * Gets the name of the asset.
     *
     * @return The asset name.
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * Gets the type/category of the asset.
     *
     * @return The asset type.
     */
    public String getAssetType() {
        return assetType;
    }
    /**
     * Gets the purchase price of the asset.
     *
     * @return the purchase price per unit of the asset
     */
    public float getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Gets the quantity of the asset owned.
     *
     * @return the number of units owned of the asset
     */
    public float getQuantity() {
        return quantity;
    }

    /**
     * Gets the current market price of the asset.
     *
     * @return the current market price per unit of the asset
     */
    public float getCurrentMarketPrice() {
        return currentMarketPrice;
    }
    /**
     * Returns a string representation of the asset.
     *
     * @return A string containing asset name, type, and quantity.
     */
    @Override
    public String toString() {
        return assetName + " | Type: " + assetType + " | Quantity: " + quantity;
    }
}

