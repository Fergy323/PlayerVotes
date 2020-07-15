package me.fergy323.playervotes;

import me.fergy323.playervotes.commands.ReloadVotes;
import me.fergy323.playervotes.commands.StartVote;
import me.fergy323.playervotes.commands.VoteNo;
import me.fergy323.playervotes.commands.VoteYes;
import me.fergy323.playervotes.scoreboard.ScoreboardManager;
import me.fergy323.playervotes.utils.Chat;
import me.fergy323.playervotes.votes.Vote;
import me.fergy323.playervotes.votes.VoteList;
import me.fergy323.playervotes.votes.VoteType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public class PlayerVotes extends JavaPlugin {

    public static PlayerVotes instance;
    public StorageManager storageManager;
    public Chat chat;
    public VoteList voteList;
    public ScoreboardManager scoreboardManager;
    private VoteType voteType;
    private static Vote inProgress;

    @Override
    public void onEnable() {
        instance = this;
        storageManager = new StorageManager();
        scoreboardManager = new ScoreboardManager();
        chat = new Chat();
        voteList = new VoteList();
        voteType = new VoteType();

        voteType.registerVoteTypes();

        getCommand("startvote").setExecutor(new StartVote());
        getCommand("voteyes").setExecutor(new VoteYes());
        getCommand("voteno").setExecutor(new VoteNo());
        getCommand("reloadvotes").setExecutor(new ReloadVotes());
    }

    @Override
    public void onDisable() {

    }

    public static PlayerVotes getInstance() {
        return instance;
    }

    public VoteType getVoteType(){
        return voteType;
    }

    public static void callVote(Vote v){
        v.startVote();
        inProgress = v;
    }

    public static Vote getVoteInProgress(){
        return inProgress;
    }

    public static void setVoteInProgress(@Nullable Vote v){
        inProgress = v;
    }
}
