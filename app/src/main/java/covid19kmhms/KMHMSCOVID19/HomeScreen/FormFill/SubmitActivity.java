package covid19kmhms.KMHMSCOVID19.HomeScreen.FormFill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import covid19kmhms.KMHMSCOVID19.HomeScreen.HomepageNav;
import covid19kmhms.KMHMSCOVID19.R;

public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_submit);
        Logo xyz = new Logo();
        xyz.start();

    }
    private class Logo extends Thread{
        public void run(){
            try{
                sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(SubmitActivity.this, HomepageNav.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            SubmitActivity.this.finish();

            //Intent intent = new Intent(FinalSubmit.this, HomepageNav.class);
            //startActivity(intent);
            //FinalSubmit.this.finish();
        }
    }
}
