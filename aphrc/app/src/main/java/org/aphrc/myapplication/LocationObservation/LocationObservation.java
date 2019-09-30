package org.aphrc.myapplication.LocationObservation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.TableView;
import org.aphrc.myapplication.tableview.adapter.AbstractTableAdapter;
import org.aphrc.myapplication.tableview.filter.Filter;
import org.aphrc.myapplication.tableview.pagination.Pagination;

import org.aphrc.myapplication.LocationObservation.tableview.TableViewAdapter;
import org.aphrc.myapplication.LocationObservation.tableview.TableViewListener;
import org.aphrc.myapplication.LocationObservation.tableview.TableViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationObservation extends Fragment {

    private AbstractTableAdapter mTableViewAdapter;
    private TableView mTableView;
    private Filter mTableFilter; // This is used for filtering the table.
    private Pagination mPagination; // This is used for paginating the table.

    // This is a sample class that provides the cell value objects and other configurations.
    private TableViewModel mTableViewModel;

    private boolean mPaginationEnabled = false;


    public LocationObservation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);


        //itemsPerPage = layout.findViewById(R.id.items_per_page_spinner);
        View tableTestContainer = layout.findViewById(R.id.table_test_container);


        EditText obs = (EditText) layout.findViewById(R.id.obsTitle1);
        obs.setText(GlobalClass.observationID);
        obs.setEnabled(false);
        EditText sgp = (EditText) layout.findViewById(R.id.sgpTitle1);
        sgp.setText(GlobalClass.socialgroupID);
        sgp.setEnabled(false);
        EditText room = (EditText) layout.findViewById(R.id.roomTitle1);
        room.setText(GlobalClass.roomID);
        room.setEnabled(false);


        if (mPaginationEnabled) {
            tableTestContainer.setVisibility(View.VISIBLE);
        //    itemsPerPage.setOnItemSelectedListener(onItemsPerPageSelectedListener);

      //      pageNumberField.addTextChangedListener(onPageTextChanged);
        } else {
           // tableTestContainer.setVisibility(View.GONE);
        }

        // Let's get TableView
        mTableView = layout.findViewById(R.id.tableview);

        initializeTableView();

        if (mPaginationEnabled) {
            mTableFilter = new Filter(mTableView); // Create an instance of a Filter and pass the
            // created TableView.

            // Create an instance for the TableView pagination and pass the created TableView.
            mPagination = new Pagination(mTableView);

            // Sets the pagination listener of the TableView pagination to handle
            // pagination actions. See onTableViewPageTurnedListener variable declaration below.
            mPagination.setOnTableViewPageTurnedListener(onTableViewPageTurnedListener);
        }


        return layout;
    }

    private void initializeTableView() {
        // Create TableView View model class  to group view models of TableView
        mTableViewModel = new TableViewModel(getContext());

        // Create TableView Adapter
        mTableViewAdapter = new TableViewAdapter(getContext(), mTableViewModel);
        mTableView.setAdapter(mTableViewAdapter);
        mTableView.setTableViewListener(new TableViewListener(mTableView));

        // Load the dummy data to the TableView
        mTableViewAdapter.setAllItems(mTableViewModel.getColumnHeaderList(), mTableViewModel
                .getRowHeaderList(), mTableViewModel.getCellList());


    }


    public void filterTable(String filter) {
        // Sets a filter to the table, this will filter ALL the columns.
//        mTableFilter.set(filter);
    }

    public void filterTableForMood(String filter) {
        // Sets a filter to the table, this will only filter a specific column.
        // In the example data, this will filter the mood column.
       //mTableFilter.set(TableViewModel.START_COLUMN_INDEX, filter);
    }

    public void filterTableForGender(String filter) {
        // Sets a filter to the table, this will only filter a specific column.
        // In the example data, this will filter the gender column.
        //mTableFilter.set(TableViewModel.SEX_COLUMN_INDEX, filter);
    }

    // The following four methods below: nextTablePage(), previousTablePage(),
    // goToTablePage(int page) and setTableItemsPerPage(int itemsPerPage)
    // are for controlling the TableView pagination.
    public void nextTablePage() {
        mPagination.nextPage();
    }

    public void previousTablePage() {
        mPagination.previousPage();
    }

    public void goToTablePage(int page) {
        mPagination.goToPage(page);
    }

    public void setTableItemsPerPage(int itemsPerPage) {
        mPagination.setItemsPerPage(itemsPerPage);
    }

    // Handler for the changing of pages in the paginated TableView.
    private Pagination.OnTableViewPageTurnedListener onTableViewPageTurnedListener = new
            Pagination.OnTableViewPageTurnedListener() {
                @Override
                public void onPageTurned(int numItems, int itemsStart, int itemsEnd) {
                    int currentPage = mPagination.getCurrentPage();
                    int pageCount = mPagination.getPageCount();
                //    previousButton.setVisibility(View.VISIBLE);
                //    nextButton.setVisibility(View.VISIBLE);

                    if (currentPage == 1 && pageCount == 1) {
                 //       previousButton.setVisibility(View.INVISIBLE);
                 //       nextButton.setVisibility(View.INVISIBLE);
                    }

                    if (currentPage == 1) {
                 //       previousButton.setVisibility(View.INVISIBLE);
                    }

                    if (currentPage == pageCount) {
                   //     nextButton.setVisibility(View.INVISIBLE);
                    }

                //    tablePaginationDetails.setText(getString(R.string.table_pagination_details, String
                     //       .valueOf(currentPage), String.valueOf(itemsStart), String.valueOf(itemsEnd)));

                }
            };


    private AdapterView.OnItemSelectedListener mItemSelectionListener = new AdapterView
            .OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // 0. index is for empty item of spinner.
            if (position > 0) {

                String filter = Integer.toString(position);

              /*  if (parent == moodFilter) {
                    filterTableForMood(filter);
                } else if (parent == genderFilter) {
                    filterTableForGender(filter);
                }*/
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Left empty intentionally.
        }
    };

    private TextWatcher mSearchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            filterTable(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private AdapterView.OnItemSelectedListener onItemsPerPageSelectedListener = new AdapterView
            .OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int itemsPerPage;
            switch (parent.getItemAtPosition(position).toString()) {
                case "All":
                    itemsPerPage = 0;
                    break;
                default:
                    itemsPerPage = Integer.valueOf(parent.getItemAtPosition(position).toString());
            }

            setTableItemsPerPage(itemsPerPage);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          /*  if (v == previousButton) {
                previousTablePage();
            } else if (v == nextButton) {
                nextTablePage();
            }*/
        }
    };

    private TextWatcher onPageTextChanged = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int page;
            if (TextUtils.isEmpty(s)) {
                page = 1;
            } else {
                page = Integer.valueOf(String.valueOf(s));
            }

            goToTablePage(page);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onResume(){
        super.onResume();
        initializeTableView();
    }
}
