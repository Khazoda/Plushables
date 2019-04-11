package com.khazoda.plush.common.item;

import com.khazoda.plush.common.Plushables;
import net.minecraft.item.Item;

public class ParentItem extends Item {

    public static final String modID = Plushables.MODID;

    public ParentItem(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
    }
}
