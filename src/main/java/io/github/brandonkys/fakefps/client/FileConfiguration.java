package io.github.brandonkys.fakefps.client;

import org.simpleyaml.configuration.file.YamlFile;

import java.io.IOException;

public class FileConfiguration {

    private YamlFile file;

    public FileConfiguration(String name) {
        this.file = new YamlFile(name);
        try {
                this.file.createNewFile();
            this.file.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(String path, Object value) {
        try {
            this.file.set(path, value);
            this.file.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefault(String path, Object value) {
        try {
            if (!contains(path)) {
                this.file.set(path, value);
                this.file.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public <A> A get(Class<A> type, String path) {
        return (A) this.file.get(path);
    }

    @SuppressWarnings("unchecked")
    public <A> A get(Class<A> type, String path, A def) {
        return (A) this.file.get(path, def);
    }

    public boolean contains(String path) {
        return this.file.contains(path);
    }

    public YamlFile getFile() {
        return file;
    }
}
