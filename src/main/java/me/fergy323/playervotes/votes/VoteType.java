package me.fergy323.playervotes.votes;

import me.fergy323.playervotes.PlayerVotes;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Set;

public class VoteType {

    private String name;
    private String label;
    private String command;

    private ArrayList<VoteType> voteTypes = new ArrayList<VoteType>();
    private PlayerVotes plugin = PlayerVotes.getInstance();

    public VoteType(){}

    public VoteType(String name, String label, String command) {
        this.name = name;
        this.label = label;
        this.command = command;
    }

    public VoteType(String label){
        this.label = label;

    }

    public String getName() {
        return name;
    }

    public String getName(String label){
        return plugin.storageManager.getConfig().getString("votes."+label+"name");
    }

    public String getLabel() {
        return label;
    }

    public String getCommand() {
        return command;
    }

    public String getCommand(String label) {
        return plugin.storageManager.getConfig().getString("votes."+label+"command");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void registerVoteTypes(){
        Set<String> votes = plugin.storageManager.getVotesFile().getSection("votes").singleLayerKeySet();
        Bukkit.getServer().getLogger().info(votes.toString());
        for(String s : votes){
            String name = plugin.storageManager.getVotesFile().getString("votes." + s +  ".name");
            Bukkit.getServer().getLogger().info("DEBUG: new VoteType name: " + name);
            String command = plugin.storageManager.getVotesFile().getString("votes." + s +  ".command");
            Bukkit.getServer().getLogger().info("DEBUG: new VoteType command: " + command);
            plugin.voteList.setVoteType(new VoteType(name, s, command));
        }
    }


}
