package sg.edu.rp.c346.id19022397.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editText);
        etGPA = findViewById(R.id.editText2);
        rgGender = findViewById(R.id.radioGroup);
        btnSave = findViewById(R.id.button);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // step 1a : get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();

        float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGenderId = rgGender.getCheckedRadioButtonId();

        //step 1b : obtain an instance of the SP
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //step 1c : obtain an instance of the sp editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        //step 1d : add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putInt("genderId", intGenderId);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //step 2a : obtain an instance of the sp
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //step 2b : retrieve the saved data from the sp object
        String strName = prefs.getString("name", "John");
        float gpa = prefs.getFloat("gpa", 0);
        int intGenderId = prefs.getInt("genderId", R.id.radioButton);

        //step 2c : update the UI element with the value
        etName.setText(strName);
        etGPA.setText(gpa + "");
        rgGender.check(intGenderId);
    }
}
