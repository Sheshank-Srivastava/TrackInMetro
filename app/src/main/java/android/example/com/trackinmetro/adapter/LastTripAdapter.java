package android.example.com.trackinmetro.adapter;

import android.content.Context;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.model.LastTripModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LastTripAdapter extends RecyclerView.Adapter<LastTripAdapter.LastTripViewHolder> {
    private Context context;
    private ArrayList<LastTripModel> mData;

    public LastTripAdapter(Context context, ArrayList<LastTripModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public LastTripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.last_trip_row,parent,false);
        final LastTripViewHolder holder = new LastTripViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LastTripViewHolder holder, int position) {
        LastTripModel model = mData.get(position);
        holder.source.setText(model.getStationOne());
        holder.destination.setText(model.getStationTwo());

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class LastTripViewHolder extends RecyclerView.ViewHolder{
        TextView source,destination;
        public LastTripViewHolder(View itemView) {
            super(itemView);
            source = itemView.findViewById(R.id.txtStationOne);
            destination = itemView.findViewById(R.id.txtStationTwo);
        }
    }
}
