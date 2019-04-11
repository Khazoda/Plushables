package com.khazoda.plush.common.block;

import javafx.scene.Parent;
import net.minecraft.util.math.AxisAlignedBB;

public interface IBlockPlushables {

    /**
     *Different Paramater versions incase bounds are uniform or you want to pass an AxisAlignedBB
     */
    ParentBlock setBoundingBox(double x1, double y1, double z1, double x2, double y2, double z2);
    ParentBlock setBoundingBox(double x, double y, double z);
    ParentBlock setBoundingBox(AxisAlignedBB a);













}
