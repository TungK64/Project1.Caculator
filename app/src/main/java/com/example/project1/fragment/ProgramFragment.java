package com.example.project1.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import com.example.project1.ITPProgram;
import com.example.project1.R;

public class ProgramFragment extends Fragment {

    private Button bta, btb, btc, btd, bte, btf, btleft, btright, btopen, btclose, btreturn, btdel;
    private Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btchange, btdot;
    private Button btmod, btmulti, btdevide, btadd, btminus, btequal;
    private TextView bintv, octtv, dectv, hextv, binview, octview, decview, hexview, mainview, subview;
    private LinearLayout binl, octl, decl, hexl;
    private String system = "dec";
    private String temp;
    private boolean checkOpr = false;
    private boolean checkMath = false;
    private boolean checkOut = true;
    public static final char CHAR_55 = 55;
    private long first, second, res;
    private String operation = "";

    void addClickEffect(View view) // Tạo hiệu ứng khi click
    {
        Drawable drawableNormal = view.getBackground();

        Drawable drawablePressed = view.getBackground().getConstantState().newDrawable();
        drawablePressed.mutate();
        drawablePressed.setColorFilter(Color.argb(50, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);

        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[] {android.R.attr.state_pressed}, drawablePressed);
        listDrawable.addState(new int[] {}, drawableNormal);
        view.setBackground(listDrawable);
    }

    public String convertDEC(long n, int b) {
        StringBuilder sb = new StringBuilder();
        long m;
        long remainder = n;

        while (remainder > 0) {
            if (b > 10) {
                m = remainder % b;
                if (m >= 10) {
                    sb.append((char) (CHAR_55 + m));
                } else {
                    sb.append(m);
                }
            } else {
                sb.append(remainder % b);
            }
            remainder = remainder / b;
        }
        return sb.reverse().toString();
    }

    // Doi so nhi phan sang thap phan
    public String BinToDec(long binaryNumber){
        int decimal = 0;
        int p = 0;
        while(true){
            if(binaryNumber == 0){
                break;
            } else {
                long temp = binaryNumber%10;
                decimal += temp*Math.pow(2, p);
                binaryNumber = binaryNumber/10;
                p++;
            }
        }
        String t = String.valueOf(decimal);
        return t;
    }

    // Doi so bat phan sang thap phan
    public String OctToDec(long OctNum){
        long num = 0;
        int p = 0;
        while(true){
            if(OctNum == 0){
                break;
            } else {
                long temp = OctNum % 10;
                num += temp * Math.pow(8, p);
                OctNum = OctNum / 10;
                p++;
            }
        }
        String t = String.valueOf(num);
        return t;
    }

    // Doi so thap luc phan sang thap phan
    public String HexToDec(String HexNum) {
        long num = 0;
        int p = 0;
        int tmp;
        int lens = HexNum.length();
        for(int i = lens - 1; i >= 0; i--) {
            if(HexNum.charAt(i) == 'A') tmp = 10;
            else if(HexNum.charAt(i) == 'B') tmp = 11;
            else if(HexNum.charAt(i) == 'C') tmp = 12;
            else if(HexNum.charAt(i) == 'D') tmp = 13;
            else if(HexNum.charAt(i) == 'E') tmp = 14;
            else if(HexNum.charAt(i) == 'F') tmp = 15;
            else tmp = Integer.parseInt(String.valueOf(HexNum.charAt(i)));
            num += tmp * Math.pow(16, p);
            p++;
        }
        String t = String.valueOf(num);
        return t;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program, container, false);

        bta = view.findViewById(R.id.btA);
        btb = view.findViewById(R.id.btB);
        btc = view.findViewById(R.id.btC);
        btd = view.findViewById(R.id.btD);
        bte = view.findViewById(R.id.btE);
        btf = view.findViewById(R.id.btF);
        btleft = view.findViewById(R.id.btLeft);
        btright = view.findViewById(R.id.btRight);
        btopen = view.findViewById(R.id.btOpen);
        btclose = view.findViewById(R.id.btClose);
        btreturn = view.findViewById(R.id.btReturn);
        btdel = view.findViewById(R.id.btDel);
        bt0 = view.findViewById(R.id.bt0);
        bt1 = view.findViewById(R.id.bt1);
        bt2 = view.findViewById(R.id.bt2);
        bt3 = view.findViewById(R.id.bt3);
        bt4 = view.findViewById(R.id.bt4);
        bt5 = view.findViewById(R.id.bt5);
        bt6 = view.findViewById(R.id.bt6);
        bt7 = view.findViewById(R.id.bt7);
        bt8 = view.findViewById(R.id.bt8);
        bt9 = view.findViewById(R.id.bt9);
        btchange = view.findViewById(R.id.btChange);
        btdot = view.findViewById(R.id.buttonDot);
        btmod = view.findViewById(R.id.btMod);
        btmulti = view.findViewById(R.id.btMulti);
        btdevide = view.findViewById(R.id.btDevide);
        btadd = view.findViewById(R.id.btAdd);
        btminus = view.findViewById(R.id.btMinus);
        btequal = view.findViewById(R.id.btEqual);

