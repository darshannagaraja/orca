/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.clouddriver.service;

import com.netflix.spinnaker.orca.clouddriver.config.JobConfigurationProperties;
import com.netflix.spinnaker.orca.clouddriver.config.PreconfiguredJobStageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty("job.preconfigured")
public class JobService {

  @Autowired JobConfigurationProperties jobConfigurationProperties;

  @Autowired
  List<PreconfiguredJobStageProperties> getPreconfiguredStages() {
    return jobConfigurationProperties.getPreconfigured().stream().filter(it -> it.enabled == true).collect(Collectors.toList());
  }

}
