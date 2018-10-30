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
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import springfox.documentation.swagger.web.SecurityConfiguration;

/**
 * {@code SpringfoxSecurityConfigurationJsonSerializer} is a JSON serializer for Springfox's {@code
 * SecurityConfiguration} class.
 * <p/>
 */
public class SpringfoxSecurityConfigurationJsonSerializer
    implements JsonSerializer<SecurityConfiguration> {

  @Override
  public JsonElement serialize(SecurityConfiguration config,
      Type type, JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("clientId", config.getClientId());
    jsonObject.addProperty("realm", config.getRealm());
    jsonObject.addProperty("appName", config.getAppName());
    jsonObject.addProperty("apiKey", config.getApiKey());
    jsonObject.addProperty("apiKeyName", config.getApiKeyName());
    jsonObject.addProperty("clientSecret", config.getClientSecret());
    jsonObject.addProperty("scopeSeparator", config.scopeSeparator());
    jsonObject.addProperty("apiKeyVehicle", config.getApiKeyVehicle());

    return jsonObject;
  }
}