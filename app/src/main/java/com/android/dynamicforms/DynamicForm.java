package com.android.dynamicforms;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
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

    public FormItem addRow(LinearLayout formLayout, Context context, int i) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setWeightSum(2.0f);
        EditText name = new EditText(context);
        Spinner type = new Spinner(context);
        type.setId(i);
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
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = dpToPx(150);
        layoutParams.setMargins(0, dpToPx(16), 0, 0);
        name.setLayoutParams(layoutParams);
        type.setLayoutParams(layoutParams);

        name.setTextSize(14);
//        myEditText.getBackground().setColorFilter(context.getResources().getColor(R.color.fourth_black), PorterDuff.Mode.SRC_ATOP);
        row.addView(name);
        row.addView(type);
        formLayout.addView(row);
        return new FormItem(row,name,type);
    }

    public EditText addValues(Context context, LinearLayout formLayout, String hint ){
        EditText myEditText = new EditText(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.width = dpToPx(300);
        myEditText.setLayoutParams(layoutParams);
        myEditText.setHint(hint);
        myEditText.setTextSize(14);
        formLayout.addView(myEditText);
        return myEditText;
    }

    private int dpToPx(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
