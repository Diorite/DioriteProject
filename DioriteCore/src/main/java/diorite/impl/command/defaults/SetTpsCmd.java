package diorite.impl.command.defaults;

import java.util.regex.Pattern;

import diorite.Server;
import diorite.command.CommandPriority;
import diorite.impl.command.SystemCommandImpl;

public class SetTpsCmd extends SystemCommandImpl
{
    public SetTpsCmd()
    {
        super("setTps", (Pattern) null, CommandPriority.LOW);
        this.setCommandExecutor((sender, command, label, matchedPattern, args) -> sender.sendMessage("§4Invalid usage, please type /setTps <number from 1 to 99>"));
        this.registredSubCommand("core", Pattern.compile("(?<tps>([0-9]{2})|([1-9]))((-multi=(?<multi>\\d+((\\.\\d+)|)))|)", Pattern.CASE_INSENSITIVE), (sender, command, label, matchedPattern, args) -> {
            final int tps = Integer.parseInt(matchedPattern.group("tps"));
            final String temp = matchedPattern.group("multi");
            final double multi = (temp == null) ? (((double) Server.DEFAULT_TPS) / tps) : Double.parseDouble(temp);
            sender.sendMessage("§7TPS set to: §8" + tps + "§7, and server speed to: §8" + multi);
            sender.getServer().setTps(tps);
            sender.getServer().setMutli(multi);
        });
    }
}
