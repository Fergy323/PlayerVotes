package me.fergy323.playervotes.votes;

import de.leonhard.storage.Yaml;
import me.fergy323.playervotes.PlayerVotes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class VoteLogger {

    private PlayerVotes plugin = PlayerVotes.getInstance();

    public VoteLogger(){}

    public void logVote(Vote v){
        if(plugin.storageManager.getConfig().getBoolean("votes.log")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
            LocalDateTime now = LocalDateTime.now();
            Yaml yaml = new Yaml("votelog_" + dtf.format(now) + v.getVoteType().getLabel() + v.getTargetPlayer().getName(), "plugins/PlayerVotes/logs");

            ArrayList<String> yesNames = new ArrayList<>();
            for (Player p : v.getForYes()) {
                yesNames.add(p.getName());
            }
            ArrayList<String> noNames = new ArrayList<>();
            for (Player p : v.getForNo()) {
                noNames.add(p.getName());
            }

            yaml.set("objective", v.getVoteObjective());
            yaml.set("started_by", v.getVoteSender().getName());
            yaml.set("votes_yes", v.getForYes().size());
            yaml.set("votes_no", v.getForNo().size());
            yaml.set("players_yes", yesNames);
            yaml.set("players_no", noNames);
            yaml.set("duration", v.getDuration());

            Bukkit.getServer().getLogger().info("[PlayerVotes] Vote log created for " + v.getVoteObjective() + "!");
        }
    }
}
