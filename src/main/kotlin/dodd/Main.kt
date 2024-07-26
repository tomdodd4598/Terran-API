package dodd

import dodd.terran.Game
import dodd.terran.World
import dodd.terran.translation.*
import dodd.terran.value.Color
import dodd.terran.value.Faction
import dodd.terran.value.Formation
import dodd.terran.value.Vector

fun main() {
    val game = Game("C:/Program Files (x86)/Steam/steamapps/common/Treasure Planet Battle at Procyon", "English")

    val zemyatin = World(game, "Zemyatin")

    val worldInfo = zemyatin.root.get<NestedNode>("WorldInfo")!!
    val players = worldInfo.get<FlatNode>("Players")!!

    val gameNode = zemyatin.root.get<NestedNode>("Game")!!
    val time = gameNode.get<NestedNode>("Time")!!

    val worldNode = zemyatin.root.get<NestedNode>("World")!!
    val playerList = worldNode.get<PlayerListNode>("PlayerList")!!
    val worldObjects = worldNode.get<WorldObjectsListNode>("WorldObjects")!!

    val gameSpecific = worldNode.get<NestedNode>("GameSpecific")!!
    val groups = gameSpecific.get<GroupsListNode>("Num Groups")!!
    val worldRules = gameSpecific.get<NestedNode>("World Rules")!!
    val ruleList = worldRules.get<RuleListNode>("Rule List")!!

    val gameImpl = zemyatin.root.get<NestedNode>("GameImpl")!!

    val world = World.create(game,
        "Zemyatin",
        "IDGS_TPWORLDNAMES_MP_ARENA_SMALL",
        "IDGS_TPWORLDDESCRIPTION_MP_ARENA_SMALL",
        Vector(1750f, 1750f, 500f),
        8,
        Color(0.101961f, 0.101961f, 0.101961f, 1f),
        Vector(0.501989f, 0.145542f, -0.852540f),
        Color(0.368627f, 0.121569f, 0.109804f, 1f),
        Color(0.909804f, 0.894118f, 0.756863f, 1f),
        "Map_Zemyatin"
    )

    fun addPlayer(name: Int, color: Color, start: Vector, direction: Vector) {
        world.addPlayer("Player $name", -1, color, start, direction, Faction.ANY, Formation.SIDE_BY_SIDE)
    }

    addPlayer(1, Color(0f, 0.502f, 0.502f, 1f), Vector(-285.8385f, 232.97235f, 0f), Vector.dir(0.396998f, -0.91782f, 0f))
    addPlayer(2, Color(0f, 0f, 1f, 1f), Vector(304.95032f, 289.50983f, 0f), Vector.dir(-0.793376f, -0.608732f, 0f))
    addPlayer(3, Color(0.502f, 0.502f, 0f, 1f), Vector(284.3098f, -284.89603f, 0f), Vector.dir(-0.755651f, 0.654975f, 0f))
    addPlayer(4, Color(1f, 1f, 0f, 1f), Vector(-281.1714f, -377.8817f, 0f), Vector.dir(0.753918f, 0.656969f, 0f))

    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.BLACK, Vector(-145.1806f, -362.53516f, 0f)))

    world.addWorldObject("Terrain_BlackHole_Small", null, "Black Hole Group", Vector(1.800463f, -2.546509f, 0f))

    fun addAsteroid(size: String, position: Vector) {
        world.addWorldObject("Asteroid_$size", "Asteroids", "Asteroid Group", position)
    }

    addAsteroid("Large", Vector(110.43811f, 578.83997f, 0f))
    addAsteroid("Med", Vector(553.69727f, 339.91428f, 0f))
    addAsteroid("Small", Vector(114.23401f, -369.5732f, 0f))
    addAsteroid("Huge", Vector(-301.37805f, -442.76627f, 0.000122f))
    addAsteroid("Large", Vector(-494.45972f, 65.156334f, 0f))
    addAsteroid("Med", Vector(-236.83673f, 556.8069f, 0f))
    addAsteroid("Small", Vector(601.0581f, 162.38853f, 0.000122f))
    addAsteroid("Med", Vector(520.6698f, -197.0405f, 0.000122f))
    addAsteroid("Large", Vector(33.852783f, -304.87033f, 0f))
    addAsteroid("Huge", Vector(49.42798f, 355.06207f, -0.000122f))
    addAsteroid("Large", Vector(-341.75085f, -26.253838f, 0f))
    addAsteroid("Med", Vector(320.70532f, 26.71062f, 0f))
    addAsteroid("Small", Vector(487.2406f, 94.61695f, -0.000122f))

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetOwnerAction("Asteroid Group", "Asteroids"),
        World.createObjectiveTaskStateAction("Kill All", true),
        World.createPlayMusicAction("BTL_NavyBig_BassnDrums", 0.7f, 2.0f, 2.0f),
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addJournalPage(World.createDefaultJournalPage())

    world.addMapText(World.createMapText("Black Hole", "IDGS_TPMAPTEXTITEMS_MP_ARENA_SMALL", Vector(1.299599f, 4.263611f, 0f)))

    println(world.build())
}
