package me.tremor.Airglow_user.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.facebook.CallbackManager;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.q42.qlassified.Qlassified;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.Activities.OnboardingActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.Login;
import me.tremor.Airglow_user.models.User;
import me.tremor.Airglow_user.service.RetrofitClient;
import me.tremor.Airglow_user.vault.VaultLocator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Fragment representing the login screen.
 */
public class LoginFragment extends Fragment {
    private ProgressDialog mProgress;
    private static final String EMAIL = "email";
    private static final String PREF_SECRET = "token";
    private CallbackManager mCallbackManager;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }//Facebook Thing

    protected SharedPreferenceVault getVault() {
        return VaultLocator.getAutomaticallyKeyedVault();
    }

    protected void saveSecret(String Token) {
        String secretValue = Token;

        final SharedPreferenceVault sharedPreferenceVault = getVault();
        if (sharedPreferenceVault != null) {
                sharedPreferenceVault.edit().putString(PREF_SECRET, secretValue).apply();
        }
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText usernameEditInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);

        Button nextButton = view.findViewById(R.id.next_button);
        Button signUpButton = view.findViewById(R.id.signup_button);
        LoginButton fbButton =view.findViewById(R.id.fb_login);

        fbButton = (LoginButton) view.findViewById(R.id.fb_login);
        mCallbackManager = CallbackManager.Factory.create();
        fbButton.setReadPermissions(Arrays.asList(EMAIL));

        Qlassified.Service.start(view.getContext());


        fbButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(
                        getActivity(),
                        R.string.success,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(
                        getActivity(),
                        R.string.cancel,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(
                        getActivity(),
                        R.string.cancel,
                        Toast.LENGTH_LONG).show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    login(usernameEditInput.getText().toString(),passwordEditText.getText().toString());
                    if(mState==true) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        ((OnboardingActivity) getActivity()).startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(),"Non autorizzato",Toast.LENGTH_SHORT).show();
                    }
                }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }//Bindings + Qlassified start activity

    private static boolean mState=true;// Todo: Develop Login state in a better one

    public void login(String mUsername,String mPassword){
        Login mLogin= new Login(mUsername,mPassword);
        Call<User> call= RetrofitClient.getInsance().getApi().login(mLogin);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),R.string.login_succesful,Toast.LENGTH_SHORT).show();
                    saveSecret(response.body().getToken());
                    mState=true;

                }else{
                    Toast.makeText(getActivity(),R.string.login_unsuccesful,Toast.LENGTH_SHORT).show();
                }
            }//Get Token and then State=True

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(),"Login non funzia",Toast.LENGTH_SHORT).show();
            }//On failure does nothing only a Toast
        });
    }


}
