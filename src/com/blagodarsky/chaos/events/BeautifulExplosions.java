package com.blagodarsky.chaos.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.util.Vector;

public class BeautifulExplosions implements Listener
{
	@EventHandler
	public void explosion (BlockExplodeEvent e)
	{
		//BeautifulExplosions.e.setCancelled(true);
		for (Block b : e.blockList())
		{
			FallingBlock m = b.getWorld().spawnFallingBlock(b.getLocation(), b.getBlockData());
			b.setType(Material.AIR);
			m.setVelocity(new Vector(0, 1, 0));
		}
	}
}
