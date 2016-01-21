package lyw.ml;
import java.util.List;
import java.util.ArrayList;

public class NeuralNetwork {
    private List<Layer> layers;
    private Layer input;
    private Layer output;

    public NeuralNetwork() {
        this.layers = new ArrayList<Layer>();
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
        if (layers.size() == 1) {
            input = layer;
        } else if (layers.size() > 1) {
            Layer prevLayer = layers.get(layers.size() - 2);
            prevLayer.setNextLayer(layer);
            layer.setPrevLayer(prevLayer);
        }
        output = layers.get(layers.size() - 1);
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setInputs(double[] inputs) {
        if (input != null) {
            int biasCount = input.hasBias() ? 1 : 0;
            if (input.getNeurons().size() - biasCount != inputs.length) {
                throw new IllegalArgumentException("The number of inputs must match the number of neurons in input layer");
            } else {
                List<Neuron> neurons = input.getNeurons();
                for (int i = biasCount; i < neurons.size(); i++) {
                    neurons.get(i).setOutput(inputs[i - biasCount]);
                }
            }
        }
    }

    public double[] getOutput() {
        double[] outputs = new double[output.getNeurons().size()];
        for (int i = 1; i < layers.size(); i++) {
            Layer layer = layers.get(i);
            layer.feedForward();
        }
        int i = 0;
        for (Neuron neuron : output.getNeurons()) {
            outputs[i++] = neuron.getOutput();
        }
        return outputs;
    }

    public double[] getWeights() {
    	List<Double> temp_weights = new ArrayList<Double>();
        for (Layer layer : layers) {
            for (Neuron neuron : layer.getNeurons()) {
                for (Synapse synapse: neuron.getSynapses()) {
                	temp_weights.add(synapse.getWeight());
                }
            }
        }
        double[] weights = new double[temp_weights.size()];
        int i = 0;
        for (Double weight : weights) {
            weights[i++] = weight;
        }
        return weights;
    }

    public void resetWeights() {
        for (Layer layer : layers) {
            for (Neuron neuron : layer.getNeurons()) {
                for (Synapse synapse : neuron.getSynapses()) {
                    synapse.setWeight(Math.random() - 0.5);
                }
            }
        }
    }
}
