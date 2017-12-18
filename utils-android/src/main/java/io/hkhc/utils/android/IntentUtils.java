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

package io.hkhc.utils.android;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by hermanc on 1/11/2016.
 * TODO merge with com.expressvpn.vpn.IntentUtils
 */
@SuppressWarnings("unused")
public class IntentUtils {


    public static String dumpIntent(Intent i){

        StringBuilder builder = new StringBuilder();

        builder.append("action : ")
                .append(i.getAction())
                .append("\n");
        if (i.getData()!=null) {
            builder.append("data : ")
                    .append(i.getDataString())
                    .append("\n");
        }

        if (i.getComponent()!=null) {
            builder.append("component : ")
                    .append(i.getComponent())
                    .append("\n");
        }

        if (i.getCategories()!=null && !i.getCategories().isEmpty()) {
            builder.append("categories :");
            for (String s : i.getCategories()) {
                builder.append(" ").append(s);
            }
            builder.append("\n");
        }

        Bundle bundle = i.getExtras();
        if (bundle != null) {
            for(String key : bundle.keySet()) {
                builder.append("[")
                        .append(key).append("=").append(bundle.get(key))
                        .append("]")
                        .append('\n');
            }
        }
        return builder.toString();
    }


}
