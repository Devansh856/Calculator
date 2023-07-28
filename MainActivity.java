package com.example.calciapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    TextView orgdata, fakedata;

    boolean show=true;
    boolean f = false;
    Button zero, one, two, three, four, five, six, seven, eight, nine, ac, c, equal, mod, div, plus, minus, mul, dot, back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        orgdata = findViewById(R.id.textView5);
        fakedata = findViewById(R.id.at);
        zero = findViewById(R.id.d18);
        one = findViewById(R.id.d13);
        two = findViewById(R.id.d14);
        three = findViewById(R.id.d15);
        four = findViewById(R.id.d9);
        five = findViewById(R.id.d10);
        six = findViewById(R.id.d11);
        seven = findViewById(R.id.d5);
        eight = findViewById(R.id.d6);
        nine = findViewById(R.id.d7);
        one = findViewById(R.id.d13);
        //operators
        plus = findViewById(R.id.d16);
        minus = findViewById(R.id.d12);
        div = findViewById(R.id.d4);
        mul = findViewById(R.id.d8);
        mod = findViewById(R.id.d2);
        ac = findViewById(R.id.d17);
        c = findViewById(R.id.d1);
        back = findViewById(R.id.d3);
        dot = findViewById(R.id.d19);
        equal = findViewById(R.id.d20);


        List<Button> buttonList = new ArrayList<>();
        buttonList.add(zero);
        buttonList.add(one);
        buttonList.add(two);
        buttonList.add(three);
        buttonList.add(four);
        buttonList.add(five);
        buttonList.add(six);
        buttonList.add(seven);
        buttonList.add(eight);
        buttonList.add(nine);

        List<Button> b2 = new ArrayList<>();
        b2.add(plus);
        b2.add(minus);
        b2.add(mul);
        b2.add(div);
        b2.add(mod);

        for (Button b : buttonList) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                  //  orgdata.setText(orgdata.getText().toString() + b.getText().toString());
                    String st=orgdata.getText().toString();

                        orgdata.setText(st+b.getText().toString());
                        String data=orgdata.getText().toString();


                        char [] t=data.toCharArray();

                        if(t[t.length-1]>='0' &&t[t.length-1]<='9') {
                            double result = process(data);

                            String str=String.valueOf(BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP));
                            if(str.substring(str.length()-2).equals("00")){
                                str=str.substring(0,str.length()-3);
                            }

                           fakedata.setText("= "+str);


                        }



                }
            });
        }

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orgdata.setText("");
                fakedata.setText("");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = orgdata.getText().toString();

                if (data1.length() > 1) {
                    orgdata.setText(data1.substring(0, data1.length() - 1));
                  //  if(show) {
                        String data = orgdata.getText().toString();
                        char[] t = data.toCharArray();
                          double result=0;
                          if(data.equals("-")||data.equals(".")){
                              fakedata.setText("");
                          }
                       else if ((!(t[t.length - 1] >= '0' && t[t.length - 1] <= '9')) && ((t[0] >= '0' && t[0] <= '9')||t[0]=='-')) {
                            t[t.length - 1] = ' ';
                            String s = String.valueOf(t);//t.toString();
                             result = process(s);

                        } else if ((t[t.length - 1] >= '0' && t[t.length - 1] <= '9') && ((t[0] >= '0' && t[0] <= '9')||t[0]=='-')) {

                             result = process(data);


                        }
                    String str=String.valueOf(BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP));
                    if(str.substring(str.length()-2).equals("00")){
                        str=str.substring(0,str.length()-3);
                    }

                    fakedata.setText("= "+str);
                   // }
                }
              else{
                    orgdata.setText("");
                    fakedata.setText("");
                }

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orgdata.setText("");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = orgdata.getText().toString();
                char [] t=data.toCharArray();

                if (orgdata.getText().toString().equals("")||(f==false&& t[t.length-1]>='0'&&t[t.length-1]<='9' )) {
                    orgdata.setText(data + ".");
                    f=true;
                }
            }
        });


        for (Button b : b2) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!orgdata.getText().equals(".")&&b.getText().toString().equals("-")) {
                        orgdata.setText(orgdata.getText().toString() + b.getText().toString());
                    } else{
                        f = false;
                    if (orgdata.getText().toString().equals("") && !fakedata.getText().toString().equals("")) {
                        String data = fakedata.getText().toString().substring(2);
                        orgdata.setText(orgdata.getText().toString() + data + b.getText().toString());
                    } else if (!orgdata.getText().toString().equals("")) {
                        String data = orgdata.getText().toString();
                        char[] t = data.toCharArray();
                        if (t[t.length - 1] >= '0' && t[t.length - 1] <= '9') {
                            orgdata.setText(orgdata.getText().toString() + b.getText().toString());
                        }

                    }
                }
                }
            });
        }


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String data=fakedata.getText().toString();
               if(!data.equals("")) {
                   show =false;
               data=data.substring(2);

                   fakedata.setText("");
                   orgdata.setText(data);
               }
            }
        });


    }
    public  double process(String exp){
        Stack<Double> digits=new Stack<>();
        Stack<Character> op=new Stack<>();
        //String check="+-*/%";
        String exp1="0";
         if(exp.charAt(0)=='-'){
             exp1=exp1.concat(exp);
         }
         else
         {
             exp1=exp;
         }
        char [] temp=exp1.toCharArray();

        for(int i=0;i<temp.length;i++){
            StringBuilder abc=new StringBuilder("");
            if(temp[i]==' ')
                continue;

            if((temp[i]>='0'  && temp[i]<='9')|| temp[i]=='.')
            {

                while(i<temp.length&&((temp[i]>='0'  && temp[i]<='9')|| temp[i]=='.'))
                    abc.append(temp[i++]);

                digits.push(Double.parseDouble(abc.toString()));
                i--;
            }
            else{
                while(!op.isEmpty() && precedence(temp[i],op.peek())){
                    double a=digits.pop();
                    double b=digits.pop();

                    digits.push(operate(op.pop(),a,b));
                }

                op.push(temp[i]);
            }
        }

        while(!op.isEmpty())
            digits.push(operate(op.pop(),digits.pop(),digits.pop()));

        return digits.pop();
    }

     boolean precedence(Character op1,Character op2){
        if((op1=='*' || op1=='/' ||op1=='%') && (op2=='+' || op2=='-')  )
            return false;
        else
            return true;

    }
     double operate(Character op,double b,double a){
        if(op=='+')
            return a+b;
        else if(op=='-')
            return a-b;
        else if(op=='/')
            return a/b;
        else if(op=='*')
            return a*b;
        else return a%b;
    }


}