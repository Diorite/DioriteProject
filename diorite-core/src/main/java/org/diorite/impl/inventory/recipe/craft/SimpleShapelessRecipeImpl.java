/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Diorite (by Bartłomiej Mazur (aka GotoFinal))
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

package org.diorite.impl.inventory.recipe.craft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.diorite.inventory.GridInventory;
import org.diorite.inventory.item.ItemStack;
import org.diorite.inventory.recipe.RecipeItem;
import org.diorite.inventory.recipe.craft.RecipeCheckResult;
import org.diorite.inventory.recipe.craft.ShapelessRecipe;
import org.diorite.inventory.slot.SlotType;

/**
 * Implementation of shapeless recipe
 */
public class SimpleShapelessRecipeImpl extends SimpleRecipeImpl implements ShapelessRecipe
{
    protected final List<RecipeItem> ingredients;

    public SimpleShapelessRecipeImpl(final List<RecipeItem> ingredients, final ItemStack result, final long priority)
    {
        super(result, priority);
        this.ingredients = new ArrayList<>(ingredients);
    }

    @Override
    public RecipeCheckResult isMatching(final GridInventory inventory)
    {
        final LinkedList<RecipeItem> ingredients = new LinkedList<>(this.getIngredients());
        final ItemStack[] items = new ItemStack[ingredients.size()];
        int j = 0;
        for (int i = 0, size = inventory.size(); i < size; i++)
        {
            if (inventory.getSlot(i).getSlotType().equals(SlotType.RESULT))
            {
                continue;
            }
            final ItemStack item = inventory.getItem(i);
            if (item == null)
            {
                continue;
            }
            boolean matching = false;
            for (final Iterator<RecipeItem> iterator = ingredients.iterator(); iterator.hasNext(); )
            {
                final RecipeItem ingredient = iterator.next();
                final ItemStack valid = ingredient.isValid(item);
                if (valid != null)
                {
                    items[j++] = valid;
                    iterator.remove();
                    matching = true;
                    break;
                }
            }
            if (! matching)
            {
                return null;
            }
        }
        return ingredients.isEmpty() ? new RecipeCheckResultImpl(this, this.result, items) : null;
    }

    @Override
    public List<RecipeItem> getIngredients()
    {
        return new ArrayList<>(this.ingredients);
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString()).append("ingredients", this.ingredients).toString();
    }
}
