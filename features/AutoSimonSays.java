package cheaters.get.banned.features;

import cheaters.get.banned.Shady;
import cheaters.get.banned.gui.config.Config;
import cheaters.get.banned.utils.KeybindUtils;
import cheaters.get.banned.utils.Utils;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoSimonSays {
    
    private boolean clicking = false;

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if(Config.autoSimonSays && !clicking && Utils.inDungeon && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            if(Shady.mc.theWorld.getBlockState(event.pos).getBlock() == Blocks.stone_button) {
                int x = event.pos.getX();
                int y = event.pos.getY();
                int z = event.pos.getZ();

                if(x == 309-200 && y >= 120 && y <= 123 && z >= 291-200 && z <= 294-200) {
                    clicking = true;
                    for(int i = 0; i < 4; i++) {
                        KeybindUtils.rightClick();
                    }
                    clicking = false;
                }
            }
        }
    }

}
