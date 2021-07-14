package com.blagodarsky.chaos.utils;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Vars
{
    public static final HashMap <ItemStack, KillersBook> KILLERS_BOOKS = new HashMap<>();
    public static boolean itemIsKillerBook (ItemStack item)
    {
        return KILLERS_BOOKS.containsKey(item);
    }
}
