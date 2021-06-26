package com.blagodarsky.chaos.utils;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

public class SheepTimer extends BukkitRunnable
{
	private Sheep s;
	private int timer = 0;
	private int eventTime = 0;
	public SheepTimer (Sheep s)
	{
		eventTime = getNextRandomTime();
		this.s = s;
	}
	
	@Override
	public void run() 
	{
		if (s.isDead())
		{
			cancel();
			return;
		}
		if (timer == eventTime)
		{
			Player p = Api.getNearestPlayerToSheep(s.getLocation());
			Api.randomMsg(p);
			timer = 0;
			eventTime = getNextRandomTime();
		}
		if (s.getTicksLived() == 300)
		{
			s.remove();
		}
		timer ++;
		
	}
	public int getNextRandomTime ()
	{
		int i = (int) (Math.random()*40+30);
		return i;
	}
	
}
