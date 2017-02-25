package com.tests.davidazar.listssandbox.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tests.davidazar.listssandbox.R;
import com.tests.davidazar.listssandbox.model.RealmCountry;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Created by David on 25/02/17.
 */

public class RealmCountriesAdapter extends RealmBasedRecyclerViewAdapter<RealmCountry, RealmCountriesAdapter.ViewHolder> {

    public RealmCountriesAdapter(Context context,
                                 RealmResults<RealmCountry> realmResults,
                                 boolean automaticUpdate,
                                 boolean animateResults) {

        super(context, realmResults, automaticUpdate, animateResults);
    }

    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.realm_country_list_item, viewGroup, false);

        return new ViewHolder(item);
//        return new ViewHolder(inflater.inflate(R.layout.realm_country_list_item,viewGroup));
    }

    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int i) {

        RealmCountry country = realmResults.get(i);


        viewHolder.name.setText(country.getName());
        viewHolder.description.setText(country.getDescription());
        viewHolder.population.setText("Población: " + country.getPopulation());

        Log.d("TAG", country.getImageUrl());

        Glide.with(viewHolder.imageUrl.getContext())
                .load(country.getImageUrl())
                .into(viewHolder.imageUrl);


    }

    class ViewHolder extends RealmViewHolder implements View.OnClickListener {

        CardView container;
        TextView name;
        TextView population;
        TextView description;
        ImageView imageUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            container = (CardView) itemView.findViewById(R.id.country_container);
            name = (TextView) itemView.findViewById(R.id.country_name);
            population = (TextView) itemView.findViewById(R.id.country_population);
            description = (TextView) itemView.findViewById(R.id.country_description);
            imageUrl = (ImageView) itemView.findViewById(R.id.country_image);

            container.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),
                    "Click en item: #" + getAdapterPosition(),
                    Toast.LENGTH_SHORT).show();
        }
    }


}
