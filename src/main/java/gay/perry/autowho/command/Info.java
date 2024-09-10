package gay.perry.autowho.command;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;
import org.jetbrains.annotations.NotNull;


public class Info extends Command {
    public Info() {
        super("autowho");
    }

    String message = "Help: Join a game and wait for it to start. The mod will automatically type /who for you.";

    public void handle(@NotNull String[] args) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[" + EnumChatFormatting.AQUA + "AutoWho" + EnumChatFormatting.GRAY + "] " + message));
    }
}
