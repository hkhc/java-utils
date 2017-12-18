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
 * Created by hermanc on 23/8/16.
 */
public class CompositeLog extends AbstractLog {

    private L[] logs;

    public static class Factory implements LogFactory {

        private LogFactory[] factories;

        public Factory(LogFactory... factories) {
            this.factories = factories;
        }

        @Override
        public L newLog(String tag) {

            L[] l = new L[factories.length];

            for(int i=0;i<factories.length;i++) {
                l[i] = factories[i].newLog(tag);
            }

            return new CompositeLog(tag, l);
        }


    }

    public CompositeLog(String tag, L[] logs) {
        super(tag);
        this.logs = logs;
    }

    @Override
    public void d(String tag, String msg) {
        for(L l : logs) {
            l.d(tag, msg);
        }
    }

    @Override
    public void w(String tag, String msg) {
        for(L l : logs) {
            l.w(tag, msg);
        }
    }

    @Override
    public void i(String tag, String msg) {
        for(L l : logs) {
            l.i(tag, msg);
        }
    }

    @Override
    public void e(String tag, String msg) {
        for(L l : logs) {
            l.e(tag, msg);
        }
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        for(L l : logs) {
            l.e(tag, msg, t);
        }
    }
}
