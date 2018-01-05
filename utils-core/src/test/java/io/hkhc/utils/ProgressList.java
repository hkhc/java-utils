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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herman on 6/1/2018.
 */

public class ProgressList implements FileOptions.Progress {

    final List<Integer> progressList = new ArrayList<>();

    @Override
    public void progress(int count) {
        progressList.add(count);
    }

    public List<Integer> getProgressList() {
        return progressList;
    }


}

