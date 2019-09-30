package org.aphrc.myapplication.Masterdetail;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.LocationObservation.LocationObservation;

public class ItemDetailActivity extends FragmentActivity {
	//ItemDetailFragment fragmentItemDetail;
	LocationObservation fragmentItemDetail;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		Item item = (Item) getIntent().getSerializableExtra("room");
		Item household = (Item) getIntent().getSerializableExtra("household");

		if (savedInstanceState == null) {
			fragmentItemDetail = new LocationObservation();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}

}
