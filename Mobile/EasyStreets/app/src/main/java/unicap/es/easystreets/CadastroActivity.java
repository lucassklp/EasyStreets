package unicap.es.easystreets;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import unicap.es.easystreets.model.User;
import unicap.es.easystreets.rest.RestRequest;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button b = (Button)findViewById(R.id.register_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button v = (Button) findViewById(R.id.voltar_button);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser()
    {
        validarDados();
        User u = new User();
        u.setEmail(((EditText)findViewById(R.id.email)).getText().toString());
        u.setName(((EditText)findViewById(R.id.name)).getText().toString());
        u.setPassword(((EditText)findViewById(R.id.password)).getText().toString());

        RestRequest<User, User> request = new RestRequest(User.class, User.class);
        request.setUrl(RestRequest.BASE_URL + "account/register");
        request.setMethod(Request.Method.POST);

        request.execute(this, u, response -> {
            Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
        }, err -> {
            Toast.makeText(this, "Erro ao adicionar o marker" + err.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isCampoVazio(String campo) {

        boolean resultado = (TextUtils.isEmpty(campo.trim()));
        return resultado;
    }


    private boolean isEmailValido(String email) {
        boolean resultado = (!isCampoVazio(email)) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return resultado;
    }

    private void validarDados() {
        boolean erro = false;
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        String msg = "";
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        String confirm_password = ((EditText)findViewById(R.id.confirm_password)).getText().toString();

        if (isCampoVazio(name)){
            erro = true;
            msg = "O campo nome está vazio";
        }
        else if(!isEmailValido(email))
        {
            erro = true;
            msg = "O campo e-mail está vazio ou não está em um formato valido";
        }
        else if (isCampoVazio(password))
        {
            erro = true;
            msg = "O campo senha está vazio";
        }
        else if (isCampoVazio(confirm_password))
        {
            erro = true;
            msg = "O campo confirma senha está vazio";
        }
        else if(!(password.equals(confirm_password)))
        {
                erro = true;
                msg = "As senhas sao diferentes";
        }

        if (erro) {
            dlg.setTitle("Erro");
            dlg.setMessage(msg);
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
