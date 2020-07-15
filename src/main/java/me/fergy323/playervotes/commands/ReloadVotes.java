package me.fergy323.playervotes.commands;

import me.fergy323.playervotes.PlayerVotes;
import me.fergy323.playervotes.lang.Lang;
import me.fergy323.playervotes.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadVotes implements CommandExecutor {

    private PlayerVotes plugin = PlayerVotes.getInstance();
    private Chat chat = plugin.chat;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (!sender.hasPermission("playervotes.reload")){
            chat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.NO_PERMISSION), sender);
            return true;
        }

        plugin.voteList.reload();

        return true;
    }
}
