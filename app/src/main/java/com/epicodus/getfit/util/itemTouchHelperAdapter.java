package com.epicodus.getfit.util;

public interface itemTouchHelperAdapter {
    boolean onItemMove (int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
