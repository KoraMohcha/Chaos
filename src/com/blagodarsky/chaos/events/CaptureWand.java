package com.blagodarsky.chaos.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import com.blagodarsky.chaos.Chaos;
import com.blagodarsky.chaos.utils.Api;

public class CaptureWand implements Listener
{
	private List<Player> cooldown = new ArrayList<>();
	@EventHandler
	public void capturing (PlayerInteractAtEntityEvent e)
	{
		if (e.getRightClicked() instanceof LivingEntity && !(e.getRightClicked() instanceof Player))
		{
			Player p = e.getPlayer();
			LivingEntity ent = (LivingEntity) e.getRightClicked();
			ItemStack i = p.getInventory().getItemInMainHand();
			if (i.equals(Chaos.Capture_wand))
			{
				ItemMeta meta = i.getItemMeta();
				List<String> lore = new ArrayList<>();
				lore.add(ent.getType().toString());
				meta.setLore(lore);
				i.setItemMeta(meta);
				ent.remove();
				Location entloc = ent.getLocation().clone();
				entloc.setY(entloc.getY()+ent.getHeight()/2);
				p.getWorld().playSound(ent.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
				p.getWorld().spawnParticle(Particle.CLOUD, entloc, 10);
				cooldown.add(p);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Chaos.plugin, new Runnable() 
				{
					
					@Override
					public void run() 
					{
						cooldown.remove(p);	
					}
				},10);
			}
		}
	}
	@EventHandler (priority = EventPriority.LOWEST)
	public void releasing (PlayerInteractEvent e)
	{
		try 
		{
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				Player p = e.getPlayer();
				
				if (cooldown.contains(p)) return;
				
				ItemStack i = p.getInventory().getItemInMainHand();
				if (i.getType().equals(Material.BLAZE_ROD) && i.getItemMeta().hasLore())
				{
					String mobName = i.getItemMeta().getLore().get(0);
					EntityType mobtype = EntityType.valueOf(mobName);
					Block b = e.getPlayer().getTargetBlock(null, 150);
					LivingEntity n = (LivingEntity) e.getPlayer().getWorld().spawnEntity(b.getLocation().add(0.5, 1, 0.5), mobtype);
					ItemMeta meta = i.getItemMeta();
					meta.setLore(null);
					i.setItemMeta(meta);
					p.getWorld().spawnParticle(Particle.CLOUD, Api.middleEntity(n), 10); 
					p.getWorld().playSound(Api.middleEntity(n), Sound.BLOCK_BEACON_ACTIVATE,1 , 1);
				}
			}
		}
		catch (Exception u)
		{
			
		}
	}
}
