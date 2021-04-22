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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by herman on 22/8/2016.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class PrintWriterLog extends AbstractLog {

    public static class Factory implements LogFactory {
        private PrintWriter writer;
        public Factory(PrintWriter writer) {
            this.writer = writer;
            L l = newLog("Factory");
            l.i("------- start of log --------");
        }
        @Override
        public L newLog(String tag) {
            return new PrintWriterLog(tag, writer);
        }
    }

    private PrintWriter writer;
    private DateFormat dateFormat;
    private String pidStr;
    private boolean useTimestamp = true;

    public PrintWriterLog(String tag, PrintWriter writer) {
        this(tag, true, writer);
    }

    public PrintWriterLog(String tag, boolean timestamp, PrintWriter writer) {
        super(tag);
        this.writer = writer;
        dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
        int pid;
        try {
            Class<?> clazz = Class.forName("android.os.Process");
            Method m = clazz.getMethod("myPid");
            pid = (int)m.invoke(null);
        }
        catch (Throwable t) {
            pid = 0;
        }
        if (pid==0) {
            pidStr = "-";
        }
        else {
            pidStr = Integer.toString(pid);
        }
    }

    private void printPadding(PrintWriter writer, int size) {
        for(int i=0;i<size;i++) writer.print(' ');
    }

    private void _log(char verbosity, String tag, String msg) {
        if (!filter(tag)) return;
        String prefix =
                (useTimestamp? dateFormat.format(System.currentTimeMillis()) + ' ' : "")
                        + pidStr + '/'
                        + tag + ' '
                        + verbosity + '/';
        int currPos = 0;
        boolean first = true;
        while (currPos<msg.length()) {
            if (first)
                writer.print(prefix);
            else
                printPadding(writer, prefix.length());
            int lineBreak = msg.indexOf('\n', currPos);
            if (lineBreak==-1) {
                writer.println(msg.substring(currPos));
                break;
            }
            else {
                writer.println(msg.substring(currPos, lineBreak));
                currPos = lineBreak+1;
                first = false;
            }
        }
    }

    @Override
    public void d(String tag, String msg) {
        if (msg==null) {
            _log('d', tag, "null");
        } else {
            _log('d', tag, msg);
        }
    }

    @Override
    public void w(String tag, String msg) {
        if (msg==null){
            _log('w', tag, "null");
        } else {
            _log('w', tag, msg);
        }
    }

    @Override
    public void i(String tag, String msg) {
        if (msg==null){
            _log('i', tag, "null");
        } else {
            _log('i', tag, msg);
        }
    }

    @Override
    public void e(String tag, String msg) {
        if (msg==null){
            _log('e', tag, "null");
        } else {
            _log('e', tag, msg);
        }
    }

    private void logException(String tag, Throwable t) {
        StringWriter strWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(strWriter);
        t.printStackTrace(printWriter);
        printWriter.flush();
        _log('e', tag, strWriter.toString());
    }

    @Override
    public void e(String tag, String msg, Throwable t) {
        if (msg==null){
            _log('e', tag, "null");
        } else {
            _log('e', tag, msg);
        }
        logException(tag, t);
    }
}
