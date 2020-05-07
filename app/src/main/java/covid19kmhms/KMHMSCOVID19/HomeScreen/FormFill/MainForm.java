package covid19kmhms.KMHMSCOVID19.HomeScreen.FormFill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import covid19kmhms.KMHMSCOVID19.R;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainForm extends AppCompatActivity {
    private String result;
    private boolean flag = true;
    Button button;
    Spinner spinnerGender , spinnerState;
    String genderSelect;
    String StateSelect;


    RadioGroup visitGroup,TypeofVisitGroup,IsPersonPositiveForCoronaGroup,IsPersonMigrantGroup,ReasonForQuarantineGroup,
            TypeOfConsultationGroup;

    RadioButton visitButton,TypeofVisitButton,IsPersonPositiveForCoronaButton,IsPersonMigrantButton,ReasonForQuarantineButton,
            TypeOfConsultationButton;

    EditText DateOfConsultation, Enter_ID_Number, Pincode, Referral_By, IfOthersPleaseSpecify, IfOthersReasonForQuarantine,
            ComplaintsField, DurationComplaint, History, Illness_Summery, ICD_10_Code, MedicineDosageDuration, Remarks, Notes;

    String DateOfConsultation_string, Enter_ID_Number_string, Pincode_string, Referral_By_string, IfOthersPleaseSpecify_string,
            IfOthersReasonForQuarantine_string, ComplaintsField_string, DurationComplaint_string, History_string,
            Illness_Summery_string, ICD_10_Code_string, MedicineDosageDuration_string, Remarks_string, Notes_string;

    String visitButton_string,TypeofVisitButton_string,IsPersonPositiveForCoronaButton_string,IsPersonMigrantButton_string,
            ReasonForQuarantineButton_string, TypeOfConsultationButton_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        spinnerGender = findViewById(R.id.spinnerGender);
        final List<String> gender = new ArrayList<>();
        gender.add(0,"Select Gender");
        gender.add("Male"); gender.add("Female"); gender.add("Others");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(dataAdapter);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String genderSelect = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), genderSelect, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerState = findViewById(R.id.spinnerState);
        ArrayAdapter<CharSequence> Stateadapter = ArrayAdapter.createFromResource(this, R.array.state,
                android.R.layout.simple_spinner_item);
        Stateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(Stateadapter);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button = (Button) findViewById(R.id.submitform);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                visitGroup = (RadioGroup) findViewById(R.id.visitGroup);
                int visitSelect = visitGroup.getCheckedRadioButtonId();
                visitButton = (RadioButton) findViewById(visitSelect);
                visitButton_string = visitButton.getText().toString();
                //visitButton_string = "visit";

                TypeofVisitGroup = (RadioGroup) findViewById(R.id.TypeofVisitGroup);
                int TypeofVisitSelect = TypeofVisitGroup.getCheckedRadioButtonId();
                TypeofVisitButton = (RadioButton) findViewById(TypeofVisitSelect);
                TypeofVisitButton_string = TypeofVisitButton.getText().toString();

                IsPersonPositiveForCoronaGroup = (RadioGroup) findViewById(R.id.IsPersonPositiveForCoronaGroup);
                int IsPersonPositiveForCoronaSelect = IsPersonPositiveForCoronaGroup.getCheckedRadioButtonId();
                IsPersonPositiveForCoronaButton = (RadioButton) findViewById(IsPersonPositiveForCoronaSelect);
                IsPersonPositiveForCoronaButton_string = IsPersonPositiveForCoronaButton.getText().toString();

                /*IsPersonMigrantGroup = (RadioGroup) findViewById(R.id.IsPersonMigrantGroup);
                int IsPersonMigrantSelect = IsPersonMigrantGroup.getCheckedRadioButtonId();
                IsPersonPositiveForCoronaButton = (RadioButton) findViewById(IsPersonPositiveForCoronaSelect);
                IsPersonMigrantButton_string = IsPersonMigrantButton.getText().toString();*/

                ReasonForQuarantineGroup = (RadioGroup) findViewById(R.id.ReasonForQuarantine);
                int ReasonForQuarantineSelect = ReasonForQuarantineGroup.getCheckedRadioButtonId();
                ReasonForQuarantineButton = (RadioButton) findViewById(ReasonForQuarantineSelect);
                ReasonForQuarantineButton_string = ReasonForQuarantineButton.getText().toString();

                TypeOfConsultationGroup = (RadioGroup) findViewById(R.id.TypeOfConsultationGroup);
                int TypeOfConsultationSelect = TypeOfConsultationGroup.getCheckedRadioButtonId();
                TypeOfConsultationButton = (RadioButton) findViewById(TypeOfConsultationSelect);
                TypeOfConsultationButton_string = TypeOfConsultationButton.getText().toString();


                DateOfConsultation = findViewById(R.id.DateOfConsultation);
                DateOfConsultation_string = DateOfConsultation.getText().toString();

                Enter_ID_Number = findViewById(R.id.Enter_ID_Number);
                Enter_ID_Number_string = Enter_ID_Number.getText().toString();

                Pincode = findViewById(R.id.pincode);
                Pincode_string = Pincode.getText().toString();

                Referral_By = findViewById(R.id.Referral_By);
                Referral_By_string = Referral_By.getText().toString();

                IfOthersPleaseSpecify = findViewById(R.id.IfOthersPleaseSpecify);
                IfOthersPleaseSpecify_string = IfOthersPleaseSpecify.getText().toString();

                IfOthersReasonForQuarantine = findViewById(R.id.IfOthersReasonForQuarantine);
                IfOthersReasonForQuarantine_string = IfOthersReasonForQuarantine.getText().toString();

                ComplaintsField = findViewById(R.id.ComplaintsField);
                ComplaintsField_string = ComplaintsField.getText().toString();

                DurationComplaint = findViewById(R.id.DurationComplaint);
                DurationComplaint_string = DurationComplaint.getText().toString();

                History = findViewById(R.id.History);
                History_string = History.getText().toString();

                Illness_Summery = findViewById(R.id.Illness_Summery);
                Illness_Summery_string = Illness_Summery.getText().toString();

                ICD_10_Code = findViewById(R.id.ICD_10_Code);
                ICD_10_Code_string = ICD_10_Code.getText().toString();

                MedicineDosageDuration = findViewById(R.id.MedicineDosageDuration);
                MedicineDosageDuration_string = MedicineDosageDuration.getText().toString();

                Remarks = findViewById(R.id.Remarks);
                Remarks_string = Remarks.getText().toString();

                Notes = findViewById(R.id.Notes);
                Notes_string = Notes.getText().toString();

                StateSelect = spinnerState.getSelectedItem().toString();
                genderSelect = spinnerGender.getSelectedItem().toString();


                Thread thread = new Thread() {
                    public void run() {
                        System.out.println("inside TestAPI");
                        String url = "http://13.126.27.50/MHMS_DEV/rest/addRecord";
                        OkHttpClient client = new OkHttpClient();
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        final String loginToken = sharedPreferences.getString("loginToken", "");
                        //final String loginToken = sharedPreferences.getString("loginToken", "");
                        //final String loginToken = "eyJEZXZlbG9wZWQgQnkiOiJlLUhlYWx0aCBSZXNlYXJjaCBDZW50ZXIsIElJSVQgQmFuZ2Fsb3JlIiwiSG9zdCI6Ikthcm5hdGFrYSBNZW50YWwgSGVhbHRoIE1hbmFnZW1lbnQgU3lzdGVtIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJwcm9mZXNzaW9uIjoiTUhNU1BzeWNoaWF0cmlzdCIsInN1YiI6Ik1ITVMgU2VjdXJpdHkgVG9rZW4iLCJsYXN0TG9naW5PcmdJZCI6ImEyMWI4ODVlLTJmM2EtNDQyNS04YjViLTBkMjc0YjQyYWYyNiIsInNlc3Npb25FbmRUaW1lIjoxNTg4ODgwMjk1LCJpc3MiOiJLTUhNUyIsInNlc3Npb25TdGFydFRpbWUiOjE1ODg4MzcwOTUsInNlc3Npb25JZCI6Ijk2OGY0MzYxLWJhMzYtNGU2ZC05MzY5LTJkMmM0NGIxNzk5ZSIsInVzZXJOYW1lIjoicHJhc2hhbnQiLCJvcmdVVUlEIjoiNGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyIiwibmJmIjoxNTg4ODM3MDk1LCJvcmdSb2xlIjoiTUhQcm9mZXNzaW9uYWwiLCJzZXNzaW9uVG9rZW4iOiJTZXNzaW9uSWQ6MTcyLjMxLjUuMTMjcHJhc2hhbnQ6NGNjNzQyODAtZWZlNS00MDE2LWI0MWUtZjI5NDcyYTRlYzEyOk1ITVM6TUhQcm9mZXNzaW9uYWwjMTU4ODgzNzA5NDYzNyMtMTM4NTg0MDk5MyM1OTkiLCJwZXJzb25JZCI6IjkyNWQ2N2NkLTdkM2MtNDA3OC04OWZiLTY5NjNjNDdiNDk2YSIsInVzZXJVVUlEIjoiNzc1YjhjM2UtNjc0Mi00YjMwLWI0NDMtYzdkNmFhNmVjNGFjIiwiZXhwIjoxNTg4ODczMDk1LCJpYXQiOjE1ODg4MzcwOTV9.ryoLG7fWO3TvuWFXdQF2eJRZ_DqxwXMeD_B4cLm_3PY";
                        JSONObject diagnosis = new JSONObject();
                        final MediaType JSON
                                = MediaType.parse("application/json; charset=utf-8");
                        try {
                            diagnosis.put("diagnosisType", "dty");
                            diagnosis.put("icdcode", ICD_10_Code_string);
                            diagnosis.put("icddescription", "icddesc");
                        } catch (
                                Exception e) {
                            e.printStackTrace();
                        }

                        JSONObject payload = new JSONObject();
                        try {
                            payload.put("dateOfConsultation", DateOfConsultation_string);
                            payload.put("visitType", TypeofVisitButton_string);
                            payload.put("visit", visitButton_string);
                            payload.put("addressLine1", Enter_ID_Number_string);
                            payload.put("district", "dist");
                            payload.put("city", "city");
                            payload.put("state", StateSelect);
                            payload.put("pincode", Pincode_string);
                            payload.put("age", Referral_By_string);
                            payload.put("gender", genderSelect);
                            payload.put("typeOfConsultation", "typecon");
                            payload.put("coronaPositive", "No");
                            payload.put("migrant", "migr");
                            payload.put("quarantineReason", ReasonForQuarantineButton_string);
                            payload.put("complaint", ComplaintsField_string);
                            payload.put("history", History_string);
                            payload.put("illnessSummary", Illness_Summery_string);
                            payload.put("prescription", MedicineDosageDuration_string);
                            payload.put("notes", Notes_string);
                            payload.put("userUuid", "uuid");
                            payload.put("latitude", "32435");
                            payload.put("longitude", "45");
                            payload.put("orgUuid", "org456");
                            JSONArray arr = new JSONArray();
                            arr.put(0, diagnosis);
                            payload.put("diagnosis", arr);

                        } catch (
                                Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println(payload.toString());

                        RequestBody formBody = RequestBody.create(JSON, payload.toString());

                        Request request = new Request.Builder()
                                .url(url)
                                .post(formBody)
                                .addHeader("Authorization", "Bearer " + loginToken)
                                .addHeader("Content-Type", "application/json")
                                .build();

                        Response response = null;

                        try {
                            response = client.newCall(request).execute();

                            if(response.code() != 200) {
                                flag = false;
                                Log.e("error code", String.valueOf(response.code()));
                            }

                            ResponseBody rb = response.body();
                            result = rb.string();
                            System.out.println("##############"+result);
                            if(result.contains("record stored successfully")) {
                                Intent intent = new Intent(MainForm.this, SubmitActivity.class);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                };thread.start();

                try {
                    thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(flag == false){
                    System.out.println("False value aaya hai");
                    Toast.makeText(getApplicationContext(),"Error occurred while saving data", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
