package Logics;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DepoCollection {

    private final Map<String, Depo<Locomotive, IAdditions>> depoStages;
    private final int frameWidth;
    private final int frameHeight;
    private final String separator = ":";

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

    public boolean saveAllData(String filename) throws IOException {

        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write("DepoCollection\n");
        for (String level : depoStages.keySet()) {
            if(!depoStages.containsKey(level)){
                return false;
            }
            fileWriter.write("Depo" + separator + level + "\n");
            ITransport locomotive = null;
            for (int i = 0; (locomotive = depoStages.get(level).get(i)) != null; i++) {
                if (locomotive.getClass().toString().equals("class Logics.Locomotive")) {
                    fileWriter.write("Locomotive" + separator);
                }
                if (locomotive.getClass().toString().equals("class Logics.ElLocomotive")) {
                    fileWriter.write("ElLocomotive" + separator);
                }
                fileWriter.write(locomotive.toString() + "\n");
            }

        }

        fileWriter.close();
        return true;
    }

    public boolean saveChosenDepoData(String filename, String name) throws IOException {

        if(!depoStages.containsKey(name)){
            return false;
        }
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write("DepoCollection\n");
        Depo<Locomotive, IAdditions> depo = depoStages.get(name);
        fileWriter.write("Depo" + separator + name + "\n");
        ITransport locomotive = null;

        for (int i = 0; (locomotive = depo.get(i)) != null; i++) {
            if (locomotive.getClass().toString().equals("class Logics.Locomotive")) {
                fileWriter.write("Locomotive" + separator);
            }
            if (locomotive.getClass().toString().equals("class Logics.ElLocomotive")) {
                fileWriter.write("ElLocomotive" + separator);
            }
            fileWriter.write(locomotive.toString() + "\n");
        }
        fileWriter.close();
        return true;
    }

    public boolean loadChosenDepoData(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        String line = scanner.nextLine();
        String key = "";
        Locomotive locomotive = null;

        if (line.contains("DepoCollection")) {

            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else {
                line = null;
            }

            while (line != null) {
                if (line.contains("Depo")) {
                    key = line.split(separator)[1];
                    if (depoStages.containsKey(key)) {
                        depoStages.get(key).deleteLocomotives();
                    } else {
                        depoStages.put(key, new Depo<>(frameWidth, frameHeight));
                    }
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                    } else {
                        break;
                    }
                    continue;
                }
                if (line.split(separator)[0].equals("Locomotive")) {
                    locomotive = new Locomotive(line.split(separator)[1]);
                } else if (line.split(separator)[0].equals("ElLocomotive")) {
                    locomotive = new ElLocomotive(line.split(separator)[1]);
                }
                var result = depoStages.get(key).add(locomotive);
                if (!result) {
                    return false;
                }
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                } else {
                    break;
                }
            }
            fileReader.close();
            return true;
        }
        fileReader.close();
        return false;
    }

    public boolean loadAllData(String filename) throws IOException {

        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);

        String line = scanner.nextLine();
        String key = "";
        Locomotive locomotive = null;

        if (line.contains("DepoCollection")) {
            depoStages.clear();
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else {
                line = null;
            }
            while (line != null) {
                if (line.contains("Depo")) {
                    key = line.split(separator)[1];
                    depoStages.put(key, new Depo<Locomotive, IAdditions>(frameWidth, frameHeight));
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                    } else {
                        break;
                    }
                    continue;
                }
                if (line.split(separator)[0].equals("Locomotive")) {
                    locomotive = new Locomotive(line.split(separator)[1]);
                } else if (line.split(separator)[0].equals("ElLocomotive")) {
                    locomotive = new ElLocomotive(line.split(separator)[1]);
                }
                var result = depoStages.get(key).add(locomotive);
                if (!result) {
                    return false;
                }
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                } else {
                    break;
                }
            }
            fileReader.close();
            return true;
        }
        fileReader.close();
        return false;
    }

}
