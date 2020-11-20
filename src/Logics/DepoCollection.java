package Logics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DepoCollection {

    private final Map<String, Depo<Locomotive, IAdditions>> depoStages;
    private final int frameWidth;
    private final int frameHeight;

    public DepoCollection(int frameWidth, int frameHeight) {
        depoStages = new HashMap<>();
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }

    public Set<String> keySet() {
        return depoStages.keySet();
    }

    public void addDepo(String name) {
        if (!depoStages.containsKey(name)) {
            depoStages.put(name, new Depo<>(frameWidth, frameHeight));
        }
    }

    public void removeDepo(String name) {
        depoStages.remove(name);
    }

    public Depo<Locomotive, IAdditions> get(String name) {
        if (depoStages.containsKey(name)) {
            return depoStages.get(name);
        }
        return null;
    }

    public Locomotive get(String name, int index) {
        if (depoStages.containsKey(name)) {
            return depoStages.get(name).get(index);
        }
        return null;
    }
}
