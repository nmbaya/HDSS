
package org.aphrc.myapplication.tableviewsample.tableview.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableviewsample.tableview.model.Cell;

/**
 * Created on 23/10/2018.
 */

public class CellViewHolder extends AbstractViewHolder {

    public final TextView cell_textview;
    public final LinearLayout cell_container;
    public Cell cell;

    public CellViewHolder(View itemView) {
        super(itemView);
        cell_textview = (TextView) itemView.findViewById(R.id.cell_data);
        cell_container = (LinearLayout) itemView.findViewById(R.id.cell_container);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
        cell_textview.setText(String.valueOf(cell.getData()));

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        cell_textview.requestLayout();
    }
}