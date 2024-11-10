package parameters;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Parameters {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final long SEED = 11;
    public static final float L = .001f;
    public static final float R = .999f;
    public static final float G = .00001f;
    public static final float BIAS = 1;
    public static final int NUMBER_OF_LAYERS = 3;
    public static final int[] NEURONS_PER_LAYER = {50, 30, 1};

    /**
     * Helper method to extract the constants in order to save them to a json file
     *
     * @return a Map of the constants (name -> value)
     */
    public static Map<String, ?> toJsonMap() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = Parameters.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(Parameters.class));
        }

        return Collections.singletonMap(Parameters.class.getSimpleName(), map);
    }
}
