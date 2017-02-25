package com.tests.davidazar.listssandbox;

import io.realm.Realm;

/**
 * Created by David on 25/02/17.
 */

public class Application extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();


        Realm.init(this);


    }
}
