
package org.aphrc.myapplication.tableview.filter;

public interface IFilterableModel {

    /**
     * Filterable query string for this object.
     *
     * @return query string for this object to be used in filtering.
     */
    String getFilterableKeyword();
}