        bintv = view.findViewById(R.id.BINtv);
        octtv = view.findViewById(R.id.OCTtv);
        dectv = view.findViewById(R.id.DECtv);
        hextv = view.findViewById(R.id.HEXtv);
        binview = view.findViewById(R.id.BINview);
        octview = view.findViewById(R.id.OCTview);
        decview = view.findViewById(R.id.DECview);
        hexview = view.findViewById(R.id.HEXview);
        mainview = view.findViewById(R.id.mainview);
        subview = view.findViewById(R.id.subview);

        binl = view.findViewById(R.id.BINlayout);
        octl = view.findViewById(R.id.OCTlayout);
        decl = view.findViewById(R.id.DEClayout);
        hexl = view.findViewById(R.id.HEXlayout);

        addClickEffect(bt0);
        addClickEffect(bt1);
        addClickEffect(bt2);
        addClickEffect(bt3);
        addClickEffect(bt4);
        addClickEffect(bt5);
        addClickEffect(bt6);
        addClickEffect(bt7);
        addClickEffect(bt8);
        addClickEffect(bt9);
        addClickEffect(bta);
        addClickEffect(btb);
        addClickEffect(btc);
        addClickEffect(btd);
        addClickEffect(btf);
        addClickEffect(btreturn);
        addClickEffect(btleft);
        addClickEffect(btright);
        addClickEffect(btadd);
        addClickEffect(btminus);
        addClickEffect(btmulti);
        addClickEffect(btdevide);
        addClickEffect(btmod);
        addClickEffect(btequal);
        addClickEffect(bintv);
        addClickEffect(binview);
        addClickEffect(octtv);
        addClickEffect(octview);
        addClickEffect(dectv);
        addClickEffect(decview);
        addClickEffect(hextv);
        addClickEffect(hexview);

        bta.setEnabled(false);
        btb.setEnabled(false);
        btc.setEnabled(false);
        btd.setEnabled(false);
        bte.setEnabled(false);
        btf.setEnabled(false);
        btchange.setEnabled(false);
        btdot.setEnabled(false);


