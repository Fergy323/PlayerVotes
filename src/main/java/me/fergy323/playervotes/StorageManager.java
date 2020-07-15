package me.fergy323.playervotes;

import de.leonhard.storage.Config;
import de.leonhard.storage.Yaml;
import me.fergy323.playervotes.lang.Lang;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumSet;

public class StorageManager {

    private Yaml votesFile;
    private Yaml langFile;
    private Config config;

    public StorageManager(){
      votesFile = new Yaml("votes", "plugins/PlayerVotes");
      langFile = new Yaml("lang", "plugins/PlayerVotes");
      config = new Config("config", "plugins/PlayerVotes");

      setVotesFile();
      setConfig();
      setLangFile();
    }

    public Config getConfig() {
        return config;
    }

    public Yaml getLangFile(){return langFile;}

    public Yaml getVotesFile(){
        return votesFile;
    }

    public String getStringWithPlaceholders(String path, Yaml yaml, @Nullable Player player){
        String string = yaml.getString(path);
        String newString = "";
        if(string.contains("{player}")){
            newString = string.replace("{player}", player.getName());
            return newString;
        }
        return null;
    }

    public String getMessage(Lang l){
        return ChatColor.translateAlternateColorCodes('&', getLangFile().getString(l.getPath()));
    }

    private void setConfig(){
        config.setDefault("votes.period", 30);
        config.setDefault("votes.log", true);
    }

    private void setLangFile(){
        EnumSet<Lang> langs = EnumSet.allOf(Lang.class);

        for(Lang l : langs){
            getLangFile().set(l.getPath(), l.getMessage());
        }
    }

    private void setVotesFile(){
        votesFile.setDefault("votes.ban.name", "&cVote Ban");
        ArrayList<String> commands = new ArrayList<>();
        commands.add("ban {player} You were vote banned!");
        votesFile.setDefault("votes.ban.commands", commands);
    }
}
