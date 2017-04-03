package com.dibosh.experiments.android.support.customfonthelper.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class TypefaceSpan extends MetricAffectingSpan{

//	/** An <code>LruCache</code> for previously loaded typefaces. */
//    private static LruCache<String, Typeface> sTypefaceCache =
//            new LruCache<String, Typeface>(12);
	/*Cache to save loaded fonts*/
	private final Typeface mTypeface;
	
	public TypefaceSpan(Typeface typeface)
	{
		mTypeface=typeface;

		//we dont need to put it in cache
	}
	@Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);

        // Note: This flag is required for proper typeface rendering
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);

        // Note: This flag is required for proper typeface rendering
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
//	@Override
//    public void updateDrawState(TextPaint ds) {
//        applyCustomTypeFace(ds, mTypeface);
//    }
//
//    @Override
//    public void updateMeasureState(TextPaint paint) {
//        applyCustomTypeFace(paint, mTypeface);
//    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;
        // Note: This flag is required for proper typeface rendering
        paint.setFlags(paint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }

}
