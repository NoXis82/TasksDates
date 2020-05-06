package com.example.tasksdates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private CalendarView mStartDateCalendar;
    private CalendarView mEndDateCalendar;
    private long mStartDate;
    private String mStartDateTxt;
    private long mEndDate;
    private String mEndDateTxt;
    private Button mChooseStartDate;
    private Button mChooseEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        startDate();
        endDate();
        btnOk();
    }

    public void initView() {
        mStartDateCalendar = findViewById(R.id.startDateCalendar);
        mEndDateCalendar = findViewById(R.id.endDateCalendar);
        mStartDateCalendar.setVisibility(View.GONE);
        mEndDateCalendar.setVisibility(View.GONE);
    }

    public void btnOk() {
        Button mBtnOK = findViewById(R.id.btnOK);
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStartDate > mEndDate) {
                    Toast.makeText(MainActivity.this,
                            R.string.error, Toast.LENGTH_LONG).show();
                    mChooseStartDate.setText(R.string.chooseStartDate);
                    mChooseEndDate.setText(R.string.chooseEndDate);
                } else {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.start)
                                    + mStartDateTxt
                                    + "\n"
                                    + getString(R.string.end)
                                    + mEndDateTxt,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void startDate() {
        mChooseStartDate = findViewById(R.id.chooseStartDate);
        mChooseStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mStartDateCalendar.setVisibility(View.VISIBLE);
                mEndDateCalendar.setVisibility(View.GONE);
                mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                                    int year,
                                                    int month,
                                                    int dayOfMonth) {
                        mStartDateTxt = year + "-" + month + "-" + dayOfMonth;
                        mChooseStartDate.setText((getString(R.string.chooseStartDate)
                                + mStartDateTxt));
                        GregorianCalendar gregorianCalendar = new GregorianCalendar();
                        gregorianCalendar.set(year, month, dayOfMonth);
                        mStartDate = gregorianCalendar.getTimeInMillis();
                        calendarView.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    public void endDate() {
        mChooseEndDate = findViewById(R.id.chooseEndDate);
        mChooseEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mStartDateCalendar.setVisibility(View.GONE);
                mEndDateCalendar.setVisibility(View.VISIBLE);
                mEndDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView,
                                                    int year,
                                                    int month,
                                                    int dayOfMonth) {
                        mEndDateTxt = year + "-" + month + "-" + dayOfMonth;
                        mChooseEndDate.setText((getString(R.string.chooseEndDate)
                                + mEndDateTxt));
                        GregorianCalendar gregorianCalendar = new GregorianCalendar();
                        gregorianCalendar.set(year, month, dayOfMonth);
                        mEndDate = gregorianCalendar.getTimeInMillis();
                        calendarView.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}
