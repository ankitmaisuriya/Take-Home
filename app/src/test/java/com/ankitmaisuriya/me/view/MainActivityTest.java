package com.ankitmaisuriya.me.view;

import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ankitmaisuriya.me.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(manifest="src/main/AndroidManifest.xml", sdk = 21 )
public class MainActivityTest{

    private MainActivity mActivity;
    private Button mButton;
    private EditText mTextView;

    @Before
    public void setup() {
        mActivity = Robolectric.buildActivity(
                MainActivity.class).create().get();
        mButton = (Button) mActivity.findViewById(R.id.btn_search);
        mTextView = (EditText) mActivity.findViewById(R.id.input_name);
    }

    @Test
    public void testForTextViewTextToBeChangedAfterClick() {
        mButton.performClick();

        String text = mTextView.getText().toString();
        assertThat(text, equalTo("Octocat"));
    }


}

