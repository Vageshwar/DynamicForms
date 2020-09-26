package com.android.dynamicforms;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class FormItem {

    LinearLayout layout;
    EditText name;
    Spinner type;

    public FormItem(LinearLayout layout, EditText name, Spinner type) {
        this.layout = layout;
        this.name = name;
        this.type = type;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }

    public EditText getName() {
        return name;
    }

    public void setName(EditText name) {
        this.name = name;
    }

    public Spinner getType() {
        return type;
    }

    public void setType(Spinner type) {
        this.type = type;
    }
}
