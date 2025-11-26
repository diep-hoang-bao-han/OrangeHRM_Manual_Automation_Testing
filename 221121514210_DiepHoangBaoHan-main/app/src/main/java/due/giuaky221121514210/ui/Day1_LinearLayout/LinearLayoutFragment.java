package due.giuaky221121514210.ui.Day1_LinearLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import due.giuaky221121514210.databinding.FragmentDay1LinearlayoutBinding;

public class LinearLayoutFragment extends Fragment {

    private FragmentDay1LinearlayoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDay1LinearlayoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView btnCallApi = binding.btnCallApi;
        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}