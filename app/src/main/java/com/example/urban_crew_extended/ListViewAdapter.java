package com.example.urban_crew_extended;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Search_Model>modelList;
    ArrayList<Search_Model>arrayList;


    public ListViewAdapter(Context Context, List<Search_Model> modelList) {
        mContext = Context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Search_Model>();
        this.arrayList.addAll(modelList);
    }

    public class ViewHolder{
        TextView mTitle;
        ImageView mIcon;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row,null);

            //locate views in row.xml
            holder.mTitle = convertView.findViewById(R.id.mainTitle);
            holder.mIcon = convertView.findViewById(R.id.mainIcon);

            convertView.setTag(holder);
        }

        else {

            holder = (ViewHolder)convertView.getTag();
        }

        //set result into textview and image view
        holder.mTitle.setText(modelList.get(position).getTitle());
        holder.mIcon.setImageResource(modelList.get(position).getIcon());


        //list view item clicks
        convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (modelList.get(position).getTitle().equals("Maruti Suzuki AltoK10")){

                    Intent intent = new Intent(mContext,AltoK10.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Maruti Suzuki Swift")){

                    Intent intent = new Intent(mContext,Swift.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Maruti Suzuki CelerioX")){

                    Intent intent = new Intent(mContext,CelerioX.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Maruti Suzuki Wagon R")){

                    Intent intent = new Intent(mContext,WagonR.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Mazda 6")){

                    Intent intent = new Intent(mContext,Mazda6.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Toyota Avalon")){

                    Intent intent = new Intent(mContext,Avalon.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Audi A7")){

                    Intent intent = new Intent(mContext,A7.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("BMW M760Li")){

                    Intent intent = new Intent(mContext,M760Li.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Honda CR-V")){

                    Intent intent = new Intent(mContext,CR_V.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Nissan X-Trail")){

                    Intent intent = new Intent(mContext,X_Trail.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Toyota Land Cruiser V8")){

                    Intent intent = new Intent(mContext,V8.class);
                    mContext.startActivity(intent);
                }

                if (modelList.get(position).getTitle().equals("Range Rover Evoque")){

                    Intent intent = new Intent(mContext,Evoque.class);
                    mContext.startActivity(intent);
                }

            }
        });

        return convertView;
    }

    //Filter
    public void filter(String charText){

        charText = charText.toLowerCase(Locale.getDefault());
        modelList.clear();

        if (charText.length()==0){

            modelList.addAll(arrayList);
        }

        else {

            for (Search_Model model : arrayList){

                if (model.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){

                    modelList.add(model);
                }
            }
        }

        notifyDataSetChanged();
    }
}
