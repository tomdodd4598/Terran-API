package dodd

import dodd.terran.Game
import dodd.terran.World
import dodd.terran.translation.*
import dodd.terran.value.*
import kotlin.math.acos

val game = Game("C:/Program Files (x86)/Steam/steamapps/common/Treasure Planet Battle at Procyon", "English")

fun diablo() {
    val world = World.create(
        game,
        "Diablo Straight",
        "IDGS_TPWORLDNAMES_MP_DIABLO_STRAIT",
        "IDGS_TPWORLDDESCRIPTION_MP_DIABLO_STRAIT",
        Vector(1825f, 1900f, 1000f),
        6,
        Color(0.090196f, 0.090196f, 0.090196f),
        Vector(0.739606f, 0.237109f, -0.629891f),
        Color(1f, 0.52549f, 0.066667f),
        Color(0.996078f, 1f, 0.882353f),
        "Map_Open_Diablo_Straight"
    )

    fun addPlayer(id: Int, color: Color, start: Vector, direction: Vector) {
        world.addPlayer("Player $id", -1, color, start, direction, Faction.ANY, Formation.LINE)
    }

    addPlayer(1, Color(0f, 0.502f, 0f), Vector(-526.635f, -1121.4581f), Vector.dir(0.995512f, -0.094636f))
    addPlayer(2, Color(0f, 0f, 0.502f), Vector(334.347f, -1133.6992f), Vector.dir(-0.999918f, 0.012794f))
    addPlayer(3, Color(0f, 0.502f, 0.502f), Vector(-554.614f, -393.513f), Vector.dir(0.997393f, 0.072156f))
    addPlayer(4, Color(0.651f, 0.7922f, 0.9412f), Vector(204.96829f, -446.43756f), Vector.dir(-0.999524f, -0.030856f))
    addPlayer(5, Color(1f, 0f, 0f), Vector(-612.34406f, 293.23584f), Vector.dir(0.963208f, -0.268756f))
    addPlayer(6, Color(1f, 1f, 0f), Vector(207.9324f, 378.60486f), Vector.dir(-0.952522f, 0.304470f))
    addPlayer(7, Color(0f, 0f, 1f), Vector(-378.004f, 961.7678f), Vector.dir(0.999634f, -0.027063f))
    addPlayer(8, Color(0.9059f, 0.5373f, 0.0392f), Vector(362.05246f, 1006.83997f), Vector.dir(-0.98992f, 0.141626f))

    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.BLACK, Vector(-1352.0924f, 265.17676f)))

    fun addIsland(type: String, position: Vector, cos: Float = 1f) {
        world.addWorldObject("Island_$type", null, "Island Group", position, Matrix.rotationZ(acos(cos)))
    }

    addIsland("Volcano_01", Vector(-570.1949f, -148.24173f), 0.913547f)
    addIsland("Volcano_02", Vector(-373.1324f, 558.6688f))
    addIsland("Volcano_03", Vector(234.8505f, 133.67128f), 0.104529f)
    addIsland("Volcano_04", Vector(139.5621f, -114.19995f))
    addIsland("Volcano_05", Vector(-459.1112f, 690.91473f))
    addIsland("Volcano_06", Vector(-445.773f, 1232.2682f))
    addIsland("Volcano_07", Vector(-512.3461f, -936.54285f, -0.000244f))
    addIsland("Volcano_02", Vector(261.23947f, -826.29535f))
    addIsland("Volcano_03", Vector(-568.94995f, 579.75446f))
    addIsland("Volcano_02", Vector(142.49583f, -705.3527f), 0.190809f)
    addIsland("Volcano_03", Vector(-622.4879f, 129.23808f))
    addIsland("Rocky_04", Vector(371.6263f, 1357.2142f))
    addIsland("Rocky_05", Vector(279.2582f, 716.40717f))
    addIsland("Rocky_06", Vector(338.89914f, -244.7013f))
    addIsland("Rocky_07", Vector(-491.25952f, -644.5425f))
    addIsland("Rocky_07", Vector(340.92377f, -1287.2688f, -0.000244f))
    addIsland("Rocky_08", Vector(-499.76703f, -1311.3401f))

    val etheriumID = world.addWorldObject("Terrain_Etherium_Current", null, null, Vector(-95f, 2.999973f))

    fun addAsteroid(size: String, position: Vector) {
        world.addWorldObject("Asteroid_$size", "Asteroids", "Asteroid Group", position)
    }

    addAsteroid("Huge", Vector(-671.7363f, -674.7682f, -0.000244f))
    addAsteroid("Huge", Vector(-766.735f, -672.57117f))
    addAsteroid("Huge", Vector(-720.5062f, -736.63135f, 0.000244f))
    addAsteroid("Huge", Vector(-868.53455f, -674.60693f))
    addAsteroid("Huge", Vector(-882.3093f, -751.91833f, -0.000244f))
    addAsteroid("Large", Vector(-968.50854f, -684.7953f))
    addAsteroid("Large", Vector(-1006.2645f, -734.2882f))
    addAsteroid("Large", Vector(425.8806f, -741.32684f))
    addAsteroid("Large", Vector(368.93405f, -797.59125f))
    addAsteroid("Huge", Vector(493.9076f, -798.9963f, 0.000244f))
    addAsteroid("Huge", Vector(551.8224f, -742.7751f))
    addAsteroid("Huge", Vector(629.0516f, -811.8658f))
    addAsteroid("Large", Vector(691.0602f, -763.6966f))
    addAsteroid("Large", Vector(761.175f, -809.22345f))
    addAsteroid("Large", Vector(762.85223f, -742.5173f, 0.000244f))
    addAsteroid("Large", Vector(376.22696f, -87.39555f, 0.000244f))
    addAsteroid("Huge", Vector(387.23868f, -170.37086f))
    addAsteroid("Huge", Vector(506.63373f, -99.59575f, -0.000244f))
    addAsteroid("Huge", Vector(567.13715f, -161.92543f))
    addAsteroid("Large", Vector(466.92822f, -159.0796f))
    addAsteroid("Large", Vector(637.56647f, -92.04521f))
    addAsteroid("Large", Vector(700.94366f, -159.24757f))
    addAsteroid("Huge", Vector(-759.28735f, 31.361816f, 0.000244f))
    addAsteroid("Huge", Vector(-796.2997f, -29.413818f))
    addAsteroid("Huge", Vector(504.4912f, 761.63727f, -0.000244f))
    addAsteroid("Med", Vector(415.11053f, 699.69196f))
    addAsteroid("Large", Vector(594.3594f, 681.6158f))
    addAsteroid("Large", Vector(-904.0491f, 37.786987f, 0.000244f))
    addAsteroid("Huge", Vector(-933.10913f, -41.302734f))
    addAsteroid("Huge", Vector(-1031.3027f, -38.692993f))
    addAsteroid("Med", Vector(-1082.1504f, 20.253052f))
    addAsteroid("Med", Vector(-1109.0947f, 57.087036f, -0.000244f))
    addAsteroid("Large", Vector(-1140.2103f, -22.520996f))
    addAsteroid("Huge", Vector(-974.51f, 41.046753f))
    addAsteroid("Huge", Vector(742.6379f, -85.16106f))
    addAsteroid("Large", Vector(791.58325f, -172.38795f))
    addAsteroid("Large", Vector(510.50885f, 679.9388f, -0.000244f))
    addAsteroid("Huge", Vector(610.9009f, 758.2603f))
    addAsteroid("Large", Vector(638.49133f, 682.49835f))
    addAsteroid("Huge", Vector(699.36615f, 754.5318f))
    addAsteroid("Huge", Vector(759.5925f, 701.1551f))
    addAsteroid("Large", Vector(815.47876f, 795.4908f))
    addAsteroid("Large", Vector(-787.062f, 698.76733f))
    addAsteroid("Huge", Vector(-633.0653f, 709.37036f))
    addAsteroid("Huge", Vector(-707.02893f, 723.1931f))
    addAsteroid("Huge", Vector(-553.7047f, 716.05347f))
    addAsteroid("Large", Vector(-700.34204f, 652.4165f))
    addAsteroid("Large", Vector(-865.8644f, 735.37646f))
    addAsteroid("Large", Vector(-877.55835f, 633.8396f))
    addAsteroid("Huge", Vector(-1040.2626f, 737.02f, 0.000244f))
    addAsteroid("Large", Vector(-988.6211f, 666.7761f, 0.000244f))

    world.addWaypointPath(World.createWaypointPath(
        "Etherium Current",
        Vector(38.08887f, -1698.24f),
        Vector(-95.6736f, -1361.6044f),
        Vector(-166.80106f, -1070.2273f),
        Vector(-129.647f, -701.41943f),
        Vector(-202.32416f, -294.2937f),
        Vector(-174.3288f, 76.673584f),
        Vector(-114.54558f, 421.9729f),
        Vector(-12.585695f, 789.38257f),
        Vector(125.55258f, 1139.5833f),
        Vector(91.48304f, 1342.9373f),
        Vector(-113.37474f, 1718.8157f)
    ))

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetupEtheriumCurrentAction("Etherium Current", etheriumID),
        World.createSetupAsteroidBeltAction("Asteroid Group", null, FollowMode.TO_END, false, 0f, 0f, 0.02f, 3.02f),
        World.createSetOwnerAction("Asteroid Group", "Asteroids"),
        World.createSetObjectiveTaskStateAction("Kill All", true),
        World.createPlayMusicAction("NEUTRL_AbyssGoo", 1f, 2f, 2f),
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Etherium Current", "IDGS_TPMAPTEXTITEMS_MP_DIABLO_STRAIT_CURRENT", Vector(-186.8336f, 9.868164f)))

    println(world.build())
}

