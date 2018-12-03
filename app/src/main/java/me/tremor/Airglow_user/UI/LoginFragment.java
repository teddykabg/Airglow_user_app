package me.tremor.Airglow_user.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import me.tremor.Airglow_user.MainActivity;
import me.tremor.Airglow_user.OnboardingActivity;
import me.tremor.Airglow_user.R;
import me.tremor.Airglow_user.models.Login;
import me.tremor.Airglow_user.models.User;
import me.tremor.Airglow_user.service.UserClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.Arrays;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Fragment representing the login screen.
 */
public class LoginFragment extends Fragment {
    private boolean isValidUsername;
    private boolean isValidPassword;
    private ProgressDialog mProgress;
    private static final String EMAIL = "email";
    Retrofit.Builder builder=new Retrofit.Builder().baseUrl("http://api.airglow.me:5000/v1/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit mRetrofit= builder.build();
    UserClient userClient= mRetrofit.create(UserClient.class);
    private CallbackManager mCallbackManager;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        Button nextButton = view.findViewById(R.id.next_button);
        Button signUpButton = view.findViewById(R.id.signup_button);
        LoginButton fbButton =view.findViewById(R.id.fb_login);
        isValidPassword=false;
        isValidUsername=false;



        fbButton = (LoginButton) view.findViewById(R.id.fb_login);
        mCallbackManager = CallbackManager.Factory.create();
        fbButton.setReadPermissions(Arrays.asList(EMAIL));

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

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isUsernameValid(usernameTextInput.getText())) {
                    usernameTextInput.setError(getString(R.string.error_username));
                    isValidUsername=false;
                }
                else{
                    usernameTextInput.setError(null);
                    isValidUsername=true;
                }
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.error_password));
                    isValidPassword=false;
                } else {
                    passwordTextInput.setError(null);// Clear the error
                    isValidPassword=true;
                    //((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false); // Navigate to the next Fragment
                }

                if(isValidPassword==true && isValidUsername==true) {
                    login(usernameTextInput.getText().toString(),passwordEditText.getText().toString());
                    if(mState==true) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        ((OnboardingActivity) getActivity()).startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(),"Non autorizzato",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        // Clear
      usernameTextInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isUsernameValid(usernameTextInput.getText())) {
                    usernameTextInput.setError(null);
                }
                return false;
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private boolean isLoggedIn() {
        return AccessToken.isCurrentAccessTokenActive()
                && !AccessToken.getCurrentAccessToken().getPermissions().isEmpty();
    }


    private void getFacebookData(JSONObject object) {

    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */
    private boolean isPasswordValid(@Nullable Editable text) {
        String mText=text.toString();
        return text != null /*&& text.length() >= 8&& mText.matches(".*\\d+.*"*)*/;
    }
    private boolean isUsernameValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
    private static String token;
    private static boolean mState=false;// stato login

    public void login(String mUsername,String mPassword){
        Login mLogin= new Login(mUsername,mPassword);
        Call<User> call= userClient.login(mLogin);


        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),R.string.login_succesful,Toast.LENGTH_SHORT).show();
                    token=response.body().getToken();
                    mState=true;

                }else{
                    Toast.makeText(getActivity(),R.string.login_unsuccesful,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(),"Login non funzia",Toast.LENGTH_SHORT).show();
            }
        });
    }

}