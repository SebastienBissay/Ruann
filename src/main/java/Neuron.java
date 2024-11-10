import processing.core.PApplet;

import static parameters.Parameters.*;

public class Neuron {
    protected Neuron[] inputs;
    protected float[] weights;
    protected float output;

    public Neuron(int weightSLength, PApplet pApplet) {
        weights = new float[weightSLength];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = weightFunction(pApplet);
        }
    }

    public Neuron(PApplet pApplet, Neuron[] inputs) {
        this(inputs.length + 1, pApplet);
        this.inputs = inputs;
    }

    protected static float weightFunction(PApplet pApplet) {
        return pApplet.random(-1, 1);
    }

    // See https://paperswithcode.com/method/srelu
    protected static float sReLu(float x) {
        if (x <= L) {
            return L + (x - L) * G;
        }
        if (x >= R) {
            return R + (x - R) * G;
        }
        return x;
    }

    public void fire() {
        output = BIAS * weights[inputs.length - 1];
        for (int i = 0; i < inputs.length - 1; i++) {
            output += inputs[i].output * weights[i];
        }
        output = sReLu(output);
    }
}
