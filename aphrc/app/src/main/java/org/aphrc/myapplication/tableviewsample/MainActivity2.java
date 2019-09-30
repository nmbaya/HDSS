
package org.aphrc.myapplication.tableviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import org.aphrc.myapplication.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.activity_container, new
                    MainFragment(), MainFragment.class.getSimpleName()).commit();
        }
    }
}