        bintv.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "bin") {
                    mainview.setText(binview.getText().toString());
                    system = "bin";
                    bt2.setEnabled(false);
                    bt3.setEnabled(false);
                    bt4.setEnabled(false);
                    bt5.setEnabled(false);
                    bt6.setEnabled(false);
                    bt7.setEnabled(false);
                    bt8.setEnabled(false);
                    bt9.setEnabled(false);
                    bta.setEnabled(false);
                    btb.setEnabled(false);
                    btc.setEnabled(false);
                    btd.setEnabled(false);
                    bte.setEnabled(false);
                    btf.setEnabled(false);
                }
            }
        });
        binview.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "bin") {
                    mainview.setText(binview.getText().toString());
                    system = "bin";
                    bt2.setEnabled(false);
                    bt3.setEnabled(false);
                    bt4.setEnabled(false);
                    bt5.setEnabled(false);
                    bt6.setEnabled(false);
                    bt7.setEnabled(false);
                    bt8.setEnabled(false);
                    bt9.setEnabled(false);
                    bta.setEnabled(false);
                    btb.setEnabled(false);
                    btc.setEnabled(false);
                    btd.setEnabled(false);
                    bte.setEnabled(false);
                    btf.setEnabled(false);
                }
            }
        });
        octtv.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "oct") {
                    mainview.setText(octview.getText().toString());
                    system = "oct";
                    bt2.setEnabled(true);
                    bt3.setEnabled(true);
                    bt4.setEnabled(true);
                    bt5.setEnabled(true);
                    bt6.setEnabled(true);
                    bt7.setEnabled(true);
                    bt8.setEnabled(false);
                    bt9.setEnabled(false);
                    bta.setEnabled(false);
                    btb.setEnabled(false);
                    btc.setEnabled(false);
                    btd.setEnabled(false);
                    bte.setEnabled(false);
                    btf.setEnabled(false);
                }
            }
        });
        octview.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "oct") {
                    mainview.setText(octview.getText().toString());
                    system = "oct";
                    bt2.setEnabled(true);
                    bt3.setEnabled(true);
                    bt4.setEnabled(true);
                    bt5.setEnabled(true);
                    bt6.setEnabled(true);
                    bt7.setEnabled(true);
                    bt8.setEnabled(false);
                    bt9.setEnabled(false);
                    bta.setEnabled(false);
                    btb.setEnabled(false);
                    btc.setEnabled(false);
                    btd.setEnabled(false);
                    bte.setEnabled(false);
                    btf.setEnabled(false);
                }
            }
        });
        dectv.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "dec") {
                    mainview.setText(decview.getText().toString());
                    system = "dec";
                    bt2.setEnabled(true);
                    bt3.setEnabled(true);
                    bt4.setEnabled(true);
                    bt5.setEnabled(true);
                    bt6.setEnabled(true);
                    bt7.setEnabled(true);
                    bt8.setEnabled(true);
                    bt9.setEnabled(true);
                    bta.setEnabled(false);
                    btb.setEnabled(false);
                    btc.setEnabled(false);
                    btd.setEnabled(false);
                    bte.setEnabled(false);
                    btf.setEnabled(false);
                }
            }
        });
        decview.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "dec") {
                    mainview.setText(decview.getText().toString());
                    system = "dec";
                    bt2.setEnabled(true);
                    bt3.setEnabled(true);
                    bt4.setEnabled(true);
                    bt5.setEnabled(true);
                    bt6.setEnabled(true);
                    bt7.setEnabled(true);
                    bt8.setEnabled(true);
                    bt9.setEnabled(true);
                    bta.setEnabled(false);
                    btb.setEnabled(false);
                    btc.setEnabled(false);
                    btd.setEnabled(false);
                    bte.setEnabled(false);
                    btf.setEnabled(false);
                }
            }
        });
        hextv.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "hex") {
                    mainview.setText(hexview.getText().toString());
                    system = "hex";
                    bt2.setEnabled(true);
                    bt3.setEnabled(true);
                    bt4.setEnabled(true);
                    bt5.setEnabled(true);
                    bt6.setEnabled(true);
                    bt7.setEnabled(true);
                    bt8.setEnabled(true);
                    bt9.setEnabled(true);
                    bta.setEnabled(true);
                    btb.setEnabled(true);
                    btc.setEnabled(true);
                    btd.setEnabled(true);
                    bte.setEnabled(true);
                    btf.setEnabled(true);
                }
            }
        });
        hexview.setOnClickListener(v -> {
            if(!checkOpr) {
                if (system != "hex") {
                    mainview.setText(hexview.getText().toString());
                    system = "hex";
                    bt2.setEnabled(true);
                    bt3.setEnabled(true);
                    bt4.setEnabled(true);
                    bt5.setEnabled(true);
                    bt6.setEnabled(true);
                    bt7.setEnabled(true);
                    bt8.setEnabled(true);
                    bt9.setEnabled(true);
                    bta.setEnabled(true);
                    btb.setEnabled(true);
                    btc.setEnabled(true);
                    btd.setEnabled(true);
                    bte.setEnabled(true);
                    btf.setEnabled(true);
                }
            }
        });

        btreturn.setOnClickListener(v -> {
            mainview.setText("");
            subview.setText("");
            binview.setText("");
            octview.setText("");
            decview.setText("");
            hexview.setText("");
            temp = " ";
            checkOut = true;
            checkOpr = false;
            checkMath = false;
            first = 0;
            second = 0;
            operation = "";
        });

        bt0.setOnClickListener(v -> {
            if(!checkMath) {
                temp = mainview.getText().toString();
                mainview.setText(temp + "0");
                checkOut = false;
            }

                if (system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Long.parseLong(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if (system == "bin") {
                    temp = mainview.getText().toString();
                    binview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(BinToDec(x));
                    long y = Long.parseLong(BinToDec(x));
                    octview.setText(convertDEC(y, 8));
                    hexview.setText(convertDEC(y, 16));
                }

                if (system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if (system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt1.setOnClickListener(v -> {
            if(!checkMath) {
                temp = mainview.getText().toString();
                mainview.setText(temp + "1");
                checkOut = false;
            }
                if (system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if (system == "bin") {
                    temp = mainview.getText().toString();
                    binview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(BinToDec(x));
                    long y = Long.parseLong(BinToDec(x));
                    octview.setText(convertDEC(y, 8));
                    hexview.setText(convertDEC(y, 16));
                }

                if (system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if (system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt2.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "2");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt3.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "3");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt4.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "4");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt5.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "5");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt6.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "6");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt7.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "7");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "oct") {
                    temp = mainview.getText().toString();
                    octview.setText(temp);
                    long x = Long.parseLong(temp);
                    decview.setText(OctToDec(x));
                    long y = Long.parseLong(OctToDec(x));
                    binview.setText(convertDEC(y, 2));
                    hexview.setText(convertDEC(y, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt8.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin" && system != "oct") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "8");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    convertDEC(x, 2);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bt9.setOnClickListener(v -> {
            if(!checkMath) {
                if (system != "bin" && system != "oct") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "9");
                    checkOut = false;
                }
            }
                if(system == "dec") {
                    temp = mainview.getText().toString();
                    decview.setText(temp);
                    long x = Integer.parseInt(temp);
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }

                if(system == "hex") {
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
        });

        bta.setOnClickListener(v -> {
            if(!checkMath) {
                if (system == "hex") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "A");
                    checkOut = false;
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                    }
                }
        });

        btb.setOnClickListener(v -> {
            if(!checkMath) {
                if (system == "hex") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "B");
                    checkOut = false;
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
            }
        });

        btc.setOnClickListener(v -> {
            if(!checkMath) {
                if (system == "hex") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "C");
                    checkOut = false;
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
            }
        });

        btd.setOnClickListener(v -> {
            if(!checkMath) {
                if (system == "hex") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "D");
                    checkOut = false;
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
            }
        });

        bte.setOnClickListener(v -> {
            if(!checkMath) {
                if (system == "hex") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "E");
                    checkOut = false;
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
            }
        });

        btf.setOnClickListener(v -> {
            if(!checkMath) {
                if (system == "hex") {
                    temp = mainview.getText().toString();
                    mainview.setText(temp + "F");
                    checkOut = false;
                    temp = mainview.getText().toString();
                    hexview.setText(temp);
                    decview.setText(HexToDec(temp));
                    long y = Long.parseLong(HexToDec(temp));
                    binview.setText(convertDEC(y, 2));
                    octview.setText(convertDEC(y, 8));
                }
            }
        });

        // Các phím chức năng
        btdel.setOnClickListener(v -> {
            if(!checkMath) {
                String tmp = mainview.getText().toString();
                if(tmp.length() > 1) {
                    tmp = tmp.substring(0, tmp.length() - 1);
                    mainview.setText(tmp + "");
                    if(system == "dec") {
                        temp = mainview.getText().toString();
                        decview.setText(temp);
                        long x = Long.parseLong(temp);
                        binview.setText(convertDEC(x, 2));
                        octview.setText(convertDEC(x, 8));
                        hexview.setText(convertDEC(x, 16));
                    }

                    if(system == "bin") {
                        temp = mainview.getText().toString();
                        binview.setText(temp);
                        long x = Long.parseLong(temp);
                        decview.setText(BinToDec(x));
                        long y = Long.parseLong(BinToDec(x));
                        octview.setText(convertDEC(y, 8));
                        hexview.setText(convertDEC(y, 16));
                    }

                    if(system == "oct") {
                        temp = mainview.getText().toString();
                        octview.setText(temp);
                        long x = Long.parseLong(temp);
                        decview.setText(OctToDec(x));
                        long y = Long.parseLong(OctToDec(x));
                        binview.setText(convertDEC(y, 2));
                        hexview.setText(convertDEC(y, 16));
                    }

                    if(system == "hex") {
                        temp = mainview.getText().toString();
                        hexview.setText(temp);
                        decview.setText(HexToDec(temp));
                        long y = Long.parseLong(HexToDec(temp));
                        binview.setText(convertDEC(y, 2));
                        octview.setText(convertDEC(y, 8));
                    }
                } else {
                    mainview.setText("");
                    binview.setText("");
                    octview.setText("");
                    decview.setText("");
                    hexview.setText("");
                }

            }
        });

        btadd.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkOpr) {
                    temp = subview.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    if(system == "bin") {
                        first = Long.parseLong(BinToDec(Long.parseLong(temp)));
                        second = Long.parseLong(BinToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "oct") {
                        first = Long.parseLong(OctToDec(Long.parseLong(temp)));
                        second = Long.parseLong(OctToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "dec") {
                        first = Long.parseLong(temp);
                        second = Long.parseLong(mainview.getText().toString());
                    }
                    if(system == "hex") {
                        first = Long.parseLong(HexToDec(temp));
                        second = Long.parseLong(HexToDec(mainview.getText().toString()));
                    }
                    if (operation == "-") {
                        res = first - second;
                    } else if (operation == "x") {
                        res = first * second;
                    } else if (operation == ":") {
                        res = first / second;
                    } else if (operation == "%") {
                        res = first % second;
                    } else {
                        res = first + second;
                    }
                    if(system == "bin") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 2));
                    }
                    if(system == "oct") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 8));
                    }
                    if(system == "dec") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                    }
                    if(system == "hex") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        String tmp = convertDEC(res, 16);
                        subview.setText(tmp + " + ");
                        first = Long.parseLong(HexToDec(tmp));
                    }
                    if(system != "hex") {
                        first = res;
                        subview.setText(res + " + ");
                    }
                    checkOut = false;
                } else {
                    if(system == "hex") {
                        String tmp = mainview.getText().toString();
                        subview.setText(tmp + " + ");
                        first = Long.parseLong(HexToDec(tmp));
                    } else {
                        first = Long.parseLong(mainview.getText().toString());
                        subview.setText(first + " + ");
                    }
                }

                mainview.setText("");
                checkOut = true;
                checkOpr = true;
                checkMath = false;
                operation = "+";
                temp = "";
            }
        });

        btminus.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkOpr) {
                    temp = subview.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    if(system == "bin") {
                        first = Long.parseLong(BinToDec(Long.parseLong(temp)));
                        second = Long.parseLong(BinToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "oct") {
                        first = Long.parseLong(OctToDec(Long.parseLong(temp)));
                        second = Long.parseLong(OctToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "dec") {
                        first = Long.parseLong(temp);
                        second = Long.parseLong(mainview.getText().toString());
                    }
                    if(system == "hex") {
                        first = Long.parseLong(HexToDec(temp));
                        second = Long.parseLong(HexToDec(mainview.getText().toString()));
                    }
                    if (operation == "-") {
                        res = first - second;
                    } else if (operation == "x") {
                        res = first * second;
                    } else if (operation == ":") {
                        res = first / second;
                    } else if (operation == "%") {
                        res = first % second;
                    } else {
                        res = first + second;
                    }
                    if(system == "bin") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 2));
                    }
                    if(system == "oct") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 8));
                    }
                    if(system == "dec") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                    }
                    if(system == "hex") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        String tmp = convertDEC(res, 16);
                        subview.setText(tmp + " - ");
                        first = Long.parseLong(HexToDec(tmp));
                    }
                    if(system != "hex") {
                        first = res;
                        subview.setText(res + " - ");
                    }
                    checkOut = false;
                } else {
                    if(system == "hex") {
                        String tmp = mainview.getText().toString();
                        subview.setText(tmp + " - ");
                        first = Long.parseLong(HexToDec(tmp));
                    } else {
                        first = Long.parseLong(mainview.getText().toString());
                        subview.setText(first + " - ");
                    }
                }

                mainview.setText("");
                checkOut = true;
                checkOpr = true;
                checkMath = false;
                operation = "-";
                temp = "";
            }
        });

        btmulti.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkOpr) {
                    temp = subview.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    if(system == "bin") {
                        first = Long.parseLong(BinToDec(Long.parseLong(temp)));
                        second = Long.parseLong(BinToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "oct") {
                        first = Long.parseLong(OctToDec(Long.parseLong(temp)));
                        second = Long.parseLong(OctToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "dec") {
                        first = Long.parseLong(temp);
                        second = Long.parseLong(mainview.getText().toString());
                    }
                    if(system == "hex") {
                        first = Long.parseLong(HexToDec(temp));
                        second = Long.parseLong(HexToDec(mainview.getText().toString()));
                    }
                    if (operation == "-") {
                        res = first - second;
                    } else if (operation == "x") {
                        res = first * second;
                    } else if (operation == ":") {
                        res = first / second;
                    } else if (operation == "%") {
                        res = first % second;
                    } else {
                        res = first + second;
                    }
                    if(system == "bin") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 2));
                    }
                    if(system == "oct") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 8));
                    }
                    if(system == "dec") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                    }
                    if(system == "hex") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        String tmp = convertDEC(res, 16);
                        subview.setText(tmp + " x ");
                        first = Long.parseLong(HexToDec(tmp));
                    }
                    if(system != "hex") {
                        first = res;
                        subview.setText(res + " x ");
                    }
                    checkOut = false;
                } else {
                    if(system == "hex") {
                        String tmp = mainview.getText().toString();
                        subview.setText(tmp + " x ");
                        first = Long.parseLong(HexToDec(tmp));
                    } else {
                        first = Long.parseLong(mainview.getText().toString());
                        subview.setText(first + " x ");
                    }
                }

                mainview.setText("");
                checkOut = true;
                checkOpr = true;
                checkMath = false;
                operation = "x";
                temp = "";
            }
        });

        btdevide.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkOpr) {
                    temp = subview.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    if(system == "bin") {
                        first = Long.parseLong(BinToDec(Long.parseLong(temp)));
                        second = Long.parseLong(BinToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "oct") {
                        first = Long.parseLong(OctToDec(Long.parseLong(temp)));
                        second = Long.parseLong(OctToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "dec") {
                        first = Long.parseLong(temp);
                        second = Long.parseLong(mainview.getText().toString());
                    }
                    if(system == "hex") {
                        first = Long.parseLong(HexToDec(temp));
                        second = Long.parseLong(HexToDec(mainview.getText().toString()));
                    }
                    if (operation == "-") {
                        res = first - second;
                    } else if (operation == "x") {
                        res = first * second;
                    } else if (operation == ":") {
                        res = first / second;
                    } else if (operation == "%") {
                        res = first % second;
                    } else {
                        res = first + second;
                    }
                    if(system == "bin") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 2));
                    }
                    if(system == "oct") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 8));
                    }
                    if(system == "dec") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                    }
                    if(system == "hex") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        String tmp = convertDEC(res, 16);
                        subview.setText(tmp + " : ");
                        first = Long.parseLong(HexToDec(tmp));
                    }
                    if(system != "hex") {
                        first = res;
                        subview.setText(res + " : ");
                    }
                    checkOut = false;
                } else {
                    if(system == "hex") {
                        String tmp = mainview.getText().toString();
                        subview.setText(tmp + " : ");
                        first = Long.parseLong(HexToDec(tmp));
                    } else {
                        first = Long.parseLong(mainview.getText().toString());
                        subview.setText(first + " : ");
                    }
                }

                mainview.setText("");
                checkOut = true;
                checkOpr = true;
                checkMath = false;
                operation = ":";
                temp = "";
            }
        });

        btmod.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkOpr) {
                    temp = subview.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    if(system == "bin") {
                        first = Long.parseLong(BinToDec(Long.parseLong(temp)));
                        second = Long.parseLong(BinToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "oct") {
                        first = Long.parseLong(OctToDec(Long.parseLong(temp)));
                        second = Long.parseLong(OctToDec(Long.parseLong(mainview.getText().toString())));
                    }
                    if(system == "dec") {
                        first = Long.parseLong(temp);
                        second = Long.parseLong(mainview.getText().toString());
                    }
                    if(system == "hex") {
                        first = Long.parseLong(HexToDec(temp));
                        second = Long.parseLong(HexToDec(mainview.getText().toString()));
                    }
                    if (operation == "-") {
                        res = first - second;
                    } else if (operation == "x") {
                        res = first * second;
                    } else if (operation == ":") {
                        res = first / second;
                    } else if (operation == "%") {
                        res = first % second;
                    } else {
                        res = first + second;
                    }
                    if(system == "bin") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 2));
                    }
                    if(system == "oct") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        res = Long.parseLong(convertDEC(res, 8));
                    }
                    if(system == "dec") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                    }
                    if(system == "hex") {
                        decview.setText(res + "");
                        binview.setText(convertDEC(res, 2));
                        octview.setText(convertDEC(res, 8));
                        hexview.setText(convertDEC(res, 16));
                        String tmp = convertDEC(res, 16);
                        subview.setText(tmp + " % ");
                        first = Long.parseLong(HexToDec(tmp));
                    }
                    if(system != "hex") {
                        first = res;
                        subview.setText(res + " % ");
                    }
                    checkOut = false;
                } else {
                    if(system == "hex") {
                        String tmp = mainview.getText().toString();
                        subview.setText(tmp + " % ");
                        first = Long.parseLong(HexToDec(tmp));
                    } else {
                        first = Long.parseLong(mainview.getText().toString());
                        subview.setText(first + " % ");
                    }
                }

                mainview.setText("");
                checkOut = true;
                checkOpr = true;
                checkMath = false;
                operation = "%";
                temp = "";
            }
        });

        btleft.setOnClickListener(v -> {
            if(system == "bin") {
                if (!checkOut) {
                    String tmp = mainview.getText().toString();
                    long x = Long.parseLong(BinToDec(Long.parseLong(tmp)));
                    x = x * 2;
                    tmp = convertDEC(x, 2);
                    if (checkMath) {
                        subview.setText(convertDEC(x / 2, 2) + " Lsh 1");
                    }
                    mainview.setText(convertDEC(x, 2));
                    decview.setText(x + "");
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }
            }
        });

        btright.setOnClickListener(v -> {
            if(system == "bin") {
                if (!checkOut) {
                    String tmp = mainview.getText().toString();
                    long x = Long.parseLong(BinToDec(Long.parseLong(tmp)));
                    x = x / 2;
                    tmp = convertDEC(x, 2);
                    if (checkMath) {
                        subview.setText(convertDEC(x * 2, 2) + " Rsh 1");
                    }
                    mainview.setText(convertDEC(x, 2));
                    decview.setText(x + "");
                    binview.setText(convertDEC(x, 2));
                    octview.setText(convertDEC(x, 8));
                    hexview.setText(convertDEC(x, 16));
                }
            }
        });

        btequal.setOnClickListener(v -> {
            if(checkMath || (checkOut && !checkOpr)) {
                String tmp = mainview.getText().toString();
                subview.setText(tmp);
            }
            if(!checkMath && !checkOut) {
                subview.setText(subview.getText().toString() + mainview.getText().toString());
                if(system == "bin") {
                    first = Long.parseLong(BinToDec(first));
                    second = Long.parseLong(BinToDec(Long.parseLong(mainview.getText().toString())));
                }
                if(system == "oct") {
                    first = Long.parseLong(OctToDec(first));
                    second = Long.parseLong(OctToDec(Long.parseLong(mainview.getText().toString())));
                }
                if(system == "dec") {
                    second = Long.parseLong(mainview.getText().toString());
                }
                if(system == "hex") {
                    second = Long.parseLong(HexToDec(mainview.getText().toString()));
                }
                if(operation == "+") {
                    res = first + second;
                }
                if(operation == "-") {
                    res = first - second;
                }
                if(operation == "x") {
                    res = first * second;
                }
                if(operation == ":") {
                    res = first / second;
                }
                if(operation == "%") {
                    res = first % second;
                }
                if(system == "bin") {
                    decview.setText(res + "");
                    binview.setText(convertDEC(res, 2));
                    octview.setText(convertDEC(res, 8));
                    hexview.setText(convertDEC(res, 16));
                    mainview.setText(convertDEC(res, 2));
                }

                if(system == "oct") {
                    decview.setText(res + "");
                    binview.setText(convertDEC(res, 2));
                    octview.setText(convertDEC(res, 8));
                    hexview.setText(convertDEC(res, 16));
                    mainview.setText(convertDEC(res, 8));
                }

                if(system == "dec") {
                    decview.setText(res + "");
                    binview.setText(convertDEC(res, 2));
                    octview.setText(convertDEC(res, 8));
                    hexview.setText(convertDEC(res, 16));
                    mainview.setText(res + "");
                }

                if(system == "hex") {
                    decview.setText(res + "");
                    binview.setText(convertDEC(res, 2));
                    octview.setText(convertDEC(res, 8));
                    hexview.setText(convertDEC(res, 16));
                    mainview.setText(convertDEC(res, 16));
                }

                checkOpr = false;
                checkMath = true;
            }
            operation = "";
        });

        return view;
    }
}
