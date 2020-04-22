package com.gamelovemiku.sheltermc.achievement;

public interface AchievementInterface {
    public String getName();
    public void setName(String name);
    public String getPermission();
    public void setPermission(String permission);
    public void doAction();
}
