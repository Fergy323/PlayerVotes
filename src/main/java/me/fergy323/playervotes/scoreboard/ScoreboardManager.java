package me.fergy323.playervotes.scoreboard;

import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import me.fergy323.playervotes.PlayerVotes;
import me.fergy323.playervotes.votes.Vote;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ScoreboardManager {

    private PlayerVotes plugin = PlayerVotes.getInstance();
    private boolean titleManager = plugin.getServer().getPluginManager().getPlugin("TitleManager") != null;

    public void setVotingScoreboard(Vote vote){
        if(titleManager){
            TitleManagerAPI titleManagerAPI = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");
            assert titleManagerAPI != null;

            for(Player p : Bukkit.getServer().getOnlinePlayers()){
                titleManagerAPI.setScoreboardTitle(p, ChatColor.WHITE + "PlayerVotes");
                Bukkit.broadcastMessage(titleManagerAPI.getScoreboardTitle(p));
            }
        }
    }
}
