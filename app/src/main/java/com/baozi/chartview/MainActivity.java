package com.baozi.chartview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChartView vChartView = findViewById(R.id.chart_view);
        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setMinute(4.89f);
        car.setMinuteDesc("4.89");
        car.setDesc("操控");
        cars.add(car);
        Car car1 = new Car();
        car1.setMinute(4.85f);
        car1.setMinuteDesc("4.85");
        car1.setDesc("内饰");
        cars.add(car1);
        Car car2 = new Car();
        car2.setMinute(4.74f);
        car2.setMinuteDesc("4.74");
        car2.setDesc("外观");
        cars.add(car2);
        Car car3 = new Car();
        car3.setMinute(4.01f);
        car3.setMinuteDesc("4.01");
        car3.setDesc("动力");
        cars.add(car3);
        Car car4 = new Car();
        car4.setMinute(4.88f);
        car4.setMinuteDesc("4.88");
        car4.setDesc("性价比");
        cars.add(car4);
        Car car5 = new Car();
        car5.setMinute(4.21f);
        car5.setMinuteDesc("4.21");
        car5.setDesc("油耗");
        cars.add(car5);
        Car car6 = new Car();
        car6.setMinute(4.44f);
        car6.setMinuteDesc("4.44");
        car6.setDesc("空间");
        cars.add(car6);
        Car car7 = new Car();
        car7.setMinute(4.00f);
        car7.setMinuteDesc("4.00");
        car7.setDesc("舒适度");
        cars.add(car7);
        vChartView.setData(cars);
    }
}
