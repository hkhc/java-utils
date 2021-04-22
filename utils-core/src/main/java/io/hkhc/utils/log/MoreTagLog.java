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

package io.hkhc.utils.log;

/**
 * Created by hermanc on 22/7/16.
 */
@SuppressWarnings("unused")
public class MoreTagLog extends AbstractLog {

    private L sourceLog;

    public MoreTagLog(String tag, L sourceLog) {
        super(tag);
        this.sourceLog = sourceLog;
    }

    @Override
    public void d(String tag, String msg) {
        sourceLog.d(tag + " : " + msg);
    }

    @Override
    public void w(String tag, String msg) {
        sourceLog.w(tag + " : " + msg);
    }

    @Override
    public void i(String tag, String msg) {
        sourceLog.i(tag + " : " + msg);
    }

    @Override
    public void e(String tag, String msg) {
        sourceLog.e(tag + " : " + msg);
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        sourceLog.e(tag + " : " + msg, t);
    }
}
