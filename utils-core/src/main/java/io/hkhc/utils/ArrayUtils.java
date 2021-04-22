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
 * Array helper methods
 */
@SuppressWarnings("unused")
public class ArrayUtils {

    /**
     * Box the elements of integer array
     * @param array the int array which elements to be boxed
     * @return Boxed Integer array
     */
    public static Integer[] box(int[] array) {
        Integer[] ia = new Integer[array.length];
        for(int i=0;i<array.length;i++) {
            ia[i] = array[i];
        }

        return ia;
    }

    /**
     * Check if an array is null or empty
     * @param array array to be inspected
     * @param <T> type of array
     * @return true if the array is null or empty
     */
    public static <T> boolean isEmpty(T[] array) {
        return array==null || array.length==0;
    }

}
