package lyw.ml;
import lyw.ml.activators.ActivationFunction;

import java.util.List;
import java.util.ArrayList;

public class Neuron {
    private List<Synapse> synapses;
    private ActivationFunction activationFunction;
    private double output;
    private double derivative;
    private double weightedSum;
    private double error;

    public Neuron() {
    	this.synapses = new ArrayList<Synapse>();
        this.error = 0.0;
    }
    
    public Neuron(ActivationFunction activationFunction) {
    	this.activationFunction = activationFunction;
    	this.synapses = new ArrayList<Synapse>();
        this.error = 0.0;
    }

    public void addSynapse(Synapse synapse) {
        this.synapses.add(synapse);
    }
    
    public void addSynapses(List<Synapse> synapses) {
    	for (Synapse synapse : synapses) {
    		this.synapses.add(synapse);
    	}
    }

    public List<Synapse> getSynapses() {
        return this.synapses;
    }

    public double[] getWeights() {
        double[] weights = new double[synapses.size()];
        int i = 0;
        for (Synapse synapse : synapses) {
            weights[i++] = synapse.getWeight();
        }
        return weights;
    }

    private void calculateWeightedSum() {
        weightedSum = 0.0;
        for (Synapse synapse : synapses) {
            weightedSum += synapse.getWeight() * synapse.getSourceNeuron().getOutput();
        }
    }

    public void activate() {
        calculateWeightedSum();
        this.output = activationFunction.activate(weightedSum);
        this.derivative = activationFunction.derivative(output);
    }
    
    public double getDerivative() {
        return this.derivative;
    }

    public void setOutput(double output) {
        this.output = output;
    }
    
    public double getOutput() {
        return this.output;
    }
    
    public void setActivationStrategy(ActivationFunction activationFunction) {
        this.activationFunction =  activationFunction;
    }
    
    public ActivationFunction getActivationStrategy() {
        return activationFunction;
    }

    public void setError(double error) {
        this.error = error;
    }
    
    public double getError() {
        return this.error;
    } 
}
