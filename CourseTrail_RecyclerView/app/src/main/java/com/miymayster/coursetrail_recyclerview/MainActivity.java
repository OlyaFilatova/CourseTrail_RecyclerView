package com.miymayster.coursetrail_recyclerview;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener {
    private ArrayList<ItemInfo> mItems;
    private Toast mToast;
    private ItemAdapter adapter;

    String[] headers = new String[]{
            "Carry on Scrolling",
            "AppCompat v23.2 - DayNight",
            "Build great things at any scale",
            "Why isn’t my animation running?"
    };
    String[] infos = new String[]{
            "Jenkins can be installed through native system packages, Docker, or even run standalone by any machine with the Java Runtime Environment installed",
            "the \"standalone\" Jenkins distribution requires a minimum of Java 7, though Java 8 is recommended. A system with more than 512MB of RAM is also recommended.",
            "1. Download Jenkins.\n" +
                    "2. Open up a terminal in the download directory and run java -jar jenkins.war\n" +
                    "3. Browse to http://localhost:8080 and follow the instructions to complete the installation.\n" +
                    "4. Many Pipeline examples require an installed Docker on the same computer as Jenkins.",
            "Jenkins is a self-contained, open source automation server which can be used to automate all sorts of tasks such as building, testing, and deploying software."
    };
    int[] images = new int[]{
            R.drawable.dog,
            R.drawable.dodge,
            R.drawable.mountain,
            R.drawable.cup_of_coffee,
            -1
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", mItems);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            populateInfoArray();
        }else{
            mItems = savedInstanceState.getParcelableArrayList("items");
        }

        adapter = new ItemAdapter(mItems, this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

    private void populateInfoArray(){
        if(mItems == null){
            mItems = new ArrayList<>();
        }else {
            mItems.clear();
        }
        for(int i = 0; i < 1000; i ++){
            int imageIndex = ((int)(Math.random()*10))%images.length;
            int resId = images[imageIndex];
            int headerIndex = ((int)(Math.random()*10))%headers.length;
            String header = headers[headerIndex];
            int infoIndex = ((int)(Math.random()*10))%infos.length;
            String info = infos[infoIndex];
            mItems.add(new ItemInfo(resId, header, info));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_shuffle){
            populateInfoArray();
            adapter.swapArray(mItems);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int itemIndex) {
        if(mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(this, "Header - " + mItems.get(itemIndex).getHeader() + ", Info - " + mItems.get(itemIndex).getInfo(), Toast.LENGTH_SHORT);
        mToast.show();
    }
}
