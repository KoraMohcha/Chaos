package com.blagodarsky.chaos.utils;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class KillTimer extends BukkitRunnable
{
	private Player victim;
	private int timer = 0;
	public KillTimer (Player victim)
	{
		this.victim = victim;
	}
	@Override
	public void run() 
	{
		if(timer==10)
		{
			victim.setHealth(0);
			cancel();
			victim.sendTitle("", "");
			return;
		}
		victim.sendTitle("You will be killed in", (10-timer) + " seconds");
		timer++;
	}

}
