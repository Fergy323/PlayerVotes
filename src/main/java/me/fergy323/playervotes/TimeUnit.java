package me.fergy323.playervotes;

import java.util.HashMap;

public enum TimeUnit {

    SECOND("Second(s)", "sec", 1),
    MINUTE("Minute(s)", "min", 60),
    HOUR("Hour(s)", "hr", 60 * 60),
    DAY("Day(s)", "d", 60 * 60 * 24),
    MONTH("Months", "m", 60 * 60 * 24 * 30);

    private String name;
    private String shortcut;
    private long toSeconds;

    private static HashMap<String, TimeUnit> ID_SHORTCUT = new HashMap<>();

    TimeUnit(String name, String shortcut,  long toSecond){
        this.name = name;
        this.shortcut = shortcut;
        this.toSeconds = toSecond;
    }

    static{
        for(TimeUnit unit : values()){
            ID_SHORTCUT.put(unit.shortcut, unit);
        }
    }

    public static TimeUnit getFromShortcut(String shortcut){
        return ID_SHORTCUT.get(shortcut);
    }

    public String getName(){
        return name;
    }

    public String getShortcut(){
        return shortcut;
    }

    public long getToSecond(){
        return toSeconds;
    }

    public static boolean existFromShortcut(String shortcut){
        return ID_SHORTCUT.containsKey(shortcut);
    }

}
