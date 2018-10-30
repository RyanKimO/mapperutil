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
 *      Date :      05/06/2018
 *      Contact :   tedkim@overnodes.com
 *
 *  ================================================================================
 *
 */

package com.overnodes.common.mapperutil.mapper;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * {@code SpringfoxUiConfigurationJsonSerializer} is a JSON serializer for Springfox's {@code
 * UiConfiguration} class.
 * <p/>
 */
public class SpringfoxUiConfigurationJsonSerializer
    implements JsonSerializer<UiConfiguration> {

  @Override
  public JsonElement serialize(UiConfiguration config, Type type,
      JsonSerializationContext jsonSerializationContext) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("validatorUrl", config.getValidatorUrl());
    jsonObject.addProperty("apisSorter", config.getApisSorter());

    JsonArray array = new JsonArray();
    for (String value : config.getSupportedSubmitMethods()) {
      array.add(new JsonPrimitive(value));
    }

    jsonObject.add("supportedSubmitMethods", array);
    jsonObject.addProperty("jsonEditor", config.isJsonEditor());
    jsonObject.addProperty("showRequestHeaders",
        config.isShowRequestHeaders());
    jsonObject.addProperty("requestTimeout", config.getRequestTimeout());

    return jsonObject;
  }
}