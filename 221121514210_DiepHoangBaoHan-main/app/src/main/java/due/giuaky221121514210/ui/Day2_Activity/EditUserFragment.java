package due.giuaky221121514210.ui.Day2_Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import due.giuaky221121514210.R;
import due.giuaky221121514210.databinding.FragmentDay2AcEditUserBinding;

public class EditUserFragment extends Fragment implements View.OnClickListener {
    private FragmentDay2AcEditUserBinding binding;
    private EditText edUser;
    private Button btDone;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate binding
        binding = FragmentDay2AcEditUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Ánh xạ view
        edUser = binding.edUser;
        btDone = binding.btDone;
        btDone.setOnClickListener(this);

        String username = getArguments().getString("USER_NAME");
        edUser.setText(username);

        return root;
    }


    @Override
    public void onClick(View view){
        if (view.getId() == R.id.btDone) {
            onDone();
        }
    }
    private void onDone(){
        String user = edUser.getText().toString().trim();

        /// Tạo Bundle chứa dữ liệu trả về
        Bundle result = new Bundle();
        result.putString("RESULT", user);

        // Gửi kết quả về cho fragment đăng ký lắng nghe với key "edit_request_key"
        getParentFragmentManager().setFragmentResult("edit_request_key", result);

        // Pop fragment khỏi back stack để quay lại ProfileFragment
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}