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

import java.util.Iterator;
import java.util.Set;

/**
 * Created by hermanc on 1/11/2016.
 * TODO merge with com.expressvpn.vpn.IntentUtils
 */

public class IntentUtils {


    public static String dumpIntent(Intent i){

        StringBuilder builder = new StringBuilder();

        builder.append("action : " + i.getAction());
        builder.append("\n");
        if (i.getData()!=null) {
            builder.append("data : " + i.getDataString());
            builder.append("\n");
        }

        if (i.getComponent()!=null) {
            builder.append("component : " + i.getComponent());
            builder.append("\n");
        }

        if (i.getCategories()!=null && !i.getCategories().isEmpty()) {
            builder.append("categories :");
            for (String s : i.getCategories()) {
                builder.append(" ");
                builder.append(s);
            }
            builder.append("\n");
        }

        Bundle bundle = i.getExtras();
        if (bundle != null) {
            Set<String> keys = bundle.keySet();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()) {
                String key = it.next();
                builder.append("[" + key + "=" + bundle.get(key)+"]");
                builder.append('\n');
            }
        }
        return builder.toString();
    }


}
