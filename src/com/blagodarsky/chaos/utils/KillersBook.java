package com.blagodarsky.chaos.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KillersBook
{
    private Player owner;
    private ItemStack book;


    private Player victim;

    public KillersBook(Player owner)
    {
        this.owner = owner;
        book = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName(ChatColor.UNDERLINE + "" + ChatColor.RED + "☠...Death Note...☠");
        book.setItemMeta(meta);
        Vars.KILLERS_BOOKS.put(book,this);
    }

    public Player getOwner()
    {
        return owner;
    }

    public ItemStack getBook()
    {
        return book;
    }

    public Player getVictim()
    {
        return victim;
    }

    public void setVictim(Player victim)
    {
        this.victim = victim;
    }
}
