/*
 * Copyright (c) 2018. Herman Cheung
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
@SuppressWarnings({"unused", "WeakerAccess"})
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

    /**
     * Alias of {@link io.hkhc.utils.log.Logger#newLog(String)}
     * @param tag tag name
     * @return L logger with specified tag
     * @see Logger#newLog(String)
     */
    public static L withTag(String tag) { return newLog(tag);}

    /**
     * Create a logger with abbreviated name of provided class
     * @param clazz class to create tag
     * @return L logger with specified tag
     */
    public static L withCls(Class clazz) { return newLog(getLogTag(clazz));}

    /**
     * Create a logger with abbreviated name of the enclosed class of the provided class
     *
     * For example,
     * <code>
     * class AppleBananaOrange {
     *     private static final L l = Logger.withEncloses(new Object());
     * }
     * </code>
     *
     * The declaration creates a logger with tag "ABO"
     *
     * @param o object from an annonymous inner class of the target enclosing class
     * @return L logger with specified tag
     */
    public static L withEncloses(Object o) { return newLog(getLogTag(o.getClass().getEnclosingClass()));}

    /**
     * Given a class object, create a abbreviate string that represent it.
     * 1. package name is ignored
     * 2. take all capital letters and digits
     * 3. if there are less than two capital letters, the letter after those capital letters will
     * also be included. For example,
     * <code>
     *     getClassNameAbbr("OrangeBananaApple").equals("OBA")
     *     getClassNameAbbr("OrangeBanana").equals("OrBa")
     *     getClassNameAbbr("AOrange").equals("AOr")
     * </code>
     * @param clazz class object to get class name
     * @return abbreviated string
     */
    public static String getClassNameAbbr(Class clazz) {
        String name = clazz.getSimpleName();
        StringBuilder longBuilder = new StringBuilder();
        StringBuilder shortBuilder = new StringBuilder();

        char[] nameArray = name.toCharArray();
        boolean isLastCap = false;
        int shortGroup = 0;
        for(char c : nameArray) {
            boolean isCap = Character.isUpperCase(c) || Character.isDigit(c);
            // long version
            if (isCap) {
                longBuilder.append(c);
            }
            // short version
            if (shortGroup<=2) {
                if (isCap) {
                    shortBuilder.append(c);
                    shortGroup++;
                }
                else if (isLastCap) {
                    shortBuilder.append(c);
                }
            }

            isLastCap = isCap;
        }

        if (shortGroup>2) {
            return longBuilder.toString();
        }
        else {
            return shortBuilder.toString();
        }
    }

    /**
     * Create log tag from class name by abbreviation, and trim the result tag name
     * to 23 characters max. It is also the tag size limitation to Android Log.
     * @param clazz
     * @return
     */
    public static String getLogTag(Class clazz) {
        final String tag = metaTag + "_" + getClassNameAbbr(clazz);
        final int length = tag.length();
        if (length > MAX_LOG_TAG_LENGTH) {
            return tag.substring(0, MAX_LOG_TAG_LENGTH);
        }
        else {
            return tag;
        }
    }

}
