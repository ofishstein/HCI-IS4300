package com.is4300.ofishstein.i5;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner shapeSpinner;
    Spinner thicknessSpinner;
    Button clearButton;
    FrameLayout frameLayout;
    SketchView sketchView;
    RadioGroup radioGroup;

    // default values
    String color = "Black";
    String shape = "Line";
    String thickness = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.canvasHolder);
        sketchView = new SketchView(this);
        frameLayout.addView(sketchView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        clearButton = (Button) findViewById(R.id.clearButton);

        shapeSpinner = (Spinner) findViewById(R.id.shapeSpinner);
        ArrayAdapter<CharSequence> shapeAdapter = ArrayAdapter.createFromResource(this,
                R.array.shapes, android.R.layout.simple_spinner_item);
        shapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shapeSpinner.setAdapter(shapeAdapter);

        thicknessSpinner = (Spinner) findViewById(R.id.thicknessSpinner);
        ArrayAdapter<CharSequence> thicknessAdapter = ArrayAdapter.createFromResource(this,
                R.array.thicknesses, android.R.layout.simple_spinner_item);
        thicknessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thicknessSpinner.setAdapter(thicknessAdapter);

        radioGroup = (RadioGroup) findViewById(R.id.colorSelector);
        sketchView.setPaintColor(color);

        this.setOnClicks();


    }

    private void setOnClicks() {
        clearButton.setOnClickListener(this);

        shapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shape = (String) parent.getItemAtPosition(position);
                Log.d("shape: ", shape);
                sketchView.setShape(shape);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        thicknessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                thickness = (String) parent.getItemAtPosition(position);
                Log.d("thickness: ", thickness);
                sketchView.setPaintThickness(thickness);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) findViewById(checkedId);
                color = (String) button.getText();
                Log.d("color: ", color);
                sketchView.setPaintColor(color);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Log.d("Main Activity", "clear button click");
        sketchView.clearCanvas();
    }
}
