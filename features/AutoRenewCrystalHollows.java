package cheaters.get.banned.features;

import cheaters.get.banned.gui.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoRenewCrystalHollows {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if(Config.renewCrystalHollowsPass && event.type == 0) {
            if(event.message.getUnformattedText().equals("Your pass to the Crystal Hollows will expire in 1 minute")) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/purchasecrystallhollowspass");
            }
        }
    }

}
