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


/**
 * OVNDResponseCode
 *
 * 오버노드 서비스의 Response 결과에 대한 값을 총괄함
 *
 * ** Notes ** S000 : 성공 200 E000 : 시스템에러
 *
 * E010 ~ E100 : 유저관련 에러 E101 ~ E200 : 데이터 처리 에러 E201 ~ E300 : DB 관련에러
 *
 * derekpark 임시 modify 수정요망 U000 ~ U100 : 권한접근 에러
 */
public enum OVNDResponseCode {

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
  ERROR_INVALID_VERIFICATION_KEY("E017", "invalid verification key"),
  ERROR_INVALID_NEW_ACCOUNT_TX_TOKEN_INVALID("E018", "invalid new account tx token key"),
  ERROR_USER_NOT_EXISTS("E019", "user does not exist"),
  ERROR_USER_ALREADY_CREATE_BLOCKCHAIN_ID("E020", "user already created blockchain id"),
  ERROR_BLOCKCHAIN_NETWORD("E021","blockchain netword error"),
  ERROR_USER_BLOCKCHAIN_ID_NOT_REGISTERED("E022","user did not register blockchain id"),
  ERROR_BLOCKCHAIN_ID_TOKEN_INVALID("E023", "invalid blockchain id token"),
  ERROR_SAVE_BLOCKCHAIN_ID("E024", "failed to save blockchain id to database"),

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

  OVNDResponseCode(String code, String message) {
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