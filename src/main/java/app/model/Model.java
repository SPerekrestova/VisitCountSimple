package app.model;

import app.entities.File;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<File> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(File file) {
        model.add(file);
    }

    public List<String> pathList() {
        return model.stream()
                .map(File::getFilePath)
                .collect(Collectors.toList());
    }

    public List<String> nameList() {
        return model.stream()
                .map(File::getFileName)
                .collect(Collectors.toList());
    }

}
