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
 *      Date :      27/05/2018
 *      Contact :   johnkoo@overnodes.com
 *
 *  ================================================================================
 *
 */
package com.overnodes.response.response;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OVNDException extends OVNDResponse {

  public static final OVNDException EXCEPTION_ERROR_SYSTEM = new OVNDException(
      OVNDResponseCode.ERROR_SYSTEM_ERROR);
  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public OVNDException() {
  }

  public OVNDException(final OVNDResponseCode ovndResponseCode) {
    this.responseCode = ovndResponseCode.getCode();
    this.responseMessage = ovndResponseCode.getMessage();
  }

  public OVNDException(final Exception exception) {
    this.LOGGER.warn("ERROR MESSAGE : {}", ExceptionUtils.getStackTrace(exception));
    this.setReturnValue(this.extractResponseCode(exception.getClass().getSimpleName()), null,
        exception.getMessage());
  }

  public OVNDException(final Exception exception, final Object data) {
    this.LOGGER.warn("ERROR MESSAGE : {}", ExceptionUtils.getStackTrace(exception));
    this.setReturnValue(this.extractResponseCode(exception.getClass().getSimpleName()), data,
        exception.getMessage());
  }

  public OVNDException(final Throwable throwable) {
    this.LOGGER.warn("Received error message: {}", ExceptionUtils.getStackTrace(throwable));
    this.setReturnValue(this.extractResponseCode(throwable.getClass().getSimpleName()), null,
        throwable.getMessage());
  }

  public OVNDException(final Throwable throwable, final Object data) {
    this.LOGGER.warn("Received error message: {}", ExceptionUtils.getStackTrace(throwable));
    this.setReturnValue(this.extractResponseCode(throwable.getClass().getSimpleName()), data,
        throwable.getMessage());
  }

  private OVNDResponseCode extractResponseCode(final String exceptionName) {
    OVNDResponseCode responseCode = OVNDResponseCode.ERROR_SYSTEM_ERROR;

    switch (exceptionName) {
      case "HttpMediaTypeNotAcceptableException":
      case "HttpMediaTypeNotSupportedException":
      case "HttpRequestMethodNotSupoortedException":
        responseCode = OVNDResponseCode.ERROR_INVALID_REQUEST;

      default:
        break;
    }

    this.LOGGER.warn("EXCEPTION MESSAGE -- {} -- {} ", exceptionName, responseCode.getCode());

    return responseCode;
  }
}