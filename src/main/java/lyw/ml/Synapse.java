package lyw.ml;

public class Synapse {
    private Neuron sourceNeuron;
    private double weight;

    public Synapse(double weight) {
        this.weight = weight;
    }
    
    public Synapse(double weight, Neuron sourceNeuron) {
    	this(weight);
        this.sourceNeuron = sourceNeuron;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public double getWeight() {
        return weight;
    }

    public void setSourceNeuron(Neuron sourceNeuron) {
    	this.sourceNeuron = sourceNeuron;
    }
    
    public Neuron getSourceNeuron() {
        return sourceNeuron;
    }
}
