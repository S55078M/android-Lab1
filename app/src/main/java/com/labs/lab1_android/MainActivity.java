package com.labs.lab1_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    private Button DecodeButton;
    private EditText editText;
    private TextView textView;
    private Spinner spinner;

    public String calculate(int base,TextView txtView)
    {
        if(txtView.getText().toString().trim().length()==0)
            return "";

        try {
            Stack<Object> stack= new Stack<>();

            int number=Integer.parseInt(txtView.getText().toString());

            while (number>0) {
                int remainder=number%base;
                if(remainder<10) {
                    stack.push(remainder);
                } else {
                    switch (remainder) {
                        case 10:
                            stack.push("A");
                            break;
                        case 11:
                            stack.push("B");
                            break;
                        case 12:
                            stack.push("C");
                            break;
                        case 13:
                            stack.push("D");
                            break;
                        case 14:
                            stack.push("E");
                            break;
                        case 15:
                            stack.push("F");
                            break;
                    }
                }
                number/=base;
            }
            StringBuffer buffer=new StringBuffer();
            while (!stack.isEmpty()) {
                buffer.append(stack.pop().toString());
            }
            return buffer.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DecodeButton = findViewById(R.id.buttonDecode);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.textResult);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.base_num_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        DecodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int base = 2;
                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        base = 2;
                        break;
                    case 1:
                        base = 4;
                        break;
                    case 2:
                        base = 8;
                        break;
                    case 3:
                        base = 10;
                        break;
                    case 4:
                        base = 16;
                        break;
                }
                spinner.getSelectedItemPosition();
                textView.setText(calculate(base, editText));
            }
        });
    }
}
