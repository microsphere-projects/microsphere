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
package io.microsphere.reflect;

import io.microsphere.lang.Deprecation;
import io.microsphere.util.Version;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import static io.microsphere.reflect.ConstructorUtils.findConstructor;

/**
 * The definition class for {@link Constructor}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see Constructor
 * @since 1.0.0
 */
public class ConstructorDefinition extends ExecutableDefinition<Constructor> {

    /**
     * @param since               the 'since' version
     * @param declaredClassName   The declared class name of the method
     * @param parameterClassNames the class names of parameters
     */
    public ConstructorDefinition(String since, String declaredClassName, String name, String... parameterClassNames) {
        super(since, declaredClassName, declaredClassName, parameterClassNames);
    }

    /**
     * @param since               the 'since' version
     * @param deprecation         the deprecation
     * @param declaredClassName   The declared class name of the method
     * @param parameterClassNames the parameter class names
     */
    public ConstructorDefinition(String since, Deprecation deprecation, String declaredClassName, String name, String... parameterClassNames) {
        super(since, deprecation, declaredClassName, declaredClassName, parameterClassNames);
    }

    /**
     * @param since               the 'since' version
     * @param declaredClassName   The declared class name of the method
     * @param parameterClassNames the class names of parameters
     */
    public ConstructorDefinition(Version since, String declaredClassName, String... parameterClassNames) {
        super(since, declaredClassName, declaredClassName, parameterClassNames);
    }

    /**
     * @param since               the 'since' version
     * @param deprecation         the deprecation
     * @param declaredClassName   The declared class name of the method
     * @param parameterClassNames the parameter class names
     */
    public ConstructorDefinition(Version since, Deprecation deprecation, String declaredClassName, String... parameterClassNames) {
        super(since, deprecation, declaredClassName, declaredClassName, parameterClassNames);
    }

    @Override
    protected Constructor resolveMember() {
        return findConstructor(getDeclaredClass(), getParameterTypes());
    }

    /**
     * Get the {@link Constructor}
     *
     * @return <code>null</code> if the {@link Constructor} can't be resolved.
     */
    public Constructor<?> getConstructor() {
        return getMember();
    }

    @Override
    public String toString() {
        return "ConstructorDefinition{" +
                "since=" + this.since +
                ", deprecation=" + this.deprecation +
                ", declaredClassName='" + this.getDeclaredClassName() + '\'' +
                ", declaredClass=" + this.getDeclaredClass() +
                ", parameterClassName=" + Arrays.toString(this.parameterClassNames) +
                ", parameterTypes=" + Arrays.toString(this.parameterTypes) +
                '}';
    }
}
