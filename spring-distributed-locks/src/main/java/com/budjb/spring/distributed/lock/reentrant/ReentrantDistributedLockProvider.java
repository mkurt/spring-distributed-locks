/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.budjb.spring.distributed.lock.reentrant;

import com.budjb.spring.distributed.lock.DistributedLock;
import com.budjb.spring.distributed.lock.DistributedLockProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A non-distributed implementation of a lock provider utilizing {@link ReentrantLock}.
 */
public class ReentrantDistributedLockProvider implements DistributedLockProvider {
    /**
     * Registry of lock names to reentrant lock instances.
     */
    final private Map<String, ReentrantLock> registry = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public DistributedLock getDistributedLock(String key) {
        if (!registry.containsKey(key)) {
            registry.put(key, new ReentrantLock());
        }
        return new ReentrantDistributedLock(registry.get(key));
    }
}
