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

package io.hkhc.utils;

/**
 * Created by herman on 24/6/15.
 */
public class ArrayUtils {

    public static Integer[] box(int[] array) {
        Integer[] ia = new Integer[array.length];
        for(int i=0;i<array.length;i++) {
            ia[i] = Integer.valueOf(array[i]);
        }

        return ia;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array==null || array.length==0;
    }

}
