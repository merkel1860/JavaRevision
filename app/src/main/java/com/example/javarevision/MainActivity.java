package com.example.javarevision;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button addBtn, clearBtn, mixBtn;
    private EditText firstText, lastText, yearOfBirthText;
    private CheckBox genderCheckBoxMale, genderCheckBoxFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingUIWidgets();
    }

    private void bindingUIWidgets() {
        addBtn = findViewById(R.id.add_btn);
        clearBtn = findViewById(R.id.clear_btn);
        mixBtn = findViewById(R.id.mix_btn);

        firstText = findViewById(R.id.first_edit_text);
        lastText = findViewById(R.id.last_edit_text);
        genderCheckBoxFemale = findViewById(R.id.gender_female_chkbox);
        genderCheckBoxMale = findViewById(R.id.gender_male_chkbox);
        yearOfBirthText = findViewById(R.id.yob_edit_text);
    }

    private CheckBox readGenderCheckBox() {
        if(genderCheckBoxMale.isChecked()){
            return genderCheckBoxMale;
        }
        if(genderCheckBoxFemale.isChecked()){
            return genderCheckBoxFemale;
        }
        return null;
    }

    public void addForm(View view) {
        if(validatingFormEntriesCheck()){
            DAO.getInstance().getPersonList().add(new Person(firstText.getText().toString(),
                    lastText.getText().toString(),readGenderCheckBox().toString(),
                    convertEditTextToInteger(yearOfBirthText)));
        }
        popUpMessage(firstText, lastText);

    }

    private void popUpMessage(EditText editText1, EditText editText2) {
        Toast.makeText(this,"Welcome "+ editText1.getText().toString()+
                " "+editText2.getText().toString(),Toast.LENGTH_SHORT).show();
    }

    private int convertEditTextToInteger(EditText year) {
        return Integer.valueOf(year.getText().toString());
    }

    /*
    *   Data is valid if and only if there is at least either a firstname
    *   or a lastname otherwise validation is rejected
    * */
    private boolean validatingFormEntriesCheck() {
        return isValidatedData();
    }

    private boolean isValidatedData() {
        boolean validatedData = false;
        if(!firstText.getText().toString().isEmpty()){
            validatedData = true;
        }else if(!lastText.getText().toString().isEmpty()){
            validatedData = true;
        }else if(readGenderCheckBox() != null){
            validatedData = true;
        }
        return validatedData;
    }

    public void clearForm(View view) {
    }

    public void mixForm(View view) {
    }
}