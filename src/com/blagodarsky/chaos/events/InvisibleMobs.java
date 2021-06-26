package com.blagodarsky.chaos.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InvisibleMobs implements Listener
{
	public void invisMobsAtNight (Player p)
	{
		if (p.getWorld().getTime() == 13000)
		{
			p.sendMessage("Go home! The monsters will be invisible soon!");
			if (p.getWorld().getTime() >= 14500 && p.getWorld().getTime() <= 23000)
			{
				
				for (Entity e : p.getWorld().getEntities())
				{
					if (e instanceof LivingEntity)
					{
						((LivingEntity) e).addPotionEffect(new PotionEffect ( PotionEffectType.INVISIBILITY, 8500, 1));
					}
					
				}
			}
		}
		if (p.getWorld().getTime() >=23000)
		{
			for (Entity e : p.getWorld().getEntities())
			{
				if (e instanceof LivingEntity)
				{
					((LivingEntity) e).addPotionEffect(new PotionEffect ( PotionEffectType.INVISIBILITY, 1, 1));
				}
			}
		}
	}
}
