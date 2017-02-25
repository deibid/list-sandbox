package com.tests.davidazar.listssandbox.views.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tests.davidazar.listssandbox.R;
import com.tests.davidazar.listssandbox.model.Country;

import java.util.List;

/**
 * Created by David on 24/02/17.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {


    private List<Country> mDataset;
    private AdapterEvents mListener;


    public interface AdapterEvents {
        void onStateChange(boolean isEmpty);
    }

    public CountriesAdapter(List<Country> mDataset, AdapterEvents listener) {
        this.mDataset = mDataset;
        this.mListener = listener;
    }

    public void setData(List<Country> countries) {
        this.mDataset = countries;
    }

    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_item, parent, false);
        return new ViewHolder(item);
    }


    @Override
    public void onBindViewHolder(CountriesAdapter.ViewHolder holder, int position) {

        Country country = mDataset.get(position);

        holder.name.setText(country.getName());
        holder.description.setText(country.getDescription());
        holder.population.setText("Población: " + country.getPopulation());


        Glide.with(holder.imageUrl.getContext())
                .load(country.getImageUrl())
                .into(holder.imageUrl);


    }

    @Override
    public int getItemCount() {

        if (mDataset == null) {
            mListener.onStateChange(true);
            return 0;
        } else {
            if (mDataset.size() == 0) {
                mListener.onStateChange(true);
                return 0;
            } else {
                mListener.onStateChange(false);
                return mDataset.size();
            }
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

//            Country country = mDataset.get(getAdapterPosition());
            //......//


        }
    }
}
