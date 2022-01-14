package com.example.project1.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project1.R;

public class HomeFragment extends Fragment {

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

    public boolean isDoubleInt(double d)
    {
        double TOLERANCE = 1E-5;
        return Math.abs(Math.floor(d) - d) < TOLERANCE;
    }




    private Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btdot;
    private Button equal, add, sub, multi, devine, mod, c, ce, del;
    private Button changeOpr, mc, mr, madd, mminus, m, ms;
    private ImageButton reverse, square, unit;
    private TextView mainscreen, subscreen;
    private String temp, opertion;
    private boolean checkOut = true;
    private boolean checkDot = false;
    private boolean checkMath = false;
    private boolean checkOpr = false;
    private boolean checkClick = false;
    private double first, second, result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bt0 = (Button) view.findViewById(R.id.So0);
        bt1 = (Button) view.findViewById(R.id.So1);
        bt2 = (Button) view.findViewById(R.id.So2);
        bt3 = (Button) view.findViewById(R.id.So3);
        bt4 = (Button) view.findViewById(R.id.So4);
        bt5 = (Button) view.findViewById(R.id.So5);
        bt6 = (Button) view.findViewById(R.id.So6);
        bt7 = (Button) view.findViewById(R.id.So7);
        bt8 = (Button) view.findViewById(R.id.So8);
        bt9 = (Button) view.findViewById(R.id.So9);
        btdot = (Button) view.findViewById(R.id.Dot);
        equal = (Button) view.findViewById(R.id.Equal);
        add = (Button) view.findViewById(R.id.Add);
        sub = (Button) view.findViewById(R.id.Minus);
        multi = (Button) view.findViewById(R.id.Multi);
        devine = (Button) view.findViewById(R.id.devine);
        mod = (Button) view.findViewById(R.id.Mod);
        c = (Button) view.findViewById(R.id.C);
        ce = (Button) view.findViewById(R.id.CE);
        del = (Button) view.findViewById(R.id.Delete);
        mainscreen = (TextView) view.findViewById(R.id.calcDisplay);
        subscreen = (TextView) view.findViewById(R.id.subDisplay);
        changeOpr = (Button) view.findViewById(R.id.daoDau);
        reverse = (ImageButton) view.findViewById(R.id.daoNguoc);
        square = (ImageButton) view.findViewById(R.id.Square);
        unit = (ImageButton) view.findViewById(R.id.unit);
        m = (Button) view.findViewById(R.id.M);
        mc = (Button) view.findViewById(R.id.MC);
        mr = (Button) view.findViewById(R.id.MR);
        madd = (Button) view.findViewById(R.id.mAdd);
        mminus = (Button) view.findViewById(R.id.mMinus);

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
        addClickEffect(btdot);
        addClickEffect(equal);
        addClickEffect(add);
        addClickEffect(sub);
        addClickEffect(devine);
        addClickEffect(mod);
        addClickEffect(multi);
        addClickEffect(c);
        addClickEffect(ce);
        addClickEffect(del);
        addClickEffect(changeOpr);
        addClickEffect(reverse);
        addClickEffect(square);
        addClickEffect(unit);

        c.setOnClickListener(v -> {
            subscreen.setText("");
            mainscreen.setText("");
            temp = "";
            opertion = "";
            first = 0;
            second = 0;
            checkDot = false;
            checkMath = false;
            checkClick = false;
            checkOut = true;
            checkOpr = false;
        });

        // Delete mainscreen
        ce.setOnClickListener(v -> {
            if(!checkMath) {
                mainscreen.setText("");
                checkOut = true;
            }
        });

        del.setOnClickListener(v -> {
            if(!checkMath) {
                temp = mainscreen.getText().toString();
                if(!temp.isEmpty()) {
                    temp = temp.substring(0, temp.length() - 1);
                    mainscreen.setText(temp + "");
                }
            }
        });

