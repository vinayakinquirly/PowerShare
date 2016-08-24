package com.inquirly.inquirlyforbusinesses.View;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.Hashtable;

public class CustomTextView extends TextView {
    public static final int BOLD = 1;
    public static final int BOLD_ITALIC = 3;
    public static final int ITALIC = 2;
    public static final int NORMAL = 0;
    public static final String TYPEFACE_EXTENSION = ".ttf";
    public static final String TYPEFACE_FOLDER = "fonts";
    private static Hashtable<String, Typeface> sTypeFaces;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        try {
            Typeface tfRegular = getTypeFace(getContext(), "Montserrat-Regular");
            Typeface tfBold = getTypeFace(getContext(), "Montserrat-Bold");
            if (getTypeface() != null) {
                switch (getTypeface().getStyle()) {
                    case NORMAL /*0*/:
                        setTypeface(tfRegular, NORMAL);
                        return;
                    case BOLD /*1*/:
                        setTypeface(tfBold, BOLD);
                        return;
                    case ITALIC /*2*/:
                        setTypeface(tfRegular, ITALIC);
                        return;
                    default:
                        return;
                }
            }
            setTypeface(tfRegular, NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        sTypeFaces = new Hashtable(4);
    }

    public static Typeface getTypeFace(Context context, String fileName) {
        Typeface tempTypeface = (Typeface) sTypeFaces.get(fileName);
        if (tempTypeface != null) {
            return tempTypeface;
        }
        tempTypeface = Typeface.createFromAsset(context.getAssets(), new StringBuilder(TYPEFACE_FOLDER).append('/').append(fileName).append(TYPEFACE_EXTENSION).toString());
        sTypeFaces.put(fileName, tempTypeface);
        return tempTypeface;
    }
}
