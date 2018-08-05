package com.example.alex.capstone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.alex.capstone.R;
import com.example.alex.capstone.model.Line;
import com.example.alex.capstone.model.PhysicalStop;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Context context;
    PhysicalStop physicalStop;

    public CustomInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.info_window_layout,null);
        TextView stopNameTv = view.findViewById(R.id.stop_name_tv);
        stopNameTv.setText(physicalStop.getName());
        String linesName=null;

        for (Line line : physicalStop.getLines()){
            linesName = line.getShortName()+" ";
        }
        //TextView lineListTv = view.findViewById(R.id.line_list_tv);
       // lineListTv.setText(linesName);

        ImageButton gotoStopInfoAct =  view.findViewById(R.id.go_to_bus_stop_info_im);

        return view;
    }

    public void setPhysicalStop(PhysicalStop physicalStop){
        this.physicalStop=physicalStop;
    }
}
