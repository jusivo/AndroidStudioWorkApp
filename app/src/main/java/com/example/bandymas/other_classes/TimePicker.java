package com.example.bandymas.other_classes;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import com.example.bandymas.R;

import java.util.Calendar;

public class TimePicker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

        //return super.onCreateDialog(savedInstanceState);
    }
    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        AppCompatButton tv1= getActivity().findViewById(R.id.time_display);
        tv1.setText("Hour: "+view.getHour()+" Minute: "+view.getMinute());

    }
}
