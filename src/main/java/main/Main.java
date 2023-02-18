package main;

import NWTW.Engine.NWTWEngine;
import NWTW.Engine.NWTWEngineAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.TextFormat;
import main.commands.hub;
import me.Money;
import okhttp3.internal.connection.RealCall;
import org.jetbrains.annotations.NotNull;

public class Main extends PluginBase {

    @Override
    public void onEnable() {
        this.getLogger().info(TextFormat.GREEN + "======MIDAGEcore LOADED======");

        this.getServer().getNetwork().setName(TextFormat.GREEN + "制作中§r");

        getServer().getPluginManager().registerEvents(new EventListener(), this);

        getServer().getCommandMap().register("MIDAGE", new hub("hub"));
    }

    @Override
    public void onDisable(){
        this.getLogger().info("MIDAGECcore disabled");
    }

}





