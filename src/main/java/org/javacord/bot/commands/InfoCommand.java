package org.javacord.bot.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import org.javacord.api.DiscordApi;
import org.javacord.api.Javacord;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.bot.Constants;
import org.javacord.bot.listeners.CommandCleanupListener;

public class InfoCommand implements CommandExecutor {

    /**
     * Executes the {@code !info} command.
     *
     * @param channel The channel where the command was issued.
     * @param message The message the command was issued in.
     */
    @Command(aliases = "!info", async = true)
    public void handleCommand(TextChannel channel, Message message) {
        final DiscordApi api = channel.getApi();
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(Constants.JAVACORD_ORANGE)
                .setTitle(api.getYourself().getName() + " - Official Javacord Bot")
                .setThumbnail(api.getYourself().getAvatar())
                .setDescription("The official bot for the Javacord Server\n\n"
                        + "Powered by Javacord and sdcf4j")
                .addInlineField("GitHub", "https://github.com/Javacord/Javacord-Bot")
                .addInlineField("Javacord Version", Javacord.VERSION)
                .addInlineField("sdcf4j Version", "v1.0.10");

        CommandCleanupListener.insertResponseTracker(embed, message.getId());
        channel.sendMessage(embed).join();
    }

}
