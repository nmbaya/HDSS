
package org.aphrc.myapplication.tableview.handler;

import org.aphrc.myapplication.tableview.TableView;
import org.aphrc.myapplication.tableview.preference.Preferences;

/**
 * Created  on 3.03.2018.
 */

public class PreferencesHandler {

    private TableView tableView;
    private ScrollHandler scrollHandler;
    private SelectionHandler selectionHandler;

    public PreferencesHandler(TableView tableView) {
        this.tableView = tableView;
        this.scrollHandler = tableView.getScrollHandler();
        this.selectionHandler = tableView.getSelectionHandler();
    }


    public Preferences savePreferences(){
        Preferences preferences = new Preferences();
        preferences.columnPosition = scrollHandler.getColumnPosition();
        preferences.columnPositionOffset = scrollHandler.getColumnPositionOffset();
        preferences.rowPosition = scrollHandler.getRowPosition();
        preferences.rowPositionOffset = scrollHandler.getRowPositionOffset();
        preferences.selectedColumnPosition = selectionHandler.getSelectedColumnPosition();
        preferences.selectedRowPosition = selectionHandler.getSelectedRowPosition();
        return preferences;
    }


    public void loadPreferences(Preferences preferences){
        scrollHandler.scrollToColumnPosition(preferences.columnPosition, preferences.columnPositionOffset);
        scrollHandler.scrollToRowPosition(preferences.rowPosition, preferences.rowPositionOffset);
        selectionHandler.setSelectedColumnPosition(preferences.selectedColumnPosition);
        selectionHandler.setSelectedRowPosition(preferences.selectedRowPosition);
    }
}
