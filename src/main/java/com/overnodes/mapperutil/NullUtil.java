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
package com.overnodes.mapperutil;

import com.google.gson.JsonElement;
import java.util.Collection;

public class NullUtil {

  public static final boolean isNotEmpty(JsonElement json) {
    return !isEmpty(json);
  }

  public static final boolean isEmpty(JsonElement json) {
    try {
      if (json == null) {
        return true;
      }
      if (json.isJsonObject()) {
        return json.isJsonNull() || json.getAsJsonObject().entrySet().isEmpty();
      } else if (json.isJsonArray()) {
        return json.isJsonNull() || json.getAsJsonArray().size() == 0;
      } else {
        return json.isJsonNull();
      }
    } catch (Exception e) {
      return true;
    }
  }

  /**
   * Checks if is object empty.
   *
   * @param object the object
   * @return true, if is object empty
   */
  public static boolean isObjectEmpty(Object object) {
    if (object == null) {
      return true;
    } else if (object instanceof String) {
      if (((String) object).trim().length() == 0) {
        return true;
      }
    } else if (object instanceof Collection) {
      return isCollectionEmpty((Collection<?>) object);
    }
    return false;
  }

  /**
   * Checks if is collection empty.
   *
   * @param collection the collection
   * @return true, if is collection empty
   */
  private static boolean isCollectionEmpty(Collection<?> collection) {
    if (collection == null || collection.isEmpty()) {
      return true;
    }
    return false;
  }
}
