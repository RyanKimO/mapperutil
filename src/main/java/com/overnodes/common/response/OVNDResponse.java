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
 *      Date :      06/05/2018
 *      Contact :   johnkoo@overnodes.com
 *
 *  ================================================================================
 *
 */
package com.overnodes.common.response;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.overnodes.common.mapperutil.MapperUtil;
import com.overnodes.common.mapperutil.NullUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OVNDResponse Object TODO 공통 라이브러리로 빼내기
 *
 * @author John Koo
 * @desc 오버노드 서비스에서 공통적으로 사용하는 Response 오브젝트로 Response에 대한 결과 및 데이터를 반환
 */
public class OVNDResponse extends RuntimeException {

  public static final OVNDResponse SUCCESS_NORMAL = new OVNDResponse(
      OVNDResponseCode.SUCCESS_NORMAL);
  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  protected String responseCode;
  protected String responseMessage;
  protected JsonElement responseData;
  protected String additionalMessage;

  public OVNDResponse() {
  }

  // Constructors
  public OVNDResponse(final OVNDResponseCode OVNDResponseCode) {
    this.responseCode = OVNDResponseCode.getCode();
    this.responseMessage = OVNDResponseCode.getMessage();
  }

  public OVNDResponse(final OVNDResponseCode responseCode, final Object data) {
    this.setReturnValue(responseCode, data, null);
  }

  public OVNDResponse(final OVNDResponseCode responseCode, final Object data,
      final String additionalMessage) {
    this.setReturnValue(responseCode, data, additionalMessage);
  }

  public JsonObject getJsonObject() {
    final JsonObject result;
    if (StringUtils.isNotEmpty(this.responseCode)) {
      result = new JsonObject();
      result.addProperty("responseCode", this.responseCode);
      result.addProperty("responseMessage", this.responseMessage);
      if (NullUtil.isNotEmpty(this.responseData)) {
        result.addProperty("responseData", MapperUtil.writeObjectAsJsonString(this.responseData));
      }
      if (StringUtils.isNotEmpty(this.additionalMessage)) {
        result.addProperty("additionalMessage", this.additionalMessage);
      }
    } else {
      return OVNDException.EXCEPTION_ERROR_SYSTEM.getJsonObject();
    }
    return result;
  }

  public <T> T getResponseDataAsObject(final Class<T> classType) {
    return MapperUtil.getGsonInstance().fromJson(this.getResponseData().getAsString(), classType);
  }

  public String getResponseCode() {
    return this.responseCode;
  }

  public void setResponseCode(final String responseCode) {
    this.responseCode = responseCode;
  }

  public String getResponseMessage() {
    return this.responseMessage;
  }

  public void setResponseMessage(final String responseMessage) {
    this.responseMessage = responseMessage;
  }

  public JsonElement getResponseData() {
    return this.responseData;
  }

  public void setResponseData(final JsonElement responseData) {
    this.responseData = responseData;
  }

  public String getAdditionalMessage() {
    return this.additionalMessage;
  }

  public void setAdditionalMessage(final String additionalMessage) {
    this.additionalMessage = additionalMessage;
  }

  protected void setReturnValue(final OVNDResponseCode code, final Object data,
      final String additionalMessage) {
    this.responseCode = code.getCode();
    this.responseMessage = code.getMessage();
    this.responseData = MapperUtil.writeObjectAsJsonElement(data);
    this.additionalMessage = additionalMessage;
  }

}
