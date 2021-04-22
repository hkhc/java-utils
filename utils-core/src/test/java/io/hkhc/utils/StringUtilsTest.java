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

import org.assertj.core.api.StrictAssertions;
import org.junit.Test;

import io.hkhc.utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by herman on 14/8/15.
 */
public class StringUtilsTest {

    @Test
    public void testConstruct() {
        StringUtils s = new StringUtils();
    }

    @Test
    public void testEqualsCharSequence() {

        StrictAssertions.assertThat(StringUtils.equalCharSequence(null, null)).isTrue();
        StrictAssertions.assertThat(StringUtils.equalCharSequence("a", null)).isFalse();
        StrictAssertions.assertThat(StringUtils.equalCharSequence(null, "a")).isFalse();
        StrictAssertions.assertThat(StringUtils.equalCharSequence("a", "b")).isFalse();
        StrictAssertions.assertThat(StringUtils.equalCharSequence("a", "a")).isTrue();

    }


    @Test
    public void testIsEmpty() {

        StrictAssertions.assertThat(StringUtils.isEmpty("")).isTrue();
        StrictAssertions.assertThat(StringUtils.isEmpty(null)).isTrue();
        StrictAssertions.assertThat(StringUtils.isEmpty(" ")).isFalse();
        StrictAssertions.assertThat(StringUtils.isEmpty("Hello")).isFalse();

    }

    @Test
    public void testOptional() {

        assertThat(StringUtils.optional("Hello")).isEqualTo("Hello");
        assertThat(StringUtils.optional("")).isEqualTo("");
        assertThat(StringUtils.optional(null)).isEqualTo("");

    }

    @Test
    public void testLengthInRange() {
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 0, 6)).isTrue();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 5, 6)).isTrue();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 0, 5)).isTrue();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 0, 4)).isFalse();
        StrictAssertions.assertThat(StringUtils.lengthInRange("Hello", 6, 6)).isFalse();
    }

    @Test
    public void testIdeographic() {
        StringUtils.IdeographicChecker checker = StringUtils.getIdeographicChecker();
        assertThat(checker).isInstanceOf(StringUtils.Jdk7IdeographicChecker.class);
        StrictAssertions.assertThat(checker.isIdeographic('一')).isTrue();
        StrictAssertions.assertThat(checker.isIdeographic('「')).isFalse();
        StrictAssertions.assertThat(checker.isIdeographic('1')).isFalse();
        StrictAssertions.assertThat(checker.isIdeographic('A')).isFalse();
    }

}
