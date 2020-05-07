package covid19kmhms.KMHMSCOVID19.HomeScreen.FormFill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import covid19kmhms.KMHMSCOVID19.R;

public class MainForm extends AppCompatActivity {
    Button button;
    Spinner spinnerGender , spinnerState;

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
                String genderSelect = spinnerGender.getSelectedItem().toString();
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
                String StateSelect = spinnerState.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        visitGroup = (RadioGroup) findViewById(R.id.visitGroup);
        int visitSelect = visitGroup.getCheckedRadioButtonId();
        visitButton = (RadioButton) findViewById(visitSelect);
        visitButton_string = visitButton.getText().toString();

        TypeofVisitGroup = (RadioGroup) findViewById(R.id.TypeofVisitGroup);
        int TypeofVisitSelect = TypeofVisitGroup.getCheckedRadioButtonId();
        TypeofVisitButton = (RadioButton) findViewById(TypeofVisitSelect);
        TypeofVisitButton_string = TypeofVisitButton.getText().toString();

        IsPersonPositiveForCoronaGroup = (RadioGroup) findViewById(R.id.IsPersonPositiveForCoronaGroup);
        int IsPersonPositiveForCoronaSelect = IsPersonPositiveForCoronaGroup.getCheckedRadioButtonId();
        IsPersonPositiveForCoronaButton = (RadioButton) findViewById(IsPersonPositiveForCoronaSelect);
        IsPersonPositiveForCoronaButton_string = IsPersonPositiveForCoronaButton.getText().toString();

        IsPersonMigrantGroup = (RadioGroup) findViewById(R.id.IsPersonMigrantGroup);
        int IsPersonMigrantSelect = IsPersonMigrantGroup.getCheckedRadioButtonId();
        IsPersonPositiveForCoronaButton = (RadioButton) findViewById(IsPersonPositiveForCoronaSelect);
        IsPersonMigrantButton_string = IsPersonMigrantButton.getText().toString();

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

        Enter_ID_Number = findViewById(R.id.DateOfConsultation);
        Enter_ID_Number_string = Enter_ID_Number.getText().toString();

        Pincode = findViewById(R.id.DateOfConsultation);
        Pincode_string = Pincode.getText().toString();

        Referral_By = findViewById(R.id.DateOfConsultation);
        Referral_By_string = Referral_By.getText().toString();

        IfOthersPleaseSpecify = findViewById(R.id.DateOfConsultation);
        IfOthersPleaseSpecify_string = IfOthersPleaseSpecify.getText().toString();

        IfOthersReasonForQuarantine = findViewById(R.id.DateOfConsultation);
        IfOthersReasonForQuarantine_string = IfOthersReasonForQuarantine.getText().toString();

        ComplaintsField = findViewById(R.id.DateOfConsultation);
        ComplaintsField_string = ComplaintsField.getText().toString();

        DurationComplaint = findViewById(R.id.DateOfConsultation);
        DurationComplaint_string = DurationComplaint.getText().toString();

        History = findViewById(R.id.DateOfConsultation);
        History_string = History.getText().toString();

        Illness_Summery = findViewById(R.id.DateOfConsultation);
        Illness_Summery_string = Illness_Summery.getText().toString();

        ICD_10_Code = findViewById(R.id.DateOfConsultation);
        ICD_10_Code_string = ICD_10_Code.getText().toString();

        MedicineDosageDuration = findViewById(R.id.DateOfConsultation);
        MedicineDosageDuration_string = MedicineDosageDuration.getText().toString();

        Remarks = findViewById(R.id.DateOfConsultation);
        Remarks_string = Remarks.getText().toString();

        Notes = findViewById(R.id.DateOfConsultation);
        Notes_string = Notes.getText().toString();




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
