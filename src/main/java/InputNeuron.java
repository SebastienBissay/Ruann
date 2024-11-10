import processing.core.PApplet;

import static parameters.Parameters.BIAS;

public class InputNeuron extends Neuron {
    public InputNeuron(int weightsLength, PApplet pApplet) {
        super(weightsLength, pApplet);
    }

    public void fire(float[] inputs) {
        output = BIAS * weights[weights.length - 1];
        for (int i = 0; i < inputs.length; i++) {
            output += inputs[i] * weights[i];
        }
        output = sReLu(output);
    }
}
