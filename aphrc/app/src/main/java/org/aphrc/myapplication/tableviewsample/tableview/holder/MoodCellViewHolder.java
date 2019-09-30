
package org.aphrc.myapplication.tableviewsample.tableview.holder;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableviewsample.tableview.TableViewModel;

/**
 * Created on 4.02.2018.
 */

public class MoodCellViewHolder extends AbstractViewHolder {

    public final LinearLayout cell_container;
    public final ImageView cell_image;

    public MoodCellViewHolder(View itemView) {
        super(itemView);
        cell_container = (LinearLayout) itemView.findViewById(R.id.cell_container);
        cell_image = (ImageView) itemView.findViewById(R.id.cell_image);
    }


    public void setData(Object data) {
        int mood = (int) data;
    }
}