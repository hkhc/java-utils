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

import android.text.Spanned;

import io.hkhc.utils.Consts;

/**
 * Helper methods for span related operations
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SpanUtils {

    public static final String TAG = Consts.logTag("SU");

    /**
     * Check if a spanned object contains spans of specific class
     * @param s The spanned to be searched
     * @param spanClass The target span class
     * @return true if a span of the target class is found in s. false otherwise.
     */
    public static boolean hasSpan(Spanned s, Class<?> spanClass) {
        return s.getSpans(0, s.length(), spanClass).length>0;
    }

    public static boolean isComposing(Spanned s) {
        return isComposing(s, 0, s.length());
    }

    /**
     * Check if the spanned is working with EditText and IME, that the IME is in composing mode.
     * IME is in composing mode when it has yet accept enough input to generate a word or phase based
     * on the IME rules.
     *
     * Reference of composing mode:
     * http://qiita.com/amay077/items/73da05ab80d23e28703a
     * http://stackoverflow.com/questions/16392417/explain-the-definitions-of-these-flags-span-composing-span-user-etc-from-th
     *
     * @param s The spanned to be checked
     * @param start The start point (inclusive) of the string to be checked
     * @param end The end point (exclusive) of the string to be checked
     * @return true if the string is in composing mode. false otherwise.
     */
    public static boolean isComposing(Spanned s, int start, int end) {
        Object[] spans = s.getSpans(start, end, Object.class);
        for(Object span : spans) {
            // TODO the SPAN_COMPOSING flag shall be combined with the bitMask SPAN_POINT_MASK_MASK
            if ((s.getSpanFlags(span) & Spanned.SPAN_COMPOSING)==Spanned.SPAN_COMPOSING) {
                return true;
            }
        }
        return false;
    }

    /**
     * List array of spans as a space delimited string of class names.
     * @param spans array of spans to be listed
     * @return Space delimited list of class names of spans.
     */
    public static String listSpans(Object[] spans) {

        StringBuilder b = new StringBuilder();
        for(Object s :spans) {
            b.append(s.getClass().getName());
            b.append(' ');
        }

        return b.toString();

    }

    /**
     * List array of classes as a space delimited string of class names.
     * @param cs array of classes to be listed
     * @return Space delimited list of classes
     */
    public static String listClass(Class[] cs) {

        StringBuilder b = new StringBuilder();
        for(Class c : cs) {
            b.append(c.getName());
            b.append(' ');
        }

        return b.toString();

    }

//    public  Object[] dumpSpans(Spanned s) {
//        Object[] spans = s.getSpans(0, s.length(), HKIDParser.HkidSpan.class);
//        for(Object span : spans) {
//            Log.d(TAG, "span class = " + span.getClass().getName() + " s:" + s.getSpanStart(span) + " e:" + s.getSpanEnd(span));
//        }
//        return spans;
//    }


}
