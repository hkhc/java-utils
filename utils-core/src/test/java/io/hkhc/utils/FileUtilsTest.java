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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 6/1/2018.
 */

public class FileUtilsTest {

    @Test
    public void testReadFileToString() throws IOException {

        assertThat(FileUtils.readFileToString("src/test/data/hello.txt"))
                .isEqualTo("Hello World");

    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFileToStringThrowException() throws IOException {

        FileUtils.readFileToString("src/test/data/non-exist.txt");

    }

    @Test
    public void testReadStreamToString() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        // when
        String result = FileUtils.readStreamToString(new ByteArrayInputStream(data));

        // then
        assertThat(result).isEqualTo(testData);

    }

    @Test
    public void testReadStreamToStringWithOptions() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        ProgressList prog = new ProgressList();

        FileOptions options = new FileOptions().bufferSize(5).progressCallback(prog);

        // when
        String result = FileUtils.readStreamToString(new ByteArrayInputStream(data),options);

        // then
        assertThat(result).isEqualTo(testData);
        assertThat(prog.getProgressList()).containsSequence(5,10,11);

    }

    @Test
    public void testReadStreamToByteArray() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        // when
        byte[] result = FileUtils.readStreamToByteArray(new ByteArrayInputStream(data));

        // then
        assertThat(result).isEqualTo(data);

    }

    @Test
    public void testReadStreamToByteArrayWithOptions() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        ProgressList prog = new ProgressList();

        FileOptions options = new FileOptions().bufferSize(5).progressCallback(prog);

        // when
        byte[] result = FileUtils.readStreamToByteArray(new ByteArrayInputStream(data),options);

        // then
        assertThat(result).isEqualTo(data);
        assertThat(prog.getProgressList()).containsSequence(5,10,11);

    }

    @Test
    public void testReadStreamToByteArray2() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        ProgressList prog = new ProgressList();

        FileOptions options = new FileOptions().bufferSize(5).progressCallback(prog);

        byte[] result = new byte[testData.length()];

        // when
        FileUtils.readStreamToByteArray(new ByteArrayInputStream(data),result, options);

        // then
        assertThat(result).isEqualTo(data);
        // always callback once, as we have a complete byte array buffer
        assertThat(prog.getProgressList()).containsSequence(11);

    }

    @Test
    public void testWriteStreamToStream() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        // when
        FileUtils.writeStreamToStream(output, new ByteArrayInputStream(data));


        // then
        assertThat(output.toByteArray()).isEqualTo(data);


    }

    @Test
    public void testWriteStreamToStreamWithOptions() throws IOException {

        // given
        String testData = "Hello World";
        byte[] data = testData.getBytes("UTF-8");

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ProgressList prog = new ProgressList();

        FileOptions options = new FileOptions().bufferSize(5).progressCallback(prog);

        // when
        FileUtils.writeStreamToStream(output, new ByteArrayInputStream(data), options);


        // then
        assertThat(output.toByteArray()).isEqualTo(data);
        assertThat(prog.getProgressList()).containsSequence(5,10,11);


    }

    @Test
    public void testGetDir() {

        assertThat(FileUtils.getDir("/a/b/c.txt")).isEqualTo("/a/b");

    }

    @Test
    public void testEnsureDirectory() throws IOException {

        // given
        File tempdir = File.createTempFile("io.hkhc.test", "dir");
        String dir = FileUtils.getDir(tempdir.toString()) + "/test"+System.currentTimeMillis();
        assertThat(new File(dir)).doesNotExist();

        // when
        FileUtils.ensureDirectory(dir);

        // then
        assertThat(new File(dir)).exists();

    }

    @Test
    public void testDeleteFolder() throws IOException {

        // given
        File tempdir = File.createTempFile("io.hkhc.test", "dir");
        String dir = FileUtils.getDir(tempdir.toString()) + "/test/a/b/c/d";
        FileUtils.ensureDirectory(dir);

        // when
        FileUtils.deleteFolder(new File(dir));

        // then
        assertThat(new File(dir)).doesNotExist();

    }


}
