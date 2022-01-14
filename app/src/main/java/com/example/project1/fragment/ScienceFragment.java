package com.example.project1.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project1.InfixToPostfix;
import com.example.project1.R;

public class ScienceFragment extends Fragment {

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

    // Tính giai thừa
    private double factorial(double n) {
        double res = 1;
        for(int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    InfixToPostfix IFP = new InfixToPostfix(); // Tính biểu thức trung tố

    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private ImageButton bfact, bt2nd, bpi, be, bsquare, bsqrt, bmu, b10, blog, bln, bdaoNguoc;
    private Button bdaoDau, bdot, badd, bminus, bdevine, bmulti, bequal;
    private Button bopen, bclose, babs;
    private Button bexp, bmod, bc, bdel;
    private TextView tvmain, tvsub;
    private String temp;
    private boolean checkExp = false;
    private boolean checkOut = true;
    private boolean checkDot = false;
    private boolean checkMath = false;
    private boolean checkClick = false;
    private boolean check2nd = false;
    private static final double pi  = 3.1415926535;
    private static final double e = 2.7182818284;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View scienceView = inflater.inflate(R.layout.fragment_science, container, false);

        // Ánh xạ
        bt2nd = (ImageButton) scienceView.findViewById(R.id.button51);
        b0 = (Button) scienceView.findViewById(R.id.b0);
        b1 = (Button) scienceView.findViewById(R.id.b1);
        b2 = (Button) scienceView.findViewById(R.id.b2);
        b3 = (Button) scienceView.findViewById(R.id.b3);
        b4 = (Button) scienceView.findViewById(R.id.b4);
        b5 = (Button) scienceView.findViewById(R.id.b5);
        b6 = (Button) scienceView.findViewById(R.id.b6);
        b7 = (Button) scienceView.findViewById(R.id.b7);
        b8 = (Button) scienceView.findViewById(R.id.b8);
        b9 = (Button) scienceView.findViewById(R.id.b9);
        b10 = (ImageButton) scienceView.findViewById(R.id.b10);
        bdel = (Button) scienceView.findViewById(R.id.bdel);
        bdaoDau = (Button) scienceView.findViewById(R.id.bdaoDau);
        bdaoNguoc = (ImageButton) scienceView.findViewById(R.id.bdao);
        bdot = (Button) scienceView.findViewById(R.id.bdot);
        badd = (Button) scienceView.findViewById(R.id.badd);
        bminus = (Button) scienceView.findViewById(R.id.bminus);
        bdevine = (Button) scienceView.findViewById(R.id.bdevine);
        bmulti = (Button) scienceView.findViewById(R.id.bmulti);
        bequal = (Button) scienceView.findViewById(R.id.bequal);
        bln = (ImageButton) scienceView.findViewById(R.id.bln);
        blog = (ImageButton) scienceView.findViewById(R.id.blog);
        bmu = (ImageButton) scienceView.findViewById(R.id.bmu);
        bsqrt = (ImageButton) scienceView.findViewById(R.id.bsqrt);
        bfact = (ImageButton) scienceView.findViewById(R.id.bfact);
        bopen = (Button) scienceView.findViewById(R.id.bopen);
        bclose = (Button) scienceView.findViewById(R.id.bclose);
        babs = (Button) scienceView.findViewById(R.id.babs);
        bexp = (Button) scienceView.findViewById(R.id.bexp);
        bmod = (Button) scienceView.findViewById(R.id.bmod);
        bpi = (ImageButton) scienceView.findViewById(R.id.bpi);
        bsquare = (ImageButton) scienceView.findViewById(R.id.bsquare);
        be = (ImageButton) scienceView.findViewById(R.id.be);
        bc = (Button) scienceView.findViewById(R.id.bc);
        tvmain = (TextView) scienceView.findViewById(R.id.tvmain);
        tvsub = (TextView) scienceView.findViewById(R.id.tvsub);

        addClickEffect(b0);
        addClickEffect(b1);
        addClickEffect(b2);
        addClickEffect(b3);
        addClickEffect(b4);
        addClickEffect(b5);
        addClickEffect(b6);
        addClickEffect(b7);
        addClickEffect(b8);
        addClickEffect(b9);
        addClickEffect(b10);
        addClickEffect(badd);
        addClickEffect(bminus);
        addClickEffect(bmulti);
        addClickEffect(bdevine);
        addClickEffect(bmod);
        addClickEffect(bdel);
        addClickEffect(bdaoDau);
        addClickEffect(bdaoNguoc);
        addClickEffect(bdot);
        addClickEffect(bequal);
        addClickEffect(bln);
        addClickEffect(blog);
        addClickEffect(bmu);
        addClickEffect(bsqrt);
        addClickEffect(bfact);
        addClickEffect(bopen);
        addClickEffect(bclose);
        addClickEffect(babs);
        addClickEffect(bexp);
        addClickEffect(bpi);
        addClickEffect(bsquare);
        addClickEffect(be);
        addClickEffect(bc);
        addClickEffect(bt2nd);

        // Xoa
        bc.setOnClickListener(v -> {
            tvsub.setText("");
            tvmain.setText("");
            temp = "";
            checkDot = false;
            checkMath = false;
            checkClick = false;
            checkOut = true;
        });

        bdel.setOnClickListener(v -> {
            if(!checkMath) {
                String tmp = tvmain.getText().toString();
                if(!tmp.isEmpty()) {
                    tmp = tmp.substring(0, tmp.length() - 1);
                    tvmain.setText(tmp + "");
                }
            }
        });


        // number
        b0.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "0");
                checkOut = false;
            }
        });
        b1.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "1");
                checkOut = false;
            }
        });
        b2.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "2");
                checkOut = false;
            }
        });
        b3.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "3");
                checkOut = false;
            }
        });
        b4.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "4");
                checkOut = false;
            }
        });
        b5.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "5");
                checkOut = false;
            }
        });
        b6.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "6");
                checkOut = false;
            }
        });
        b7.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "7");
                checkOut = false;
            }
        });
        b8.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "8");
                checkOut = false;
            }
        });
        b9.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                temp = tvmain.getText().toString();
                tvmain.setText(temp + "9");
                checkOut = false;
            }
        });
        bdot.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                if(!checkDot) {
                    temp = tvmain.getText().toString();
                    tvmain.setText(temp + ".");
                    checkDot = true;
                }
            }
        });
        bpi.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                if(!checkDot) {
                    temp = tvmain.getText().toString();
                    tvmain.setText(temp + pi);
                    checkDot = true;
                    checkClick = true;
                    checkOut = false;
                }
            }
        });
        be.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                if(!checkDot) {
                    temp = tvmain.getText().toString();
                    tvmain.setText(temp + e);
                    checkDot = true;
                    checkClick = true;
                    checkOut = false;
                }
            }
        });
        bt2nd.setOnClickListener(v -> {
            if(!check2nd) {
                bt2nd.setImageResource(R.drawable.img);
                check2nd = true;
                b10.setImageResource(R.drawable.img_7);
                bsquare.setImageResource(R.drawable.img_5);
                bln.setImageResource(R.drawable.img_12);
                blog.setImageResource(R.drawable.img_11);
                bsqrt.setImageResource(R.drawable.img_10);
            } else {
                bt2nd.setImageResource(R.drawable.img_1);
                check2nd = false;
                b10.setImageResource(R.drawable.img_9);
                bsquare.setImageResource(R.drawable.square);
                bln.setImageResource(R.drawable.img_14);
                blog.setImageResource(R.drawable.img_13);
                bsqrt.setImageResource(R.drawable.img_6);
            }
        });
        bopen.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                if (!checkDot) {
                    tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + "( ");
                    checkDot = true;
                    checkOut = true;
                }
            }
        });
        bclose.setOnClickListener(v -> {
            if(!checkMath && !checkClick) {
                if (!checkDot) {                
                    tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " )");
                    tvmain.setText(" ");
                    checkDot = true;
                }
            }
        });

        // Simple Math
        badd.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkMath) {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText("( 0 " +tvmain.getText().toString() + " ) + ");
                    }
                    else tvsub.setText(tvmain.getText().toString() + " + ");
                }
                else {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText(tvsub.getText().toString() + "( 0 " +tvmain.getText().toString() + " ) + ");
                    }
                    else tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " + ");
                }

                tvmain.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
            }
        });

        bminus.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkMath) {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText("( 0 " +tvmain.getText().toString() + " ) - ");
                    }
                    else tvsub.setText(tvmain.getText().toString() + " - ");
                }
                else {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText(tvsub.getText().toString() + "( 0 " +tvmain.getText().toString() + " ) - ");
                    }
                    else tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " - ");
                }

                tvmain.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
            }
        });

        bmulti.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkMath) {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText("( 0 " +tvmain.getText().toString() + " ) x ");
                    }
                    else tvsub.setText(tvmain.getText().toString() + " x ");
                }
                else {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText(tvsub.getText().toString() + "( 0 " +tvmain.getText().toString() + " ) x ");
                    }
                    else tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " x ");
                }

                tvmain.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
            }
        });

        bdevine.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkMath) {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText("( 0 " +tvmain.getText().toString() + " ) : ");
                    }
                    else tvsub.setText(tvmain.getText().toString() + " : ");
                }
                else {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText(tvsub.getText().toString() + "( 0 " +tvmain.getText().toString() + " ) : ");
                    }
                    else tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " : ");
                }

                tvmain.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
            }
        });

        bmod.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkMath) {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText("( 0 " + tvmain.getText().toString() + " ) % ");
                    }
                    else tvsub.setText(tvmain.getText().toString() + " % ");
                }
                else {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText(tvsub.getText().toString() + "( 0 " +tvmain.getText().toString() + " ) % ");
                    }
                    else tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " % ");
                }

                tvmain.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
            }
        });

        bmu.setOnClickListener(v -> {
            if(!checkOut) {
                if(checkMath) {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText("( 0 " +tvmain.getText().toString() + " ) ^ ");
                    }
                    else tvsub.setText(tvmain.getText().toString() + " ^ ");
                }
                else {
                    if(tvmain.getText().toString().charAt(0) == '-') {
                        tvsub.setText(tvsub.getText().toString() + "( 0 " +tvmain.getText().toString() + " ) ^ ");
                    }
                    else tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " ^ ");
                }

                tvmain.setText("");
                checkMath = false;
                checkDot = false;
                checkClick = false;
                checkOut = true;
            }
        });

        // Other math button

        bdaoDau.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                temp = tvmain.getText().toString();
                t = Double.parseDouble(temp);
                if (!checkMath) {
                    if (t < 0) {
                        t = Math.abs(t);
                        tvmain.setText(t + "");
                    } else tvmain.setText("-" + t + "");
                    checkClick = true;
                    checkOut = false;
                } else {
                    tvsub.setText("negate(" + t + ")");
                    if (t < 0) {
                        t = Math.abs(t);
                        tvmain.setText(t + "");
                    } else tvmain.setText("-" + t + "");
                    checkClick = true;
                    checkOut = false;
                }
            }
        });

        bdaoNguoc.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                temp = tvmain.getText().toString();
                t = Double.parseDouble(temp);
                if (t != 0) {
                    if (!checkMath) {
                        t = 1 / t;
                        tvmain.setText(t + "");
                        checkClick = true;
                        checkOut = false;
                    } else {
                        tvsub.setText("1/(" + t + ")");
                        tvmain.setText(1 / t + "");
                        checkClick = true;
                        checkOut = false;
                    }
                }
            }
        });

        bfact.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                t = Double.parseDouble(tvmain.getText().toString());
                if(t >= 0) {
                    if(!checkMath) {
                        t = factorial(t);
                        if(isDoubleInt(t)) {
                            long a = (long) t;
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    } else {
                        if(isDoubleInt(t)) {
                            long a = (long) t;
                            tvsub.setText(a + "!");
                            a = (long) factorial(t);
                            tvmain.setText(a + "");
                        } else {
                            tvsub.setText(t + "!");
                            t = factorial(t);
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    }
                }
            }
        });

        b10.setOnClickListener(v -> {
            if(!checkOut) {
                if (!check2nd) {
                    double t;
                    t = Double.parseDouble(tvmain.getText().toString());
                    if (!checkMath) {
                        t = Math.pow(10, t);
                        if(isDoubleInt(t)) {
                            long a =(long) t;
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    } else {
                        tvsub.setText("10 ^ " + t);
                        t = Math.pow(10, t);
                        if(isDoubleInt(t)) {
                            long a =(long) t;
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    }
                } else {
                    double t;
                    t = Double.parseDouble(tvmain.getText().toString());
                    if (!checkMath) {
                        t = Math.pow(2, t);
                        if(isDoubleInt(t)) {
                            long a =(long) t;
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    } else {
                        tvsub.setText("2 ^ " + t);
                        t = Math.pow(2, t);
                        if(isDoubleInt(t)) {
                            long a =(long) t;
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    }
                }
            }
        });

        babs.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                t = Double.parseDouble(tvmain.getText().toString());
                if(!checkMath) {
                    t = Math.abs(t);
                    if(isDoubleInt(t)) {
                        long a =(long) t;
                        tvmain.setText(a + "");
                    } else {
                        tvmain.setText(t + "");
                    }
                    checkClick = true;
                    checkOut = false;
                } else {
                    if(isDoubleInt(t)) {
                        long a = (long) t;
                        tvsub.setText("abs(" + a + ")");
                        a = (long) Math.abs(t);
                        tvmain.setText(a + "");
                    } else {
                        tvsub.setText("abs(" + t + ")");
                        t = Math.abs(t);
                        tvmain.setText(t + "");
                    }
                    checkClick = true;
                    checkOut = false;
                }
            }
        });

        blog.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                t = Double.parseDouble(tvmain.getText().toString());
                if(t > 0) {
                    if (!check2nd){
                        if (!checkMath) {
                            t = Math.log10(t);
                            if(isDoubleInt(t)) {
                                long a =(long) t;
                                tvmain.setText(a + "");
                            } else {
                                tvmain.setText(t + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        } else {
                            if(isDoubleInt(t)) {
                                long a = (long) t;
                                tvsub.setText("log(" + a + ")");
                                t = Math.log10(t);
                                if(isDoubleInt(t)) {
                                    long q =(long) t;
                                    tvmain.setText(q + "");
                                } else {
                                    tvmain.setText(t + "");
                                }
                            } else {
                                tvsub.setText("log(" + t + ")");
                                t = Math.log10(t);
                                tvmain.setText(t + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        }
                    } else {
                        if (!checkMath) {
                            t = Math.log10(t) / Math.log10(2);
                            if(isDoubleInt(t)) {
                                long a =(long) t;
                                tvmain.setText(a + "");
                            } else {
                                tvmain.setText(t + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        } else {
                            if(isDoubleInt(t)) {
                                long a = (long) t;
                                tvsub.setText("log2(" + a + ")");
                                t = Math.log10(t) / Math.log10(2);
                                if(isDoubleInt(t)) {
                                    long q =(long) t;
                                    tvmain.setText(q + "");
                                } else {
                                    tvmain.setText(t + "");
                                }
                            } else {
                                tvsub.setText("log2(" + t + ")");
                                t = Math.log10(t) / Math.log10(2);
                                tvmain.setText(t + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        }
                    }
                }
            }
        });

        bln.setOnClickListener(v -> {
            if(!checkOut) {
                double t;
                t = Double.parseDouble(tvmain.getText().toString());
                if (t > 0) {
                    if (!check2nd) {
                        if (!checkMath) {
                            t = Math.log(t);
                            tvmain.setText(t + "");
                            checkClick = true;
                            checkOut = false;
                        } else {
                            tvsub.setText("ln(" + t + ")");
                            t = Math.log(t);
                            tvmain.setText(t + "");
                            checkClick = true;
                            checkOut = false;
                        }
                    } else {
                        if (!checkMath) {
                            t = Math.pow(e, t);
                            tvmain.setText(t + "");
                            checkClick = true;
                            checkOut = false;
                        } else {
                            tvsub.setText("e^(" + t + ")");
                            t = Math.pow(e, t);
                            tvmain.setText(t + "");
                            checkClick = true;
                            checkOut = false;
                        }
                    }
                }
            }
        });

        bexp.setOnClickListener(v -> {
            if(!checkOut) {
                if(!checkMath) {
                    double t = Double.parseDouble(tvmain.getText().toString());
                    tvsub.setText(tvsub.getText().toString() + tvmain.getText().toString() + " x 10 ^ ");
                    tvmain.setText("");
                    checkOut = true;
                    checkClick = false;
                } else {
                    double t = Double.parseDouble(tvmain.getText().toString());
                    tvsub.setText(t + " x 10 ^ ");
                    tvmain.setText("");
                    checkOut = true;
                    checkClick = false;
                    checkMath = false;
                }
            }
        });

        bsquare.setOnClickListener(v -> {
            if(!checkOut) {
                if (!check2nd) {
                    Double t;
                    temp = tvmain.getText().toString();
                    t = Double.parseDouble(temp);
                    if (!checkMath) {
                        t = t * t;
                        if(isDoubleInt(t)) {
                            long a =Math.round(t);
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    } else {
                        if(isDoubleInt(t)) {
                            long a = Math.round(t);
                            tvsub.setText("sqr(" + a + ")");
                            tvmain.setText(a * a + "");
                        } else {
                            tvsub.setText("sqr(" + t + ")");
                            tvmain.setText(t * t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    }
                } else {
                    Double t;
                    temp = tvmain.getText().toString();
                    t = Double.parseDouble(temp);
                    if (!checkMath) {
                        t = t * t * t;
                        if(isDoubleInt(t)) {
                            long a =Math.round(t);
                            tvmain.setText(a + "");
                        } else {
                            tvmain.setText(t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    } else {
                        if(isDoubleInt(t)) {
                            long a = Math.round(t);
                            tvsub.setText("cube(" + a + ")");
                            tvmain.setText(a * a * a + "");
                        } else {
                            tvsub.setText("cube(" + t + ")");
                            tvmain.setText(t * t * t + "");
                        }
                        checkClick = true;
                        checkOut = false;
                    }
                }
            }
        });

        bsqrt.setOnClickListener(v -> {
            if(!checkOut) {
                if(!check2nd) {
                    double t;
                    temp = tvmain.getText().toString();
                    t = Double.parseDouble(temp);
                    if (t >= 0) {
                        if (!checkMath) {
                            t = Math.sqrt(t);
                            if(isDoubleInt(t)) {
                                long a =Math.round(t);
                                tvmain.setText(a + "");
                            } else {
                                tvmain.setText(t + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        } else {
                            if(isDoubleInt(t)) {
                                long a = (long) t;
                                tvsub.setText("√(" + a + ")");
                                t = Math.sqrt(t);
                                if(isDoubleInt(t)) {
                                    long b = (long) t;
                                    tvmain.setText(b + "");
                                } else {
                                    tvmain.setText(t + "");
                                }
                            } else {
                                tvsub.setText("√(" + t + ")");
                                tvmain.setText(Math.sqrt(t) + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        }
                    }
                } else {
                    double a = 1.0 / 3;
                    double t;
                    temp = tvmain.getText().toString();
                    t = Double.parseDouble(temp);
                    if (t >= 0) {
                        if (!checkMath) {
                            t = Math.pow(t, a);
                            if(isDoubleInt(t)) {
                                long b =Math.round(t);
                                tvmain.setText(b + "");
                            } else {
                                tvmain.setText(t + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        } else {
                            if(isDoubleInt(t)) {
                                long b = (long) t;
                                tvsub.setText("cuberoot(" + b + ")");
                                t = Math.pow(t, a);
                                if(isDoubleInt(t)) {
                                    long c = (long) t;
                                    tvmain.setText(c + "");
                                } else {
                                    tvmain.setText(t + "");
                                }
                            } else {
                                tvsub.setText("cuberoot(" + t + ")");
                                tvmain.setText(Math.pow(t, a) + "");
                            }
                            checkClick = true;
                            checkOut = false;
                        }
                    }
                }
            }
        });

        // Ket qua
        bequal.setOnClickListener(v -> {
            if(checkMath) {
                temp = tvmain.getText().toString();
                tvsub.setText(temp + "");
                tvmain.setText(temp + "");
            }
            if(!checkMath && !checkOut) {
                String tmp;
                checkClick = true;
                checkDot = false;
                checkClick = false;
                String element[] = null;
                if(tvmain.getText().toString().charAt(0) == '-') {
                    tmp = tvsub.getText().toString() + "( 0 " + tvmain.getText().toString() + " )";
                }
                else {
                    tmp = tvsub.getText().toString() + tvmain.getText().toString();
                }
                tvsub.setText(tmp + "");
                element = IFP.processString(tmp);
                element = IFP.postfix(element);
                tmp = IFP.valueMath(element);
                if(isDoubleInt(Double.parseDouble(tmp))) {
                    int t = (int) Double.parseDouble(tmp);
                    tmp = String.valueOf(t);
                }
                tvmain.setText(tmp + "");
                checkMath = true;
            }
        });

        return scienceView;
    }
}
