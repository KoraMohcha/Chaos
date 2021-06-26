package com.blagodarsky.chaos.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadCutter implements Listener
{
	@EventHandler
	public void axe (EntityDamageByEntityEvent e)
	{
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			if (p.getHealth()-e.getDamage() >0)
			{
				return;
			}
			ItemStack is = ((Player)e.getDamager()).getInventory().getItemInMainHand();
			if (is.getType().toString().contains("_AXE"))
			{
				ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setOwner(p.getName());
				skull.setItemMeta(meta);
				p.getWorld().dropItem(p.getLocation(), skull);
			}
		}
	}
}
