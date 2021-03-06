package eu.the5zig.mod.modules.items.server;

import com.google.common.collect.Sets;
import eu.the5zig.mod.I18n;
import eu.the5zig.mod.The5zigAPI;
import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.modules.AbstractModuleItem;
import eu.the5zig.mod.render.RenderLocation;

import java.util.Collections;
import java.util.Set;

public class OnlineFriends extends AbstractModuleItem {

	private final Set<String> DUMMY_FRIENDS = Sets.newHashSet("5zig", "Notch", "jeb_", "Dinnerbone");

	@Override
	public void registerSettings() {
		getProperties().addSetting("length", "", 5, 1, 15, 1);
	}

	@Override
	public void render(int x, int y, RenderLocation renderLocation, boolean dummy) {
		Set<String> friends = getFriends(dummy);
		String pre = getProperties().buildPrefix("");
		int index = 0;
		int length = length();
		for (String friend : friends) {
			The5zigMod.getVars().drawString(pre + friend, x, y + (index * The5zigMod.getVars().getFontHeight()));
			index++;
			if (index >= length) {
				break;
			}
		}
	}

	@Override
	public boolean shouldRender(boolean dummy) {
		return dummy || (The5zigAPI.getAPI().getActiveServer() != null);
	}

	@Override
	public int getWidth(boolean dummy) {
		Set<String> friends = getFriends(dummy);
		int maxWidth = 0;
		for (String friend : friends) {
			int width = The5zigMod.getVars().getStringWidth(friend);
			if (width > maxWidth) {
				maxWidth = width;
			}
		}
		return maxWidth;
	}

	@Override
	public int getHeight(boolean dummy) {
		return Math.min(length(), getFriends(dummy).size()) * The5zigMod.getVars().getFontHeight();
	}

	private int length() {
		Float length = (Float) getProperties().getSetting("length").get();
		if (length == null) {
			length = 5f;
		}
		return length.intValue();
	}

	private Set<String> getFriends(boolean dummy) {
		if (dummy) {
			return DUMMY_FRIENDS;
		}
		Set<String> friends = The5zigAPI.getAPI().getActiveServer().getGameListener().getOnlineFriends();
		if (!friends.isEmpty()) {
			return friends;
		}
		return Collections.singleton(I18n.translate("modules.item.online_friends.nobody"));
	}
}
