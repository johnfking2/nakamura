/**
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.sakaiproject.nakamura.api.basiclti;

import org.sakaiproject.nakamura.api.lite.accesscontrol.AccessDeniedException;
import org.sakaiproject.nakamura.api.lite.content.Content;
import org.sakaiproject.nakamura.api.lite.Session;
import org.sakaiproject.nakamura.api.lite.StorageClientException;
/**
 * A simple abstraction so that local implementers can choose a different LTI context_id
 * mapping strategy.
 */
public interface LiteBasicLTIContextIdResolver {

  /**
   * Return the LTI <code>context_id</code> for given {@link Node}.
   * 
   * @param node
   * @return Return <code>null</code> if the LTI context_id cannot be determined.
   */
  public String resolveContextId(Content node, String groupId, Session session) throws AccessDeniedException, StorageClientException;

}
