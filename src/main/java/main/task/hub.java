package main.task;

import NWTW.Engine.NWTWEngine;
import NWTW.Engine.NWTWEngineAPI;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.network.protocol.ToastRequestPacket;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scheduler.NukkitRunnable;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.TextFormat;
import me.Grade;
import me.Money;

public class hub extends NukkitRunnable {

    private final Player player;

    private hub (Player player){
        this.player = player;
    }

    @Override
    public void run() {

        if (this.player.getLevel().equals((Object) Server.getInstance().getDefaultLevel())) {
            Grade grade = Grade.map.get(player.getName());
            Money money = Money.map.get(player.getName());

            ScoreBoard board = NWTWEngineAPI.getScoreboardManager().createScoreboard("§l§cMIDAGE", "Lobby", DisplaySlot.SIDEBAR);
            board.addLine(TextFormat.GRAY + "------------------");
            board.addLine(PlaceHolderAPI.translate(Server.getInstance().getPlayer(player.getName()), TextFormat.LIGHT_PURPLE + "%player_name%"));
            board.addLine("-----===++===-----");
            board.addLine(TextFormat.MINECOIN_GOLD +"Coin : " + TextFormat.GRAY + money.getMoney() + TextFormat.GREEN +"  Level : " + TextFormat.GRAY +grade.getGrade());
            NWTWEngineAPI.getScoreboardManager().registerScoreboard(NWTWEngine.getPlugin(), board);
            NWTWEngineAPI.getScoreboardManager().setPlayerSelectScoreboard(player.getPlayer(), board);
        }
    }
}
