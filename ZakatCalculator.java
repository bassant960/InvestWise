package org.example;
import java.util.Scanner;


/**
 * Represents an Asset's zakat calculator with details such as asset, Duration.
 * Provides methods to calculate Zakat depends on assetvalue, duration, gold price.
 */
public class ZakatCalculator {
    Scanner scanner = new Scanner(System.in);
    private Asset asset;
    private int Duration;
    private double Zakat;
    private double Limit;
    final double GOLD_PRICE = 3715;
    public ZakatCalculator(Asset asset, int Duration) {
        this.asset = asset;
        this.Duration = Duration;
        this.Limit = GOLD_PRICE*85;
    }
    public double getZakat() {
        return Zakat;
    }
    public void Calulate(){
        if(Duration >= 1 && asset.calculateCurrentValue()>=Limit){
            this.Zakat = asset.calculateCurrentValue()*(0.025);
        }
        else{
            System.out.println("This Asset hasn't zakat");
        }
    }

}
