package me.tremor.Airglow_user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import me.tremor.Airglow_user.models.Registration;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import java.util.Calendar;
import java.util.Date;

/**
 * Fragment representing the Registration screen
 */
public class RegisterFragment extends Fragment {

    DatePickerDialog.OnDateSetListener mDateListener;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        final TextInputEditText mDate = view.findViewById(R.id.date_of_birth);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputLayout rePasswordTextInput = view.findViewById(R.id.password_text_reinput);
        final TextInputEditText rePasswordEditText = view.findViewById(R.id.repassword_edit_text);
        final TextInputEditText emailOrCell = view.findViewById(R.id.email_cellphone);
        Button signUpButton = view.findViewById(R.id.signup_button);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.choose_sex);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId) {
                    case R.id.user_sex_m: {
                        group.findViewById(R.id.user_sex_f);
                    }
                        break;
                    case R.id.user_sex_f:{
                        group.findViewById(R.id.user_sex_m);
                    }
                        break;
                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.error_password));
                } else {
                    passwordTextInput.setError(null); // Clear the error
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
                if (!isPasswordValid(rePasswordEditText.getText()) || !(rePasswordEditText.getText().equals(passwordEditText.getText()))) {
                    rePasswordTextInput.setError(getString(R.string.error_password));
                } else {
                    rePasswordTextInput.setError(null); // Clear the error
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }

            }
        });

        mDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Calendar mCalendar=Calendar.getInstance();
                int year=mCalendar.get(Calendar.YEAR);
                int month=mCalendar.get(Calendar.MONTH);
                int day=mCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDialog=new DatePickerDialog(
                        getActivity(),android.R.style.Theme_Material_Light_Dialog,mDateListener,year,month,day);
                mDialog.show();
            }
        });
        mDateListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String date=dayOfMonth+"/"+month+"/"+year;
                mDate.setText(date);
            }
        };
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        rePasswordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(rePasswordEditText.getText())) {
                    rePasswordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });


        return view;
    }

    private void registration(String first_name, String last_name, Date date_of_birth,String sex,String password,String email_cellphone){
        Registration mRegistration=new Registration(first_name,last_name, date_of_birth,sex,email_cellphone,password);

    }

    private boolean isPasswordValid(@Nullable Editable text) {
        String mText=text.toString();
        return text != null && text.length() >= 8 && mText.matches(".*\\d+.*");
    }

    private boolean isDateValid(@Nullable Editable text) {

        return text != null ;
    }
    private boolean isNameValid(@Nullable Editable text) {

        return text != null ;
    }
    private boolean isLastNameValid(@Nullable Editable text) {

        return text != null ;
    }


}
