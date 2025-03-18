package prm392.fpt.edu.vn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import prm392.fpt.edu.vn.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;
    private Button registerSignUpButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userName = findViewById(R.id.register_name);
        userEmail = findViewById(R.id.register_email);
        userPassword = findViewById(R.id.register_password);
        registerSignUpButton = findViewById(R.id.login_button);

        mAuth = FirebaseAuth.getInstance();

        registerSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String pass = userPassword.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !pass.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Account successfully created",Toast.LENGTH_LONG).show();
                                Intent intentToHome = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(intentToHome);
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Failure: " + task.getException(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else
                    Toast.makeText(RegisterActivity.this, "Please fill empty field", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void signInToLogin(View view){
        Intent intentSignIntoLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intentSignIntoLogin);
    }

}