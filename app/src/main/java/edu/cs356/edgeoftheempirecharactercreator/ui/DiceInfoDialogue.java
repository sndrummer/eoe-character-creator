package edu.cs356.edgeoftheempirecharactercreator.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import edu.cs356.edgeoftheempirecharactercreator.R;

public class DiceInfoDialogue extends Dialog {
    private Button mExitBtn;

    public DiceInfoDialogue(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dice_dialog);
        mExitBtn = findViewById(R.id.exit_btn);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}
