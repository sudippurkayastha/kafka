/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.streams.kstream.internals;

public abstract class AbstractKTableKTableJoinValueGetterSupplier<K, R, V1, V2> implements KTableValueGetterSupplier<K, R> {
    final protected KTableValueGetterSupplier<K, V1> valueGetterSupplier1;
    final protected KTableValueGetterSupplier<K, V2> valueGetterSupplier2;

    public AbstractKTableKTableJoinValueGetterSupplier(final KTableValueGetterSupplier<K, V1> valueGetterSupplier1,
                                                       final KTableValueGetterSupplier<K, V2> valueGetterSupplier2) {
        this.valueGetterSupplier1 = valueGetterSupplier1;
        this.valueGetterSupplier2 = valueGetterSupplier2;
    }

    @Override
    public String[] storeNames() {
        final String[] storeNames1 = valueGetterSupplier1.storeNames();
        final String[] storeNames2 = valueGetterSupplier2.storeNames();
        final String[] stores = new String[storeNames1.length + storeNames2.length];
        System.arraycopy(storeNames1, 0, stores, 0, storeNames1.length);
        System.arraycopy(storeNames2, 0, stores, storeNames1.length, storeNames2.length);
        return stores;
    }

}
