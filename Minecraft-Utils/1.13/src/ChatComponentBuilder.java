import eu.the5zig.util.minecraft.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 5zig. All rights reserved © 2015
 * <p>
 * Utility class for ChatComponent serialization.
 */
public class ChatComponentBuilder {

	private static final Map<ChatColor, a> TRANSLATE = new HashMap<ChatColor, a>();
	private static final Pattern url = Pattern.compile("^(?:(https?)://)?([-\\w_\\.]{2,}\\.[a-z]{2,4})(/\\S*)?$");

	static {
		for (int i = 0; i < a.values().length; i++) {
			TRANSLATE.put(ChatColor.values()[i], a.values()[i]);
		}
	}

	// CHATCOMPONENT = eu
	// ChatComponentStyle = es
	// ChatStyle = ez
	// TEXTCOMPONENT = iq
	// ClickEvent = hy
	// HoverEvent = ij

	public static ij fromLegacyText(String message) {
		iq base = new iq("");
		StringBuilder builder = new StringBuilder();
		iq currentComponent = new iq("");
		ip currentStyle = new ip();
		currentComponent.a(currentStyle);
		Matcher matcher = url.matcher(message);

		for (int i = 0; i < message.length(); i++) {
			char c = message.charAt(i);

			if (c == ChatColor.COLOR_CHAR) {
				i++;
				c = message.charAt(i);
				if (c >= 'A' && c <= 'Z') {
					c += 32;
				}

				ChatColor format = ChatColor.getByChar(c);
				if (format == null) {
					continue;
				}

				if (builder.length() > 0) {
					iq old = currentComponent;
					currentComponent = old.g();
					currentStyle = currentComponent.b();
					old.a(builder.toString());
					builder = new StringBuilder();
					base.a(old);
				}

				switch (format) {
					case BOLD:
						currentStyle.a(true);
						break;
					case ITALIC:
						currentStyle.b(true);
						break;
					case STRIKETHROUGH:
						currentStyle.c(true);
						break;
					case UNDERLINE:
						currentStyle.d(true);
						break;
					case MAGIC:
						currentStyle.e(true);
						break;
					case RESET:
						format = ChatColor.WHITE;
					default:
						currentComponent = new iq("");
						currentStyle = new ip();
						currentComponent.a(currentStyle);
						currentStyle.a(TRANSLATE.get(format));
						break;
				}
				continue;
			}
			int pos = message.indexOf(' ', i);
			if (pos == -1) {
				pos = message.length();
			}
			if (matcher.region(i, pos).find()) {
				if (builder.length() > 0) {
					iq old = currentComponent;
					currentComponent = old.g();
					old.a(builder.toString());
					builder = new StringBuilder();
					base.a(old);
				}

				iq old = currentComponent;
				currentComponent = old.g();
				currentStyle = currentComponent.b();
				String urlStr = message.substring(i, pos);
				if (!urlStr.startsWith("http"))
					urlStr = "http://" + urlStr;
				currentComponent.a(urlStr);
				ii clickEvent = new ii(ii.a.a, urlStr);
				currentStyle.a(clickEvent);
				base.a(currentComponent);
				i += pos - i - 1;
				currentComponent = old;
				currentStyle = currentComponent.b();
				continue;
			}
			builder.append(c);
		}
		if (builder.length() > 0) {
			currentComponent.a(builder.toString());
			base.a(currentComponent);
		}


		return base;
	}

}
