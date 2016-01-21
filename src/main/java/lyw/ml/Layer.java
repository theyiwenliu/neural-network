package lyw.ml;
import java.util.List;
import java.util.ArrayList;

public class Layer {
	private Layer prevLayer;
    private Layer nextLayer;
    private List<Neuron> neurons;
    private Neuron bias;

    public Layer() {
        this.neurons = new ArrayList<Neuron>();
    }

    public Layer(Layer prevLayer) {
        this();
        this.prevLayer = prevLayer;
    }

    public Layer(Layer prevLayer, Neuron bias) {
        this(prevLayer);
        this.bias = bias;
        neurons.add(bias);
    }

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
        if (prevLayer != null) {
            for (Neuron prevLayerNeuron : prevLayer.getNeurons()) {
                neuron.addSynapse(new Synapse(Math.random() - 0.5, prevLayerNeuron));
            }
        }
    }

    public void addNeuron(Neuron neuron, double[] weights) {
        neurons.add(neuron);
        if (prevLayer != null) {
            if (prevLayer.getNeurons().size() != weights.length) {
                throw new IllegalArgumentException("The number of weights must match the number of neurons in previous layer");
            } else {
                List<Neuron> prevLayerNeurons = prevLayer.getNeurons();
                for (int i = 0; i < prevLayerNeurons.size(); i++) {
                    neuron.addSynapse(new Synapse(weights[i], prevLayerNeurons.get(i)));
                }
            }
        }
    }
    
    public List<Neuron> getNeurons() {
        return this.neurons;
    }

    public void feedForward() {
        int biasCount = hasBias() ? 1 : 0;
        for (int i = biasCount; i < neurons.size(); i++) {
            neurons.get(i).activate();
        }
    }

    void setPrevLayer(Layer prevLayer) {
        this.prevLayer = prevLayer;
    }
    
    public Layer getPrevLayer() {
        return prevLayer;
    }

    void setNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
    }
    
    public Layer getNextLayer() {
        return nextLayer;
    }

    public boolean isInputLayer() {
        return prevLayer == null;
    }
    
    public boolean isOutputLayer() {
        return nextLayer == null;
    }

    public boolean hasBias() {
        return bias != null;
    }
}
