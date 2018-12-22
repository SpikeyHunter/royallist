package com.spikeyhunter.royallist;

import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;

import com.google.common.base.Joiner;

public class Main extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener
{
  public Main() {}
  
  public static ArrayList<String> staff = new ArrayList<String>();
  public static ArrayList<String> donorslist = new ArrayList<String>();
  public static ArrayList<String> famous = new ArrayList<String>();
  public static ArrayList<String> youtuber = new ArrayList<String>();
  public static ArrayList<String> hiding = new ArrayList<String>();
  public static ArrayList<String> twitch = new ArrayList<String>();
  
  UpdateList ul = new UpdateList();
  
  public void onEnable() {
    saveDefaultConfig();
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    getCommand("listhide").setExecutor(new Hider(this));
    try {
      Metrics metrics = new Metrics(this);
      metrics.start();
      System.out.println("Data sent to Metrics successfully.");
    } catch (IOException e) {
      System.out.println("Couldn't send data to Metrics :(");

      
    }
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player p = e.getPlayer();
    if ((p.hasPermission("royallist.staff")) && (!staff.contains(p.getName())))
    			{
      staff.add(p.getName());
    }
    if ((p.hasPermission("royallist.donor")) && (!donorslist.contains(p.getName())))
    {
      donorslist.add(p.getName());
    }
    if ((p.hasPermission("royallist.famous")) && (!famous.contains(p.getName())))
    {
      famous.add(p.getName());
    }
    if ((p.hasPermission("royallist.youtube")) && (!youtuber.contains(p.getName())))
    {
      youtuber.add(p.getName());
    }
    if ((p.hasPermission("royallist.twitch")) && (!twitch.contains(p.getName())))
    {
      twitch.add(p.getName());
    }
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    staff.remove(p.getName());
    donorslist.remove(p.getName());
    hiding.remove(p.getName());
    famous.remove(p.getName());
    youtuber.remove(p.getName());
    twitch.remove(p.getName());
  }
  
  public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("royallist")) {
      if (!s.hasPermission("royallist.admin")) {
        s.sendMessage(ChatColor.RED + "No permissions.");
        return true;
      }
      if (args.length == 0) {
        PluginDescriptionFile pdfFile = getDescription();
        s.sendMessage(color("&6&lRoyalList &8>>&8 developed by &7SpikeyHunter&8, &8Version: &7" + pdfFile.getVersion()));
        return true;
      }
      if (args[0].equalsIgnoreCase("reload")) {
        reloadConfig();
        s.sendMessage(color(getConfig().getString("Config reload message")));
        return true;
      }
      
      else {
    	  s.sendMessage(ChatColor.GOLD+ "Sender must be in game");
      }
    }
    ul.checkList();
    String ppl = Integer.toString(Bukkit.getOnlinePlayers().size() - hiding.size());
    String pplMax = Integer.toString(Bukkit.getMaxPlayers());
    if (cmd.getName().equalsIgnoreCase("list")) {
      for (String listMessage : getConfig().getStringList("ListMessage")) {
        s.sendMessage(color(listMessage).replace("%Players%", ppl).replace("%MaxPlayers%", pplMax).replace("%Staff%", color(getStaffMessage())).replace("%StaffAmount%", Integer.toString(staff.size())).replace("%Donors%", color(getDonorMessage())).replace("%DonorsAmount%", Integer.toString(donorslist.size())).replace("%Famous%", color(getFamousMessage())).replace("%FamousAmount%", Integer.toString(famous.size())).replace("%Youtube%", color(getYoutubeMessage())).replace("%YoutubeAmount%", Integer.toString(youtuber.size())).replace("%Twitch%", color(getTwitchMessage())).replace("%TwitchAmount%", Integer.toString(twitch.size())));
      }
    }
    




    return true;
  }
  
  public String color(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }
  
  public String getStaffMessage() {
    if (staff.size() >= 1) {
      return Joiner.on(", ").join(staff);
    }
    return getConfig().getString("None");
  }
  
  public String getDonorMessage() {
    if (donorslist.size() >= 1) {
      return Joiner.on(", ").join(donorslist);
    }
    return getConfig().getString("None");
  }
  
  public String getFamousMessage() {
    if (famous.size() >= 1) {
      return Joiner.on(", ").join(famous);
    }
    return getConfig().getString("None");
  }
  
  public String getYoutubeMessage() {
    if (youtuber.size() >= 1) {
      return Joiner.on(", ").join(youtuber);
    }
    return getConfig().getString("None");
  }
  
  public String getTwitchMessage() {
    if (twitch.size() >= 1) {
      return Joiner.on(", ").join(twitch);
    }
    return getConfig().getString("None");
  }
}