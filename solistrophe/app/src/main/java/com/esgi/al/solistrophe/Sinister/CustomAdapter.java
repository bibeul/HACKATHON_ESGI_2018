package com.esgi.al.solistrophe.Sinister;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.al.solistrophe.R;
import com.esgi.al.solistrophe.model.Sinister;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Sinister> implements View.OnClickListener {

    private ArrayList<Sinister> SinisterSet;
    private int lastPosition = -1;

    Context mContext;

    private static class ViewHolder {

        TextView txtName;
        TextView txtSeverity;
        TextView txtDescription;
        TextView txtId;
        TextView txtAccountId;
        ImageView state;
    }

    public CustomAdapter(ArrayList<Sinister> data, Context context){
        super(context, R.layout.sinister_row, data);
        this.SinisterSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Sinister model = (Sinister) object;

        switch (v.getId()) {
            case R.id.sinister_state:
                Snackbar.make(v, "Etat de l'alerte " + model.getState(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Get the sinister model for this position
        Sinister model = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sinister_row, parent, false);
            viewHolder.state = convertView.findViewById(R.id.sinister_state);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.sinister_description);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.sinister_name);
            viewHolder.txtSeverity = (TextView) convertView.findViewById(R.id.sinister_severity);

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
        viewHolder.txtSeverity.setText("SEVERITY: " + model.getSeverity());

        switch (model.getState()) {
            case 0:
                viewHolder.state.setOnClickListener(this);
                viewHolder.state.setTag(position);
                viewHolder.state.setImageResource(R.drawable.status_ok);
                break;
            case 1:
                viewHolder.state.setOnClickListener(this);
                viewHolder.state.setTag(position);
                viewHolder.state.setImageResource(R.drawable.status_pending);
                break;
            case 2:
                viewHolder.state.setOnClickListener(this);
                viewHolder.state.setTag(position);
                viewHolder.state.setImageResource(R.drawable.status_ko);
                break;
            default:
                viewHolder.state.setOnClickListener(this);
                viewHolder.state.setTag(position);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
