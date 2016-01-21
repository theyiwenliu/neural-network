package lyw.ml.activators;

public class SigmoidActivationFunction implements ActivationFunction {
    public double activate(double weightedSum) {
        return 1.0 / (1 + Math.exp(-1.0 * weightedSum));
    }

    public double derivative(double output) {
        return output * (1.0 - output);
    }
}
