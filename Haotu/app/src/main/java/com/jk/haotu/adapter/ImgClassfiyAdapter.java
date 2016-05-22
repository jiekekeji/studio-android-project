package com.jk.haotu.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
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
public class ImgClassfiyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ImgClassify> mClassifies;
    private int defaultSelection;//默认被选中的item

    public ImgClassfiyAdapter(Context context, List<ImgClassify> classifies) {
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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImgClassify classify = mClassifies.get(i);
        //判断是否要设置为选中的状态
        if (i==defaultSelection){
            viewHolder.titleTv.setTextColor(mContext.getResources().getColor(R.color.red));
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
        }else {
            viewHolder.titleTv.setTextColor(mContext.getResources().getColor(R.color.light_black));
//            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
            convertView.setBackgroundResource(R.drawable.shape_border);

        }

        //设置数据
        viewHolder.titleTv.setText(classify.getTitle());
        return convertView;
    }

    /**
     * @param position 设置选中的item
     */
    public void setSelectPosition(int position) {
        if (!(position < 0 || position > mClassifies.size())) {
            defaultSelection = position;
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        TextView titleTv;
        ImageView imgview;
    }
}
