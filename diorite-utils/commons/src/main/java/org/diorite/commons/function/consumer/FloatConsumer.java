/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017. Diorite (by Bartłomiej Mazur (aka GotoFinal))
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.diorite.commons.function.consumer;

/**
 * Represents an operation that accepts a single {@code float}-valued argument and
 * returns no result.  This is the primitive type specialization of
 * {@link java.util.function.Consumer} for {@code float}.  Unlike most other functional interfaces,
 * {@code FloatConsumer} is expected to operate via side-effects.
 *
 * @see java.util.function.Consumer
 */
@FunctionalInterface
public interface FloatConsumer extends Consumer<Float>
{

    /**
     * Performs this operation on the given argument.
     *
     * @param value
     *         the input argument
     */
    void accept(float value);

    @Override
    default void accept(Float value)
    {
        this.accept(value.floatValue());
    }
}