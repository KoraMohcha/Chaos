package com.blagodarsky.chaos.utils;

import java.util.Collection;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import com.blagodarsky.chaos.Chaos;

public class Api 
{
	
	public static void randomSheepColor (Sheep s)
	{
		DyeColor [] allColours = DyeColor.values();
		int i = (int) (Math.random()*allColours.length);
		s.setColor(allColours[i]);
	}
	public static void randomMsg(Player p)
	{
		if (p == null)
		{
			return;
		}
		List <String> chaosMsgs = Chaos.plugin.getConfig().getStringList("sheep-dialog");
		int i = (int) (Math.random()*chaosMsgs.size());
		p.sendMessage(getRandomMsgColor() + chaosMsgs.get(i));
	}
	public static Player getNearestPlayerToSheep (Location l)
	{
		Collection<Entity> nearestEntities = l.getWorld().getNearbyEntities(l, 7, 7, 7);
		for (Entity m : nearestEntities)
		{
			if (m instanceof Player)
			{
				return (Player) m;
			}
		}
		return null;
	}
	public static ChatColor getRandomMsgColor ()
	{
		int i = (int) (Math.random()*ChatColor.values().length);
		return ChatColor.values()[i];
	}
	public static Location middleEntity (LivingEntity ent)
	{
		Location entloc = ent.getLocation().clone();
		entloc.setY(entloc.getY()+ent.getHeight()/2);
		return entloc;
	}
}
