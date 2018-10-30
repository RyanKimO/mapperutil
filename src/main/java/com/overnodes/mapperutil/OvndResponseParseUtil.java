/*
 *     Copyright 2018 Overnodes. All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the “License”);
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an “AS IS” BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ================================================================================
 *
 *      Developer : Ted Kim
 *      Date :      12/10/2018
 *      Contact :   tedkim@overnodes.com
 *
 *  ================================================================================
 *
 */

package com.overnodes.mapperutil;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.overnodes.ui.kr.v2.common.response.OVNDResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

public class OvndResponseParseUtil {

  public static OVNDResponse parseJsonToOVNDResponse(JsonObject jsonObject) {
    return MapperUtil.getGsonInstance().fromJson(jsonObject, OVNDResponse.class);
  }

  public static <T> T getActualObject(JsonObject jsonObject, Class<T> returnType) {
    JsonElement jsonElement = getResponseData(jsonObject);
    if (isEmptyData(jsonElement)) {
      return null;
    }

    String content = jsonElement.getAsString();
    return MapperUtil.writeStringAsObject(content, returnType);
  }

  public static <T> Collection<T> getActualCollection(JsonObject jsonObject,
      Class<T> returnType) {
    JsonElement jsonElement = getResponseData(jsonObject);

    if (isEmptyData(jsonElement)) {
      return Collections.EMPTY_LIST;
    }
    String content = jsonElement.getAsString();
    Type listType = createListType(returnType);

    return MapperUtil.writeStringAsObject(content, listType);
  }

  private static boolean isEmptyData(JsonElement jsonElement) {
    return jsonElement == null;
  }

  private static JsonElement getResponseData(JsonObject jsonObject) {
    OVNDResponse ovndResponse = parseJsonToOVNDResponse(jsonObject);
    return ovndResponse.getResponseData();
  }

  private static <T> Type createListType(Class<T> classOfT) {
    return TypeToken.getParameterized(Collection.class, classOfT).getType();
  }
}