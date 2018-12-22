package com.spikeyhunter.royallist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Hider
  implements org.bukkit.command.CommandExecutor
{
  private Main core;
  
  public Hider(Main core) { this.core = core; }
  
  UpdateList ul = new UpdateList();
  
  public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) { if (cmd.getName().equalsIgnoreCase("listhide")) {
      if (((s instanceof Player)) && 
        (!hasPermission(s, "royallist.admin"))) {
    	 s.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("NoPermission")));
        return true;
      }
      
      if (args.length == 0) {
        ul.checkList();
        if ((s instanceof Player)) {
          if ((Main.donorslist.contains(s.getName())) || (Main.staff.contains(s.getName())) || (Main.twitch.contains(s.getName())) || (Main.youtuber.contains(s.getName())) || (Main.famous.contains(s.getName()))) {
            Main.donorslist.remove(s.getName());
            Main.staff.remove(s.getName());
            Main.famous.remove(s.getName());
            Main.twitch.remove(s.getName());
            Main.youtuber.remove(s.getName());
            Main.hiding.add(s.getName());
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("HiddenSelf")));
          } else {
            Main.hiding.remove(s.getName());
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("NotHiddenSelf")));
          }
          return true;
        }
        s.sendMessage(ChatColor.RED + "Use /listhide <player>");
        return true;
      }
      
      Player target = org.bukkit.Bukkit.getPlayer(args[0]);
      if (target == null) {
        s.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("Player Does Not Exist")));
        return true;
      }
      ul.checkList();
      if ((Main.donorslist.contains(target.getName())) || (Main.staff.contains(target.getName())) || (Main.twitch.contains(target.getName())) || (Main.youtuber.contains(target.getName())) || (Main.famous.contains(target.getName()))) {
        Main.donorslist.remove(target.getName());
        Main.staff.remove(target.getName());
        Main.famous.remove(target.getName());
        Main.twitch.remove(target.getName());
        Main.youtuber.remove(target.getName());
        Main.hiding.add(target.getName());
        s.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("HiddenOther").replace("%Target%", target.getName())));
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("HiddenSelf")));
      } else {
        Main.hiding.remove(target.getName());
        s.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("NotHiddenOther").replace("%Target%", target.getName())));
        target.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getString("NotHiddenSelf")));
      }
    }
    return true;
  }
  
  public boolean hasPermission(CommandSender commandSender, String permission) {
    for (org.bukkit.permissions.PermissionAttachmentInfo perm : commandSender.getEffectivePermissions()) {
      if (perm.getPermission().equalsIgnoreCase(permission)) {
        return true;
      }
    }
    return false;
  }
}