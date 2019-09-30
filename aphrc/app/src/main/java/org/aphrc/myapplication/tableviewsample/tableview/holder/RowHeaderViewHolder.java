
package org.aphrc.myapplication.tableviewsample.tableview.holder;

import android.view.View;
import android.widget.TextView;

import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import org.aphrc.myapplication.R;

/**
 * Created on 23/10/2017.
 */


public class RowHeaderViewHolder extends AbstractViewHolder {
    public final TextView row_header_textview;

    public RowHeaderViewHolder(View itemView) {
        super(itemView);
        row_header_textview = (TextView) itemView.findViewById(R.id.row_header_textview);
    }
}