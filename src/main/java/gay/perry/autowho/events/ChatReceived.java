package gay.perry.autowho.events;

import net.minecraft.client.Minecraft;
import net.weavemc.loader.api.event.ChatReceivedEvent;
import net.weavemc.loader.api.event.SubscribeEvent;

public class ChatReceived {

    @SubscribeEvent
    public void onChatMessage(ChatReceivedEvent event) throws InterruptedException {
        if (!event.getMessage().getUnformattedText().contains(":")) {
            if (event.getMessage().getUnformattedText().contains("Protect your bed and destroy the enemy beds.")) {
                Thread.sleep(1000);
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/who");
            }
        }

        // AutoGL for Duels
        if (!event.getMessage().getUnformattedText().contains(":")) {
            if (event.getMessage().getUnformattedText().contains("Eliminate your opponents!")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac Good Luck, have fun.");
            }
        }

        // Lilith stats checking support for normal Duels
        if (event.getMessage().getUnformattedText().contains("Opponent: ")) {
            String message1 = event.getMessage().getUnformattedText();
            String username = extractUsername(message1);
            if (username != null) {
                String cleanedUsername = removeTitle(username);
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/sc duels " + cleanedUsername);
            }
        }

        // Lilith stats checking support for Duels with more than one opponent
        if (event.getMessage().getUnformattedText().contains("Opponents: ")) {
            String message2 = event.getMessage().getUnformattedText();
            String[] usernames = extractUsernames(message2);
            if (usernames != null) {
                for (int i = 0; i < usernames.length && i < 4; i++) {
                    String cleanedUsernames = removeTitle(usernames[i]);
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/sc duels " + cleanedUsernames);
                }
            }
        }
    }



    // Extract the username of ur opponent (Duels)
    private String extractUsername(String message1) {
        // Ensure the message is formatted correctly
        if (message1.contains("Opponent: ")) {
            // Split the string by ": " to extract the username
            String[] parts = message1.split(": ");
            if (parts.length > 1) {
                return parts[1];  // Return the username
            }
        }
        return null;
    }

    // Method to remove titles from username (normal Duels)
    private String removeTitle(String username) {
        // Remove anything within square brackets (e.g., [VIP], [MVP+], etc.)
        return username.replaceAll("\\[.*?] ", "").trim();
    }



    // Extract the usernames of Bridge Duels with more than one Opponent
    private String[] extractUsernames(String message2) {
        if (message2.contains("Opponents: ")) {
            String usernamesPart = message2.split(": ")[1];
            String[] usernames = usernamesPart.split(", ");

            return usernames.length <= 4 ? usernames : new String[]{usernames[0], usernames[1], usernames[2], usernames[3]};
        }
        return null;
    }

    // Method to remove titles from usernames
    private String[] removeTitle(String[] usernames) {
        String[] cleanedUsernames = new String[usernames.length];

        for (int i = 0; i < usernames.length; i++) {
            // Remove anything within square brackets (e.g., [VIP], [MVP+], etc.)
            cleanedUsernames[i] = usernames[i].replaceAll("\\[.*?] ", "").trim();
        }
        return cleanedUsernames;
    }
}
