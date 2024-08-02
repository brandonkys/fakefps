package io.github.brandonkys.fakefps.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {

    private static Client instance;
    private FileConfiguration config;


    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return instance;
    }

    @Override
    public void onInitializeClient() {

    }

    public void init() {

        this.config = new FileConfiguration("config/fakefps.yml");
        this.config.setDefault("min", 230000);
        this.config.setDefault("max", 250000);

    }

    public FileConfiguration getConfig() {
        return config;
    }
}