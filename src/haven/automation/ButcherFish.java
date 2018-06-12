/* Modified on 18/06/11, merged with block split for now */

package haven.automation;

import haven.*;

public class ButcherFish implements Runnable, WItemDestroyCallback {
    private GameUI gameUI;
    private boolean fishdone;
    private boolean splitDone;
    private static final int TIMEOUT = 2000;
    private static final int DELAY = 8;

    public ButcherFish(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    @Override
    public void run() {
        WItem fish;

        fish = Utils.findItemByPrefixInInv(gameUI.maininv, "gfx/invobjs/fish-");

        if (fish == null) {
            WItem block;

            while (true) {
                block = Utils.findItemByPrefixInInv(gameUI.maininv, "gfx/invobjs/small/wblock");

                if (block == null) {
                    block = Utils.findItemByPrefixInInv(gameUI.maininv, "gfx/invobjs/wblock");

                    if (block == null)
                        break;
                }

                splitDone = false;
                block.registerDestroyCallback(this);

                FlowerMenu.setNextSelection("Split");
                // TODO: Don't know well
                gameUI.ui.lcc = block.rootpos();
                block.item.wdgmsg("iact", block.c, 0);

                int timeout = 0;

                while (!splitDone) {
                    timeout += DELAY;

                    if (timeout >= TIMEOUT)
                        return;

                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }

        else {
            while ((fish = Utils.findItemByPrefixInInv(gameUI.maininv, "gfx/invobjs/fish-")) != null) {
                fishdone = false;
                fish.registerDestroyCallback(this);

                FlowerMenu.setNextSelection("Butcher");
                gameUI.ui.lcc = fish.rootpos();
                fish.item.wdgmsg("iact", fish.c, 0);

                int timeout = 0;

                while (!fishdone) {
                    timeout += DELAY;
                    if (timeout >= TIMEOUT)
                        return;
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void notifyDestroy() {
        fishdone = true;
        splitDone = true;
    }
}
