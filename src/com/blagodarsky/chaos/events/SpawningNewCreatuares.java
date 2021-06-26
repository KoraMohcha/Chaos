package com.blagodarsky.chaos.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Explosive;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.blagodarsky.chaos.Chaos;
import com.blagodarsky.chaos.utils.Api;
import com.blagodarsky.chaos.utils.SheepTimer;

public class SpawningNewCreatuares implements Listener
{
	private List<Sheep> m = new ArrayList<>();
	@EventHandler
	public void myCreatures (EntityDamageByEntityEvent e)
	{
		if(e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player)
		{
			Player p = (Player) e.getDamager();
			if (p.isSneaking())
			{
				if (e.isCancelled() || e.getEntity().getType() == EntityType.WOLF || e.getDamager().getType() == EntityType.WOLF)
				{
					return;
				}
				Wolf w = (Wolf)e.getDamager().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.WOLF);
				w.setTarget((LivingEntity) e.getDamager());
			}
		}
	}
	
	@EventHandler
	public void mine (ExplosionPrimeEvent e)
	{
		Location l = e.getEntity().getLocation();
		for (int i = 0; i<5;i++)
		{
			Sheep s = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
			s.setCustomName("Dinnerbone");
			s.setNoDamageTicks(20);
			Api.randomSheepColor(s);
			m.add(s);
			SheepTimer b = new SheepTimer(s);
			b.runTaskTimer(Chaos.plugin, 1, 1);
		}
	}
	@EventHandler (priority = EventPriority.LOW)
	public void notMine (EntityDamageEvent e)
	{
		if(m.contains(e.getEntity()))
		{
			if(e.getCause() == DamageCause.BLOCK_EXPLOSION || e.getCause() == DamageCause.ENTITY_EXPLOSION)
			{
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void spawningFireZombies (EntitySpawnEvent e)
	{
		if (e.getEntity().getType() == EntityType.ZOMBIE)
		{
			Zombie z = (Zombie) e.getEntity();
			z.setFireTicks(1728000);
			z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1728000, 0, false));
		}
	}
	@EventHandler
	public void explosiveArrows (ProjectileHitEvent e)
	{
		if (e.getEntity() instanceof EnderPearl) return;
		
		e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 5);
		e.getEntity().remove();
	}
	@EventHandler
	public void spawningGhast (EntitySpawnEvent e)
	{
		if (e.getEntity() instanceof Sheep || e.getEntity() instanceof Wolf || e.getEntity() instanceof FallingBlock|| e.getEntity() instanceof Item)
		{
			return;
		}
		int i = (int) (Math.random()*100);
		if (e.getEntity() instanceof Enderman)
		{
			Enderman m = (Enderman)e.getEntity();
			ItemStack in = new ItemStack(Material.IRON_SWORD);
			//m.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_BLOCK));
			//for (m.getEquipment())
			m.setCarriedBlock(Bukkit.createBlockData(Material.BEDROCK));
			return;
		}
		if (i == 1)
		{
			e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.GHAST);
			e.getEntity().remove();
		}
		
	}
	
}