package xyz.appmaker.keralarescue;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.appmaker.keralarescue.Activities.FieldsActivity;
import xyz.appmaker.keralarescue.Models.UserLogin;
import xyz.appmaker.keralarescue.Models.UserResponse;
import xyz.appmaker.keralarescue.Tools.APIService;
import xyz.appmaker.keralarescue.Tools.PreferensHandler;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    PreferensHandler prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        prefs = new PreferensHandler(getApplicationContext());
        String userToken = prefs.getUserToken();

        if (!userToken.equals("")) {
            Intent fieldsAct = new Intent(MainActivity.this, FieldsActivity.class);
            startActivity(fieldsAct);
            finish();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.108:8000")

                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final APIService service = retrofit.create(APIService.class);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String message = "";
                if (username.equals("")) {
                    message = "Please enter Username\n";
                }
                if (password.equals("")) {
                    message += "Please enter password";
                }
                if (!message.equals("")) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    return;
                }
                UserLogin user = new UserLogin(username, password);
                Call<UserResponse> userResponseCall = service.login(user);
                userResponseCall.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            prefs.setUserToken(response.body().token);
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
                            Intent fieldsAct = new Intent(MainActivity.this, FieldsActivity.class);
                            startActivity(fieldsAct);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
