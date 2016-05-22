package com.jk.haotu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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
import com.jk.haotu.bean.ImgClassify;
import com.jk.haotu.bean.Tngou;
import com.jk.haotu.net.URLapi;

import java.util.List;

/**
 * Created by jack on 16/5/21.
 */
public class SecondItemFrgAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ImgClassify> mClassifies;

    public SecondItemFrgAdapter(Context context, List<ImgClassify> classifies) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mClassifies = classifies;
    }


    @Override
    public int getCount() {
        return mClassifies.size();
    }

    @Override
    public Object getItem(int i) {
        return mClassifies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_fr_second, null);

            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.textview);
//            viewHolder.viewPager= (ViewPager) convertView.findViewById(R.id.viewpager);
//            viewHolder.viewPager.setAdapter(new IndexHeaderAdapter(mContext, mList));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImgClassify classify = mClassifies.get(i);
        //设置数据
        viewHolder.titleTv.setText(classify.getTitle());
        return convertView;
    }

    static class ViewHolder {
        TextView titleTv;
        ImageView imgview;
    }
}
