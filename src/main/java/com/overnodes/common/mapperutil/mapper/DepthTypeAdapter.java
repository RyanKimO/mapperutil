/*
 *     Copyright 2018 Overnodes. All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ================================================================================
 *
 *      Developer : John Koo
 *      Date :      11/08/2018
 *      Contact :   johnkoo@overnodes.com
 *
 *  ================================================================================
 *
 */
package com.overnodes.common.mapperutil.mapper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepthTypeAdapter implements JsonDeserializer<Depth> {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Override
  public Depth deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    List<String> ignore = new ArrayList<>();
    if (json.getAsJsonArray().get(2).getAsJsonArray().size() > 0) {
      String ignoreElem = json.getAsJsonArray().get(2).getAsJsonArray().get(0).getAsString();
      ignore.add(ignoreElem);
    }
    return new Depth(json.getAsJsonArray().get(0).getAsDouble(),
        json.getAsJsonArray().get(1).getAsDouble(), ignore);
  }


}
