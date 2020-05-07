package covid19kmhms.KMHMSCOVID19.Login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import covid19kmhms.KMHMSCOVID19.HomeScreen.HomepageNav;
import covid19kmhms.KMHMSCOVID19.R;
import okhttp3.OkHttpClient;

public class LoginBmr extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private ProgressBar spinner;
    private TextView Info;
    private Button Login;
    private ImageView image;
    private int counter = 5;
    OkHttpClient client = new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bmr);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);


        Info.setText("");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);

                System.out.println("inside on click listener");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        final SharedPreferences sharedPreferences;
                        MHPFlow mhpFlow = new MHPFlow();
                        final String userName = ((EditText) findViewById(R.id.etName)).getText().toString();
                        final String password = ((EditText) findViewById(R.id.etPassword)).getText().toString();
                        System.out.println(userName+"     --------------------------     " +password);


                        String jwtToken = mhpFlow.login(userName, password);
                        if(jwtToken == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Invalid username and/or password entered", Toast.LENGTH_LONG).show();
                                }
                            });
                            Log.e("invalid input", "wrong id or/and password entered");
                        }
                        else {
                            try {
                                JSONObject jsonObject = new JSONObject(jwtToken);
                                String token = jsonObject.getString("token");
                                sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("loginToken", token);
                                Log.e("inside try", token);
                                Intent intent = new Intent(LoginBmr.this, HomepageNav.class);
                                LoginBmr.this.startActivity(intent);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Wrong user ID or/and password entered", Toast.LENGTH_LONG).show();
                                Log.e("messge", "Wrong user ID or/and password entered");
                            }
                        }

                    }

                };
                new Thread(runnable).start();
            }
        });
    }

} //Login ends

