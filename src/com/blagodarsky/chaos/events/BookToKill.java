package com.blagodarsky.chaos.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

import com.blagodarsky.chaos.Chaos;
import com.blagodarsky.chaos.utils.KillTimer;

public class BookToKill implements Listener
{
	@EventHandler
	public void bookRedEvent (PlayerEditBookEvent e)
	{
		if(!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(Chaos.deathNote.getItemMeta().getDisplayName()))
		{
			return;
		}
		BookMeta iChto = e.getNewBookMeta();
		if (Bukkit.getPlayer(iChto.getPage(1).split(" ")[0])!=null)
		{
			e.getPlayer().sendMessage("He will die soon");
			e.getPlayer().getInventory().clear(e.getSlot());

			KillTimer timer = new KillTimer(e.getPlayer());
			timer.runTaskTimer(Chaos.plugin, 0, 20);
		}
	}
}
