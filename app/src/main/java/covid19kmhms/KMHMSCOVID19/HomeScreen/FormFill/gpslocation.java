package covid19kmhms.KMHMSCOVID19.HomeScreen.FormFill;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import covid19kmhms.KMHMSCOVID19.R;

public class gpslocation extends AppCompatActivity {
    Button button;

    TextView btLocation;
    TextView textView1, textView2, textView3, textView4, textView5,textView6;
    EditText DateOfConsultation, ConsultationAddress, Pincode, Referral_By, IfOthersPleaseSpecify, IfOthersReasonForQuarantine,
            ComplaintsField, DurationComplaint, History, Illness_Summery, ICD_10_Code, MedicineDosageDuration, Remarks, Notes;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpslocation);

        button = (Button) findViewById(R.id.submitform);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gpslocation.this, SubmitActivity.class);
                startActivity(intent);

            }
        });

        btLocation = findViewById(R.id.bt_location);
        textView1 = findViewById(R.id.pincode);
        textView2 = findViewById(R.id.spinnerState);
        textView3 = findViewById(R.id.VillageTown);
        textView4 = findViewById(R.id.District);
        textView5 = findViewById(R.id.ConsultationAddress);
        textView6 = findViewById(R.id.Referral_By);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(gpslocation.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(gpslocation.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(gpslocation.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(), 1
                        );
                        //set Latitude
                        textView1.setText(Html.fromHtml(
                                "<font color='#6200EE'><b> </b><br></font>"
                                        + addresses.get(0).getLatitude()
                        ));
                        //set Longitude
                        textView2.setText(Html.fromHtml(
                                "<font color='#6200EE'><b> </b><br></font>"
                                        + addresses.get(0).getLongitude()
                        ));
                        //set Country
                        textView3.setText(Html.fromHtml(
                                "<font color='#6200EE'><b></b><br></font>"
                                        + addresses.get(0).getCountryName()
                        ));
                        //set Locality
                        textView4.setText(Html.fromHtml(
                                "<font color='#6200EE'><b></b><br></font>"
                                        + addresses.get(0).getLocality()
                        ));
                        //set address
                        textView5.setText(Html.fromHtml(
                                "<font color='#6200EE'><b></b><br></font>"
                                        + addresses.get(0).getAddressLine(0)
                        ));
                        textView6.setText(Html.fromHtml(
                                "<font color='#6200EE'><b></b><br></font>"
                                        + addresses.get(0).getPostalCode()
                        ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
