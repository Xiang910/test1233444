package main.commands;

import NWTW.Engine.NWTWEngine;
import NWTW.Engine.NWTWEngineAPI;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.defaults.VanillaCommand;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.network.protocol.ToastRequestPacket;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.TextFormat;
import me.Grade;
import me.Money;

public class hub extends VanillaCommand {

    public hub(String name){
        super("hub");
        this.commandParameters.clear();
        this.setDescription("Back to Lobby");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args){

        if (!(sender instanceof Player)){
            sender.sendMessage("Bruh you using console or commandblock lah");
            return false;
        }

        Player p = (Player) sender;

        Grade grade = Grade.map.get(sender.getName());
        Money money = Money.map.get(sender.getName());

        ScoreBoard board = NWTWEngineAPI.getScoreboardManager().createScoreboard("§l§cMIDAGE", "Lobby", DisplaySlot.SIDEBAR);
        board.addLine(TextFormat.GRAY+"------------------");
        board.addLine(PlaceHolderAPI.translate((Player) sender, TextFormat.LIGHT_PURPLE+"%player_name%"));
        board.addLine("-----===++===-----");
        board.addLine("Coin : " + money.getMoney() + " Level : " + grade.getGrade());
        NWTWEngineAPI.getScoreboardManager().registerScoreboard(NWTWEngine.getPlugin(),board);
        NWTWEngineAPI.getScoreboardManager().setPlayerSelectScoreboard((Player) sender,board);

        ToastRequestPacket tpk = new ToastRequestPacket();
        tpk.title = "[" + TextFormat.MINECOIN_GOLD + "MIDAGE" +"]";
        tpk.content = TextFormat.GREEN+"WELCOME BACK TO"+TextFormat.DARK_GREEN+"LOBBY";

        p.getInventory().clearAll();
        p.getCursorInventory().clearAll();
        p.getOffhandInventory().clearAll();
        p.getEffects().clear();

        p.getFoodData().setLevel(20);
        p.getFoodData().setFoodSaturationLevel(5);

        p.setGamemode(Player.ADVENTURE);

        p.getInventory().setItem(0, Item.get(ItemID.COMPASS).setCustomName("§l"+TextFormat.WHITE+"[§a游戏选择器"+ TextFormat.WHITE+"]"));
        p.getInventory().setItem(8, Item.get(ItemID.BOOK).setCustomName("§l" +TextFormat.AQUA+ "[§e个人资料§b]"));

        return false;
    }
}
