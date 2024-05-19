package com.example.bandymas.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bandymas.MainActivity;
import com.example.bandymas.R;
import com.example.bandymas.home_page;
import com.example.bandymas.notifications_;
import com.example.bandymas.other_classes.sql_db;
import com.example.bandymas.registration_form;
import com.example.bandymas.ui.login.LoginViewModel;
import com.example.bandymas.ui.login.LoginViewModelFactory;
import com.example.bandymas.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;
    private sql_db db;
    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null)
        {
            Intent intent = new Intent(getApplicationContext(), home_page.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });
        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });
        //db = new sql_db(this);
        //--------------------------------SU FIREBASE:--------------------------------
        mAuth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                //loginViewModel.login(usernameEditText.getText().toString(),
                        //passwordEditText.getText().toString());
                String email_entered = usernameEditText.getText().toString();
                String passw_entered = passwordEditText.getText().toString();
                //boolean checking = db.checkPasswordEmail(passw_entered, email_entered);
                if (email_entered.equals("") || passw_entered.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Enter all fields",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                        signInUserFromDatabase(mAuth, email_entered, passw_entered);
                        Intent intent = new Intent(getApplicationContext(), home_page.class);
                        startActivity(intent);
                }

            }
        });
        //clickable text if a user forgot the password which takes to new password creation
        TextView click = findViewById(R.id.textView8);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, home_page.class);
                startActivity(intent);
                finish();
            }
        });
        //back
        ImageButton back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        //redirectina user i puslapi jeigu jis prisijunges:
        Intent intent = new Intent(LoginActivity.this, home_page.class);
        startActivity(intent);
        finish();
    }
    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
    //metodas su firebase, kad prijungtu vartotoja:
    private void signInUserFromDatabase(FirebaseAuth mAuth, String emaill, String usernamee)
    {
        mAuth.signInWithEmailAndPassword(emaill, usernamee)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Login succesful.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), home_page.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}