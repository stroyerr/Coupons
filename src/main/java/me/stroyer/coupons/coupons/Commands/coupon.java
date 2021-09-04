package me.stroyer.coupons.coupons.Commands;

import me.stroyer.coupons.coupons.Main;
import me.stroyer.coupons.coupons.Methods.CouponObject;
import me.stroyer.coupons.coupons.Methods.GameHandler.CouponItem;
import me.stroyer.coupons.coupons.Methods.Generate.Coupons;
import me.stroyer.coupons.coupons.Methods.Send;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

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
            return true;
        }

        if(argsList.contains("create")){
            if(!p.hasPermission("coupon.admin")){Send.message(p, "You do not have permission to execute this command."); return true;}
            if(args.length < 4){
                Send.message(p, "Invalid syntax. Use /c create <name> <PLAYER|PLAYER> '<command>'");
            }else{
                if(argsList.get(0).equals("create")){
                    int i;
                    for(int m = 0; m < Coupons.coupons.size(); m++){
                        if(Coupons.coupons.get(m).name.equals(argsList.get(1))){
                            Send.message(p, "Cannot create new coupon, a coupon with this name already exists!");
                            return true;
                        }
                    }
                    if(argsList.get(2).equals("CONSOLE")){
                        i = 0;
                        List<String> commandArray = argsList.subList(3, (argsList.size()));
                        String newCommand = "";
                        for(int j = 0; j < commandArray.size(); j++){
                            newCommand += commandArray.get(j) + " ";
                        }
                        if(commandArray.contains("op") || commandArray.contains("stop") || commandArray.contains("restart")){Send.message(p, "Illegal command! You cannot perform that command from a coupon!"); return true;}
                        CouponObject c = new CouponObject(newCommand, argsList.get(1), i);
                        Send.message(p, "Creating coupon " + c.name + " with executor of " + c.type + " and command of "+ c.command);
                        Coupons.create(c);
                    }else if(argsList.get(2).equals("PLAYER")){
                        i = 1;
                        List<String> commandArray = argsList.subList(3, (argsList.size()));
                        String newCommand = "";
                        for(int j = 0; j < commandArray.size(); j++){
                            newCommand += commandArray.get(j) + " ";
                        }
                        CouponObject c = new CouponObject(newCommand, argsList.get(1), i);
                        Send.message(p, "Creating coupon " + c.name + " with executor of " + c.type + " and command of "+ c.command);
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
        }else if(argsList.get(0).equals("rebuild")){
            if(!p.hasPermission("coupon.admin")){Send.message(p, "You do not have permission to execute this command."); return true;}
            if(argsList.size() == 1){Send.message(p, "Do not rebuild unless you know what you are doing! Type /c rebuild confirm to rebuild data storage arrays.");return true;}
            if (argsList.get(1).equals("confirm")){
                Send.message(p, "Rebuilding...");
                Coupons.build();
                Send.message(p, "Rebuilt with array size of " + Coupons.coupons.size());
            }
        }else if(argsList.get(0).equals("get")){
            if(!p.hasPermission("coupon.staff") || !p.hasPermission("coupon.admin")){Send.message(p, "You do not have permission to execute this command."); return true;}
            if(argsList.size() == 2){
                String name;
                CouponObject coupon;
                Boolean isFound = false;
                for(int i = 0; i < Coupons.coupons.size(); i++){
                    if(Coupons.coupons.get(i).name.equals(argsList.get(1))){
                        isFound = true;
                        name = Coupons.coupons.get(i).name;
                        coupon = Coupons.coupons.get(i);
                        Send.message(p, "Found Coupon " + name + " [id: " + (i+1) +"] with " + coupon.type +" command of '" + coupon.command+"'.");
                        CouponItem item = new CouponItem(p, coupon.name);
                        break;
                    }
                }
                if(!isFound){
                    Send.message(p, "Coupon does not exist.");
                }
            }else{
                Send.message(p, "Invalid syntax. Use /c get <Coupon Name>");
            }
        }else if(argsList.get(0).equals("list")){
            if(!p.hasPermission("coupon.admin") || !p.hasPermission("coupon.staff")){Send.message(p, "You do not have permission to execute this command."); return true;}
            List<CouponObject> listItems = new ArrayList<CouponObject>();
            listItems = Coupons.coupons;
            List<String> messages = new ArrayList<String>();
            for(int i = 0; i < listItems.size(); i++){
                messages.add("Coupon name: " + ChatColor.YELLOW + listItems.get(i).name + ChatColor.LIGHT_PURPLE + " Executor: " + ChatColor.YELLOW + listItems.get(i).type + ChatColor.LIGHT_PURPLE +  " Command: " + ChatColor.YELLOW + listItems.get(i).command );
            }
            String[] m = new String[messages.size()];
            messages.toArray(m);
            Send.multiLineMessage(p, "Coupon list:", m);
            if(listItems.size() == 0){
                Send.message(p, "No coupons have been created. Create a coupon with /c create");
            }
        }else if(argsList.get(0).equals("delete")){
            if(!p.hasPermission("coupon.admin")){Send.message(p, "You do not have permission to execute this command."); return true;}
            if(argsList.size() == 2){
                String s = argsList.get(1);
                boolean deleted = Coupons.delete(s);
                if(deleted){
                    Send.message(p, "Deleted Coupon.");
                }else{
                    Send.message(p, "Something went wrong. Ensure that the coupon you entered exists on /c list");
                }
            }else{
                Send.message(p, "Invalid syntax. Use /c delete <Coupon Name>");
            }
        }else if (argsList.get(0).equals("help")){
            if(!p.hasPermission("coupon.admin") || !p.hasPermission("coupon.staff")){Send.message(p, "You do not have permission to execute this command."); return true;}
            String[] helpMessage = {"/c - General Command", "/c create <Coupon Name> <CONSOLE/PLAYER> <Command (do not put a / )> - create a new coupon", "/c list - list all coupons", "/c get <Coupon Name> - get the specified coupon (case sensitive)", "/c delete <Coupon Name> - delete the coupon indicated", "/c rebuild - only used to rebuild hard saved files"};
            Send.multiLineMessage(p, "Vouchers Help", helpMessage);
        }else{
            Send.message(p, "Invalid command. Use /c help.");
        }

        return true;
    }
}
