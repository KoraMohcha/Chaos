package com.blagodarsky.chaos.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Helper implements Listener
{
	public void helper (PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if (p.hasPlayedBefore()) return;
		else
		{
			p.sendMessage("Hello, traveller. I`ll help you how you must survive on this server,"
					+ " and what you must be careful in. First, there are a lot of ghasts here."
					+ "They are everywhere. To help you with him, I gave you especial possibility."
					+ "When you have a sword in your inventory, take it in hand and right click, to send a"
					+ "wither head to place where you watch. But be careful with him! You can be blown up with it!"
					+ "When the night come, don`t go outside! All mobs will be invisible then."
					+ "Another advice I`ll give you, you should press shift when you mine diamonds, gold and other"
					+ "ores.");
		}
	}
}
