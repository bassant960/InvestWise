package org.example;

/**
 * Represents a Zakat calculator for assets.
 * Handles Zakat calculation based on asset value, duration, and gold price.
 */
public class ZakatCalculator {
    private Asset asset;
    private int Duration;
    private double Zakat;
    private double Limit;
    final double GOLD_PRICE = 3715;

    /**
     * Constructs a new ZakatCalculator with the given asset and duration.
     *
     * @param asset    The asset for which Zakat is being calculated.
     * @param Duration The ownership duration in years.
     */
    public ZakatCalculator(Asset asset, int Duration) {
        this.asset = asset;
        this.Duration = Duration;
        this.Limit = GOLD_PRICE * 85;
    }

    /**
     * Returns the calculated Zakat value.
     *
     * @return The calculated Zakat value.
     */
    public double getZakat() {
        return Zakat;
    }

    /**
     * Calculates the Zakat for the asset.
     */
    public void Calulate() {

        if (Duration >= 1 && asset.calculateCurrentValue() >= Limit) {
            this.Zakat = asset.calculateCurrentValue() * 0.025;
        } else {
            System.out.println("This Asset hasn't zakat");
        }
    }
}
