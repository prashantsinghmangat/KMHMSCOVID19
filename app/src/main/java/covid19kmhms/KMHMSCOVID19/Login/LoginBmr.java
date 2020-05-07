package covid19kmhms.KMHMSCOVID19.Login;

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

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);

        //spinner=(ProgressBar)findViewById(R.id.progressBar);
        //spinner.setVisibility(View.GONE);

        Info.setText("");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for loader circle
                //spinner.setVisibility(View.VISIBLE);

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
                        try{
                            JSONObject jsonObject = new JSONObject(jwtToken);
                            String token = jsonObject.getString("token");
                            sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("loginToken", token);
                            Log.e("inside try", token);
                            Intent intent = new Intent(LoginBmr.this, HomepageNav.class);
                            /*intent.putExtra("userName", userName);
                            intent.putExtra("password", password);*/
                            LoginBmr.this.startActivity(intent);

                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Wrong user ID or/and password entered", Toast.LENGTH_LONG).show();
                            Log.e("messge", "Wrong user ID or/and password entered");
                        }
                        /*try {
                            Log.e("try","inside LoginBmr try----------------------------------");
                            //Log.e("JWT Token", jwtToken);
                            JSONObject jsonObjectbject = new JSONObject(jwtToken);
                            String token = jsonObjectbject.getString("token");
                            Log.e("Token", token);

                            JSONObject decodedResult = new JSONObject(MHPFlow.decoded(jwtToken));
                            //Log.e("decoded String (result)", decodedResult.toString());
                            //Log.e("getAssociatedOrg()",mhpFlow.getAssociatedOrg(token, decodedResult.getString("sessionToken")).toString());
                            JSONArray jsonArray = mhpFlow.getAssociatedOrg(token, decodedResult.getString("sessionToken"));
                            //System.out.println(jsonArray.getJSONObject(jsonArray.length()-1));
                            int cnt = 0;
                            ArrayList<String> list = new ArrayList<String>();
                            list.add("MHE/OP*");
                            while(cnt < jsonArray.length()){
                                JSONObject mheObject = jsonArray.getJSONObject(cnt);
                                list.add(mheObject.getString("name"));
                                cnt++;
                            }
                            //Log.e("MHE list", list.toString());


                            Log.e("try", "outside LoginBmr try----------------------------------");
                            Intent intent = new Intent(LoginBmr.this, HomepageNav.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("password", password);
                            intent.putExtra("list", list);
                            LoginBmr.this.startActivity(intent);

                        }catch(Exception e){
                            e.printStackTrace();
                        }*/

                    }

                };
                new Thread(runnable).start();
            }
        });
    }

} //Login ends

