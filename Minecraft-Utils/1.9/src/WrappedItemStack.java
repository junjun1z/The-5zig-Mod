import com.google.common.collect.Multimap;
import eu.the5zig.mod.MinecraftFactory;
import eu.the5zig.mod.gui.ingame.ItemStack;
import eu.the5zig.mod.util.GLUtil;
import eu.the5zig.util.minecraft.ChatColor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 5zig.
 * All rights reserved © 2015
 */
public class WrappedItemStack implements ItemStack {

	private static final UUID ITEM_MODIFIER_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

	private adq item;

	public WrappedItemStack(adq item) {
		this.item = item;
	}

	@Override
	public int getAmount() {
		return item.b;
	}

	@Override
	public int getMaxDurability() {
		return item.j();
	}

	@Override
	public int getCurrentDurability() {
		return item.h();
	}

	@Override
	public String getKey() {
		return getResourceKey(item);
	}

	@Override
	public String getDisplayName() {
		return item.q();
	}

	@Override
	public List<String> getLore() {
		return item.a(((Variables) MinecraftFactory.getVars()).getPlayer(), false);
	}

	@Override
	public int getHealAmount() {
		return item.b() instanceof adk ? ((adk) item.b()).h(item) : 0;
	}

	@Override
	public float getSaturationModifier() {
		return item.b() instanceof adk ? ((adk) item.b()).i(item) : 0;
	}

	@Override
	public void render(int x, int y, boolean withGenericAttributes) {
		if (item == null)
			return;
		((Variables) MinecraftFactory.getVars()).renderItem(item, x, y);

		if (withGenericAttributes) {
			for (rw modifier : rw.values()) {
				Multimap<String, sn> multimap = item.a(modifier);
				for (Map.Entry<String, sn> entry : multimap.entries()) {
					sn attribute = entry.getValue();
					double value = attribute.d();
					if (ITEM_MODIFIER_UUID.equals(attribute.a())) {
						if (((Variables)MinecraftFactory.getVars()).getPlayer() == null) {
							value += 1;
							value += (double) ago.a(item, sf.a);
						} else {
							value += ((Variables) MinecraftFactory.getVars()).getPlayer().a(yt.e).b();
							value += (double) ago.a(item, sf.a);
						}
					}
					if (entry.getKey().equals("generic.attackDamage") || entry.getKey().equals("generic.armor")) {
						GLUtil.disableDepth();
						GLUtil.pushMatrix();
						GLUtil.translate(x + 8, y + 10, 1);
						GLUtil.scale(0.7f, 0.7f, 0.7f);
						MinecraftFactory.getVars().drawString(ChatColor.BLUE + "+" + Math.round(value), 0, 0);
						GLUtil.popMatrix();
						GLUtil.enableDepth();
					}
				}
			}
		}
	}

	public static String getResourceKey(adq item) {
		kk resourceLocation = ado.f.b(item.b());
		return resourceLocation == null ? null : resourceLocation.toString();
	}
}
