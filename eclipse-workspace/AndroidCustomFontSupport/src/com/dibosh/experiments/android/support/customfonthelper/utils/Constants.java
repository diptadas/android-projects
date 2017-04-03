package com.dibosh.experiments.android.support.customfonthelper.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import androidbangladesh.bengali.support.BengaliUnicodeString;

public class Constants {
	
	public static String SUTONNI_MJ="sutonni.TTF";
	public static String SOLAIMAN_LIPI="solaimanlipinormal.ttf";
	
	public static String WEBVIEW_LOADED="loaded";

	/**
	 * Logs messages based on the class it gets sent from
	 * @param obj
	 * @param msg
	 */
	public static void log(Object obj,String msg)
	{
		Log.d(obj.getClass().getSimpleName(), msg);
	}
	public static SpannableString getSpannableWithFont(Context context,String text,Typeface font,float size)
	{
		SpannableString retVal=new SpannableString(text);
		TypefaceSpan span=new TypefaceSpan(font);
		retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		if(size != -1)
		{
			retVal.setSpan(new RelativeSizeSpan(size),0, retVal.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return retVal;
	}
	/**
	 * Experimental! Trying to incorporate bijoy to show bangla in tabs/titlebars
	 * @param text
	 * @param font
	 * @param size
	 * @return
	 */
	public static SpannableString getBanglaInBijoy(String text,Typeface font,float size)
	{
		//first convert to unicode then to bijoy
		String uni_text= getBanglaString(text);
//		String mod_text = BijoyFontUtil.convertUnicode2BijoyString(uni_text);
		String mod_text = BijoyFontUtil.convertUnicode2BijoyString(text);
		SpannableString retVal=new SpannableString(mod_text);
		TypefaceSpan span=new TypefaceSpan(font);
		
		retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		if(size != -1)
		{
			retVal.setSpan(new RelativeSizeSpan(size),0, retVal.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		return retVal;
	}
	/**
	 * Experimental! On some devices <strong>getBanglaSpannableWithSize</strong> is not 
	 * working for titlebars,tabs and native components.Use this method for them instead.
	 * @param text
	 * @param font
	 * @param size
	 * @return
	 */
	public static SpannableString getBanglaSpannableWithSizeForNativeItems(String text,Typeface font,float size)
	{
		SpannableString retVal=new SpannableString(getBanglaString(text));
		TypefaceSpan span=new TypefaceSpan(font);
		
		retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		if(size != -1)
		{
			retVal.setSpan(new RelativeSizeSpan(size),0, retVal.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		return retVal;
	}
	/**
	 * Used to get bangla texts for UI components.
	 * @param text
	 * @param size
	 * @param banglaSupported TODO
	 */
	public static SpannableString getBanglaSpannableWithSize(String text,Typeface font,float size, boolean banglaSupported)
	{
		SpannableString retVal=null;
		if(banglaSupported)//device supports bangla
		{
			log(Constants.class,"Bangla Supported Device");
			retVal=new SpannableString(text);
		}
		else
		{
			//device doesn't support,need unicode.
			retVal=new SpannableString(BengaliUnicodeString.getBengaliUTF(text));
		}
		TypefaceSpan span=new TypefaceSpan(font);
		
		retVal.setSpan(span, 0, retVal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		if(size != -1)
		{
			retVal.setSpan(new RelativeSizeSpan(size),0, retVal.length(),
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		return retVal;
	}
	/**
	 * Directly get the UTF8 representation of a 
	 * Bangla ASCII sequence.
	 * @param text
	 * @return SpannableString
	 */
	public static SpannableString getBanglaSpannable(String text)
	{
		SpannableString retVal=new SpannableString(BengaliUnicodeString.getBengaliUTF(text));
		
		return retVal;
	}
	/**
	 * Directly get the UTF8 representation of a 
	 * Bangla ASCII sequence.
	 * @param text
	 * @return String
	 */
	public static String getBanglaString(String text)
	{
		return BengaliUnicodeString.getBengaliUTF(text);
	}
	
	
	


}
