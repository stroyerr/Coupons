package me.stroyer.coupons.coupons.Commands;

import me.stroyer.coupons.coupons.Main;
import me.stroyer.coupons.coupons.Methods.CouponObject;
import me.stroyer.coupons.coupons.Methods.Generate.Coupons;
import me.stroyer.coupons.coupons.Methods.Send;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class coupon implements CommandExecutor {

    private final Main main;
    public coupon(Main main){this.main = main;}

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        List<String> argsList = Arrays.asList(args);

        Player p = (Player) sender;

        if(args.length == 0){
            Send.message(p, "Coupons plugin developed by Stroyer_ for LonelyMC. Use /coupons help for commands.");
        }

        if(argsList.contains("create")){
            if(args.length != 4){
                Send.message(p, "Invalid syntax. Use /c create <name> <PLAYER|PLAYER> <command>");
            }else{
                if(argsList.get(0).equals("create")){
                    int i;
                    if(argsList.get(2).equals("CONSOLE")){
                        i = 0;
                        CouponObject c = new CouponObject(argsList.get(3), argsList.get(1), i);
                        Send.message(p, "Creating coupon " + c.name + " with executor of " + c.type);
                        Coupons.create(c);
                    }else if(argsList.get(2).equals("PLAYER")){
                        i = 1;
                        CouponObject c = new CouponObject(argsList.get(3), argsList.get(1), i);
                        Send.message(p, "Creating coupon " + c.name + " with executor of " + c.type);
                        Coupons.create(c);
                    }else{
                        Send.message(p, "Invalid arguments. Command type must be either 'CONSOLE' or 'PLAYER'.");
                        return true;
                    }
                }else{
                    Send.message(p, "Invalid syntax. Use /c create <name> <PLAYER|PLAYER> <command>");
                    return true;
                }
            }
        }

        return true;
    }
}
