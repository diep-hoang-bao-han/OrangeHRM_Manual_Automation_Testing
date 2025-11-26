package due.giuaky221121514210.ui.Day2_Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import due.giuaky221121514210.R;

import due.giuaky221121514210.databinding.FragmentDay2AcLoginBinding;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private FragmentDay2AcLoginBinding binding;
    private EditText edUser;
    private EditText edPassword;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Bước 1: Inflate binding
        binding = FragmentDay2AcLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Bước 2: Ánh xạ view qua binding
        edUser = binding.edUser;
        edPassword = binding.edPassword;

        // Bước 3: Thiết lập sự kiện
        binding.btLogin.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btLogin) {
            onLogin();
        }
    }

    // Hàm xử lý khi bấm Login
    private void onLogin() {
        String user = edUser.getText().toString().trim();
        String pass = edPassword.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            // Dùng requireContext() thay cho this
            Toast.makeText(requireContext(), "Bạn chưa nhập user hoặc password", Toast.LENGTH_SHORT).show();
        } else {
            // Tạo instance của ProfileFragment
            ProfileFragment profileFragment = new ProfileFragment();

            // Truyền dữ liệu qua Bundle nếu cần
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", user);
            profileFragment.setArguments(bundle);

            // Sử dụng NavController để điều hướng
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_nav_day2_activity_login_to_nav_day2_activity_profile, bundle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}