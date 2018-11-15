package tecnologica.produtos.mostra.iftm.culturaba.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tecnologica.produtos.mostra.iftm.culturaba.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText senha;
    private LinearLayout linearLayout;
    private ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linearLayout = findViewById(R.id.linear_login);
        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_senha);
        loading = findViewById(R.id.loading);
        mAuth = FirebaseAuth.getInstance();

    }

    public void login(View view) {
        loading.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            loading.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Falha ao fazer login, tente novamente! :(", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    }
                });
    }

    public void createAccount(View view) {
        loading.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loading.setVisibility(View.GONE);
                            FirebaseUser user = mAuth.getCurrentUser();
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Conta Criada com Sucesso! Faça o Login! :D", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else {
                            loading.setVisibility(View.GONE);
                            Snackbar snackbar = Snackbar
                                    .make(linearLayout, "Falha ao criar a conta, tente novamente! :(", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        loading.setVisibility(View.GONE);
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("USER", user);
        startActivity(it);
    }
}
