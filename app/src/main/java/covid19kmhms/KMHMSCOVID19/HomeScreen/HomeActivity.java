package covid19kmhms.KMHMSCOVID19.HomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import covid19kmhms.KMHMSCOVID19.HomeScreen.FormFill.MainForm;
import covid19kmhms.KMHMSCOVID19.R;

public class HomeActivity extends Fragment {

    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_homeactivity, container, false);


        button = (Button) view.findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainForm.class);
                startActivity(intent);

            }
        });
        return view;
    }
}