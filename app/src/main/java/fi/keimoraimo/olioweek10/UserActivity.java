package fi.keimoraimo.olioweek10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private RadioGroup radioGroup;
    private Button addUser;

    private CheckBox checkBoxKandidaatti, checkBoxDiplomiInsinoori, checkBoxTekniikanTohtori, checkBoxUimaMaisteri;
    private ArrayList<String> degrees = new ArrayList<>();
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioGroup = findViewById(R.id.RadioGroup);
        addUser = findViewById(R.id.buttonAddUser);

        checkBoxKandidaatti = findViewById(R.id.checkBoxKandidaatti);
        checkBoxDiplomiInsinoori = findViewById(R.id.checkBoxDiplomiInsinoori);
        checkBoxTekniikanTohtori = findViewById(R.id.checkBoxTekniikanTohtori);
        checkBoxUimaMaisteri = findViewById(R.id.checkBoxUimaMaisteri);

        checkBoxKandidaatti.setOnCheckedChangeListener(this::onCheckedChanged);
        checkBoxDiplomiInsinoori.setOnCheckedChangeListener(this::onCheckedChanged);
        checkBoxTekniikanTohtori.setOnCheckedChangeListener(this::onCheckedChanged);
        checkBoxUimaMaisteri.setOnCheckedChangeListener(this::onCheckedChanged);

        context = this;
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String degree = "";

        switch (buttonView.getId()) {
            case R.id.checkBoxKandidaatti:
                degree = checkBoxKandidaatti.getText().toString();
                break;
            case R.id.checkBoxDiplomiInsinoori:
                degree = checkBoxDiplomiInsinoori.getText().toString();
                break;
            case R.id.checkBoxTekniikanTohtori:
                degree = checkBoxTekniikanTohtori.getText().toString();
                break;
            case R.id.checkBoxUimaMaisteri:
                degree = checkBoxUimaMaisteri.getText().toString();
            default:
                break;
        }

        if (isChecked) {
            degrees.add(degree);
        } else {
            degrees.remove(degree);
        }
    }

    public void addUser(View view){
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString();

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        String degreeProgram = radioButton.getText().toString();

        User user = new User(firstName, lastName, email, degreeProgram, degrees);
        UserStorage us = UserStorage.getInstance();
        us.addUser(user, context);

    }
}