fun zemyatin() {
    val world = World.create(
        game,
        "Zemyatin",
        "IDGS_TPWORLDNAMES_MP_ARENA_SMALL",
        "IDGS_TPWORLDDESCRIPTION_MP_ARENA_SMALL",
        Vector(1750f, 1750f, 500f),
        8,
        Color(0.101961f, 0.101961f, 0.101961f),
        Vector(0.501989f, 0.145542f, -0.852540f),
        Color(0.368627f, 0.121569f, 0.109804f),
        Color(0.909804f, 0.894118f, 0.756863f),
        "Map_Zemyatin"
    )

    fun addPlayer(id: Int, color: Color, start: Vector, direction: Vector) {
        world.addPlayer("Player $id", -1, color, start, direction, Faction.ANY, Formation.SIDE_BY_SIDE)
    }

    addPlayer(1, Color(0f, 0.502f, 0.502f), Vector(-285.8385f, 232.97235f), Vector.dir(0.396998f, -0.91782f))
    addPlayer(2, Color(0f, 0f, 1f), Vector(304.95032f, 289.50983f), Vector.dir(-0.793376f, -0.608732f))
    addPlayer(3, Color(0.502f, 0.502f, 0f), Vector(284.3098f, -284.89603f), Vector.dir(-0.755651f, 0.654975f))
    addPlayer(4, Color(1f, 1f, 0f), Vector(-281.1714f, -377.8817f), Vector.dir(0.753918f, 0.656969f))

    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.BLACK, Vector(-145.1806f, -362.53516f)))

    world.addWorldObject("Terrain_BlackHole_Small", null, "Black Hole Group", Vector(1.800463f, -2.546509f))

    fun addAsteroid(size: String, position: Vector) {
        world.addWorldObject("Asteroid_$size", "Asteroids", "Asteroid Group", position)
    }

    addAsteroid("Large", Vector(110.43811f, 578.83997f))
    addAsteroid("Med", Vector(553.69727f, 339.91428f))
    addAsteroid("Small", Vector(114.23401f, -369.5732f))
    addAsteroid("Huge", Vector(-301.37805f, -442.76627f, 0.000122f))
    addAsteroid("Large", Vector(-494.45972f, 65.156334f))
    addAsteroid("Med", Vector(-236.83673f, 556.8069f))
    addAsteroid("Small", Vector(601.0581f, 162.38853f, 0.000122f))
    addAsteroid("Med", Vector(520.6698f, -197.0405f, 0.000122f))
    addAsteroid("Large", Vector(33.852783f, -304.87033f))
    addAsteroid("Huge", Vector(49.42798f, 355.06207f, -0.000122f))
    addAsteroid("Large", Vector(-341.75085f, -26.253838f))
    addAsteroid("Med", Vector(320.70532f, 26.71062f))
    addAsteroid("Small", Vector(487.2406f, 94.61695f, -0.000122f))

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetOwnerAction("Asteroid Group", "Asteroids"),
        World.createSetObjectiveTaskStateAction("Kill All", true),
        World.createPlayMusicAction("BTL_NavyBig_BassnDrums", 0.7f, 2f, 2f),
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Black Hole", "IDGS_TPMAPTEXTITEMS_MP_ARENA_SMALL", Vector(1.299599f, 4.263611f)))

    println(world.build())
}

fun test() {
    val world = World(game, "Diablo Straight")

    val worldInfo = world.root.get<NestedNode>("WorldInfo")
    val players = worldInfo?.get<FlatNode>("Players")

    val gameNode = world.root.get<NestedNode>("Game")
    val time = gameNode?.get<NestedNode>("Time")

    val worldNode = world.root.get<NestedNode>("World")
    val playerList = worldNode?.get<PlayerListNode>("PlayerList")
    val worldObjects = worldNode?.get<WorldObjectsListNode>("WorldObjects")

    val gameSpecific = worldNode?.get<NestedNode>("GameSpecific")
    val waypointPathInfo = gameSpecific?.get<ArrayNode>("Waypoint Path Info Vector")
    val groups = gameSpecific?.get<GroupsListNode>("Num Groups")
    val worldRules = gameSpecific?.get<NestedNode>("World Rules")
    val ruleList = worldRules?.get<RuleListNode>("Rule List")

    val gameImpl = world.root.get<NestedNode>("GameImpl")

    //println(waypointPathInfo)
}

fun main() {
    //diablo()
    //zemyatin()
    test()
}
