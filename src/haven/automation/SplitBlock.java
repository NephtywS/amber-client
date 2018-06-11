// TODO: How to add button on macro menu?
// TODO: Pagina - find the resources file

//package haven.automation;
//
//import haven.*;
//
//public class SplitBlock implements Runnable, WItemDestroyCallback {
//    private GameUI gameUI;
//    private boolean splitDone;
//    private static final int TIMEOUT = 2000;
//    private static final int DELAY = 8;
//
//    public SplitBlock(GameUI gameUI) { this.gameUI = gameUI; }
//
//    @Override
//    public void run() {
//        WItem block;
//
//        block = Utils.findItemByPrefixInInv(gameUI.maininv, "gfx/invobjs/small/wblock");
//
//        while (true) {
//            if (block == null) {
//                block = Utils.findItemByPrefixInInv(gameUI.maininv, "gfx/invobjs/wblock");
//
//                if (block == null)
//                    break;
//            }
//
//            splitDone = false;
//            block.registerDestroyCallback(this);
//
//            FlowerMenu.setNextSelection("Split");
//            // TODO: Don't know well
//            gameUI.ui.lcc = block.rootpos();
//            block.item.wdgmsg("iact", block.c, 0);
//
//            int timeout = 0;
//            while (!splitDone) {
//                timeout += DELAY;
//
//                if (timeout >= TIMEOUT)
//                    return;
//
//                try {
//                    Thread.sleep(DELAY);
//                } catch (InterruptedException e) {
//                    return;
//                }
//            }
//        }
//    }
//
//    @Override
//    public void notifyDestroy() { splitDone = true; }
//}
