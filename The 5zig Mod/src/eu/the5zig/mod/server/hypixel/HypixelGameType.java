package eu.the5zig.mod.server.hypixel;

public enum HypixelGameType {

	GENERAL("General", "General", 0),
	QUAKECRAFT("Quakecraft", "Quake", 2),
	WALLS("Walls", "Walls", 3),
	PAINTBALL("Paintball", "Paintball", 4),
	SURVIVAL_GAMES("Blitz Survival Games", "HungerGames", 5),
	TNTGAMES("The TNT Games", "TNTGames", 6),
	VAMPIREZ("VampireZ", "VampireZ", 7),
	WALLS3("Mega Walls", "Walls3", 13),
	ARCADE("Arcade", "Arcade", 14),
	ARENA("Arena Brawl", "Arena", 17),
	MCGO("Cops and Crims", "MCGO", 21),
	UHC("UHC Champions", "UHC", 20),
	BATTLEGROUND("Warlords", "Battleground", 23),
	TURBO_KART_RACERS("Turbo Kart Racers", "GingerBread", 25),
	SKYWARS("SkyWars", "SkyWars", 51);

	private final String name;
	private String databaseName;
	private final int id;

	HypixelGameType(String name, String databaseName, int id) {
		this.name = name;
		this.databaseName = databaseName;
		this.id = id;
	}

	/**
	 * @return The official name of the GameType
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The name used in the API.
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @return The internal ID that is occasionally used in various database schemas
	 */
	public int getId() {
		return id;
	}

	public static HypixelGameType fromName(String name) {
		for (HypixelGameType gameType : values()) {
			if (gameType.getName().equals(name))
				return gameType;
		}
		return null;
	}

	/**
	 * @param id The internal id
	 * @return The GameType associated with that id, or null if there isn't one.
	 */
	public static HypixelGameType fromId(int id) {
		for (HypixelGameType gameType : values()) {
			if (gameType.id == id) {
				return gameType;
			}
		}
		return null;
	}

}