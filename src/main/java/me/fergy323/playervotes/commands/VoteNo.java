package me.fergy323.playervotes.commands;

import me.fergy323.playervotes.PlayerVotes;
import me.fergy323.playervotes.lang.Lang;
import me.fergy323.playervotes.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteNo implements CommandExecutor {

    private PlayerVotes plugin = PlayerVotes.getInstance();
    private Chat chatFormat = plugin.chat;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!sender.hasPermission("playervotes.vote")){
            chatFormat.sendNoPermissionMessage(sender);
            return true;
        }

        if(PlayerVotes.getVoteInProgress() == null){
            chatFormat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.NO_VOTE_IN_PROGRESS), sender);
            return true;
        }

        if(!(sender instanceof Player)){
            chatFormat.sendNoPlayerMessage(sender);
            return true;
        }

        Player player = (Player) sender;

        if(PlayerVotes.getVoteInProgress().hasVoted(player)){
            chatFormat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.ALREADY_VOTED), player);
            return true;
        }

        PlayerVotes.getVoteInProgress().vote(player, false);


        return true;
    }
}
