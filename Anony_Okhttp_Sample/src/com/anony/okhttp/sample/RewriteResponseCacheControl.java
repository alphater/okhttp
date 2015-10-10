/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anony.okhttp.sample;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.File;
import java.io.IOException;

public final class RewriteResponseCacheControl {
  /** Dangerous interceptor that rewrites the server's cache-control header. */
  private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
    @Override public Response intercept(Chain chain) throws IOException {
      Response originalResponse = chain.proceed(chain.request());
      return originalResponse.newBuilder()
          .header("Cache-Control", "max-age=60")
          .build();
    }
  };

  private final OkHttpClient client;

  public RewriteResponseCacheControl(File cacheDirectory) throws Exception {
    Cache cache = new Cache(cacheDirectory, 1024 * 1024);
    cache.evictAll();

    client = new OkHttpClient();
    client.setCache(cache);
  }

  public void run() throws Exception {
    for (int i = 0; i < 5; i++) {
      System.out.println("    Request: " + i);

      Request request = new Request.Builder()
          .url("https://api.github.com/search/repositories?q=http")
          .build();

      if (i == 2) {
        // Force this request's response to be written to the cache. This way, subsequent responses
        // can be read from the cache.
        System.out.println("Force cache: true");
        client.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
      } else {
        System.out.println("Force cache: false");
        client.networkInterceptors().clear();
      }

      Response response = client.newCall(request).execute();
      response.body().close();
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

      System.out.println("    Network: " + (response.networkResponse() != null));
      System.out.println();
    }
  }

  public static void main(String... args) throws Exception {
    new RewriteResponseCacheControl(new File("RewriteResponseCacheControl.tmp")).run();
  }
}
