/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Diorite (by Bartłomiej Mazur (aka GotoFinal))
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

package org.diorite.entity;

public interface Boat extends Entity, ObjectEntity
{
    /**
     * Returns the max speed of a boat.
     *
     * @return Value of the boat's max speed.
     */
	public double getMaxSpeed();
	
    /**
     * Sets max speed of a boat.
     *
     * @param isWorkingOnLand if a boat should work on the land.
     */
	public void setMaxSpeed(double maxSpeed);
	
    /**
     * Returns true if a boat works on land.
     *
     * @return true if a boat works on land.
     */
	public boolean getWorkingOnLand();
	
    /**
     * Sets if a boat should be working on the land.
     *
     * @param isWorkingOnLand if a boat should be working on the land.
     */
	public void setWorkingOnLand(boolean isWorkingOnLand);
}
