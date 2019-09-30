
package org.aphrc.myapplication.tableview.filter;

public class FilterItem {
    private FilterType filterType;
    private String filter;
    private int column;

    public FilterItem(FilterType type, int column, String filter) {
        this.filterType = type;
        this.column = column;
        this.filter = filter;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public String getFilter() {
        return filter;
    }

    public int getColumn() {
        return column;
    }
}