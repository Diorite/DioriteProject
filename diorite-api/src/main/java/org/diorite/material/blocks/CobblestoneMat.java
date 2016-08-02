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

package org.diorite.material.blocks;

import java.util.Map;

import org.diorite.material.Material;
import org.diorite.material.VariantMat;
import org.diorite.material.VariantableMat;
import org.diorite.material.items.SmeltableMat;
import org.diorite.utils.collections.maps.CaseInsensitiveMap;

import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;

/**
 * Class representing 'Cobblestone' block material in minecraft. <br>
 * ID of block: 4 <br>
 * String ID of block: minecraft:cobblestone <br>
 * Hardness: 2 <br>
 * Blast Resistance 30
 */
@SuppressWarnings("JavaDoc")
public class CobblestoneMat extends StonyMat implements VariantableMat, SmeltableMat
{
    /**
     * Sub-ids used by diorite/minecraft by default
     */
    public static final int USED_DATA_VALUES = 1;

    public static final CobblestoneMat COBBLESTONE = new CobblestoneMat();

    private static final Map<String, CobblestoneMat>    byName = new CaseInsensitiveMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);
    private static final Byte2ObjectMap<CobblestoneMat> byID   = new Byte2ObjectOpenHashMap<>(USED_DATA_VALUES, SMALL_LOAD_FACTOR);

    @SuppressWarnings("MagicNumber")
    public CobblestoneMat()
    {
        super("COBBLESTONE", 4, "minecraft:cobblestone", "COBBLESTONE", (byte) 0x00, 2, 30);
    }

    public CobblestoneMat(final String enumName, final int id, final String minecraftId, final int maxStack, final String typeName, final byte type, final float hardness, final float blastResistance)
    {
        super(enumName, id, minecraftId, maxStack, typeName, type, hardness, blastResistance);
    }

    @Override
    public CobblestoneMat getType(final String name)
    {
        return getByEnumName(name);
    }

    @Override
    public CobblestoneMat getType(final int id)
    {
        return getByID(id);
    }

    @Override
    public VariantMat getVariant()
    {
        return VariantMat.CLASSIC;
    }

    @Override
    public VariantableMat getVariant(final VariantMat variant)
    {
        if (variant == VariantMat.MOSSY)
        {
            return MossyCobblestoneMat.MOSSY_COBBLESTONE;
        }
        return this;
    }

    @Override
    public Material getSmeltResult()
    {
        return STONE;
    }

    /**
     * Returns one of Cobblestone sub-type based on sub-id, may return null
     *
     * @param id sub-type id
     *
     * @return sub-type of Cobblestone or null
     */
    public static CobblestoneMat getByID(final int id)
    {
        return byID.get((byte) id);
    }

    /**
     * Returns one of Cobblestone sub-type based on name (selected by diorite team), may return null
     * If block contains only one type, sub-name of it will be this same as name of material.
     *
     * @param name name of sub-type
     *
     * @return sub-type of Cobblestone or null
     */
    public static CobblestoneMat getByEnumName(final String name)
    {
        return byName.get(name);
    }

    /**
     * Register new sub-type, may replace existing sub-types.
     * Should be used only if you know what are you doing, it will not create fully usable material.
     *
     * @param element sub-type to register
     */
    public static void register(final CobblestoneMat element)
    {
        allBlocks.incrementAndGet();
        byID.put((byte) element.getType(), element);
        byName.put(element.getTypeName(), element);
    }

    @Override
    public CobblestoneMat[] types()
    {
        return CobblestoneMat.cobblestoneTypes();
    }

    /**
     * @return array that contains all sub-types of this block.
     */
    public static CobblestoneMat[] cobblestoneTypes()
    {
        return byID.values().toArray(new CobblestoneMat[byID.size()]);
    }

    static
    {
        CobblestoneMat.register(COBBLESTONE);
    }
}
