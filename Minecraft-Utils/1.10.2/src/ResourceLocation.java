import eu.the5zig.mod.util.IResourceLocation;

public class ResourceLocation extends kn implements IResourceLocation {

	public ResourceLocation(String resourcePath) {
		super(resourcePath);
	}

	public ResourceLocation(String resourceDomain, String resourcePath) {
		super(resourceDomain, resourcePath);
	}

	public static ResourceLocation fromObfuscated(kn resourceLocation) {
		return new ResourceLocation(resourceLocation.b(), resourceLocation.a());
	}

	public String getResourcePath() {
		return a();
	}

	public String getResourceDomain() {
		return b();
	}
}
