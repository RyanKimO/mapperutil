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

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import springfox.documentation.spring.web.json.Json;

/**
 * {@code SpringfoxJsonSerializer} is a JSON serializer for Springfox's {@code Json} class.
 * <p/>
 */
public class SpringfoxJsonToGsonTypeAdapter implements JsonSerializer<Json> {

  @Override
  public JsonElement serialize(Json json, Type type,
      JsonSerializationContext jsonSerializationContext) {
    JsonParser parser = new JsonParser();
    return parser.parse(json.value());
  }
}