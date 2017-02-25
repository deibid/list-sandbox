package com.tests.davidazar.listssandbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tests.davidazar.listssandbox.model.Country;
import com.tests.davidazar.listssandbox.util.DataUtil;
import com.tests.davidazar.listssandbox.views.adapters.CountriesAdapter;

import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements CountriesAdapter.AdapterEvents{

    private RecyclerView mList;
    private CountriesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mEmptyStateView;
    private List<Country> mCountries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        mList = (RecyclerView) findViewById(R.id.list);
        mLayoutManager = new LinearLayoutManager(this);
        mEmptyStateView = (TextView) findViewById(R.id.empty_state_view);


        mAdapter = new CountriesAdapter(mCountries, this);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        invalidateOptionsMenu();

        switch (item.getItemId()) {
            case R.id.action_layout_chooser_linear:
                mLayoutManager = new LinearLayoutManager(this);
                break;

            case R.id.action_layout_chooser_staggered:
                mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                break;

            case R.id.action_get_countries:
                mCountries = DataUtil.getCountries();
                break;
        }

        mList.setLayoutManager(mLayoutManager);
        mAdapter = new CountriesAdapter(mCountries,this);
        mList.setAdapter(mAdapter);

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionItemLinear = menu.findItem(R.id.action_layout_chooser_linear);
        MenuItem actionItemStaggered = menu.findItem(R.id.action_layout_chooser_staggered);
        MenuItem actionLoadCountries = menu.findItem(R.id.action_get_countries);

        if(mCountries != null){
            actionLoadCountries.setVisible(false);
        }

        if (mLayoutManager instanceof LinearLayoutManager) {
            actionItemLinear.setVisible(false);
            actionItemStaggered.setVisible(true);
        } else {
            actionItemLinear.setVisible(true);
            actionItemStaggered.setVisible(false);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onStateChange(boolean isEmpty) {

        if (isEmpty) {
            mList.setVisibility(View.INVISIBLE);
            mEmptyStateView.setVisibility(View.VISIBLE);
        } else {
            mList.setVisibility(View.VISIBLE);
            mEmptyStateView.setVisibility(View.INVISIBLE);
        }

    }
}
