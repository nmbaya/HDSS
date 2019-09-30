
package org.aphrc.myapplication.tableview.adapter.recyclerview.holder;

import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created on 23/10/2018.
 */

public abstract class AbstractViewHolder extends RecyclerView.ViewHolder {
    public enum SelectionState {SELECTED, UNSELECTED, SHADOWED}

    // Default value
    private SelectionState m_eState = SelectionState.UNSELECTED;

    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public void setSelected(SelectionState selectionState) {
        m_eState = selectionState;

        if (selectionState == SelectionState.SELECTED) {
            itemView.setSelected(true);
        } else if (selectionState == SelectionState.UNSELECTED) {
            itemView.setSelected(false);
        }
    }

    public boolean isSelected() {
        return m_eState == SelectionState.SELECTED;
    }

    public boolean isShadowed() {
        return m_eState == SelectionState.SHADOWED;
    }

    public void setBackgroundColor(@ColorInt int p_nColor) {
        itemView.setBackgroundColor(p_nColor);
    }

    public void onViewRecycled() {
    }

    public boolean onFailedToRecycleView() {
        return false;
    }

}
