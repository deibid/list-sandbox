package com.tests.davidazar.listssandbox.util;

import com.tests.davidazar.listssandbox.model.Country;
import com.tests.davidazar.listssandbox.model.RealmCountry;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by David on 24/02/17.
 */

public class DataUtil {


    public static List<Country> getCountries() {

        Country mexico = new Country("Mexico",
                "123,138,911",
                "https://lonelyplanetimages.imgix.net/mastheads/93931301.jpg?sharp=10&vib=20&w=1200",
                "Es un país de América, ubicado en la parte meridional de América del Norte. Su capital " +
                        "es la Ciudad de México.12 Políticamente es una república " +
                        "democrática, representativa y federal compuesta por 32 entidades federativas.");

        Country usa = new Country("United States",
                "309,911,111",
                "http://www.newyorker.com/wp-content/uploads/2015/12/Veix-Goodbye-New-York-Color-1200.jpg",
                "Es un país soberano constituido en república federal constitucional compuesta por 50 estados y un distrito federal.");

        Country hongKong = new Country("Hong Kong",
                "7,188,111",
                "https://www.whitecase.com/sites/whitecase/files/images/locations/HongKong_Tablet_1920x960.jpg",
                "Es una región administrativa especial de la República Popular China. " +
                        "Está formada por una península y varias islas situadas en la costa sur " +
                        "del mar de la China Meridional, en el delta del río Perla, 100 km al " +
                        "sudeste la ciudad de Cantón y al este de la región administrativa especial de Macao.");

        Country japan = new Country("Japón",
                "127,300,109",
                "https://summertriptojapan.files.wordpress.com/2015/09/mount-fuji-8.jpg",
                "Es un país soberano insular del este de Asia. Situado en el océano Pacífico; " +
                        "tiene al oeste el mar del Japón, China, Corea del Norte, Corea del Sur y Rusia.");



        List<Country> result = new ArrayList<>();
        result.add(mexico);
        result.add(usa);
        result.add(hongKong);
        result.add(japan);

        return result;

    }



    public static void addRealmCountry(Realm realm){

        List<Country> countries = getCountries();

        int index = getCurrentRealmCountryIndex(realm);

        Country country = countries.get(index);

        realm.beginTransaction();

        RealmCountry realmCountry = realm.createObject(RealmCountry.class,country.getName());
        realmCountry.setDescription(country.getDescription());
        realmCountry.setPopulation(country.getPopulation());
        realmCountry.setImageUrl(country.getImageUrl());

        realm.commitTransaction();

    }


    public static void deleteRealmCountry(Realm realm){

        int index = getCurrentRealmCountryIndex(realm);

        realm.beginTransaction();
        realm.where(RealmCountry.class).findAll().get(index-1).deleteFromRealm();
        realm.commitTransaction();

    }


    public static int getCurrentRealmCountryIndex(Realm realm){
        return realm.where(RealmCountry.class).findAll().size();
    }

}
