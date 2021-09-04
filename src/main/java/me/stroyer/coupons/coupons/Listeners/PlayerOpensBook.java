package me.stroyer.coupons.coupons.Listeners;

import me.stroyer.coupons.coupons.ExecuteCommand.ConsoleCommand;
import me.stroyer.coupons.coupons.Methods.CouponObject;
import me.stroyer.coupons.coupons.Methods.Generate.Coupons;
import me.stroyer.coupons.coupons.Methods.Send;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class PlayerOpensBook implements Listener {
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR )|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            if(e.getPlayer().getInventory().getItemInMainHand().getType() == book.getType() || e.getPlayer().getInventory().getItemInOffHand().getType() == book.getType()){
                Player p = e.getPlayer();
                book = e.getPlayer().getInventory().getItemInMainHand();
                BookMeta bm = (BookMeta) book.getItemMeta();
                e.setCancelled(true);
                if(bm.hasAuthor()){
                    if(bm.getAuthor().equals("LonelyMC Coupons")) {
                        if(!(bm.hasGeneration())){
                            Send.message(p, "You are attempting to use a FAKE coupon!");
                            return;
                        }
                        if(bm.getGeneration() == (BookMeta.Generation.ORIGINAL)) {
                            if(bm.hasDisplayName()){
                                String[] couponNameArray = bm.getDisplayName().split(" ");
                                String couponName = ChatColor.stripColor(couponNameArray[0]);
                                Coupons.build();
                                List<CouponObject> couponsList = Coupons.coupons;
                                Boolean foundCoupon = false;
                                CouponObject couponObj = null;
                                for(int i = 0; i < couponsList.size(); i ++){
                                    if(couponsList.get(i).name.equals(couponName)){
                                        foundCoupon = true;
                                        couponObj = couponsList.get(i);
                                        break;
                                    }else{
                                        foundCoupon = false;
                                    }
                                }
                                if(foundCoupon){
                                    String commandToExecute = couponObj.command;
                                    String command =  commandToExecute;
                                    if(couponObj.type.equals("CONSOLE")){
                                        ConsoleCommand.send(command, p);
                                    }else if(couponObj.type.equals("PLAYER")){
                                        p.chat("/" + couponObj.command);
                                    }else{
                                        Send.message(p, "Coupon is corrupt. Please contact Stroyer_ immediately.");
                                    }
                                    e.setCancelled(true);
                                    p.getInventory().removeItem(book);
                                    Bukkit.broadcastMessage(ChatColor.RED + "Lonely" + ChatColor.GOLD + "MC" + ChatColor.GRAY + "Coupons // " + ChatColor.YELLOW + p.getName() + ChatColor.LIGHT_PURPLE + " used a " + ChatColor.YELLOW + couponObj.name + ChatColor.LIGHT_PURPLE + " coupon!");

                                }else{
                                    Send.message(p, "This coupon does not exist or is expired! If you believe this is an error, contact staff.");
                                }
                            }
                        }else{
                            Send.message(p, "This Coupon is not an original and is intead a " + bm.getGeneration().toString() + ". Attemping to create or use fraudulent coupons can result in a ban!");
                            p.getInventory().removeItem(book);
                        }
                    }
                }

            }
        }
    }
}
