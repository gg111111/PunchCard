package com.qin_kai.punchcard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.qin_kai.punchcard.model.CardVO;

import java.text.SimpleDateFormat;
import java.util.List;

public class CardAdapter extends ArrayAdapter<CardVO> {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int resourceId;

    public CardAdapter(@NonNull Context context, int resource, @NonNull List<CardVO> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        CardVO card = getItem(position);
//
//        // 加载传入的布局
//        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
//        ImageView fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
//        TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
//
//        fruitName.setText(card.getContent());
//        return view;
//    }


    @NonNull
    @Override
    // 优化ListView converView对加载好的布局进行缓存 ViewHolder将图片和TextView 储存到View中
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CardVO card = getItem(position);
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            // 加载传入的布局
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder.Content= (TextView) view.findViewById(R.id.tv_card_content);
            viewHolder.feel= (TextView) view.findViewById(R.id.tv_card_feel);
            viewHolder.location= (TextView) view.findViewById(R.id.tv_card_location);
            viewHolder.username= (TextView) view.findViewById(R.id.tv_username);
            viewHolder.createTime = (TextView) view.findViewById(R.id.tv_createtime);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.Content.setText(card.getContent());
        viewHolder.feel.setText(card.getFeel());
        viewHolder.location.setText(card.getAddress());
        viewHolder.username.setText(card.getUsername());
        viewHolder.createTime.setText(sdf.format(card.getCreateTime()));

        return view;
    }

    class ViewHolder {

        TextView Content;
        TextView feel;
        TextView location;
        TextView username;
        TextView createTime;
    }
}
