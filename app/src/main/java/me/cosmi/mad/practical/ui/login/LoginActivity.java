package me.cosmi.mad.practical.ui.login;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import me.cosmi.mad.practical.R;
import me.cosmi.mad.practical.ui.listx.ListActivity;

public class LoginActivity extends AppCompatActivity {

    private final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);

        final Button btnLogin = this.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {
            this.database
                    .child("Users")
                    .child("mad")
                    .get()
                    .addOnSuccessListener(dataSnapshot -> {
                        final EditText etEmail = this.findViewById(R.id.etEmail);
                        final EditText etPassword = this.findViewById(R.id.etPassword);
                        final HashMap<String, String> value = (HashMap<String, String>) dataSnapshot.getValue();

                        final String email = etEmail.getText().toString();
                        final String password = etPassword.getText().toString();

                        if (email.equalsIgnoreCase(value.getOrDefault("username", ""))
                                && password.equals(value.getOrDefault("password", ""))) {

                            ListActivity.start(this);
                        } else Toast.makeText(this, "Email or Password invalid", Toast.LENGTH_SHORT).show();
                    });
        });
    }
}