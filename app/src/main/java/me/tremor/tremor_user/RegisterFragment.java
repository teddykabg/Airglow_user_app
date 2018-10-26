package me.tremor.tremor_user;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Fragment representing the login screen for Shrine.
 */
public class RegisterFragment extends Fragment {
    RadioButton mMale;
    RadioButton mFemale;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        mMale = view.findViewById(R.id.user_sex_m);
        mFemale = view.findViewById(R.id.user_sex_f);
        return view;
    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.user_sex_m:
                if (checked){
                    mFemale.setChecked(false);
                }

                    break;
            case R.id.user_sex_f:
                if (checked){
                    mMale.setChecked(false);
                }

                    break;
        }
    }
}
