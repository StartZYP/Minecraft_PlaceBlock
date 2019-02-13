package com.qq44920040.Minecraft.PlaceBlock;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class PluginMain extends JavaPlugin implements Listener {
    private List SetWorldNmae;
    private List<String> ItemKeyValue;
    private String Msg;
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!(file.exists())){
            saveDefaultConfig();
        }
        SetWorldNmae = getConfig().getStringList("World");
        ItemKeyValue = getConfig().getStringList("ItemKeyValue");
        Msg = getConfig().getString("Msg");
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("pd")&&args.length==1&&sender.isOp()){
            if (args[0].equalsIgnoreCase("reload")){
                sender.sendMessage("重载成功");
                reloadConfig();
                SetWorldNmae = getConfig().getStringList("World");
                ItemKeyValue = getConfig().getStringList("ItemKeyValue");
                Msg = getConfig().getString("Msg");
            }
        }
        return super.onCommand(sender, command, label, args);
    }
    


    @EventHandler
    public void PlayerPlaceBlock(BlockPlaceEvent event){
        Player p = event.getPlayer();
        if (SetWorldNmae.toString().contains(p.getWorld().getName())){
            int x = event.getBlockReplacedState().getX();
            int y = event.getBlockReplacedState().getY();
            int z = event.getBlockReplacedState().getZ();
            ItemKeyValue.forEach((String KeyVaule)->{
                String[] ArrayKeyVaule = KeyVaule.split("-");
                String BlockA = ArrayKeyVaule[0];
                String BlockB = ArrayKeyVaule[1];
                int BlockId = event.getBlockPlaced().getTypeId();
                if (BlockA.equalsIgnoreCase(BlockId+":"+event.getBlockPlaced().getData())){
                    if (BlockB.equalsIgnoreCase(p.getWorld().getBlockAt(x+1,y,z).getType().getId()+":"+p.getWorld().getBlockAt(x+1,y,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockB.equalsIgnoreCase(p.getWorld().getBlockAt(x-1,y,z).getType().getId()+":"+p.getWorld().getBlockAt(x-1,y,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockB.equalsIgnoreCase(p.getWorld().getBlockAt(x,y+1,z).getType().getId()+":"+p.getWorld().getBlockAt(x,y+1,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockB.equalsIgnoreCase(p.getWorld().getBlockAt(x,y-1,z).getType().getId()+":"+p.getWorld().getBlockAt(x,y-1,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockB.equalsIgnoreCase(p.getWorld().getBlockAt(x,y,z-1).getType().getId()+":"+p.getWorld().getBlockAt(x,y,z-1).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockB.equalsIgnoreCase(p.getWorld().getBlockAt(x,y,z+1).getType().getId()+":"+p.getWorld().getBlockAt(x,y,z+1).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                }
                if (BlockB.equalsIgnoreCase(BlockId+":"+event.getBlockPlaced().getData())){
                    if (BlockA.equalsIgnoreCase(p.getWorld().getBlockAt(x+1,y,z).getType().getId()+":"+p.getWorld().getBlockAt(x+1,y,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockA.equalsIgnoreCase(p.getWorld().getBlockAt(x-1,y,z).getType().getId()+":"+p.getWorld().getBlockAt(x-1,y,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockA.equalsIgnoreCase(p.getWorld().getBlockAt(x,y-1,z).getType().getId()+":"+p.getWorld().getBlockAt(x,y-1,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockA.equalsIgnoreCase(p.getWorld().getBlockAt(x,y+1,z).getType().getId()+":"+p.getWorld().getBlockAt(x,y+1,z).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockA.equalsIgnoreCase(p.getWorld().getBlockAt(x,y,z-1).getType().getId()+":"+p.getWorld().getBlockAt(x,y,z-1).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                        return;
                    }
                    if (BlockA.equalsIgnoreCase(p.getWorld().getBlockAt(x,y,z+1).getType().getId()+":"+p.getWorld().getBlockAt(x,y,z+1).getData())){
                        p.sendMessage(Msg);
                        event.setCancelled(true);
                    }
                }
            });
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
