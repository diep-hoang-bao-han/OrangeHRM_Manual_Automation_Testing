package due.giuaky221121514210.ui.Day2_Listview.ListViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import due.giuaky221121514210.R;

public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private List<ContactModel> listContact;

    public ContactAdapter(Context mContext, List<ContactModel> listContact){
        this.mContext = mContext;
        this.listContact = listContact;
    }


    @Override
    public int getCount(){
        return listContact.size();
    }

    @Override
    public Objects getItem(int i){
        return null;
    }

    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup){
        View rowView = convertView;
        if(rowView == null){

            LayoutInflater inflater = LayoutInflater.from(mContext);
            rowView = inflater.inflate(R.layout.item_contact_day2_listview, null);

            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName1);
            holder.tvPhone = (TextView) rowView.findViewById(R.id.tvPhone1);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar1);
            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(listContact.get(i).getName());
        holder.tvPhone.setText(listContact.get(i).getPhone());
        holder.ivAvatar.setImageResource(listContact.get(i).getImage());

        return rowView;
    }


    static class ViewHolder{
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
    }
}