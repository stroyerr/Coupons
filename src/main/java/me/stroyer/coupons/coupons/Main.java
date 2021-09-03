package me.stroyer.coupons.coupons;

import me.stroyer.coupons.coupons.Commands.coupon;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("coupon").setExecutor(new coupon(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
