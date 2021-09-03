package me.stroyer.coupons.coupons;

import me.stroyer.coupons.coupons.Commands.coupon;
import me.stroyer.coupons.coupons.Methods.FileManagement.SaveCoupons;
import me.stroyer.coupons.coupons.Methods.Generate.Coupons;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Main extends JavaPlugin {

    static File f = new File("./plugins/Coupons/data.txt");

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("coupon").setExecutor(new coupon(this));

        try{
            Path path = Paths.get("./plugins/Coupons");
            Files.createDirectories(path);
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Coupons.build();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
