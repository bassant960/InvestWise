package org.example;
/**
 * Represents an asset with a specific type and associated fields.
 */
public class Asset {
    private String type;
    private String[] fields;
    /**
     * Constructs an Asset with the given type and fields.
     *
     * @param type   The type of the asset (e.g., "Stock", "RealEstate", "Gold","Crypto").
     * @param fields The specific fields related to the asset.
     */

    public Asset(String type, String[] fields) {
        this.type = type;
        this.fields = fields;
    }
    /**
     * Returns the type of the asset.
     *
     * @return The asset type.
     */
    public String getType() {
        return type;
    }
    /**
     * Returns the fields associated with the asset.
     *
     * @return An array of fields for this asset.
     */
    public String[] getFields() {
        return fields;
    }
    /**
     * Sets the fields for this asset.
     *
     * @param fields An array of fields to associate with the asset.
     */
    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
