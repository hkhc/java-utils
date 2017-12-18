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

package io.hkhc.utils.android.log;

import io.hkhc.utils.log.AbstractLog;
import io.hkhc.utils.log.L;
import io.hkhc.utils.log.LogFactory;

/**
 * Created by herman on 21/2/2016.
 */
public class AndroidLog extends AbstractLog {

    public static class Factory implements LogFactory {
        @Override
        public L newLog(String tag) {
            return new AndroidLog(tag);
        }
    }

    public AndroidLog(String tag) {
        super(tag);
    }

    @Override
    public void d(String tag, String msg) {
        if (!filter(tag)) return;
        android.util.Log.d(tag, msg);
    }

    @Override
    public void w(String tag, String msg) {
        if (!filter(tag)) return;
        android.util.Log.w(tag, msg);
    }

    @Override
    public void i(String tag, String msg) {
        if (!filter(tag)) return;
        android.util.Log.i(tag, msg);
    }

    @Override
    public void e(String tag, String msg) {
        if (!filter(tag)) return;
        android.util.Log.e(tag, msg);
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        if (!filter(tag)) return;
        android.util.Log.e(tag, msg, t);
    }
}
