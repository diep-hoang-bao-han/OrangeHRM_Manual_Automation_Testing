package due.giuaky221121514210.ui.Day2_Listview.ListViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import due.giuaky221121514210.R;
import due.giuaky221121514210.databinding.FragmentDay2ListviewBinding;

public class ListViewFragment extends Fragment{
    private FragmentDay2ListviewBinding binding;
    private List<ContactModel> listContact = new ArrayList<>();
    private ListView lvContact;
    private ContactAdapter mAdapter;
    private ImageView ivUser;
    private TextView tvName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDay2ListviewBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initData();
        initView();

        mAdapter = new ContactAdapter(requireContext(), listContact);
        lvContact.setAdapter(mAdapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ContactModel model = listContact.get(i);
                Toast.makeText(requireContext(), model.getName() + ": " + model.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }


    private void initView(){
        lvContact = binding.lvContact1;
    }

    private void initData(){
        listContact.add(new ContactModel("Nguyễn Thị Ngủm", "0338776671", R.drawable.ic_user_c));
        listContact.add(new ContactModel("Trần Thị Bình", "0988111222", R.drawable.ic_user_a));
        listContact.add(new ContactModel("Đào Thị Mơ", "0988111333", R.drawable.ic_user_b));
        listContact.add(new ContactModel("Hồ Văn Dũng", "9988111555", R.drawable.ic_user_c));
        listContact.add(new ContactModel("Đỗ Thị Mai", "8988222333", R.drawable.ic_user_d));
        listContact.add(new ContactModel("Ngô Thị Mận", "8988555222", R.drawable.ic_user_b));
        listContact.add(new ContactModel("Nguyễn Quang Tèo", "9999666111", R.drawable.ic_user_a));
        listContact.add(new ContactModel("Giang Văn Cẩn", "898856789", R.drawable.ic_user_d));
        listContact.add(new ContactModel("Ngô Văn Ngô", "8988856789", R.drawable.ic_user_a));
        listContact.add(new ContactModel("Phùng A Chài", "8988856789", R.drawable.ic_user_b));
        listContact.add(new ContactModel("Đình Thị Bình", "8988856789", R.drawable.ic_user_c));
        listContact.add(new ContactModel("Lỗ Văn Lắp", "8988856789", R.drawable.ic_user_d));
        listContact.add(new ContactModel("Hâm Văn Hấp", "8988856789", R.drawable.ic_user_a));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}