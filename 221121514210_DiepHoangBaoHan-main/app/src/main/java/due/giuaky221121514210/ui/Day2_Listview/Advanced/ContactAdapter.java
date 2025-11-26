package due.giuaky221121514210.ui.Day2_Listview.Advanced;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

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
            rowView = inflater.inflate(R.layout.item_contact_day2_advanced, null);

            ViewHolder holder = new ViewHolder();
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) rowView.findViewById(R.id.tvPhone);
            holder.ivAvatar = (ImageView) rowView.findViewById(R.id.ivAvatar);
            holder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            holder.btCall = (ImageView) rowView.findViewById(R.id.btCall);
            holder.btEdit2 = (ImageView) rowView.findViewById(R.id.btEdit2);
            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.tvName.setText(listContact.get(i).getName());
        holder.tvPhone.setText(listContact.get(i).getPhone());
        holder.ivAvatar.setImageResource(listContact.get(i).getImage());

        holder.btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCall(i);
            }
        });
        return rowView;
    }

    private void onCall(int position){
        ContactModel contact = listContact.get(position);
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+contact.getPhone()));
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        mContext.startActivity(intent);
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvPhone;
        ImageView ivAvatar;
        ImageView btCall;
        ImageView btEdit2;
    }
}