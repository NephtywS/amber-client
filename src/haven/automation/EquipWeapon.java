/* Modified on 18/06/13, changed to drink water. */

package haven.automation;

import haven.*;

import java.util.List;

public class EquipWeapon implements Runnable {
    private GameUI gui;
    private static final int TIMEOUT = 2000;

    public EquipWeapon(GameUI gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        // WItem weapon = getWeapon(gui.maininv);
        WItem weapon = null;

        Inventory beltInv = null;
        InventoryBelt quickBeltInv = null;

        if (weapon == null) {
            if (Config.quickbelt) {
                Widget belt = null;
                for (Widget w = gui.lchild; w != null; w = w.prev) {
                    if (w instanceof BeltWnd) {
                        belt = w;
                        break;
                    }
                }

                if (belt == null) {
                    gui.error("Belt is null");
                    return;
                }

                for (Widget w = belt.lchild; w != null; w = w.prev) {
                    if (w instanceof InventoryBelt) {
                        // weapon = getWeaponQuickBelt((InventoryBelt) w);
                        quickBeltInv = (InventoryBelt) w;

                        IMeter.Meter stam = gui.getmeter("stam", 0);
                        if (stam == null || stam.a > 99) {
                            gui.error("Stamina is full!");
                            return;
                        }

                        List<WItem> containers = quickBeltInv.getItemsPartial("Waterskin");

                        for (WItem wi : containers) {
                            ItemInfo.Contents cont = wi.item.getcontents();
                            if (cont != null) {
                                FlowerMenu.setNextSelection("Drink");
                                gui.ui.lcc = wi.rootpos();
                                wi.item.wdgmsg("iact", wi.c, 0);
                            }
                        }

                        break;
                    }
                }
//            } else {
//                Window belt = gui.getwnd("Belt");
//
//                if (belt == null) {
//                    gui.error("Belt is null at else");
//                    return;
//                }
//
//                for (Widget w = belt.lchild; w != null; w = w.prev) {
//                    if (w instanceof Inventory) {
//                        beltInv = (Inventory) w;
//                        // weapon = getWeapon((Inventory) w);
//
//                        IMeter.Meter stam = gui.getmeter("stam", 0);
//                        if (stam == null || stam.a > 99) {
//                            gui.error("Stamina is full!");
//                            return;
//                        }
//
//                        List<WItem> containers = beltInv.getItemsPartial("Waterskin");
//                        gui.error(String.valueOf(containers.size()));
//
//                        for (WItem wi : containers) {
//                            ItemInfo.Contents cont = wi.item.getcontents();
//                            if (cont != null) {
//                                FlowerMenu.setNextSelection("Drink");
//                                gui.ui.lcc = wi.rootpos();
//                                wi.item.wdgmsg("iact", wi.c, 0);
//                            }
//                        }
//
//                        break;
//                    }
//                }
//            }
            }
        }

//        if (weapon == null)
//            return;

//        GItem weaponItem = weapon.item;
//
//        weaponItem.wdgmsg("take", new Coord(weaponItem.sz.x / 2, weaponItem.sz.y / 2));
//
//        try {
//            if (!Utils.waitForOccupiedHand(gui, TIMEOUT, "waitForOccupiedHand timed-out"))
//                return;
//        } catch (InterruptedException ie) {
//            return;
//        }
//
//        Equipory e = gui.getequipory();
//        if (e == null)
//            return;
//        e.wdgmsg("drop", 6);
//
//        try {
//            if (!Utils.waitForOccupiedHand(gui, TIMEOUT, "waitForOccupiedHand2 timed-out"))
//                return;
//        } catch (InterruptedException ie) {
//            return;
//        }
//
//        WItem hand = gui.vhand;
//        if (hand != null) {
//            if (beltInv != null)
//                beltInv.wdgmsg("drop", weaponItem.c.add(Inventory.sqsz.div(2)).div(Inventory.invsq.sz()));
//            else if (quickBeltInv != null)
//                quickBeltInv.wdgmsg("drop", weapon.c.add(Inventory.sqsz.div(2)).div(Inventory.invsq.sz()));
//            else
//                gui.maininv.wdgmsg("drop", weapon.c.add(Inventory.sqsz.div(2)).div(Inventory.invsq.sz()));
//        }
    }

    private WItem getWeapon(Inventory inv) {
        WItem weapon = inv.getItemPartial("Sword");
        if (weapon == null)
            weapon = inv.getItemPartial("Battleaxe");
        if (weapon == null)
            weapon = inv.getItemPartial("Axe");
        return weapon;
    }

    private WItem getWeaponQuickBelt(InventoryBelt inv) {
        WItem weapon = inv.getItemPartial("Sword");
        if (weapon == null)
            weapon = inv.getItemPartial("Battleaxe");
        if (weapon == null)
            weapon = inv.getItemPartial("Axe");
        return weapon;
    }
}
