package android.example.com.trackinmetro.adapter;

import android.content.Context;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.activity.LastTripsActivity;
import android.example.com.trackinmetro.model.RouteListModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListRouteAdapter extends RecyclerView.Adapter<ListRouteAdapter.ListRouteViewHolder> {
    Context context;
    ArrayList<RouteListModel> mData;

    public ListRouteAdapter(Context context, ArrayList<RouteListModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ListRouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_list_row,parent,false);
        final ListRouteViewHolder holder = new ListRouteViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListRouteViewHolder holder, int position) {
        RouteListModel model = mData.get(position);
        /**
         * Station Name
         */
        holder.txtName.setText(model.getStationName());
        /**
         * View Station Color Code
         */

        holder.colorCode.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        /**
         * Track ImageView
         */
        Log.d("Adapter",model.getStationName() +"--"+ LastTripsActivity.sourceName+"--"+LastTripsActivity.destinationName+"--"+position);
        holder.upperTrack.setVisibility(View.INVISIBLE);
        holder.bottomTrack.setVisibility(View.INVISIBLE);

        if(position != 0){
            Log.d("Adapter","Source");
            holder.upperTrack.setVisibility(View.VISIBLE);
        }
        if(position != LastTripsActivity.totalStation-1){
//            Log.d("Adapter",);
            holder.bottomTrack.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class ListRouteViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        View colorCode;
        ImageView upperTrack,bottomTrack;

        public ListRouteViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.route_name);
            colorCode = itemView.findViewById(R.id.view_StationColorCode);
            upperTrack = itemView.findViewById(R.id.img_zerotrack);
            bottomTrack = itemView.findViewById(R.id.img_nthtrack);

        }
    }
}
