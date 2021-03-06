/*
 * Copyright 2015 Eric Liu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anony.okhttp;

import android.text.TextUtils;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.HashMap;

/**
 * Created by Anonymous on 15/8/25.
 */
public class RequestParams {

    private final HashMap<String, String> params;

    public RequestParams() {
        params = new HashMap<>();
    }

    public void put(String key, String value) {
        params.put(key, value);
    }

    public void put(String key, Object value) {
        params.put(key, value.toString());
    }

    public void remove(String key) {
        params.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }

            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(entry.getValue());
        }

        return stringBuilder.toString();
    }

    public String toQueryString(String url) {
        String queryString = toString();

        if (!TextUtils.isEmpty(queryString)) {
            url += url.contains("?") ? "&" : "?";
            url += queryString;
        }

        return url;
    }

    public RequestBody toRequestBody() {
        FormEncodingBuilder builder = new FormEncodingBuilder();

        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        return builder.build();

    }

}
