package me.fergy323.playervotes.lang;

public enum Lang {

    PREFIX("prefix", "&1[&fPlayerVotes&1]"),
    NO_PERMISSION("noPermission", "&cYou do not have permission to execute this command."),
    ONLY_PLAYERS("onlyPlayers", "&cOnly players may execute this command."),
    INVALID_PLAYER("invalidPlayer", "&cThe specified player does not exist."),
    USAGE("usage", "&cUsage: &f"),
    INVALID_VOTE("invalidVote", "&cThat type of vote does not exist."),
    VOTE_IN_PROGRESS("voteInProgress", "&cThere is already a vote in progress."),
    NO_VOTE_IN_PROGRESS("noVoteInProgress", "&cThere are no votes in progress."),
    ALREADY_VOTED("alreadyVoted", "&cYou have already voted on this."),
    VOTE_STARTED("voteStarted", "&a{sender} has started a vote to {objective}!"),
    VOTED_YES("votedYes", "&aYou voted Yes for {objective}."),
    VOTED_NO("votedNo", "&aYou voted No for {objective}."),
    VOTE_ENDED("voteEnded", "&aThe vote by {sender} has ended."),
    VOTE_YESRESULT("resultYes", "&aThe server has decided to {objective}. (Yes {yes}% : No {no}%)"),
    VOTE_NORESULT("resultNo", "&aThe server has decided NOT to {objective}. (Yes {yes}% : No {no}%)"),
    VOTE_DRAWN("resultDrawn", "&aThe server has drawn on the vote to {objective}. (Yes {yes}% : No {no}%)");


    private final String path;
    private final String message;

    Lang(String path, String message){
        this.path = path;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
