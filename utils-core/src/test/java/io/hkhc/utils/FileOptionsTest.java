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

package io.hkhc.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 6/1/2018.
 */

public class FileOptionsTest {

    @Test
    public void testDefaultBufferSize() {

        FileOptions options = new FileOptions();

        assertThat(options.getBufferSize()).isEqualTo(FileOptions.DEFAULT_BUFFER_SIZE);

    }

    @Test
    public void testChangeBufferSize() {

        FileOptions options = new FileOptions().bufferSize(12345);

        assertThat(options.getBufferSize()).isEqualTo(12345);

        options.setBufferSize(23456);

        assertThat(options.getBufferSize()).isEqualTo(23456);

    }

    @Test
    public void testDefaultEncoding() {

        FileOptions options = new FileOptions();

        assertThat(options.getEncoding()).isEqualTo(FileOptions.DEFAULT_ENCODING);

    }

    @Test
    public void testChangeEncoding() {

        FileOptions options = new FileOptions().encoding("ABCDE");

        assertThat(options.getEncoding()).isEqualTo("ABCDE");

        options.setEncoding("MNOPQ");
        assertThat(options.getEncoding()).isEqualTo("MNOPQ");

    }

    @Test
    public void testDefaultCloseStream() {

        FileOptions options = new FileOptions();

        assertThat(options.isCloseInputStream()).isTrue();
        assertThat(options.isCloseOutputStream()).isTrue();

    }

    @Test
    public void testChangeCloseStream() {

        FileOptions options = new FileOptions()
                .closeInputStream(false)
                .closeOutputStream(false);

        assertThat(options.isCloseInputStream()).isFalse();
        assertThat(options.isCloseOutputStream()).isFalse();

        options.setCloseInputStream(true);
        options.setCloseOutputStream(true);

        assertThat(options.isCloseInputStream()).isTrue();
        assertThat(options.isCloseOutputStream()).isTrue();

    }

    @Test
    public void testDefaultProgress() {

        FileOptions options = new FileOptions();

        assertThat(options.getProgressCallback()).isNotNull();

    }

    @Test
    public void testExecuteProgress() {

        FileOptions options = new FileOptions();

        ProgressList list = new ProgressList();

        options.setProgressCallback(list);

        options.getProgressCallback().progress(100);

        assertThat(list.getProgressList()).hasSize(1);

    }

}
