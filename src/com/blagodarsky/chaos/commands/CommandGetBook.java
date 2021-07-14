package com.blagodarsky.chaos.commands;

import com.blagodarsky.chaos.utils.KillersBook;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGetBook implements CommandExecutor
{

    @Override
    public boolean onCommand( CommandSender sender ,Command cmd, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;
            p.getInventory().addItem(new KillersBook(p).getBook());
        }
        else sender.sendMessage("Only for players!");
        return true;
    }
}
