package org.aphrc.myapplication.Utilities.SearchSpinner.interfaces;

import android.view.View;

/**
 * Created by  on 2/12/17.
 */

public interface ISpinnerSelectedView {
    View getNoSelectionView();
    View getSelectedView(int position);
}
