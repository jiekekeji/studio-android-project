package com.jk.yueba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jk.yueba.R;
import com.jk.yueba.bean.User;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 联系人列表适配器
 * Created by jack on 16/5/28.
 */
public class ContactsAdapter extends BaseAdapter {

    private List<User> users;
    private Context mContext;
    private LayoutInflater mInflater;

    public ContactsAdapter(List<User> users, Context context) {
        this.users = users;
        this.mContext = context;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView)
        {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_contact_fr, null);

            viewHolder.nameTv = (TextView) convertView.findViewById(R.id.username);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User user=users.get(position);
        viewHolder.nameTv.setText(user.getAccount());

        return convertView;
    }

    class ViewHolder{
        TextView nameTv;
    }
}
