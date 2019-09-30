package org.aphrc.myapplication.Masterdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.FrameLayout;

import org.aphrc.myapplication.LocationObservation.LocationObservation;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Masterdetail.ItemsListFragment.OnItemSelectedListener;
import org.aphrc.myapplication.R;


public class ItemsListActivity extends FragmentActivity implements OnItemSelectedListener {
	private boolean isTwoPane = false;
	DataBaseHelper dataBaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);

		dataBaseHelper=new DataBaseHelper(getBaseContext());
		determinePaneLayout();
	}

	private void determinePaneLayout() {
		FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
		if (fragmentItemDetail != null) {
			isTwoPane = true;
			ItemsListFragment fragmentItemsList = 
					(ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
			fragmentItemsList.setActivateOnItemClick(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.items, menu);
		return true;
	}

	@Override
	public void onItemSelected(String item) {

		if (item.contains("/")){
			String items[]=item.split("/");
			String Room =items[0];
			String Household=items[1];
			GlobalClass.roomID = Room;
			GlobalClass.socialgroupID= Household ;
		}

		if (isTwoPane) {
			LocationObservation fragmentItemDetail = new LocationObservation();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		} else {
			Intent i = new Intent(this, ItemDetailActivity.class);
			startActivity(i);
		}
	}


}
