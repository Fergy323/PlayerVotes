package me.fergy323.playervotes.commands;

import me.fergy323.playervotes.PlayerVotes;
import me.fergy323.playervotes.lang.Lang;
import me.fergy323.playervotes.utils.Chat;
import me.fergy323.playervotes.votes.Vote;
import me.fergy323.playervotes.votes.VoteType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartVote implements CommandExecutor {

    private PlayerVotes plugin = PlayerVotes.getInstance();
    private Chat chatFormat = plugin.chat;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!sender.hasPermission("playervotes.vote.start")){
            chatFormat.sendNoPermissionMessage(sender);
            return true;
        }

        if(args.length < 2){
            chatFormat.sendUsage(cmd.getUsage(), sender);
            return true;
        }

        if(PlayerVotes.getVoteInProgress() != null){
            chatFormat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.VOTE_IN_PROGRESS), sender);
            return true;
        }

        if(!plugin.voteList.isVoteType(args[0])){
            chatFormat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.INVALID_VOTE), sender);
            chatFormat.sendFormattedMessage("These are the currently valid vote types on your server:", sender);
            for(VoteType v : plugin.voteList.getVoteTypes()){
                chatFormat.sendFormattedMessage(v.getName(), sender);
            }
            return true;
        }

        String voteLabel = args[0];
        Player target = Bukkit.getServer().getPlayerExact(args[1]);

        if(target == null){
            chatFormat.sendNoPlayerMessage(sender);
            return true;
        }

        VoteType voteType = new VoteType(voteLabel);

        PlayerVotes.callVote(new Vote(voteType, sender, target));

        return true;
    }
}
