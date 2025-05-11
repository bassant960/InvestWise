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
     * Calculates the Return on Investment (ROI) for the asset.
     *
     * @return The ROI percentage.
     */
    public float calculateROI() {
        float initialInvestment = quantity * purchasePrice;
        float currentValue = calculateCurrentValue();
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
     * Returns a string representation of the asset.
     *
     * @return A string containing asset name, type, and quantity.
     */
    @Override
    public String toString() {
        return assetName + " | Type: " + assetType + " | Quantity: " + quantity;
    }
}
