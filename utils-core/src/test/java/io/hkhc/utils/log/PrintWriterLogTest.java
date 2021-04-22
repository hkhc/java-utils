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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import io.hkhc.utils.log.PrintWriterLog;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 30/10/2016.
 * To be run in non-android (i.e. non roboletric) environment intentionally
 * @see PrintWriterLog
 */
public class PrintWriterLogTest {

    StringWriter stringWriter;
    PrintWriter printWriter;
    PrintWriterLog log;

    @Before
    public void setUp() throws Exception {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter, true);
        log = new PrintWriterLog("TAG", printWriter);
    }

    @Test
    /**
        @see XVAND-447
     */
    public void test_can_create_object_without_android() {

        log.d("HELLO");
        String result = stringWriter.toString();
        assertThat(result.trim()).endsWith("-/TAG d/HELLO");

    }

    @Test
    public void test_print_writer_log_with_null_messages() {

        log.d(null);
        assertThat(stringWriter.toString().trim()).endsWith("null");
        log.w(null);
        assertThat(stringWriter.toString().trim()).endsWith("null");
        log.e(null);
        assertThat(stringWriter.toString().trim()).endsWith("null");

        String emptyStringObject = null;
        log.i(emptyStringObject);
        assertThat(stringWriter.toString().trim()).endsWith("null");

    }

    @After
    public void tearDown(){
        printWriter.close();
    }

}