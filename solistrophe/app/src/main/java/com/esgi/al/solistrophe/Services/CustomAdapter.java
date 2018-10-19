package com.esgi.al.solistrophe.Services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.model.Service;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Service> implements View.OnClickListener {

    private ArrayList<Service> ServiceSet;
    private int lastPosition = -1;

    Context mContext;

    private static class ViewHolder {

        TextView txtName;
        TextView txtDescription;
        TextView txtId;
    }

    public CustomAdapter(ArrayList<Service> data, Context context){
        super(context, R.layout.service_row, data);
        this.ServiceSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Service model = (Service) object;

        switch (v.getId()) {
            case R.id.service_id:
                Snackbar.make(v, "Etat de l'alerte " + model.getId(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Get the service model for this position
        Service model = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.service_row, parent, false);
            viewHolder.txtId = (TextView) convertView.findViewById(R.id.service_id);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.service_description);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.service_name);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(model.getName());
        viewHolder.txtDescription.setText(model.getDescription());
        viewHolder.txtId.setText("ID:" + model.getId());


        // Return the completed view to render on screen
        return convertView;
    }
}
