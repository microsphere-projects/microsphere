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
package io.microsphere.invoke;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static io.microsphere.invoke.MethodHandleUtils.findVirtual;
import static io.microsphere.reflect.MethodUtils.findMethod;

/**
 * The benchmark of {@link MethodHandleUtils}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see MethodHandleUtils
 * @since 1.0.0
 */
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class MethodHandleUtilsBenchmark {

    private static final MethodHandle staticMethodHandle = findVirtual(MethodHandleUtilsBenchmark.class, "echo");

    private static final Method method = findMethod(MethodHandleUtilsBenchmark.class, "echo");

    private final MethodHandle methodHandle = findVirtual(MethodHandleUtilsBenchmark.class, "echo");

    static {
        method.setAccessible(true);
    }

    @Benchmark
    public void directAccess() {
        this.echo();
    }

    @Benchmark
    public void invokeMethod() throws Throwable {
        method.invoke(this);
    }

    @Benchmark
    public void invokeExactMethodHandle() throws Throwable {
        invokeExact(methodHandle);
    }

    @Benchmark
    public void invokeExactStaticMethodHandle() throws Throwable {
        invokeExact(staticMethodHandle);
    }

    private void invokeExact(MethodHandle methodHandle) throws Throwable {
        methodHandle.invokeExact(this);
    }

    private void echo() {
    }
}