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

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pandac on 5/1/15.
 */
@SuppressWarnings("unused")
public class AndroidFileUtils {

    private static final String ASSET_FILE_PREFIX = "/android_asset/";

    @SuppressLint("NewApi")
    public static String getDocumentPath() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT) {
            return Environment.DIRECTORY_DOCUMENTS;
        }
        else
            return "Documents";
    }

    public static InputStream openUriAsInputStream(Context context, Uri uri) throws IOException {

        if (ContentResolver.SCHEME_FILE.equals(uri.getScheme()) &&
            uri.getPath().startsWith(ASSET_FILE_PREFIX)) {
            int l = ASSET_FILE_PREFIX.length();
            return context.getAssets().open(uri.getPath().substring(l));
        }
        else {
            return context.getContentResolver().openInputStream(uri);
        }

    }

}
