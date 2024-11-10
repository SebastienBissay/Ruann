import processing.core.PApplet;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

// Random Untrained Artificial Neurons Network
public class Ruann extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Ruann.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
    }

    @Override
    public void setup() {
        noLoop();
    }

    @Override
    public void draw() {
        Ann redAnn = new Ann(3, this);
        Ann greenAnn = new Ann(3, this);
        Ann blueAnn = new Ann(3, this);
        loadPixels();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float[] inputs = {
                        map(x, 0, width, 0, 1),
                        map(y, 0, height, 0, 1),
                        map(dist(x, y, width / 2f, height / 2f), 0, max(width, height) / sqrt(2), 0, 1)};
                int red = round(256 * redAnn.run(inputs));
                int green = round(256 * greenAnn.run(inputs));
                int blue = round(256 * blueAnn.run(inputs));
                pixels[x + width * y] = color(red, green, blue);
            }
        }
        updatePixels();

        saveSketch(this);
    }
}
