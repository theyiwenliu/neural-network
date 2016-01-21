package lyw.ml.activators;

public interface ActivationFunction {
    double activate(double weightedSum);
    double derivative(double weightedSum);
}
