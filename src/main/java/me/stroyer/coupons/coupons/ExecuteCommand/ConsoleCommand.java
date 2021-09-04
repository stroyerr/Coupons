package me.stroyer.coupons.coupons.ExecuteCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ConsoleCommand {
    public static void send(String command, Player p){
        if(command.contains("[") && command.contains("]")){
            String result = command.substring(command.indexOf("[") + 1, command.indexOf("]"));
            if(result.equals("player")){
                String newCommand = command.replace("[player]", p.getName());
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, newCommand);
            }
        }else{
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, command);
        }

    }
}
