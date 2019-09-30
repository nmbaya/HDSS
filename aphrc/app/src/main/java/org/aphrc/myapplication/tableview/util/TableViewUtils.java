
package org.aphrc.myapplication.tableview.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created  on 18/09/2018.
 */

public class TableViewUtils {


    /**
     * Helps to force width value before calling requestLayout by the system.
     */
    public static void setWidth(View view, int width) {
        // Change width value from params
        ((RecyclerView.LayoutParams) view.getLayoutParams()).width = width;

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), View
                .MeasureSpec.EXACTLY);
        view.measure(widthMeasureSpec, heightMeasureSpec);

        view.requestLayout();
    }

    /**
     * Gets the exact width value before the view drawing by main thread.
     */
    public static int getWidth(View view) {
        view.measure(LinearLayout.LayoutParams.WRAP_CONTENT, View.MeasureSpec.makeMeasureSpec
                (view.getMeasuredHeight(), View.MeasureSpec.EXACTLY));
        return view.getMeasuredWidth();
    }

}
