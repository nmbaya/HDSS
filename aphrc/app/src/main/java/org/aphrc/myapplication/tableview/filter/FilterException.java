
package org.aphrc.myapplication.tableview.filter;

public class FilterException extends Exception {

    public FilterException() {
        super("Error in searching from table.");
    }

    public FilterException(String column, String query) {
        super("Error searching " + query + " in column " + column + " of table. Column contents "
                + "are not of class " + String.class.getCanonicalName());
    }
}
