package org.diorite.impl.connection.listeners;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.impl.Main;
import org.diorite.impl.ServerImpl;
import org.diorite.impl.connection.NetworkManager;
import org.diorite.impl.connection.packets.play.PacketPlayInListener;
import org.diorite.impl.connection.packets.play.in.*;
import org.diorite.impl.connection.packets.play.out.PacketPlayOutDisconnect;
import org.diorite.impl.entity.PlayerImpl;
import org.diorite.impl.multithreading.BlockBreakAction;
import org.diorite.impl.multithreading.ChatAction;
import org.diorite.impl.multithreading.input.ChatThread;
import org.diorite.impl.multithreading.input.CommandsThread;
import org.diorite.impl.multithreading.input.TabCompleteThread;
import org.diorite.impl.multithreading.map.ChunkMultithreadedHandler;
import org.diorite.chat.ChatPosition;
import org.diorite.chat.component.BaseComponent;

public class PlayListener implements PacketPlayInListener
{
    private final ServerImpl     server;
    private final NetworkManager networkManager;
    private final PlayerImpl     player;

    public PlayListener(final ServerImpl server, final NetworkManager networkManager, final PlayerImpl player)
    {
        this.server = server;
        this.networkManager = networkManager;
        this.player = player;
    }


    @Override
    public void handle(final PacketPlayInKeepAlive packet)
    {
        this.networkManager.updateKeepAlive();
    }

    @Override
    public void handle(final PacketPlayInSettings packet)
    {
//        final byte oldViewDistance = this.player.getViewDistance();
        this.player.setViewDistance(packet.getViewDistance());
//        if (oldViewDistance != this.player.getViewDistance())
//        {
//            this.player.getPlayerChunks().wantUpdate();
//        }
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInCustomPayload packet)
    {
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInHeldItemSlot packet)
    {
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInPositionLook packet)
    {
        this.player.setPositionAndRotation(packet.getX(), packet.getY(), packet.getZ(), packet.getYaw(), packet.getPitch());
    }

    @Override
    public void handle(final PacketPlayInFlying packet)
    {
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInPosition packet)
    {
        this.player.setPosition(packet.getX(), packet.getY(), packet.getZ());
    }

    @Override
    public void handle(final PacketPlayInLook packet)
    {
        this.player.setRotation(packet.getYaw(), packet.getPitch());
    }

    @Override
    public void handle(final PacketPlayInChat packet)
    {
        final String str = packet.getContent();
        //noinspection HardcodedFileSeparator
        if (str.startsWith("/"))
        {
            CommandsThread.add(new ChatAction(str.substring(1), this.player));
        }
        else
        {
            ChatThread.add(new ChatAction(str, this.player));
        }
    }

    @Override
    public void handle(final PacketPlayInTabComplete packet)
    {
        TabCompleteThread.add(new ChatAction(packet.getContent(), this.player));
    }

    @Override
    public void handle(final PacketPlayInAbilities packet)
    {
        this.player.setAbilities(packet);
    }

    @Override
    public void handle(final PacketPlayInResourcePackStatus packet)
    {
        // TODO This is not needed? Maybe create event or something other...
    }

    @Override
    public void handle(final PacketPlayInSetCreativeSlot packet)
    {
        Main.debug("creative slot: " + packet.getSlot() + ", item: " + packet.getItem());
        // TODO: meh.
    }

    @Override
    public void handle(final PacketPlayInEntityAction packet)
    {
        packet.getEntityAction().doAction(this.player, packet.getJumpBoost());
    }

    @Override
    public void handle(final PacketPlayInArmAnimation packet)
    {
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInBlockDig packet)
    {
        if (packet.getAction() == PacketPlayInBlockDig.BlockDigAction.FINISH_DIG)
        {
            ChunkMultithreadedHandler.add(new BlockBreakAction(packet.getBlockLocation().setWorld(this.player.getWorld()), this.player));
        }
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInBlockPlace packet)
    {
        //   ChunkMultithreadedHandler.add(new BlockPlaceAction(packet.getLocation(), , this.player));
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInClientCommand packet)
    {
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInCloseWindow packet)
    {
        Main.debug("Close windows: " + packet.getId());
        // TODO: implement
    }

    @Override
    public void handle(final PacketPlayInWindowClick packet)
    {
        Main.debug("Click (" + packet.getId() + ") slot: " + packet.getClickedSlot() + ", type: " + packet.getClickType() + ", action: " + packet.getActionNumber() + ", item: " + packet.getClicked());
        // TODO: implement
    }

    public NetworkManager getNetworkManager()
    {
        return this.networkManager;
    }

    public ServerImpl getServer()
    {
        return this.server;
    }

    @Override
    public void disconnect(final BaseComponent message)
    {
        this.server.getPlayersManager().playerQuit(this.player);

        this.networkManager.sendPacket(new PacketPlayOutDisconnect(message));
        this.networkManager.close(message, true);
        this.server.getServerConnection().remove(this.networkManager);

        this.server.broadcastSimpleColoredMessage(ChatPosition.ACTION, "&3&l" + this.player.getName() + "&7&l left from the server!");
        this.server.broadcastSimpleColoredMessage(ChatPosition.SYSTEM, "&3" + this.player.getName() + "&7 left from the server!");
        this.server.sendConsoleSimpleColoredMessage("&3" + this.player.getName() + " &7left the server. (" + message.toLegacyText() + "&7)");
        // TODO: implement
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("server", this.server).append("networkManager", this.networkManager).toString();
    }

    public PlayerImpl getPlayer()
    {
        return this.player;
    }
}
