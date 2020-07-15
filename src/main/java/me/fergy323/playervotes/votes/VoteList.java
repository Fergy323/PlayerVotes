package me.fergy323.playervotes.votes;

import me.fergy323.playervotes.PlayerVotes;

import java.util.ArrayList;

public class VoteList {

    private ArrayList<VoteType> voteTypes = new ArrayList<>();

    public ArrayList<VoteType> getVoteTypes() {
        return voteTypes;
    }

    public void setVoteType(VoteType voteType){
        getVoteTypes().add(voteType);
    }

    public boolean isVoteType(VoteType voteType){
        if(voteTypes.contains(voteType)){
            return true;
        }
        return false;
    }

    public boolean isVoteType(String label){
        for(VoteType voteType : getVoteTypes()){
            if(voteType.getLabel().equals(label)){
                return true;
            }
        }
        return false;
    }

    public void reload(){
        voteTypes.clear();
        PlayerVotes.getInstance().getVoteType().registerVoteTypes();
    }
}
