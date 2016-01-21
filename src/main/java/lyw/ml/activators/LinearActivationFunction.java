package lyw.ml.activators;

public class LinearActivationFunction implements ActivationFunction {
    public double activate(double weightedSum) {
        return weightedSum;
    }

    public double derivative(double output) {
        return 1.0;
    }
}
