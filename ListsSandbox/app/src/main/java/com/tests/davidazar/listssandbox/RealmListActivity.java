package com.tests.davidazar.listssandbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tests.davidazar.listssandbox.model.RealmCountry;
import com.tests.davidazar.listssandbox.util.DataUtil;
import com.tests.davidazar.listssandbox.views.adapters.RealmCountriesAdapter;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RealmListActivity extends AppCompatActivity implements View.OnClickListener {


    private RealmRecyclerView mList;
    private RealmCountriesAdapter mAdapter;

    private Button mButtonPlus;
    private Button mButtonMinus;

    private Realm mRealm;
    private RealmResults<RealmCountry> mRealmCountries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_list);

        mRealm = Realm.getDefaultInstance();

        mList = (RealmRecyclerView) findViewById(R.id.realm_list);
        mButtonPlus = (Button) findViewById(R.id.button_plus);
        mButtonMinus = (Button) findViewById(R.id.button_minus);

        mButtonPlus.setOnClickListener(this);
        mButtonMinus.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();

        mRealmCountries = mRealm.where(RealmCountry.class).findAll();
        mRealmCountries.addChangeListener(new RealmChangeListener<RealmResults<RealmCountry>>() {
            @Override
            public void onChange(RealmResults<RealmCountry> element) {
                checkButtonState();
            }
        });

        if (mRealmCountries.size() != 0) {
            mAdapter = new RealmCountriesAdapter(this, mRealmCountries, true, true);
            mList.setAdapter(mAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkButtonState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList.setAdapter(null);
        mRealmCountries.removeChangeListeners();
        mRealm.close();

    }

    @Override
    public void onClick(View view) {

        if (mAdapter == null) {
            mAdapter = new RealmCountriesAdapter(this, mRealmCountries, true, true);
            mList.setAdapter(mAdapter);
        }

        switch (view.getId()) {

            case R.id.button_plus:
                DataUtil.addRealmCountry(mRealm);
                break;

            case R.id.button_minus:
                DataUtil.deleteRealmCountry(mRealm);
                break;
        }
    }



    private void checkButtonState(){


        int countriesSize = mRealmCountries.size();

        if (countriesSize == 0) {
            mButtonMinus.setEnabled(false);
            mButtonMinus.animate().alpha(0.5f);
        }

        if(countriesSize > 0 && countriesSize < 4){
            mButtonMinus.setEnabled(true);
            mButtonMinus.setAlpha(1f);

            mButtonPlus.setEnabled(true);
            mButtonPlus.animate().alpha(1f);

        }

        if (countriesSize == 4) {

            mButtonPlus.setEnabled(false);
            mButtonPlus.animate().alpha(0.5f);
        }
    }
}
