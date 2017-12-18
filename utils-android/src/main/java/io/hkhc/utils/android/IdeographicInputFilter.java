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

import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;

import io.hkhc.utils.Consts;
import io.hkhc.utils.StringUtils.IdeographicChecker;

import static io.hkhc.utils.StringUtils.getIdeographicChecker;

/**
 * Created by pandac on 12/12/14.
 * TODO : Symbol character still passed. Need a testcase (for level<19?)
 */
public class IdeographicInputFilter implements InputFilter {

    private static final String TAG = Consts.logTag("IIF");
    private boolean inverted = false;
    private IdeographicChecker ideographicChecker = getIdeographicChecker();

    public IdeographicInputFilter() {
        this(false);
    }

    public IdeographicInputFilter(boolean inverted) {
        this.inverted = inverted;
    }

    // invoked when filtering occur. so that the class may be extended to be notified text is changed
    protected void onFiltered(CharSequence result) {
        // do nothing
    }

    protected void onAccepted(CharSequence result) {
        // do nothing
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        SpannableStringBuilder ssb = new SpannableStringBuilder(source, start, end);

        // if an input method is composing text, not to filter at the momemt
        if ((source instanceof Spanned) && SpanUtils.isComposing((Spanned) source, start, end))
            return null;

        int curr = 0;
        boolean filtered = false; // become true if the input text has changed
        while (curr<ssb.length()) {
            char c = ssb.charAt(curr);
            boolean isIdeo = ideographicChecker.isIdeographic(c);
            if ((!inverted && !isIdeo) || (inverted && isIdeo) ) {
                ssb.replace(curr, curr + 1, "");
                filtered = true;
            }
            else
                curr++;
        }

        if (filtered)
            onFiltered(ssb);
        else
            onAccepted(ssb);

        return ssb;

    }


}
