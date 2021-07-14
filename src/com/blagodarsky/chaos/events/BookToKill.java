package com.blagodarsky.chaos.events;

import com.blagodarsky.chaos.Chaos;
import com.blagodarsky.chaos.utils.KillersBook;
import com.blagodarsky.chaos.utils.Vars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class BookToKill implements Listener
{
    @EventHandler
    public void edit (PlayerEditBookEvent e)
    {
        if(Vars.itemIsKillerBook(e.getPlayer().getInventory().getItemInMainHand()))
        {
            ItemStack book = e.getPlayer().getInventory().getItemInMainHand();
            BookMeta meta = (BookMeta) book.getItemMeta();
            String words [] = e.getNewBookMeta().getPage(1).split(" ");
            if (Bukkit.getPlayer(words[0]) != null)
            {
                Player killedPlayer = Bukkit.getPlayer(words[0]);
                new BukkitRunnable()
                {
                    int i = 10;
                    @Override
                    public void run()
                    {
                        if(i>0)
                        {
                            killedPlayer.sendTitle("You Will die in " + i-- + " seconds", "");
                        }
                        else
                        {
                            killedPlayer.setHealth(0);
                            cancel();
                        }
                    }
                }.runTaskTimer(Chaos.plugin,0, 20);
            }
        }
    }
}
