package com.example.project1.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project1.R;
import com.example.project1.Retrofit.RetrofitBuilder;
import com.example.project1.Retrofit.RetrofitInterface;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyFragment extends Fragment {
    @Nullable
    private Spinner convertToDropDown;
    private Spinner convertFromDropDown;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bce, bdel;
    private TextView tv1, tv2, tv3;
    private boolean checkTv = true;
    private String temp;

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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, container, false);

        convertToDropDown = (Spinner) view.findViewById(R.id.convert_from);
        convertFromDropDown = (Spinner) view.findViewById(R.id.convert_to);
        b0 = (Button) view.findViewById(R.id.n0);
        b1 = (Button) view.findViewById(R.id.n1);
        b2 = (Button) view.findViewById(R.id.n2);
        b3 = (Button) view.findViewById(R.id.n3);
        b4 = (Button) view.findViewById(R.id.n4);
        b5 = (Button) view.findViewById(R.id.n5);
        b6 = (Button) view.findViewById(R.id.n6);
        b7 = (Button) view.findViewById(R.id.n7);
        b8 = (Button) view.findViewById(R.id.n8);
        b9 = (Button) view.findViewById(R.id.n9);
        bce = (Button) view.findViewById(R.id.deleteALL);
        bdel = (Button) view.findViewById(R.id.delete1);
        tv1 = (TextView) view.findViewById(R.id.textView1);
        tv2 = (TextView) view.findViewById(R.id.textView2);
        tv3 = (TextView) view.findViewById(R.id.textView3);

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
        addClickEffect(bce);

        tv1.setOnClickListener(v -> {
            if(!checkTv) {
                checkTv = true;
            }
        });

        tv2.setOnClickListener(v -> {
            if(checkTv) {
                checkTv = false;
            }
        });

        bdel.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                if(!temp.isEmpty()) {
                    temp = temp.substring(0, temp.length() - 1);
                    tv1.setText(temp + "");
                    RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                    Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            JsonObject res = response.body();
                            JsonObject rates = res.getAsJsonObject("rates");
                            double currency = Double.valueOf(tv1.getText().toString());
                            double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                            double result = currency * multi;
                            tv2.setText(String.valueOf(result));
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }
            }else {
                temp = tv2.getText().toString();
                if (!temp.isEmpty()) {
                    temp = temp.substring(0, temp.length() - 1);
                    tv2.setText(temp + "");
                    RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                    Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            JsonObject res = response.body();
                            JsonObject rates = res.getAsJsonObject("rates");
                            double currency = Double.valueOf(tv2.getText().toString());
                            double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                            double result = currency * multi;
                            tv1.setText(String.valueOf(result));
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });
                }
            }
        });

        bce.setOnClickListener(v -> {
            tv1.setText("");
            tv2.setText("");
        });

        b0.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "0");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "0");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b1.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "1");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "1");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b2.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "2");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "2");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b3.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "3");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "3");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b4.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "4");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "4");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b5.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "5");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "5");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b6.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "6");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "6");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b7.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "7");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "7");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b8.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "8");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "8");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        b9.setOnClickListener(v -> {
            if(checkTv) {
                temp = tv1.getText().toString();
                tv1.setText(temp + "9");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertToDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv1.getText().toString());
                        double multi = Double.valueOf(rates.get(convertFromDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv2.setText(String.valueOf(result));
                        tv3.setText("1 "+convertToDropDown.getSelectedItem().toString()+" = "+multi+" "+convertFromDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            } else {
                temp = tv2.getText().toString();
                tv2.setText(temp + "9");
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropDown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("rates");
                        double currency = Double.valueOf(tv2.getText().toString());
                        double multi = Double.valueOf(rates.get(convertToDropDown.getSelectedItem().toString()).toString());
                        double result = currency * multi;
                        tv1.setText(String.valueOf(result));
                        tv3.setText("1 "+convertFromDropDown.getSelectedItem().toString()+" = "+multi+" "+convertToDropDown.getSelectedItem().toString());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

        String [] dropDownList = new String[] {"VND", "USD", "EUR", "EGP", "CNY", "IDR", "INR", "JPY", "SGD", "THB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertToDropDown.setAdapter(adapter);
        convertFromDropDown.setAdapter(adapter);

        return view;
    }
}
