/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Diorite (by Bartłomiej Mazur (aka GotoFinal))
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.diorite.impl.command.defaults;

import java.util.concurrent.ForkJoinPool;
import java.util.regex.Pattern;

import org.diorite.impl.command.SystemCommandImpl;
import org.diorite.cfg.messages.DioriteMessages;
import org.diorite.command.CommandPriority;

public class SaveCmd extends SystemCommandImpl
{
    public SaveCmd()
    {
        super("save", Pattern.compile("(save(-all|))", Pattern.CASE_INSENSITIVE), CommandPriority.LOW);
        this.setDescription("Saves the world");
        this.setCommandExecutor((sender, command, label, matchedPattern, args) -> {
            //sender.getCore().broadcastSimpleColoredMessage(Core.PREFIX_MSG + "&7Saving all worlds...");
            DioriteMessages.broadcastMessage(DioriteMessages.MSG_SAVE_START);
            ForkJoinPool.commonPool().submit(() -> {
                sender.getCore().getWorldsManager().getWorlds().parallelStream().forEach(w -> w.save(args.has(0) && args.asBoolean(0)));
                //sender.getCore().broadcastSimpleColoredMessage(Core.PREFIX_MSG + "&7All worlds saved!");
                DioriteMessages.broadcastMessage(DioriteMessages.MSG_SAVE_DONE);
            });
        });
    }
}
