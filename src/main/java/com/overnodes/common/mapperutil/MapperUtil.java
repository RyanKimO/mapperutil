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
 *      Date :      07/05/2018
 *      Contact :   johnkoo@overnodes.com
 *
 *  ================================================================================
 *
 */
package com.overnodes.common.mapperutil;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.overnodes.common.mapperutil.mapper.Depth;
import com.overnodes.common.mapperutil.mapper.DepthTypeAdapter;
import com.overnodes.common.mapperutil.mapper.OVNDDateTypeAdapter;
import com.overnodes.common.mapperutil.mapper.SpringfoxApiListingJsonSerializer;
import com.overnodes.common.mapperutil.mapper.SpringfoxJsonToGsonTypeAdapter;
import com.overnodes.common.mapperutil.mapper.SpringfoxResourceListingJsonSerializer;
import com.overnodes.common.mapperutil.mapper.SpringfoxSecurityConfigurationJsonSerializer;
import com.overnodes.common.response.OVNDResponse;
import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import eu.bittrade.crypto.core.ECKey;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.math.ec.custom.sec.SecP256K1Curve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.ResourceListing;
import springfox.documentation.spring.web.json.Json;

public class MapperUtil {

  private static final Gson GSON_INSTANCE = new GsonBuilder()
      .registerTypeAdapter(Date.class, new OVNDDateTypeAdapter())
      .registerTypeAdapter(Depth.class, new DepthTypeAdapter())
      // needed for making calls to /v2/api-docs
      .registerTypeAdapter(Json.class,
          new SpringfoxJsonToGsonTypeAdapter())
      // rest are needed for making calls to /swagger-ui.html
      .registerTypeAdapter(ApiListing.class,
          new SpringfoxApiListingJsonSerializer())
//        .registerTypeAdapter(SwaggerResource.class,
//            new SpringfoxResourceJsonSerializer())
      .registerTypeAdapter(ResourceListing.class,
          new SpringfoxResourceListingJsonSerializer())
//        .registerTypeAdapter(UiConfiguration.class,
//            new SpringfoxUiConfigurationJsonSerializer())
      // needed if you have security
      .registerTypeAdapter(SecurityConfiguration.class,
          new SpringfoxSecurityConfigurationJsonSerializer())
      //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

      .setExclusionStrategies(new GsonExclusionStrategy(SecP256K1Curve.class))
      .setExclusionStrategies(new GsonExclusionStrategy(ECKey.class))
      .create();

  private static final Gson GSON_INSTANCE_WITH_NAMING_POLICY = new GsonBuilder()
      .registerTypeAdapter(Date.class, new OVNDDateTypeAdapter())
      .registerTypeAdapter(Depth.class, new DepthTypeAdapter())
      .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
      .create();


  private static final JsonParser JSON_PARSER_INSTANCE = new JsonParser();
  private static final Type LIST_OF_JSONOBJECT = new TypeToken<List<JsonObject>>() {
  }.getType();
  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public MapperUtil() {
    throw new UnsupportedOperationException();
  }

  private static Gson getGson() {
    return new GsonBuilder()
        .registerTypeAdapter(Date.class, new OVNDDateTypeAdapter())
        .setExclusionStrategies(new GsonExclusionStrategy(SecP256K1Curve.class))
        .setExclusionStrategies(new GsonExclusionStrategy(SecP256K1Curve.class))
        .create();
  }

  public static Gson getGsonInstance() {
    return GSON_INSTANCE;
  }

  public static Gson getGsonInstanceWithNamingPolicy() {
    return GSON_INSTANCE_WITH_NAMING_POLICY;
  }


  public static JsonParser getJsonParserInstance() {
    return JSON_PARSER_INSTANCE;
  }

  public static Type getListOfJsonobject() {
    return LIST_OF_JSONOBJECT;
  }

  // Object Mappers
  public static final <T> T writeObjectAsObject(Object content, final Class<T> returnType)
      throws OVNDResponse {
    return GSON_INSTANCE.fromJson(convertToJsonElement(content), returnType);
  }

  public static final <T> T writeObjectAsObject(Object content, Type typeOfT)
      throws OVNDResponse {
    return GSON_INSTANCE.fromJson(convertToJsonElement(content), typeOfT);
  }

  public static final JsonElement writeObjectAsJsonElement(Object content) {
    return GSON_INSTANCE.toJsonTree(content);
  }

  public static final String writeObjectAsJsonString(Object content) {
    return GSON_INSTANCE.toJsonTree(content).toString();
  }

  public static final <T> T writeStringAsObject(String content, Type typeOfT)
      throws OVNDResponse {
    return GSON_INSTANCE.fromJson(content, typeOfT);
  }

  public static final <T> T writeStringAsObject(String content, Class<T> returnType) {
    return GSON_INSTANCE.fromJson(content, returnType);
  }

  private static JsonElement convertToJsonElement(final Object object) {
    JsonElement jsonElement;

    if (object != null && StringUtils
        .equals(object.getClass().getSimpleName(), String.class.getName())) {
      jsonElement = JSON_PARSER_INSTANCE.parse((String) object);
    } else {
      jsonElement = writeObjectAsJsonElement(object);
    }

    return jsonElement;
  }


  public static class GsonExclusionStrategy implements ExclusionStrategy {

    private final Class<?> typeToExclude;

    public GsonExclusionStrategy(Class<?> clazz) {
      this.typeToExclude = clazz;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
      return (this.typeToExclude != null && this.typeToExclude == clazz);
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
      return false;
    }

  }


}
