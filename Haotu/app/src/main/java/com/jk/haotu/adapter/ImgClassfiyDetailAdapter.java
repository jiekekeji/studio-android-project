package com.jk.haotu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jk.haotu.R;
import com.jk.haotu.bean.Tngou;
import com.jk.haotu.net.URLapi;

import java.util.List;

/**
 * Created by jack on 16/5/21.
 */
public class ImgClassfiyDetailAdapter extends BaseAdapter {

    private Context mContext;
    private List<Tngou> mList;
    private LayoutInflater mInflater;

    public ImgClassfiyDetailAdapter(Context context, List<Tngou> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    /**
     * 返回item的总数
     *
     * @return
     */
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LogUtils.i("item次数:" + i);
        return showtypeSecondItem(i, convertView);
    }


    /**
     * 显示第二种类型的item
     *
     * @param i
     * @param convertView
     */
    private View showtypeSecondItem(int i, View convertView) {
        ViewHolderSecond viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolderSecond();
            convertView = mInflater.inflate(R.layout.adapter_main_second, null);

//            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.name);
            viewHolder.imgview = (ImageView) convertView.findViewById(R.id.imgview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderSecond) convertView.getTag();
        }

        //设置数据
        // set item values to the viewHolder:
        Tngou tngou = mList.get(i);
//        viewHolder.titleTv.setText(tngou.getTitle());
        Glide.with(mContext).load(URLapi.IMG_PRE + tngou.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.test).into(viewHolder.imgview);
        LogUtils.i("列表图片url=" + URLapi.IMG_PRE + tngou.getImg());
        return convertView;
    }


    static class ViewHolderSecond {
        TextView titleTv;
        ImageView imgview;
    }
}
