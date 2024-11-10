import processing.core.PApplet;

import static parameters.Parameters.NEURONS_PER_LAYER;
import static parameters.Parameters.NUMBER_OF_LAYERS;

public class Ann {
    private final Neuron[][] structure;

    public Ann(int numberInputs, PApplet pApplet) {
        structure = new Neuron[NUMBER_OF_LAYERS][];

        // Inputs
        structure[0] = new InputNeuron[NEURONS_PER_LAYER[0]];
        for (int i = 0; i < NEURONS_PER_LAYER[0]; i++) {
            structure[0][i] = new InputNeuron(numberInputs + 1, pApplet);
        }

        // Following layers
        for (int i = 1; i < NUMBER_OF_LAYERS; i++) {
            structure[i] = new Neuron[NEURONS_PER_LAYER[i]];
            for (int j = 0; j < NEURONS_PER_LAYER[i]; j++) {
                structure[i][j] = new Neuron(pApplet, structure[i - 1]);
            }
        }
    }

    public float run(float[] inputs) {
        for (InputNeuron inputNeuron : (InputNeuron[]) structure[0]) {
            inputNeuron.fire(inputs);
        }
        for (int i = 1; i < NUMBER_OF_LAYERS; i++) {
            for (Neuron neuron : structure[i]) {
                neuron.fire();
            }
        }
        return structure[NUMBER_OF_LAYERS - 1][0].output;
    }
}
