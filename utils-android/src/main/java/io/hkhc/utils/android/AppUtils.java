/*
 * Copyright (c) 2017. Herman Cheung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.hkhc.utils.android;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.hkhc.utils.ArrayUtils;


/**
 * Created by pandac on 24/12/14.
 */
@SuppressWarnings("unused")
public class AppUtils {

    /**
     * Construct a localized String for current version of the app
     * @param c Context of the app
     * @param versionString the resource ID for the String template to construct the version String.
     *                      The template consists of two placeholder. The first is for version name
     *                      and the second is for version code.
     * @return The string of version. If the String template is not found.
     */
    public static String getVersion(Context c, int versionString) {
        PackageManager pm = c.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(c.getPackageName(), 0);
            return c.getString(versionString, info.versionName, info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            // It is unlikely that the PackageManager cannot find PackageInfo of the package of the
            // currently running app.
            return "UNKNOWN";
        }

    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean myStartActivity(Context c, Intent aIntent) {
        try
        {
            c.startActivity(aIntent);
            return true;
        }
        catch (ActivityNotFoundException e)
        {
            return false;
        }
    }

    /**
     * Jump to the app page for Google Play Store, so that user may rate the app
     * http://martin.cubeactive.com/android-how-to-create-a-rank-this-app-button/
     * @param c Context
     */
    public static void rateApp(Context c) {

        String packageName = c.getPackageName();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //Try Google play
        intent.setData(Uri.parse("market://details?id="+packageName));
        if (!myStartActivity(c, intent)) {
            //Market (Google play) app seems not installed, let's try to open a webbrowser
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?"+packageName));
            if (!myStartActivity(c, intent)) {
                //Well if this also fails, we have run out of options, inform the user.
                Toast.makeText(c, "Could not open Android market, please install the market app.", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // Google Inbox, Yahoo Mail, Outlook shall use this
    // GMail are OK too
    public static Intent createSendMailIntent2(Context c, Class cls, String[] emailTo, String[] emailCc, String subject, Uri attachment) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        if (cls!=null) intent.setClass(c, cls);

        intent.setType("message/rfc822");

        String body = "";

        intent.putExtra(Intent.EXTRA_EMAIL, emailTo);
        if (emailCc!=null && emailCc.length>0) {
            intent.putExtra(Intent.EXTRA_CC, emailCc);
        }

        // Most mail client get subject line here
        intent.putExtra(Intent.EXTRA_SUBJECT,  subject); /* getResources().getString(R.string.email_subject)*/
        intent.putExtra(Intent.EXTRA_TEXT,     body);

        return intent;
    }

    // Gmail, Dropbox Mailbox, Cloud Magic shall use this
    public static Intent createSendMailIntent(Context c, Class cls, String[] emailTo, String[] emailCc, String subject, Uri attachment) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        if (cls!=null) intent.setClass(c, cls);

        String body = "";

        // ** It is OK for most mail client just by specifying "mailto:" in data field.
        // However, Dropbox's Mailbox require email address present there too.
        // ** If mailto: is omitted, no mail client can resolve for ACTION_SENDTO.
        try {
            intent.setData(Uri.parse(
                "mailto:" + emailTo[0]
                + "?subject=" + URLEncoder.encode(subject, "utf-8")
                + "&body=" + URLEncoder.encode(body, "utf-8")
            ));
        }
        catch (UnsupportedEncodingException e) {
            // should not reach there
        }

        // ** ACTON_SENDTO and setType won't work together
        // intent.setType("message/rfc822");

        // Most mail client get addresses here
        intent.putExtra(Intent.EXTRA_EMAIL, emailTo);
        if (!ArrayUtils.isEmpty(emailCc)) {
            intent.putExtra(Intent.EXTRA_CC, emailCc);
        }

        // Most mail client get subject line here
        intent.putExtra(Intent.EXTRA_SUBJECT,  subject);
        intent.putExtra(Intent.EXTRA_TEXT,     body);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;

    }

}
