package com.blagodarsky.chaos.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class InfiniteMobs implements Listener
{
	@EventHandler
	public void rubber (EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Player && e.getEntity() instanceof LivingEntity)
		{
			Player p = (Player) e.getDamager();
			LivingEntity m = (LivingEntity) e.getEntity();
			if(m.getHealth() - e.getDamage() <= 0)
			{
				if(p.isSneaking())
				{
					m.getWorld().spawnEntity(m.getLocation(), m.getType());
				}
			}
		}
	}
}
