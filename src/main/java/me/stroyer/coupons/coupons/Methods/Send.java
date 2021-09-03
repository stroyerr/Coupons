package me.stroyer.coupons.coupons.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Send {
    public static void message(Player p, String msg){
        p.sendMessage(ChatColor.RED + "Lonely" + ChatColor.GOLD + "MC" + ChatColor.GRAY + "Coupons // " + ChatColor.LIGHT_PURPLE + msg);
    }
}