        // Number button
        bt0.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "0");
                checkOut = false;
            }
        });
        bt1.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "1");
                checkOut = false;
            }
        });
        bt2.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "2");
                checkOut = false;
            }
        });
        bt3.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "3");
                checkOut = false;
            }
        });
        bt4.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "4");
                checkOut = false;
            }
        });
        bt5.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "5");
                checkOut = false;
            }
        });
        bt6.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "6");
                checkOut = false;
            }
        });
        bt7.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "7");
                checkOut = false;
            }
        });
        bt8.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "8");
                checkOut = false;
            }
        });
        bt9.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = mainscreen.getText().toString();
                mainscreen.setText(temp + "9");
                checkOut = false;
            }
        });
        btdot.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                if(!checkDot) {
                    temp = mainscreen.getText().toString();
                    mainscreen.setText(temp + ".");
                    checkDot = true;
                }
            }
        });

        // Math button
        add.setOnClickListener(v -> {
            if(!checkOut) {
                if (checkOpr) {
                    temp = subscreen.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    first = Double.parseDouble(temp);
                    second = Double.parseDouble(mainscreen.getText().toString());
                    if (opertion == "-") {
                        result = first - second;
                    } else if (opertion == "x") {
                        result = first * second;
                    } else if (opertion == ":") {
                        result = first / second;
                    } else if (opertion == "%") {
                        result = first % second;
                    } else {
                        result = first + second;
                    }
                    first = result;
                    if(isDoubleInt(result)) {
                        int res = (int) result;
                        subscreen.setText(res + " + ");
                    } else {
                        subscreen.setText(result + " + ");
                    }
                    checkOpr = false;
                } else {
                    first = Double.parseDouble(mainscreen.getText() + "");
                    subscreen.setText(mainscreen.getText().toString() + "  +  ");
                }

                mainscreen.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOpr = true;
                checkOut = true;
                temp = "";
                opertion = "+";
            }
        });

        sub.setOnClickListener(v -> {
            if(!checkOut) {
                if (checkOpr) {
                    temp = subscreen.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    first = Double.parseDouble(temp);
                    second = Double.parseDouble(mainscreen.getText().toString());
                    if (opertion == "-") {
                        result = first - second;
                    } else if (opertion == "x") {
                        result = first * second;
                    } else if (opertion == ":") {
                        result = first / second;
                    } else if (opertion == "%") {
                        result = first % second;
                    } else {
                        result = first + second;
                    }
                    first = result;
                    if(isDoubleInt(result)) {
                        int res = (int) result;
                        subscreen.setText(res + " - ");
                    } else {
                        subscreen.setText(result + " - ");
                    }
                    checkOpr = false;
                } else {
                    first = Double.parseDouble(mainscreen.getText() + "");
                    subscreen.setText(mainscreen.getText().toString() + "  -  ");
                }

                mainscreen.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
                checkOpr = true;
                temp = "";
                opertion = "-";
            }
        });

        multi.setOnClickListener(v -> {
            if(!checkOut) {
                if (checkOpr) {
                    temp = subscreen.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    first = Double.parseDouble(temp);
                    second = Double.parseDouble(mainscreen.getText().toString());
                    if (opertion == "-") {
                        result = first - second;
                    } else if (opertion == "x") {
                        result = first * second;
                    } else if (opertion == ":") {
                        result = first / second;
                    } else if (opertion == "%") {
                        result = first % second;
                    } else {
                        result = first + second;
                    }
                    first = result;
                    if(isDoubleInt(result)) {
                        int res = (int) result;
                        subscreen.setText(res + " x ");
                    } else {
                        subscreen.setText(result + " x ");
                    }
                    checkOpr = false;
                } else {
                    first = Double.parseDouble(mainscreen.getText() + "");
                    subscreen.setText(mainscreen.getText().toString() + "  x  ");
                }

                mainscreen.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOpr = true;
                checkOut = true;
                temp = "";
                opertion = "x";
            }
        });

        devine.setOnClickListener(v -> {
            if(!checkOut) {
                if (checkOpr) {
                    temp = subscreen.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    first = Double.parseDouble(temp);
                    second = Double.parseDouble(mainscreen.getText().toString());
                    if (opertion == "-") {
                        result = first - second;
                    } else if (opertion == "x") {
                        result = first * second;
                    } else if (opertion == ":") {
                        result = first / second;
                    } else if (opertion == "%") {
                        result = first % second;
                    } else {
                        result = first + second;
                    }
                    first = result;
                    if(isDoubleInt(result)) {
                        int res = (int) result;
                        subscreen.setText(res + " : ");
                    } else {
                        subscreen.setText(result + " : ");
                    }
                    checkOpr = false;
                } else {
                    first = Double.parseDouble(mainscreen.getText() + "");
                    subscreen.setText(mainscreen.getText().toString() + "  :  ");
                }

                mainscreen.setText("");
                checkMath = false;
                checkClick = false;
                checkDot = false;
                checkOpr = true;
                checkOut = true;
                temp = "";
                opertion = ":";
            }
        });

        mod.setOnClickListener(v -> {
            if(!checkOut) {
                if (checkOpr) {
                    temp = subscreen.getText().toString();
                    temp = temp.substring(0, temp.length() - 3);
                    first = Double.parseDouble(temp);
                    second = Double.parseDouble(mainscreen.getText().toString());
                    if (opertion == "-") {
                        result = first - second;
                    } else if (opertion == "x") {
                        result = first * second;
                    } else if (opertion == ":") {
                        result = first / second;
                    } else if (opertion == "%") {
                        result = first % second;
                    } else {
                        result = first + second;
                    }
                    first = result;
                    subscreen.setText(result + " % ");
                    checkOpr = false;
                } else {
                    first = Double.parseDouble(mainscreen.getText() + "");
                    subscreen.setText(mainscreen.getText().toString() + "  %  ");
                }

                mainscreen.setText("");
                checkMath = false;
                checkDot = false;
                checkOpr = true;
                checkOut = true;
                checkClick = false;
                temp = "";
                opertion = "%";
            }
        });

        changeOpr.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                temp = mainscreen.getText().toString();
                t = Double.parseDouble(temp);
                if (!checkMath) {
                    if (t < 0) {
                        t = Math.abs(t);
                        if(isDoubleInt(t)) {
                            int a = (int) t;
                            mainscreen.setText(a + "");
                        } else {
                            mainscreen.setText(t + "");
                        }
                    } else {
                        if(isDoubleInt(t)) {
                            int a = (int) t;
                            mainscreen.setText("-" + a + "");
                        } else {
                            mainscreen.setText("-" + t + "");
                        }
                    }
                    checkClick = true;
                    checkOut = false;
                } else {
                    if(isDoubleInt(t)) {
                        int a = (int) t;
                        subscreen.setText("negate(" + a + ")");
                    } else {
                        subscreen.setText("negate(" + t + ")");
                    }
                    if (t < 0) {
                        t = Math.abs(t);
                        if(isDoubleInt(t)) {
                            int a = (int) t;
                            mainscreen.setText(a + "");
                        } else {
                            mainscreen.setText(t + "");
                        }
                    } else {
                        if(isDoubleInt(t)) {
                            int a = (int) t;
                            mainscreen.setText("-" + a + "");
                        } else {
                            mainscreen.setText("-" + t + "");
                        }
                    }
                    checkClick = true;
                    checkOut = false;
                }
            }
        });

        reverse.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                temp = mainscreen.getText().toString();
                t = Double.parseDouble(temp);
                if (t != 0) {
                    if (!checkMath) {
                        t = 1 / t;
                        mainscreen.setText(t + "");
                        checkClick = true;
                        checkOut = false;
                    } else {
                        subscreen.setText("1/(" + t + ")");
                        mainscreen.setText(1 / t + "");
                        checkClick = true;
                        checkOut = false;
                    }
                }
            }
        });

        square.setOnClickListener(v -> {
            if(!checkOut) {
                Double t;
                temp = mainscreen.getText().toString();
                t = Double.parseDouble(temp);
                if (!checkMath) {
                    t = t * t;
                    if(isDoubleInt(t)) {
                        long a = Math.round(t);
                        mainscreen.setText(a+"");
                    } else {
                        mainscreen.setText(t + "");
                    }
                    checkClick = true;
                    checkOut = false;
                } else {
                    if(isDoubleInt(t)) {
                        long a = Math.round(t);
                        subscreen.setText("sqr(" + a + ")");
                        mainscreen.setText(a * a +"");
                    } else {
                        subscreen.setText("sqr(" + t + ")");
                        mainscreen.setText(t * t + "");
                    }
                    checkClick = true;
                    checkOut = false;
                }
            }
        });

        unit.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                temp = mainscreen.getText().toString();
                t = Double.parseDouble(temp);
                if (t >= 0) {
                    if (!checkMath) {
                        t = Math.sqrt(t);
                        if(isDoubleInt(t)) {
                            int a = (int) t;
                            mainscreen.setText(a + "");
                        } else {
                            mainscreen.setText((t + ""));
                        }
                        checkClick = true;
                        checkOut = false;
                    } else {
                        if(isDoubleInt(t)) {
                            int a = (int) t;
                            subscreen.setText("√(" + a + ")");
                            mainscreen.setText((int)Math.sqrt(a) + "");
                        } else {
                            subscreen.setText("√(" + t + ")");
                            mainscreen.setText(Math.sqrt(t) + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    }
                }
            }
        });

        // Ket qua
        equal.setOnClickListener(v -> {
            if(checkMath) {
                temp = mainscreen.getText().toString();
                subscreen.setText(temp + "");
                mainscreen.setText(temp + "");
            }
            if(!checkMath && !checkOut) {
                checkDot = false;
                checkClick = false;
                checkOpr = false;
                temp = mainscreen.getText().toString();
                second = Double.parseDouble(mainscreen.getText().toString());
                subscreen.setText(subscreen.getText().toString()+mainscreen.getText().toString()+ "");
                mainscreen.setText("");
                if(opertion == "+") {
                    result = first + second;
                }
                if(opertion == "-") {
                    result = first - second;
                }
                if(opertion == "x") {
                    result = first * second;
                }
                if(opertion == ":") {
                    result = first / second;
                }
                if(opertion == "%") {
                    result = first % second;
                }
                if(isDoubleInt(result)) {
                    int res = (int) result;
                    mainscreen.setText(res + "");
                } else {
                    mainscreen.setText(result + "");
                }
                checkMath = true;
            }
            opertion = "";
        });

        return view;
    }

}

