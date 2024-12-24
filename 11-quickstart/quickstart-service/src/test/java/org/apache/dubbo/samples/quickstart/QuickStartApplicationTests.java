/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.samples.quickstart;

import org.apache.dubbo.config.annotation.DubboReference;

import org.apache.dubbo.samples.quickstart.dubbo.api.DemoService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuickStartApplicationTests {

    @DubboReference(scope = "remote")
    private DemoService demoService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executeJob(() -> {
                String result = demoService.sayHello("world" + finalI);
                System.out.println("Receive result ======> " + result);
            });
        }
    }

    public static void executeJob(Runnable handle) {
        executeJob(handle, null);
    }

    public static void executeJob(Runnable handle, Runnable exceptionHandler) {
        handle.run(); // excute job
    }
}
