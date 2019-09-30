package org.aphrc.myapplication.Masterdetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Fragments.Observation.ObservationActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;

public class ItemsListFragment extends Fragment {

	private OnItemSelectedListener listener;
	private SearchView search;

	ExpandableListView expandableListView;
	ExpandableListAdapter expandableListAdapter;
	List<String> expandableListTitle;
	HashMap<String, List<String>> expandableListDetail;
	DataBaseHelper db;

	public interface OnItemSelectedListener {
		public void onItemSelected(String i);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnItemSelectedListener) {
			listener = (OnItemSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement ItemsListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Create arraylist from item fixtures
		//ArrayList<Item> items = Item.getItems();
		//dataBaseHelper = new DataBaseHelper(this.getContext());


	/*	ArrayList<String> items = dataBaseHelper.getAllObservations();


		adapterItems = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1, items);*/

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate view
		View view = inflater.inflate(R.layout.activity_main_exp, container,
				false);
		// Bind adapter to ListView
		//setContentView(R.layout.activity_main_exp);

		db=new DataBaseHelper(getContext());
		expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
		// expandableListDetail = ExpandableListDataPump.getData();



		expandableListDetail = db.getData();
		expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
		expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);

		expandableListView.setAdapter(expandableListAdapter);
		expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				GlobalClass.observationID= null;
				GlobalClass.socialgroupID= null;
				GlobalClass.roomID=expandableListTitle.get(groupPosition);
				listener.onItemSelected("");

				db.getObservationOBS(GlobalClass.roomID);


			}
		});

		expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				if (GlobalClass.observationID==null){
					Intent obs = new Intent(getContext(), ObservationActivity.class);
					obs.putExtra("mode","N");
					startActivity(obs);
				}

			}
		});

		expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {

				GlobalClass.observationID= null;
				GlobalClass.socialgroupID= null;
				GlobalClass.roomID=expandableListTitle.get(groupPosition);

				String Item=expandableListTitle.get(groupPosition)+"/"+expandableListDetail.get(
						expandableListTitle.get(groupPosition)).get(
						childPosition);
				listener.onItemSelected(Item );

				db.getObservationOBS(GlobalClass.roomID);

				return false;
			}
		});

		return view;
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		/*lvItems.setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);*/


	}
}
