/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.controller.internal;

import org.apache.ambari.server.controller.spi.Resource;

/**
 * Operation level is specified along with some requests. It identifies
 * the logical level, at which the operation is executed.
 */
public class RequestOperationLevel {

  /**
   * Conversion table is used to convert user input into our internal names
   * of resources, defined at
   * org.apache.ambari.server.controller.spi.Resource.Type
    */
  private static final String [][] LEVEL_ALIASES= new String [][]{
    // FORMAT: <external alias> , <internal alias>
    {"CLUSTER", "Cluster"},
    {"SERVICE", "Service"},
    {"HOST", "Host"},
    {"HOST_COMPONENT", "HostComponent"},
  };

  private static final int ALIAS_COLUMN = 0;
  private static final int INTERNAL_NAME_COLUMN = 1;

  /**
   * Converts external operation level alias to an internal name
   */
  public static String getInternalLevelName(String external)
          throws IllegalArgumentException{
    String refinedAlias = external.trim().toUpperCase();
    for (String [] pair : LEVEL_ALIASES) {
      if (pair[ALIAS_COLUMN].equals(refinedAlias)) {
        return pair[INTERNAL_NAME_COLUMN];
      }
    }
    String message = String.format("Unknown operation level %s", external);
    throw new IllegalArgumentException(message);
  }

  /**
   * Converts internal operation level name to an external alias
   */
  public static String getExternalLevelName(String internal) {
    for (String [] pair : LEVEL_ALIASES) {
      if (pair[INTERNAL_NAME_COLUMN].equals(internal)) {
        return pair[ALIAS_COLUMN];
      }
    }
    // That should never happen
    String message = String.format("Unknown internal " +
            "operation level name %s", internal);
    throw new IllegalArgumentException(message);
  }

  public RequestOperationLevel(Resource.Type level, String clusterName,
                               String serviceName, String hostComponentName,
                               String hostName) {
    this.level = level;
    this.clusterName = clusterName;
    this.serviceName = serviceName;
    this.hostComponentName = hostComponentName;
    this.hostName = hostName;
  }

  /**
   * Valid values are Cluster, Service, Host and HostComponent. Component level
   * is identical to Service level, and that's why it is not supported
   * as a standalone level.
   */
  private Resource.Type level;

  // Fields below are not used as of now and reserved for future use

  /**
   * Source cluster for request. Specified for all requests
   */
  private String clusterName;

  /**
   * Source service for request. Specified for Service-level
   * and HostComponent-level requests.
   */
  private String serviceName;

  /**
   * Source host component for request. Specified for
   * HostComponent-level requests.
   */
  private String hostComponentName;

  /**
   * Source host for request. Specified for Host-level and
   * HostComponent-level requests.
   */
  private String hostName;


  public Resource.Type getLevel() {
    return level;
  }

  public String getClusterName() {
    return clusterName;
  }

  public String getServiceName() {
    return serviceName;
  }

  public String getHostComponentName() {
    return hostComponentName;
  }

  public String getHostName() {
    return hostName;
  }

  @Override
  public String toString() {
    return "RequestOperationLevel{" +
            "level=" + level +
            ", clusterName='" + clusterName + '\'' +
            ", serviceName='" + serviceName + '\'' +
            ", hostComponentName='" + hostComponentName + '\'' +
            ", hostName='" + hostName + '\'' +
            '}';
  }
}
