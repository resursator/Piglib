package com.shnupbups.piglib.rei;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.CollectionUtils;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import com.shnupbups.piglib.Piglib;
import com.shnupbups.piglib.rei.category.BarteringCategory;
import com.shnupbups.piglib.rei.category.PiglinLovedCategory;
import com.shnupbups.piglib.rei.category.PiglinRepellentsCategory;
import com.shnupbups.piglib.rei.category.PiglinSafeArmorCategory;
import com.shnupbups.piglib.rei.display.BarteringDisplay;
import com.shnupbups.piglib.rei.display.PiglinLovedDisplay;
import com.shnupbups.piglib.rei.display.PiglinRepellentsDisplay;
import com.shnupbups.piglib.rei.display.PiglinSafeArmorDisplay;

public class PiglibClientPlugin implements REIClientPlugin {

	@Override
	public void registerCategories(CategoryRegistry registry) {
		registry.add(
				new BarteringCategory(),
				new PiglinLovedCategory(),
				new PiglinSafeArmorCategory(),
				new PiglinRepellentsCategory()
		);
		registry.removePlusButton(PiglibPlugin.BARTERING);
		registry.removePlusButton(PiglibPlugin.PIGLIN_LOVED);
		registry.removePlusButton(PiglibPlugin.PIGLIN_SAFE_ARMOR);
		registry.removePlusButton(PiglibPlugin.PIGLIN_REPELLENTS);
		registry.addWorkstations(PiglibPlugin.BARTERING, EntryIngredients.of(Items.PIGLIN_SPAWN_EGG));
		registry.addWorkstations(PiglibPlugin.PIGLIN_LOVED, EntryIngredients.of(Items.PIGLIN_SPAWN_EGG));
		registry.addWorkstations(PiglibPlugin.PIGLIN_SAFE_ARMOR, EntryIngredients.of(Items.PIGLIN_SPAWN_EGG));
		registry.addWorkstations(PiglibPlugin.PIGLIN_REPELLENTS, EntryIngredients.of(Items.PIGLIN_SPAWN_EGG));
	}

	@Override
	public void registerDisplays(DisplayRegistry registry) {
		registry.add(new BarteringDisplay(CollectionUtils.map(Lists.newArrayList(Piglib.PIGLIN_BARTERING_ITEMS.values()), ItemStack::new)));
		registry.add(new PiglinLovedDisplay(getPiglinLovedStacks()));
		registry.add(new PiglinSafeArmorDisplay(CollectionUtils.map(Lists.newArrayList(Piglib.PIGLIN_SAFE_ARMOR.values()), ItemStack::new)));
		registry.add(new PiglinRepellentsDisplay(CollectionUtils.map(Lists.newArrayList(ItemTags.PIGLIN_REPELLENTS.values()), ItemStack::new)));
	}

	public List<ItemStack> getPiglinLovedStacks() {
		List<ItemStack> list = CollectionUtils.map(Lists.newArrayList(ItemTags.PIGLIN_LOVED.values()), ItemStack::new);
		list.addAll(CollectionUtils.map(Lists.newArrayList(Piglib.PIGLIN_LOVED_NUGGETS.values()), (item) -> new ItemStack(item, 64)));
		return list;
	}
}
