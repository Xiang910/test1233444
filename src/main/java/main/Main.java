package main;

import NWTW.Engine.NWTWEngine;
import NWTW.Engine.NWTWEngineAPI;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.TextFormat;
import org.jetbrains.annotations.NotNull;
import NWTW.Engine.Translate.TranslateManager;

import javax.xml.soap.Text;
import java.util.Locale;

public class Main extends PluginBase {

    @Override
    public void onEnable() {
        this.getLogger().info(TextFormat.GREEN+"======MIDAGEcore LOADED======");

        this.getServer().getNetwork().setName(TextFormat.GREEN + "制作中");
    }

    @EventHandler
    public void onJoinDone(PlayerLocallyInitializedEvent e){
        Player p = e.getPlayer();

        if (p.getLevel() != Server.getInstance().getDefaultLevel()){
            p.teleport(Server.getInstance().getDefaultLevel().getSafeSpawn());
        }

        NWTW.Engine.ScoreBoard.ScoreBoard scoreboard = NWTWEngine.getPlugin().getScoreboardManager().createScoreboard();
        scoreboard.setTitle("Lobby");
        scoreboard.setName("§l§cMIDAGE");
        scoreboard.addLine("----------");
        scoreboard.setDisplaySlot(DisplaySlot.SIDEBAR);
        NWTWEngine.getPlugin().getScoreboardManager().registerScoreboard(this, scoreboard);
        NWTWEngine.getPlugin().getScoreboardManager().setPlayerSelectScoreboard(p, scoreboard);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b = e.getBlock();
        if (p.getLevel() == getServer().getDefaultLevel()) {
            if (e.getAction().equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
                if (b != null) {
                    if (b.getId() == BlockID.WOODEN_PRESSURE_PLATE) {
                        e.setCancelled(true);
                    }
                }
                }
            }
        }

    @EventHandler
    public boolean onCommand(CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        switch (cmd.getName().toLowerCase()) {
            case "hub":
                if (sender instanceof Player) {
                    ((Player) sender).teleport(getServer().getDefaultLevel().getSafeSpawn().add(0.5, 1.5, 0.5));
                }
        }
        return true;
    }

    public void chat(PlayerChatEvent e){
        Server.getInstance().getOnlinePlayers().values().forEach(v ->{
            v.sendMessage(NWTWEngineAPI.getTranslateManager().translate("auto","zh_CN","idiot"));
        });
        e.setCancelled();
    }



    }

