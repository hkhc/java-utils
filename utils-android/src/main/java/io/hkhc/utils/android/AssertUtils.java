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

import junit.framework.AssertionFailedError;

import static io.hkhc.utils.android.SpanUtils.listClass;
import static io.hkhc.utils.android.SpanUtils.listSpans;

/**
 * Created by herman on 13/8/15.
 */
@SuppressWarnings("unused")
public class AssertUtils {

    public static void assertSpanClassSequence(Object[] spans, Class... spanClasses) {
        if (spans.length!=spanClasses.length) {

            throw new AssertionFailedError("spans array length expect " + spanClasses.length +" : " + listClass(spanClasses)+ ", but it is " + spans.length + " : " + listSpans(spans));
        }
        for(int i=0;i<spans.length;i++) {
            if (!spans[i].getClass().equals(spanClasses[i]))
                throw new AssertionFailedError("span "+i+" expect "+spanClasses[i].getName()+ ", but it is "+spans[i].getClass().getName() );
        }
    }


}
