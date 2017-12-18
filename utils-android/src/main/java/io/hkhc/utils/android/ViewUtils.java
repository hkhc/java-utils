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

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by pandac on 10/12/14.
 */
public class ViewUtils {

    public static void text(ViewGroup vg, int resId, TextWatcher w) {

        TextView tv = (TextView)vg.findViewById(resId);
//        tv.addTextChangedListener(new );

    }

    private static class SimpleTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // do nothing intentionally
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // do nothing intentionally
        }

        @Override
        public void afterTextChanged(Editable s) {
            // do nothing intentionally
        }
    }

    /**
     * Shorthand to accumulate filters to EditText. Existing filters are preserved. New filters in
     * parameters are add to the end of the array.
     * @param et target EditText
     * @param filters the filters to be added
     */
    public static void addFilter(EditText et, InputFilter... filters){

        InputFilter[] filtersInUse = et.getFilters();
        InputFilter[] newFilterSet = new InputFilter[filtersInUse.length+filters.length];

        System.arraycopy(filtersInUse, 0, newFilterSet, 0, filtersInUse.length);
        System.arraycopy(filters, 0, newFilterSet, filtersInUse.length, filters.length);
        et.setFilters(newFilterSet);

    }

    /**
     * Convert dp (device independent pixel) value to pixel value
     * @param dip the dp value to be converted
     * @param context the context that determine the dp convesion ratio. Normally it is the activity
     *                reference that render on screen.
     * @return the converted value in pixel.
     */
    public static float dp2px(int dip, Context context){
        float scale = context.getResources().getDisplayMetrics().density;
        return dip * scale + 0.5f;
    }


}
