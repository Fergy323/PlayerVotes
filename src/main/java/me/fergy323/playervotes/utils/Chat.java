package me.fergy323.playervotes.utils;

import me.fergy323.playervotes.PlayerVotes;
import me.fergy323.playervotes.lang.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Chat {

    PlayerVotes playerVotes = PlayerVotes.getInstance();

    public String getMessagePrefix(){
        return ChatColor.translateAlternateColorCodes( '&', playerVotes.storageManager.getLangFile().getString(Lang.PREFIX.getPath()));
    }

    public void sendFormattedMessage(String m, CommandSender t){
        t.sendMessage(getMessagePrefix() + " " + ChatColor.WHITE + ChatColor.translateAlternateColorCodes('&', m));
    }

    public void sendNoPermissionMessage(CommandSender t){
        t.sendMessage(getMessagePrefix() + " " + playerVotes.storageManager.getMessage(Lang.NO_PERMISSION));
    }

    public void sendPlayersOnlyMessage(CommandSender t){
        t.sendMessage(getMessagePrefix() + " " + playerVotes.storageManager.getMessage(Lang.ONLY_PLAYERS));
    }

    public void sendNoPlayerMessage(CommandSender t){
        t.sendMessage(getMessagePrefix() + " " + playerVotes.storageManager.getMessage(Lang.INVALID_PLAYER));
    }

    public void sendErrorMessage(String m, CommandSender t){
        t.sendMessage(getMessagePrefix() + " " + ChatColor.RED + m);
    }

    public void sendUsage(String u, CommandSender t){
        t.sendMessage(getMessagePrefix() + " " + playerVotes.storageManager.getMessage(Lang.USAGE) + u);
    }

    public static void broadcast(String m){
        String prefix = ChatColor.translateAlternateColorCodes( '&', PlayerVotes.getInstance().storageManager.getLangFile().getString(Lang.PREFIX.getPath()));
        String message = prefix + " " + ChatColor.translateAlternateColorCodes('&', m);

        Bukkit.getConsoleSender().sendMessage(message);
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            p.sendMessage(message);
        }
    }

    public static String capitalize(String s){
        char[] sArray = s.toCharArray();

        String firstLetter = String.valueOf(sArray[0]).toUpperCase();

        sArray[0] = firstLetter.charAt(0);

        return Arrays.toString(sArray);
    }



}
