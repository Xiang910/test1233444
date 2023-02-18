package main;

import NWTW.Engine.FormAPI.*;
import NWTW.Engine.FormAPI.Response.FormResponseSimple;
import NWTW.Engine.FormAPI.ModalForm;
import NWTW.Engine.FormAPI.SimpleForm;
import NWTW.Engine.FormAPI.CustomForm;
import NWTW.Engine.NWTWEngine;
import NWTW.Engine.NWTWEngineAPI;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.*;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.inventory.transaction.InventoryTransaction;
import cn.nukkit.inventory.transaction.action.InventoryAction;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.Sound;
import cn.nukkit.network.protocol.PlaySoundPacket;
import cn.nukkit.network.protocol.ToastRequestPacket;
import cn.nukkit.permission.Permission;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.BossBarColor;
import cn.nukkit.utils.TextFormat;
import me.Grade;
import me.Money;
import org.checkerframework.checker.guieffect.qual.UI;
import org.jetbrains.annotations.NotNull;


public class EventListener implements Listener {
    @EventHandler
    public void join(PlayerLocallyInitializedEvent event) {

        Grade grade = Grade.map.get(event.getPlayer().getName());
        Money money = Money.map.get(event.getPlayer().getName());

        /*ScoreBoard board = NWTWEngineAPI.getScoreboardManager().createScoreboard("§l§cMIDAGE", "Lobby", DisplaySlot.SIDEBAR);
        board.addLine(TextFormat.GRAY+"------------------");
        board.addLine(PlaceHolderAPI.translate(event.getPlayer(), TextFormat.LIGHT_PURPLE+"%player_name%"));
        board.addLine("-----===++===-----");
        board.addLine("Coin : " + money.getMoney() + " Level : " + grade.getGrade());
        NWTWEngineAPI.getScoreboardManager().registerScoreboard(NWTWEngine.getPlugin(),board);
        NWTWEngineAPI.getScoreboardManager().setPlayerSelectScoreboard(event.getPlayer(),board);*/

        ToastRequestPacket tpk = new ToastRequestPacket();
        tpk.title = "[" + TextFormat.MINECOIN_GOLD + "MIDAGE" +"]";
        tpk.content = TextFormat.GREEN+"BETA "+TextFormat.DARK_GREEN+"TESTING";

        Player p = event.getPlayer();

        if (p.getLevel() != Server.getInstance().getDefaultLevel()){
            p.teleport(Server.getInstance().getDefaultLevel().getSafeSpawn());
        }


       /* PlaySoundPacket psp = new PlaySoundPacket();

        psp.name = Sound.RANDOM_LEVELUP.getSound();
        psp.pitch = 1;
        psp.volume = 1;
        psp.x = (int) p.getLocation().getX()* 8;
        psp.y = (int) p.getLocation().getY()* 8;
        psp.z = (int) p.getLocation().getZ()* 8;
        p.getNetworkSession().sendPacket(psp);*/

        p.level.addSound(p, Sound.RANDOM_LEVELUP, 1, 1, p);


    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent e){
        Player p = e.getPlayer();

        Item item = e.getItem();

        if (e.getAction().equals(PlayerInteractEvent.Action.RIGHT_CLICK_AIR)){
            if (p.getLevel() == Server.getInstance().getDefaultLevel()){
                if (item.getId() == ItemID.COMPASS && item.hasCustomName()) {
                    SimpleForm GForm = new SimpleForm("游戏选择器", TextFormat.AQUA + "你想选择哪种游戏？" + TextFormat.GRAY + "全伺服器的游戏都在这里等你选择")
                            .addButton("TextFormat.GRAY+\"炸弹小分队 \" + TextFormat.WHITE + \"BombSquad\"")
                            .addButton(TextFormat.DARK_RED+"关闭", ImageType.PATH, "texture/other/barrier");
                    GForm.send(p, (((player, formWindowSimple, i) -> {
                        if (i == -1) return;

                        switch (i) {
                            case 0:
                                p.sendMessage("游戏未完成 soon...");//player.getServer().dispatchCommand(player, "sus");
                                break;

                            case 1:
                                p.getLevel().addSound(p, Sound.NOTE_PLING, 1, 1, p);
                                break;
                        }
                    })));

                    /*SimpleForm GForm = new SimpleForm("游戏选择器")
                            .setContent(TextFormat.AQUA + "你想选择哪种游戏？" + TextFormat.GRAY + "全伺服器的游戏都在这里等你选择")
                            .addButton("炸弹小分队 BombSquad");
                    GForm.send(p, ((player, formWindowSimple, i1) -> {
                        if (i1 == -1) return;

                        switch (i1){
                            case 0 :
                                player.sendMessage("游戏未完成 soon...");//player.getServer().dispatchCommand(player, "sus");
                                break;
                                p.getLevel().addSound(p, Sound.NOTE_PLING, 1, 1, p);
                        }
                    }));

                } else if (item.getId() == ItemID.BOOK && item.hasCustomName()) {
                    Grade grade = new Grade();
                    CustomForm form = new CustomForm("个人资料")
                            .addLabel(TextFormat.AQUA +"这里查看你的资料")
                            .addLabel( TextFormat.GREEN +"所需经验升级 : " +TextFormat.WHITE+grade.getMaxExp());
                }*/
                } else if (item.getId() == ItemID.BOOK && item.hasCustomName()) {
                    Grade grade = Grade.map.get(p.getName());

                    CustomForm form = new CustomForm("个人资料")
                            .addLabel(TextFormat.AQUA + "这里查看你的资料")
                            .addLabel(TextFormat.GREEN + "所需的经验升级 : " + TextFormat.WHITE+ grade.getMaxExp());
                }

            }
        }
    }

