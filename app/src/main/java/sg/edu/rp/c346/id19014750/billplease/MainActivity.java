package sg.edu.rp.c346.id19014750.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Step 1: Declare the field variables
    EditText userAmount;
    EditText userPax;
    ToggleButton userSvs;
    ToggleButton userGst;
    EditText userDisc;
    RadioGroup rgPay;
    Button btnSplit;
    Button btnReset;
    TextView userTotal;
    TextView userPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Link the field variables to UI components in layout
        userAmount = findViewById(R.id.userAmount);
        userPax = findViewById(R.id.userPax);
        userSvs = findViewById(R.id.toggleBtnSvs);
        userGst = findViewById(R.id.toggleBtnGstEnabled);
        userDisc = findViewById(R.id.userDisc);
        rgPay = findViewById(R.id.payGroup);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);
        userTotal = findViewById(R.id.textTotal);
        userPay = findViewById(R.id.textSplit);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amt = Integer.parseInt(userAmount.getText().toString());
                int pax = Integer.parseInt(userPax.getText().toString());
                double total;
                double split;
                String textTotal;
                String textSplit;
                double disc = Double.parseDouble(userDisc.getText().toString());

                //Total (without Disc)
                if(userSvs.isChecked() && userGst.isChecked()){
                    total = amt + (amt * 0.10) + (amt * 0.07);
                }
                else if(userSvs.isChecked() && !userGst.isChecked()){
                    total = amt + (amt * 0.10);
                }
                else if(!userSvs.isChecked() && userGst.isChecked()){
                    total = amt + (amt * 0.07);
                }
                else{
                    total = amt;
                }

                //Discount
                if(userDisc.getText().toString().trim().length() != 0){

                    total = total - (total * (disc / 100));
                }

                //Split
                split = total / pax;

                textTotal = String.format("Total Bill: $%.2f", total);

                if(rgPay.getCheckedRadioButtonId() == R.id.payCash){
                    textSplit = String.format("Each Pays: %.2f in Cash", split);
                }
                else{
                    textSplit = String.format("Each Pays: %.2f via PayNow to 912345678", split);
                }

                userTotal.setText(textTotal);
                userPay.setText(textSplit);

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAmount.setText("");
                userPax.setText("");
                userSvs.setChecked(false);
                userGst.setChecked(false);
                userDisc.setText("");
            }
        });

    }
}