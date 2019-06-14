/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.clouddriver.pipeline.providers.gce;

import com.netflix.spinnaker.orca.clouddriver.pipeline.servergroup.support.TargetServerGroupLinearStageSupport;
import com.netflix.spinnaker.orca.clouddriver.tasks.MonitorKatoTask;
import com.netflix.spinnaker.orca.clouddriver.tasks.providers.gce.autoscaling.UpsertGceAutoscalingPolicyTask;
import com.netflix.spinnaker.orca.clouddriver.tasks.servergroup.ServerGroupCacheForceRefreshTask;
import com.netflix.spinnaker.orca.pipeline.TaskNode;
import com.netflix.spinnaker.orca.pipeline.model.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ModifyGceAutoscalingPolicyStage extends TargetServerGroupLinearStageSupport {

  @Override
  protected void taskGraphInternal(Stage stage, TaskNode.Builder builder) {
    builder
        .withTask("upsert", UpsertGceAutoscalingPolicyTask.class)
        .withTask("monitor", MonitorKatoTask.class)
        .withTask("forceCacheRefresh", ServerGroupCacheForceRefreshTask.class)
        .withTask("waitForScalingProcesses", WaitForGceAutoscalingPolicyTask.class);
  }
}
