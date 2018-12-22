package com.spikeyhunter.royallist;
import org.bukkit.entity.Player;

public class UpdateList
{
  public UpdateList() {}
  
  public void checkList() {
    for (Player p : org.bukkit.Bukkit.getServer().getOnlinePlayers()) {
      if ((!Main.famous.contains(p.getName())) && (hasPermission(p, "royallist.famous"))) {
        Main.famous.add(p.getName());
        if ((Main.hiding.contains(p.getName())) && (Main.famous.contains(p.getName()))) {
          Main.famous.remove(p.getName());
        }
      }
      if ((Main.famous.contains(p.getName())) && (!hasPermission(p, "royallist.famous"))) {
        Main.famous.remove(p.getName());
      }
      if ((!Main.twitch.contains(p.getName())) && (hasPermission(p, "royallist.twitch"))) {
        Main.twitch.add(p.getName());
        if ((Main.hiding.contains(p.getName())) && (Main.twitch.contains(p.getName()))) {
          Main.twitch.remove(p.getName());
        }
      }
      if ((Main.twitch.contains(p.getName())) && (!hasPermission(p, "royallist.twitch"))) {
        Main.twitch.remove(p.getName());
      }
      if ((!Main.youtuber.contains(p.getName())) && (hasPermission(p, "royallist.youtube"))) {
        Main.youtuber.add(p.getName());
        if ((Main.hiding.contains(p.getName())) && (Main.youtuber.contains(p.getName()))) {
          Main.youtuber.remove(p.getName());
        }
      }
      if ((Main.youtuber.contains(p.getName())) && (!hasPermission(p, "royallist.youtube"))) {
        Main.youtuber.remove(p.getName());
      }
      if ((!Main.donorslist.contains(p.getName())) && (hasPermission(p, "royallist.donor"))) {
        Main.donorslist.add(p.getName());
        if ((Main.hiding.contains(p.getName())) && (Main.donorslist.contains(p.getName()))) {
          Main.donorslist.remove(p.getName());
        }
      }
      if ((Main.donorslist.contains(p.getName())) && (!hasPermission(p, "royallist.donor"))) {
        Main.donorslist.remove(p.getName());
      }
      if ((!Main.staff.contains(p.getName())) && (hasPermission(p, "royallist.staff"))) {
        Main.staff.add(p.getName());
        if ((Main.hiding.contains(p.getName())) && (Main.staff.contains(p.getName()))) {
          Main.staff.remove(p.getName());
        }
      }
      if ((Main.staff.contains(p.getName())) && (!hasPermission(p, "royallist.staff")))
        Main.staff.remove(p.getName());
    }
  }
  
  public boolean hasPermission(Player player, String permission) {
    for (org.bukkit.permissions.PermissionAttachmentInfo perm : player.getEffectivePermissions()) {
      if (perm.getPermission().equalsIgnoreCase(permission)) {
        return true;
      }
    }
    return false;
  }
}