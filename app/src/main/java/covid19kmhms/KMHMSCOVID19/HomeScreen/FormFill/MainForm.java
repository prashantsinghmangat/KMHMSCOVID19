package covid19kmhms.KMHMSCOVID19.HomeScreen.FormFill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import covid19kmhms.KMHMSCOVID19.R;

public class MainForm extends AppCompatActivity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        button = (Button) findViewById(R.id.submitform);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainForm.this, SubmitActivity.class);
                startActivity(intent);

            }
        });


    }
}
