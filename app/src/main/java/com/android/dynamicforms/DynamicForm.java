package com.android.dynamicforms;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DynamicForm {
    Context context;

    public DynamicForm(Context context) {
        this.context = context;
    }

    public EditText setEditText(LinearLayout formLayout, Context context, String hint) {
        EditText myEditText = new EditText(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        myEditText.setLayoutParams(layoutParams);
        myEditText.setHint(hint);
//        layoutParams.setMargins(0, dpToPx(16), 0, 0);
        myEditText.setTextSize(14);
//        myEditText.getBackground().setColorFilter(context.getResources().getColor(R.color.fourth_black), PorterDuff.Mode.SRC_ATOP);
        formLayout.addView(myEditText);
        return myEditText;
    }

    public LinearLayout addRow(LinearLayout formLayout, Context context, String hint) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(2.0f);
        EditText name = new EditText(context);
        Spinner type = new Spinner(context);
        List<String> list = new ArrayList<String>();
        list.add("Text");
        list.add("Number");
        list.add("Date");
        list.add("Radio Button");
        list.add("DropDown");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(dataAdapter);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1.0f;



        name.setLayoutParams(layoutParams);
        type.setLayoutParams(layoutParams);
        name.setHint(hint);
//        layoutParams.setMargins(0, dpToPx(16), 0, 0);
        name.setTextSize(14);
//        myEditText.getBackground().setColorFilter(context.getResources().getColor(R.color.fourth_black), PorterDuff.Mode.SRC_ATOP);
        row.addView(name);
        row.addView(type);
        formLayout.addView(row);

        return row;
    }
}
