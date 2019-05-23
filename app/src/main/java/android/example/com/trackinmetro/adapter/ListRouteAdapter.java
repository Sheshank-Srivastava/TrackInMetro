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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListRouteAdapter extends RecyclerView.Adapter<ListRouteAdapter.ListRouteViewHolder> {
    Context context;
    ArrayList<RouteListModel> mData;
    ClickListener clickListener;

    public ListRouteAdapter(Context context, ArrayList<RouteListModel> mData, ClickListener listener) {
        this.context = context;
        this.mData = mData;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ListRouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_list_row, parent, false);
        final ListRouteViewHolder holder = new ListRouteViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, holder.getAdapterPosition());
            }
        });
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
//        setColor(holder.colorCode, model, position);
        setViewColor(holder.colorCode, model.getStationColorlist().get(0));
        /**
         * Track ImageView
         */
//        Log.d("Adapter", model.getStationName() + "--" + LastTripsActivity.sourceName + "--" + LastTripsActivity.destinationName + "--" + position);
        holder.upperTrack.setVisibility(View.INVISIBLE);
        holder.bottomTrack.setVisibility(View.INVISIBLE);

        if (position != 0) {

            holder.upperTrack.setVisibility(View.VISIBLE);
        }
        if (position != LastTripsActivity.totalStation - 1) {
            holder.bottomTrack.setVisibility(View.VISIBLE);
        }
        /**
         * Open Dialog on Click
         */

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ListRouteViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        View colorCode;
        ImageView upperTrack, bottomTrack;
        LinearLayout layout;

        public ListRouteViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.route_name);
            colorCode = itemView.findViewById(R.id.view_StationColorCode);
            upperTrack = itemView.findViewById(R.id.img_zerotrack);
            bottomTrack = itemView.findViewById(R.id.img_nthtrack);
            layout = itemView.findViewById(R.id.layout_trackName);
        }
    }

    /**
     * Set Color Functionality
     *
     * @param colorCode
     * @param model
     */

    String tempColor;

//    private void setColor(View colorCode, RouteListModel model, int position) {
//
//        if (model.getStationColorlist().size() == 1) {
//            setViewColor(colorCode, model.getStationColorlist().get(0));
//            tempColor = null;
//            tempColor = model.getStationColorlist().get(0);
//        } else {
//
//            if (position == (mData.size() - 1)) {
//                setViewColor(colorCode, tempColor);
//            } else {
//                RouteListModel tempModelmData = mData.get(++position);
//                ArrayList<String> tempList = new ArrayList<>(model.getStationColorlist());
//                tempList.retainAll(tempModelmData.getStationColorlist());
//                Log.d("GivenColor", tempList.toString() + "->" + tempModelmData.getStationName());
//                setViewColor(colorCode, tempList.get(0));
//                tempColor = null;
//                tempColor = tempList.get(0);
//
//            }
//
//        }
//
//        //----------------------------------------------------------
//        Log.d("ColorCode", model.getStationName() + "===>" + model.getStationColorlist().get(00));
//
//    }

    private void setViewColor(View colorCode, String color) {
        if (color.equalsIgnoreCase("yellow"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.yellow));
        else if (color.equalsIgnoreCase("pink"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.pink));
        else if (color.equalsIgnoreCase("red"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.red));
        else if (color.equalsIgnoreCase("green"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.green));
        else if (color.equalsIgnoreCase("magenta"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.magenta));
        else if (color.equalsIgnoreCase("blue"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.blue));
        else if (color.equalsIgnoreCase("violet"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.blue_violet));
        else if (color.equalsIgnoreCase("orange"))
            colorCode.setBackgroundColor(context.getResources().getColor(R.color.orange));
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
