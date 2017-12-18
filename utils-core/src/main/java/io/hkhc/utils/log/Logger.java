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

import io.hkhc.utils.Consts;

/**
 * Created by herman on 21/2/2016.
 */
public class Logger {

    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static LogFactory logFactory = new StdoutLog.Factory();
    private static String metaTag = Consts.META_TAG;

    public static void setLogFactory(LogFactory lf) {
        logFactory = lf;
    }
    public static void setMetaTag(String metaTag) {
        Logger.metaTag = metaTag;
    }

    public static L newLog(String tag) {
        return logFactory.newLog(metaTag+"_"+tag);
    }

    public static String getClassNameAbbr(Class clazz) {
        String name = clazz.getSimpleName();
        StringBuffer buffer = new StringBuffer();
        char[] nameArray = name.toCharArray();
        for(char c : nameArray) {
            if (Character.isUpperCase(c)) buffer.append(c);
        }
        return buffer.toString();
    }

    public static String getLogTag(Class clazz) {
        final String tag = "XV_"+ getClassNameAbbr(clazz);
        final int length = tag.length();
        if (length > MAX_LOG_TAG_LENGTH) {
            return tag.substring(0, MAX_LOG_TAG_LENGTH);
        }
        return tag;
    }
}
