package me.fergy323.playervotes.votes;

import me.fergy323.playervotes.PlayerVotes;
import me.fergy323.playervotes.lang.Lang;
import me.fergy323.playervotes.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Vote {

    private VoteType voteType;
    private String voteObjective;
    private int duration;
    private boolean inProgress = false;
    private boolean result;
    private CommandSender voteSender;
    private Player targetPlayer;

    private ArrayList<Player> forYes = new ArrayList<>();
    private ArrayList<Player> forNo = new ArrayList<>();

    private PlayerVotes plugin = PlayerVotes.getInstance();
    private VoteLogger voteLogger = new VoteLogger();

    public Vote(VoteType type, CommandSender sender, Player target){
        this.voteType = type;
        this.voteSender = sender;
        this.targetPlayer = target;
        this.voteObjective = voteType.getLabel() + " " + targetPlayer.getName();
        duration = plugin.storageManager.getConfig().getInt("votes.period");
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public String getVoteObjective(){
        return voteObjective;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public boolean getResult(){
        return result;
    }

    public CommandSender getVoteSender() {
        return voteSender;
    }

    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public boolean hasVoted(Player p){
        return this.forYes.contains(p) || this.forNo.contains(p);
    }

    public ArrayList<Player> getForYes() {
        return forYes;
    }

    public ArrayList<Player> getForNo() {
        return forNo;
    }

    private int[] calculatePercentage(int votesYes, int votesNo){
        int total = votesYes + votesNo;
        if(total == 0){
            return new int[] {0, 0};
        }
        int yesPercentage = votesYes / total;
        int noPercentage = votesNo / total;

        return new int[] {yesPercentage, noPercentage};
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTargetPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public void startVote(){
        PlayerVotes.setVoteInProgress(this);
        inProgress = true;

        plugin.scoreboardManager.setVotingScoreboard(this);

        Chat.broadcast(plugin.storageManager.getMessage(Lang.VOTE_STARTED).replace("{sender}", voteSender.getName()).replace("{objective}", getVoteObjective()));
        if(voteSender instanceof Player) {
            forYes.add((Player) voteSender);
        }
        forNo.add(targetPlayer);
            BukkitRunnable voteRunnable = new BukkitRunnable() {
            int secondsPassed = 0;
            @Override
            public void run() {
                secondsPassed++;
                if(secondsPassed == duration){
                    stopVote();
                    this.cancel();
                }
            }
        };
        voteRunnable.runTaskTimer(plugin, 0, 20L);
     }

    public void stopVote(){
        Chat.broadcast(plugin.storageManager.getMessage(Lang.VOTE_ENDED).replace("{sender}", voteSender.getName()));
        inProgress = false;
        int[] percentages = calculatePercentage(forYes.size(), forNo.size());
        if(forNo.size() > forYes.size()){
            Chat.broadcast(plugin.storageManager.getMessage(Lang.VOTE_NORESULT)
                    .replace("{objective}", this.getVoteObjective())
                    .replace("{yes}", String.valueOf(percentages[0]))
                    .replace("{no}", String.valueOf(percentages[1])));
            result = false;
        }else if(forYes.size() > forNo.size()){
            Chat.broadcast(plugin.storageManager.getMessage(Lang.VOTE_YESRESULT)
                    .replace("{objective}", this.getVoteObjective())
                    .replace("{yes}", String.valueOf(percentages[0]))
                    .replace("{no}", String.valueOf(percentages[1])));
            result = true;
        }else{
            Chat.broadcast(plugin.storageManager.getMessage(Lang.VOTE_DRAWN)
                    .replace("{objective}", this.getVoteObjective())
                    .replace("{yes}", String.valueOf(percentages[0]))
                    .replace("{no}", String.valueOf(percentages[1])));
            result = false;
        }

        if(result){
            List<String> commands = plugin.storageManager.getVotesFile().getStringList("votes." + this.getVoteType().getLabel() + ".commands");
            for(String command : commands) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
            }
        }
        voteLogger.logVote(this);
        PlayerVotes.setVoteInProgress(null);
    }

    public void vote(Player p, boolean s){
        if(s) {
            forYes.add(p);
            plugin.chat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.VOTED_YES).replace("{objective}", getVoteObjective()), p);
        }else{
            forNo.add(p);
            plugin.chat.sendFormattedMessage(plugin.storageManager.getMessage(Lang.VOTED_NO).replace("{objective}", getVoteObjective()), p);
        }
    }





}
