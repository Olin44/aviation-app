package pl.olin44.aviationapp.utils;

public class WeightCalculation {

    private static final double KG_TO_LBS_RATIO = 2.20462262;
    private static final double LB_TO_KGS_RATIO = 0.45359237;

    private WeightCalculation() {
    }

    public static double kgToLb(double weight) {
        return weight * KG_TO_LBS_RATIO;
    }

    public static double lbToKg(double weight) {
        return weight * LB_TO_KGS_RATIO;
    }
}
