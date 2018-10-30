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
 *      Date :      24/07/2018
 *      Contact :   tedkim@overnodes.com
 *
 *  ================================================================================
 *
 */

package com.overnodes.common.mapperutil;

import java.util.Iterator;
import java.util.List;

public class StringUtil {

  public static String concatenate(List<String> listOfItems, String separator) {
    StringBuilder sb = new StringBuilder();
    Iterator<String> stit = listOfItems.iterator();

    while (stit.hasNext()) {
      sb.append(stit.next());
      if (stit.hasNext()) {
        sb.append(separator);
      }
    }

    return sb.toString();
  }
}