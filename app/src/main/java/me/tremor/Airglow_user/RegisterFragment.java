package me.tremor.Airglow_user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import me.tremor.Airglow_user.models.Registration;
import me.tremor.Airglow_user.models.User;
import me.tremor.Airglow_user.service.UserClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Fragment representing the Registration screen
 */
public class RegisterFragment extends Fragment {

    DatePickerDialog.OnDateSetListener mDateListener;
    char mSex;
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl("http://api.airglow.me:5000/v1/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit mRetrofit= builder.build();
    UserClient userClient= mRetrofit.create(UserClient.class);

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        boolean fieldsCheck=false;//Verifica se tutti i campi sono corretti;

        final TextInputLayout firstNameInput= view.findViewById(R.id.first_layout);
        final TextInputLayout lastNameInput= view.findViewById(R.id.last_layout);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputLayout rePasswordTextInput = view.findViewById(R.id.password_text_reinput);
        final TextInputLayout dateInput= view.findViewById(R.id.date_layout);
        final TextInputLayout emailInput= view.findViewById(R.id.email_layout);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.choose_sex);

        final TextInputEditText firstNameEdit = view.findViewById(R.id.first_edit);
        final TextInputEditText lastNameEdit = view.findViewById(R.id.last_edit);
        final TextInputEditText dateEdit = view.findViewById(R.id.date_of_birth);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputEditText rePasswordEditText = view.findViewById(R.id.repassword_edit_text);
        final TextInputEditText emailOrCellEdit = view.findViewById(R.id.email_cellphone);
        Button signUpButton = view.findViewById(R.id.signup_button);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId) {
                    case R.id.user_sex_m: {
                        group.findViewById(R.id.user_sex_f);
                        setSex('M');

                    }
                        break;
                    case R.id.user_sex_f:{
                        group.findViewById(R.id.user_sex_m);
                        setSex('F');
                    }
                        break;
                }
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fieldsCheck=true;//Verifica se tutti i campi sono corretti;
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.error_password));
                    if (!(rePasswordEditText.getText().equals(passwordEditText.getText()))) {
                        rePasswordTextInput.setError(getString(R.string.error_password));
                        Toast.makeText(getActivity(),"Sono diversi!!",Toast.LENGTH_SHORT);
                        fieldsCheck=false;
                    } else {
                        rePasswordTextInput.setError(null); // Clear the error
                        Toast.makeText(getActivity(),"Sono Ugualiii!!",Toast.LENGTH_SHORT);
                        fieldsCheck=true;
                        //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                    }

                } else {
                    passwordTextInput.setError(null);// Clear the error
                    rePasswordTextInput.setError(null);
                    fieldsCheck=true;
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
                /*if (!isPasswordValid(rePasswordEditText.getText()) || !(rePasswordEditText.getText().equals(passwordEditText.getText()))) {
                    rePasswordTextInput.setError(getString(R.string.error_password));
                    
                    fieldsCheck=false;
                } else {
                    rePasswordTextInput.setError(null); // Clear the error
                    fieldsCheck=true;
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }*/

                if (!isValid(firstNameEdit.getText())) {
                    firstNameInput.setError(getString(R.string.error_first_name));
                    fieldsCheck=false;
                } else {
                    firstNameInput.setError(null); // Clear the error
                    fieldsCheck=true;
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
                if (!isValid(lastNameEdit.getText())) {
                    lastNameInput.setError(getString(R.string.error_last_name));
                    fieldsCheck=false;
                } else {
                    lastNameInput.setError(null);// Clear the error
                    fieldsCheck=true;
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
                if (!isValid(dateEdit.getText())) {
                    dateInput.setError(getString(R.string.error_last_name));
                    fieldsCheck=false;
                } else {
                    dateInput.setError(null);
                    fieldsCheck=true;// Clear the error
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
                if (!isValid(emailOrCellEdit.getText())) {
                    emailInput.setError(getString(R.string.error_last_name));
                    fieldsCheck=false;
                } else {
                    emailInput.setError(null);// Clear the error
                    fieldsCheck=true;
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }
                //Todo:fields controls

                if(fieldsCheck ==true){
                    registration(firstNameEdit.getText().toString(),lastNameEdit.getText().toString(),dateEdit.getText().toString(),mSex,passwordEditText.getText().toString(),emailOrCellEdit.getText().toString());
                }
            }
        });

        dateEdit.setOnClickListener(new View.OnClickListener(){

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
                String date=year+"-"+month+"-"+dayOfMonth;
                dateEdit.setText(date);
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
       firstNameEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isValid(firstNameEdit.getText())) {
                    firstNameInput.setError(null); //Clear the error
                }
                return false;
            }
        });


        return view;
    }

    private void registration(String first_name, String last_name, String date_of_birth,char sex,String password,String email_cellphone){
        Registration mRegistration=new Registration(first_name,last_name, date_of_birth,sex,email_cellphone,password);
        Call<User> call=userClient.registration(mRegistration);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),R.string.registration_succesful,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(),R.string.registration_unsuccesful,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(),R.string.registration_unsuccesful,Toast.LENGTH_SHORT).show();
            }
        });




    }

    private boolean isPasswordValid(@Nullable Editable text) {
        String mText=text.toString();
        return text != null && text.length() >= 8 && mText.matches(".*\\d+.*");
    }

    private boolean isValid(@Nullable Editable text) {

        return text.length() != 0;
    }

    public void setSex(char sex){
        mSex=sex;
    }


}
