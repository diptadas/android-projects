package com.dibosh.experiments.android.support.customfonthelper;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;

import com.dibosh.experiments.android.support.customfonthelper.utils.Constants;
/**
 * This class helps you to get the custom font(Bangla also) support for all
 * the android components which take string to display.
 *
 * @author Md. Abdul Munim Dibosh
 *
 */
public class AndroidCustomFontSupport {

	public static SpannableString getStringRepresentationInCustomFont(Context context,String text,Typeface customFont, float size)
	{	
		return Constants.getSpannableWithFont(context, text, customFont,size);
	}
	/**
	 * Get a spannable string with proper bangla font encoded in UTF-8.
	 * The spannable string can be set for any view elements which has 
	 * setText(String),setTitle(String) or any other method that takes
	 * a string to display.
	 * @param context The context of the application
	 * @param text The non-unicode bangla text
	 * @param size The font size; this wont have much effect if the view has
	 * a fixed text size.(e.g.textView.setTextSize);give a value of -1 if you
	 * don't need to change font size and wish to use the default.
	 * @return
	 */
	public static SpannableString getCorrectedBengaliFormat(String text,Typeface font,float size)
	{
		SpannableString spannable=null;
		boolean banglaSupported=AndroidCustomFontSupport.isBanglaSupportedInDevice();
		
		spannable=Constants.getBanglaSpannableWithSize(text, font, size, banglaSupported);
//		spannable=Constants.getBanglaSpannableWithSize(text, font, size, true);//test
		return spannable;
	}
	/**
	 * Checks if the device has native bangla support.
	 * true if supported.
	 * @return Boolean
	 */
	public static boolean isBanglaSupportedInDevice()
	{
		Locale[] locales=Locale.getAvailableLocales();
		for(Locale locale:locales)
		{
			//any possible bangla support
			if((locale.toString().toLowerCase().contains("bn") && (locale.toString().toLowerCase().contains("bd") || locale.toString().toLowerCase().contains("bengali"))) 
					|| locale.getCountry().toLowerCase().contains("bd") || locale.getDisplayCountry().toLowerCase().contains("bd"))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Get a spannable string encoded in UTF-8.
	 * The spannable string can be set for any view elements which has 
	 * setText(String),setTitle(String) or any other method that takes
	 * a string to display.
	 * N.B. You have to take care for font stuffs on yours.
	 * Takes care of checking if the device supports bangla.
	 * @param text a simple plain string;should not be encoded in utf before
	 * @return SpannableString
	 */
	public static SpannableString getCorrectedBengaliFormat(String text)
	{
		if(!AndroidCustomFontSupport.isBanglaSupportedInDevice())
		{
			return Constants.getBanglaSpannable(text);
		}
		return new SpannableString(text);//normal
	}
	/**
	 * Get a string encoded in UTF-8.
	 * 
	 * Takes care of checking if the device supports bangla.
	 * @param text
	 * @return String
	 */
	public static String getCorrectedBengaliFormatAsString(String text)
	{
		if(!AndroidCustomFontSupport.isBanglaSupportedInDevice())
		{
			return Constants.getBanglaString(text);
		}
		return text;//send it back!Moron,your device supports bangla!
	}
	/**
	 * Experimental! On some devices <strong>getCorrectedBengaliFormat</strong> is not 
	 * working for titlebars,tabs and native components.Use this method for them instead.
	 * This method is a bit naive,checks if the device supports bangla,if doesn't 
	 * forces to use BIJOY for which you must provide it an array of fonts containing
	 * both the unicode and BIJOY fonts.
	 * @param text a simple plain string;should not be encoded in utf before
	 * @param fonts a 2 items array of fonts. The first one is a unicode font 
	 * of course <b>solaimanlipinormal.ttf</b> & the second one is a bijoy font(e.g.<b>karnafuli.ttf</b>)
	 * The reverse of this rule will result in a mess!
	 * @param size
	 * @return
	 */
	public static SpannableString getCorrectedBengaliFormatForNativeComponents(String text,Typeface[] fonts,float size)
	{
		if(!AndroidCustomFontSupport.isBanglaSupportedInDevice())
		{
			//if bangla is not supported
			return Constants.getBanglaInBijoy(text, fonts[1], size);
		}
		return Constants.getBanglaSpannableWithSizeForNativeItems(text, fonts[0], size);
	}
	/**
	 * This method is same as the old bls native library.Provided just to ensure backward 
	 * compatibility. Recommended to use getBengaliUTFWithFontSize.
	 * @see getBengaliUTFWithFontSize
	 * @param context context The context of the application
	 * @param text The non-unicode bangla text
	 * @param view The view which has some text attributes to be displayed.
	 * (e.g.Button,TextView,EditText)
	 */
	@Deprecated
	public static void getBengaliUTF(String text,Typeface font,View view)
	{
		boolean banglaSupported=AndroidCustomFontSupport.isBanglaSupportedInDevice();
		SpannableString string=Constants.getBanglaSpannableWithSize(text,font, -1, banglaSupported);
		if(view instanceof TextView)
		{
			((TextView)view).setText(string);
		}
		else if(view instanceof Button)
		{
			((Button)view).setText(string);
		}
		else if(view instanceof EditText)
		{
			((EditText)view).setText(string);
		}
	}
	/**
	 * Converts a simple plain string to UTF-8
	 * @param text
	 * @return
	 */
	public static String getBanglaUTFFromText(String text)
	{
		return Constants.getBanglaString(text);
	}
}
