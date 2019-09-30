
package org.aphrc.myapplication.tableview.preference;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * Created  on 4.03.2018.
 */

public class SavedState extends View.BaseSavedState {

    public Preferences preferences;

    public SavedState(Parcelable superState) {
        super(superState);
    }

    private SavedState(Parcel in) {
        super(in);

    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeParcelable(preferences, flags);
    }

    public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
        public SavedState createFromParcel(Parcel in) { return new SavedState(in); }

        public SavedState[] newArray(int size) { return new SavedState[size]; }
    };
}

