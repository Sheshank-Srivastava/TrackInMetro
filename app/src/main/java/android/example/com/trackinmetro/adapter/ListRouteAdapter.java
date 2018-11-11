package android.example.com.trackinmetro.adapter;

import android.content.Context;
import android.example.com.trackinmetro.R;
import android.example.com.trackinmetro.model.RouteListModel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.txtName.setText(model.getStationName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class ListRouteViewHolder extends RecyclerView.ViewHolder{
        TextView txtColor,txtName;
        public ListRouteViewHolder(View itemView) {
            super(itemView);
            txtColor = itemView.findViewById(R.id.route_color);
            txtName = itemView.findViewById(R.id.route_name);
        }
    }
}
