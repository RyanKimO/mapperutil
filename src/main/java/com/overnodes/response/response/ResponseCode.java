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
package com.overnodes.response.response;

public enum ResponseCode {

  // S000 SUCCESS
  SUCCESS_NORMAL("S000", "success"),

  // E000 SYSTEM ERROR
  ERROR_SYSTEM_ERROR("E000", "system error"),

  // E010 ~ E100 : USER ERROR
  ERROR_EMAIL_ALREADY_EXISTS("E010", "email duplication"),
  ERROR_EMAIL_NOT_EXISTS("E011", "email does not exist"),
  ERROR_EMAIL_VERIFICATION_NECESSRAY("E012", "verification email sent. verification necessary"),
  ERROR_INVALID_PASSWORD("E013", "invalid password"),
  ERROR_RESET_PASSWORD_TOKEN_INVALID("E014", "invalid reset password token"),
  ERROR_RESET_PASSWORD_TOKEN_EXPIRED("E015", "reset password token is expired"),
  ERROR_LOGIN_REQUIRED("E016", "login is required. redirect to login"),

  // E101 ~ E200 : DATA
  ERROR_INVALID_REQUEST("E101", "invalid request"),
  ERROR_INVALID_PARAMETER("E102", "invalid parameter"),

  // E201 ~ E300 :
  // Common JPA Exceptions
  ERROR_ENTITY_EXISTS("E201", "entity already exists"),
  ERROR_ENTITY_NOT_FOUND("E202", "entity not found"),
  ERROR_LOCK_TIMEOUT("E203", "lock time out"),
  ERROR_NON_UNIQUE_RESULT("E204", "non unique result"),
  ERROR_NO_RESULT("E205", "no result"),
  ERROR_OPTIMISTIC_LOCK("E206", "optimistic lock"),
  ERROR_PESSIMISTIC_LOCK("E207", "passimistic lock"),
  ERROR_QUERY_TIMEOUT("E208", "query timeout"),
  ERROR_ROLLBACK("E209", "roll back exception"),
  ERROR_TRANSACTION_REQUIRED("210", "transaction roll back"),

  // U000 ~ U100 : RESTRICT
  RESTRICT_TRANSACTION_ACCESS("U001", "user unable transaction"),
  STEEMJ_API_ERROR("U002", "steem api error"),
  STEEMJ_SERVICE_EXCEPTION("U003", "steemJ service error");


  private final String code;
  private final String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