    @EventHandler
    public void onFirstJoin(PlayerPreLoginEvent e) {
        Player p = e.getPlayer();
        ModalForm joinForm = new ModalForm("§b欢迎加入§eMIDAGE!", "以下是在此服务器需要遵守的规则\n1. 请勿在服务器内使用任何外挂\n2. 请不要歧视任何玩家§r" + TextFormat.GRAY + "(种族歧视&性别歧视等)§r\n3. 禁止私信骚扰玩家", "§a我接受",
                TextFormat.RED + "不接受");
        joinForm.send(p, (((player, formWindowModal, i) -> {
            if (i == -1) joinForm.send(p);

            switch (i){
                case 0: {
                    p.sendMessage(TextFormat.GREEN+"你接受了我们的规则/服务条款");
                    break;
                }
                case 1: {
                    player.kick("§c你需要接受服务器的规则/条款才能进行游玩!");
                    break;
                }
            }
        })));
        /*ModalForm joinForm = new ModalForm("§b欢迎加入§eMIDAGE!", "以下是在此服务器需要遵守的规则\n1. 请勿在服务器内使用任何外挂\n2. 请不要歧视任何玩家§r" + TextFormat.GRAY + "(种族歧视&性别歧视等)§r\n3. 禁止私信骚扰玩家", "§a我接受",
                TextFormat.RED + "不接受");
        joinForm.send(p, ((player, formWindowModal, i) -> {
            if (i == -1) joinForm.send(p);

            switch (i){
                case 0 : {
                    p.sendMessage(TextFormat.GREEN+"你接受了我们的规则/服务条款");
                    break;
                }

                case 1 : {
                    player.kick("§c你需要接受服务器的规则才能进行游玩!");
                    break;
                }
            }
        }));*/
    }

    public Server server;

    @EventHandler
    public void onBlockOpen(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (player.getLevel().equals((Object)this.server.getDefaultLevel()) && event.getAction().equals((Object)PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && (block instanceof BlockChest || block instanceof BlockEnchantingTable || block instanceof BlockCraftingTable || block instanceof BlockAnvil || block instanceof BlockBeacon || block instanceof BlockDoor || block instanceof BlockTrapdoor || block instanceof BlockEnderChest || block instanceof BlockFurnace)) {
            event.setCancelled();
        }
    }

    @EventHandler
    public void onPickup(InventoryPickupItemEvent event) {
        EntityItem entity = event.getItem();
        if (entity.getLevel().equals((Object)this.server.getDefaultLevel())) {
            event.setCancelled();
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(Permission.DEFAULT_OP)) {
            event.setCancelled();
        }
    }

    @EventHandler
    public void onInventoryChange(InventoryTransactionEvent event) {
        InventoryTransaction transaction = event.getTransaction();
        Player source = transaction.getSource();
        for (InventoryAction action : transaction.getActions()) {
            if (!(action instanceof SlotChangeAction) || !source.getLevel().equals((Object)this.server.getDefaultLevel()) || source.getInventory() == null) continue;
            event.setCancelled();
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(Permission.DEFAULT_OP)) {
            event.setCancelled();
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(Permission.DEFAULT_OP)) {
            event.setCancelled();
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setFormat(player.getDisplayName() +  /*⨞*/ "> §r" + event.getMessage());
    }
}
