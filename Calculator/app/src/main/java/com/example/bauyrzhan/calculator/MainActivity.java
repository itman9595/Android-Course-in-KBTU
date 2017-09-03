package com.example.bauyrzhan.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText txtFieldResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtFieldResults = (EditText) findViewById(R.id.txtFieldResults);
    }

    boolean isEmpty = true;
    Character ch;

    public void buttonClick(View view) throws InterruptedException {

        if(txtFieldResults.getText().length() > 0) {
            isEmpty = false;
            ch = txtFieldResults.getText().toString().charAt(txtFieldResults.getText().length() - 1);
        }
        else {
            isEmpty = true;
        }

        switch (view.getId()) {
            case R.id.bnClickNumber0:
                txtFieldResults.append("0");
                break;
            case R.id.bnClickNumber1:
                txtFieldResults.append("1");
                break;
            case R.id.bnClickNumber2:
                txtFieldResults.append("2");
                break;
            case R.id.bnClickNumber3:
                txtFieldResults.append("3");
                break;
            case R.id.bnClickNumber4:
                txtFieldResults.append("4");
                break;
            case R.id.bnClickNumber5:
                txtFieldResults.append("5");
                break;
            case R.id.bnClickNumber6:
                txtFieldResults.append("6");
                break;
            case R.id.bnClickNumber7:
                txtFieldResults.append("7");
                break;
            case R.id.bnClickNumber8:
                txtFieldResults.append("8");
                break;
            case R.id.bnClickNumber9:
                txtFieldResults.append("9");
                break;
            case R.id.bnClickAddition: {
                if(!isEmpty) {
                    if (Character.isDigit(ch)) {
                        txtFieldResults.append("+");
                    } else if (ch != '+') {
                        final String replace = txtFieldResults.getText().toString().substring(0, txtFieldResults.getText().length() - 1) + '+';
                        txtFieldResults.setText(replace);
                        txtFieldResults.setSelection(txtFieldResults.length());
                    }
                }
            }
                break;
            case R.id.bnClickSubstract: {
                if(!isEmpty) {
                    if (Character.isDigit(ch)) {
                        txtFieldResults.append("-");
                    } else if (ch != '-') {
                        final String replace = txtFieldResults.getText().toString().substring(0, txtFieldResults.getText().length() - 1) + '-';
                        txtFieldResults.setText(replace);
                        txtFieldResults.setSelection(txtFieldResults.length());
                    }
                }
            }
                break;
            case R.id.bnClickMultiply:
                if(!isEmpty) {
                    if(Character.isDigit(ch)) {
                        txtFieldResults.append("*");
                    }
                    else if (ch != '*') {
                        final String replace = txtFieldResults.getText().toString().substring(0, txtFieldResults.getText().length() - 1) + '*';
                        txtFieldResults.setText(replace);
                        txtFieldResults.setSelection(txtFieldResults.length());
                    }
                }
                break;
            case R.id.bnClickDivision:
                if(!isEmpty) {
                    if (Character.isDigit(ch)) {
                        txtFieldResults.append("/");
                    } else if (ch != '/') {
                        final String replace = txtFieldResults.getText().toString().substring(0, txtFieldResults.getText().length() - 1) + '/';
                        txtFieldResults.setText(replace);
                        txtFieldResults.setSelection(txtFieldResults.length());
                    }
                }
                break;
            case R.id.bnClickEqual: {
                if(!isEmpty && Character.isDigit(ch)) {
                    double answer = 0;
                    String number = "";
                    char op = '.';

                    for(int i=0;i<txtFieldResults.length();i++) {
                        char charAtPos = txtFieldResults.getText().toString().charAt(i);
                        if(i == txtFieldResults.length()-1) {
                            number += charAtPos;
                            switch(op) {
                                case '+':
                                    answer += Double.parseDouble(number);
                                    break;
                                case '-':
                                    answer -= Double.parseDouble(number);
                                    break;
                                case '*':
                                    answer *= Double.parseDouble(number);
                                    break;
                                case '/':
                                    answer /= Double.parseDouble(number);
                                    break;
                                default:
                                    break;
                            }
                        }
                        else if(Character.isDigit(charAtPos)) {
                            number += charAtPos;
                        }
                        else {

                            if(op == '.') {
                                answer += Double.parseDouble(number);
                            }
                            else {
                                switch(op) {
                                    case '+':
                                        answer += Double.parseDouble(number);
                                        break;
                                    case '-':
                                        answer -= Double.parseDouble(number);
                                        break;
                                    case '*':
                                        answer *= Double.parseDouble(number);
                                        break;
                                    case '/':
                                        answer /= Double.parseDouble(number);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            number = "";
                            op = charAtPos;

                        }
                    }

//                    Log.i("Activity", "'"+String.valueOf(answer)+"'");
//                    Toast.makeText(this, String.valueOf(answer), Toast.LENGTH_SHORT).show();

                    if(op != '.') {
                        txtFieldResults.setText(String.valueOf(answer));
                    }

                }
            }
                break;
        }
    }

}
