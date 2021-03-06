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

import java.io.File;
import java.io.IOException;

/**
 * Created by herman on 21/2/2016.
 */
public interface L {

    String getTag();
    void d(String msg);
    void w(String msg);
    void i(String msg);
    void e(String msg);
    void e(String msg, Throwable t);
    void d(String tag, String msg);
    void w(String tag, String msg);
    void i(String tag, String msg);
    void e(String tag, String msg);
    void e(String tag, String msg, Throwable t);
    void i(File f) throws IOException;

}
