package me.stroyer.coupons.coupons.Methods.GameHandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class CouponItem {
    public CouponItem(Player p, String name){
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bm = (BookMeta) item.getItemMeta();
        bm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&l" + name + " &6&lCoupon"));
        bm.addPage("Hey, you just opened a book\nAs you can see there are 2 lines in this book");
        bm.addPage("Welocome to the second page\n \nThis is on the third line.");
        bm.setAuthor("LonelyMC Coupons");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Open this book to redeem your coupon!");
        bm.setLore(lore);
        item.setItemMeta(bm);
        p.getInventory().addItem(item);
    }
}
