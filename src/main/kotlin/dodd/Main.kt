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
        "Map_Open_Diablo_Straight",
        bufferSize = 200f
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

    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.black, Vector(-1352.0924f, 265.17676f)))

    fun addIsland(typeID: String, position: Vector, cos: Float = 1f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(acos(cos)))
    }

    addIsland("Island_Volcano_01", Vector(-570.1949f, -148.24173f), 0.913547f)
    addIsland("Island_Volcano_02", Vector(-373.1324f, 558.6688f))
    addIsland("Island_Volcano_03", Vector(234.8505f, 133.67128f), 0.104529f)
    addIsland("Island_Volcano_04", Vector(139.5621f, -114.19995f))
    addIsland("Island_Volcano_05", Vector(-459.1112f, 690.91473f))
    addIsland("Island_Volcano_06", Vector(-445.773f, 1232.2682f))
    addIsland("Island_Volcano_07", Vector(-512.3461f, -936.54285f, -0.000244f))
    addIsland("Island_Volcano_02", Vector(261.23947f, -826.29535f))
    addIsland("Island_Volcano_03", Vector(-568.94995f, 579.75446f))
    addIsland("Island_Volcano_02", Vector(142.49583f, -705.3527f), 0.190809f)
    addIsland("Island_Volcano_03", Vector(-622.4879f, 129.23808f))
    addIsland("Island_Rocky_04", Vector(371.6263f, 1357.2142f))
    addIsland("Island_Rocky_05", Vector(279.2582f, 716.40717f))
    addIsland("Island_Rocky_06", Vector(338.89914f, -244.7013f))
    addIsland("Island_Rocky_07", Vector(-491.25952f, -644.5425f))
    addIsland("Island_Rocky_07", Vector(340.92377f, -1287.2688f, -0.000244f))
    addIsland("Island_Rocky_08", Vector(-499.76703f, -1311.3401f))

    val etheriumObjectID = world.addWorldObject("Terrain_Etherium_Current", null, null, Vector(-95f, 2.999973f))

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
        World.createSetupEtheriumCurrentAction(etheriumObjectID, "Etherium Current"),
        World.createSetupAsteroidBeltAction("Asteroid Group", null, FollowMode.TO_END, false, 0f, 0f, 0.02f, 3.02f),
        World.createSetOwnerAction("Asteroid Group", "Asteroids"),
        World.createSetObjectiveTaskStateAction("Kill All", true),
        World.createPlayMusicAction("NEUTRL_AbyssGoo", 1f, 2f, 2f)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Etherium Current", "IDGS_TPMAPTEXTITEMS_MP_DIABLO_STRAIT_CURRENT", Vector(-186.8336f, 9.868164f)))

    println(world.build())
}

fun dragons() {
    val world = World.create(
        game,
        "Dragons Nest",
        "IDGS_TPWORLDNAMES_MP_ARENA",
        "IDGS_TPWORLDDESCRIPTION_MP_ARENA_LARGE",
        Vector(1750f, 1750f, 500f),
        10,
        Color(0.090196f, 0.090196f, 0.090196f),
        Vector(-0.593311f, 0.549935f, -0.587838f),
        Color(0.250980f, 0.509804f, 0.180392f),
        Color(0.992157f, 0.996078f, 0.909804f),
        "Map_DragonsNest"
    )

    fun addPlayer(id: Int, color: Color, start: Vector, direction: Vector) {
        world.addPlayer("Player $id", -1, color, start, direction, Faction.ANY, Formation.SIDE_BY_SIDE)
    }

    addPlayer(1, Color(0f, 0.502f, 0.502f), Vector(-810.8588f, 7.178986f), Vector.dir(0.99862f, 0.052516f))
    addPlayer(2, Color(0.502f, 0.502f, 0f), Vector(-0.446236f, 749.6373f), Vector.dir(-0.059969f, -0.9982f))
    addPlayer(3, Color(0.651f, 0.7922f, 0.9412f), Vector(6.476181f, -684.0395f), Vector.dir(0.028614f, 0.999591f))
    addPlayer(4, Color(1f, 0f, 0f), Vector(-587.4156f, 510.82135f), Vector.dir(0.722116f, -0.691772f))
    addPlayer(5, Color(1f, 0f, 1f), Vector(578.3267f, -486.13425f), Vector.dir(-0.787641f, 0.616134f))
    addPlayer(6, Color(0.7529f, 0.8627f, 0.7529f), Vector(614.0336f, 498.55988f), Vector.dir(-0.838852f, -0.54436f))
    addPlayer(7, Color(0.502f, 0f, 0f), Vector(-552.6162f, -524.29443f), Vector.dir(0.616675f, 0.787218f))
    addPlayer(8, Color(0f, 0f, 1f), Vector(739.2843f, 4.082199f), Vector.dir(-0.998379f, -0.056907f))

    val asteroidPlayerIndex = world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.black, Vector(-189.3843f, -391.80975f)))
    val dragonPlayerIndex = world.addPlayerListElement(World.createFakeFleetElement("Dragons", Color.white, Vector(73.16046f, 97.105835f), Vector.dir(0.377298f, -0.926092f)))

    fun addIsland(typeID: String, position: Vector, cos: Float = 1f, reverse: Boolean = false) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(acos(cos).let { if (reverse) -it else it }))
    }

    addIsland("Island11", Vector(593.9529f, 205.11626f), 0.32556f, true)
    addIsland("Island_Rocky_02", Vector(-0.663002f, -41.20365f))
    addIsland("Island_Rocky_06", Vector(-210.82018f, 538.31537f))
    addIsland("Island02", Vector(284.2792f, 514.01385f))
    addIsland("Island06", Vector(-550.0975f, -63.10529f))
    addIsland("Island08", Vector(599.31525f, -189.26865f))
    addIsland("Island08", Vector(-340.00607f, -440.50412f))
    addIsland("Island_Rocky_01", Vector(278.2439f, -490.7169f))
    addIsland("Island_Med01", Vector(-711.94025f, -200.62675f))

    fun addAsteroid(size: String, position: Vector) {
        world.addWorldObject("Asteroid_$size", "Asteroids", "Asteroid Group", position)
    }

    addAsteroid("Large", Vector(286.57913f, 103.3194f))
    addAsteroid("Med", Vector(272.23532f, -72.081055f))
    addAsteroid("Small", Vector(255.4793f, -252.46716f))
    addAsteroid("Small", Vector(33.94876f, -333.44055f, -0.000122f))
    addAsteroid("Small", Vector(-122.97598f, -397.45337f))
    addAsteroid("Small", Vector(-350.17474f, -255.22034f))
    addAsteroid("Small", Vector(-315.39996f, -68.5871f))
    addAsteroid("Small", Vector(-387.52136f, 117.73651f))
    addAsteroid("Huge", Vector(-259.00262f, 203.95416f))
    addAsteroid("Large", Vector(-91.305695f, 367.22708f))
    addAsteroid("Med", Vector(13.736923f, 256.09293f, -30f))
    addAsteroid("Med", Vector(194.70226f, 243.272f))
    addAsteroid("Small", Vector(160.0329f, 155.08075f))
    addAsteroid("Small", Vector(137.07669f, -225.49567f))
    addAsteroid("Small", Vector(60.411835f, 339.69247f))
    addAsteroid("Med", Vector(60.579254f, -389.54706f))
    addAsteroid("Med", Vector(182.42752f, -302.45123f))
    addAsteroid("Med", Vector(-228.732f, -333.11414f, 0.000122f))
    addAsteroid("Large", Vector(-381.14832f, -138.30621f))
    addAsteroid("Large", Vector(-52.401367f, -421.33197f, -0.000122f))

    fun addDragon(position: Vector, cos: Float) {
        world.addWorldObject("Animal_SpaceDragon", "Dragons", "Dragon Group", position, Matrix.rotationZ(acos(cos)))
    }

    addDragon(Vector(106.30721f, 75.81913f, 12f), -0.766046f)
    addDragon(Vector(112.630615f, 152.48358f, 19.99997f), -0.766049f)
    addDragon(Vector(47.491673f, 140.87505f, 35.99997f), -0.766051f)

    world.addWaypointPath(World.createWaypointPath(
        "Asteroid Path",
        Vector(194.486f, -270.4562f),
        Vector(53.256634f, -351.66745f),
        Vector(-59.41383f, -356.08096f),
        Vector(-196.62004f, -317.25153f),
        Vector(-306.2032f, -218.09283f),
        Vector(-370.28613f, -17.374634f),
        Vector(-363.4583f, 110.42905f),
        Vector(-314.89197f, 206.56105f),
        Vector(-231.5422f, 280.80075f),
        Vector(-85.22492f, 313.9124f),
        Vector(76.25892f, 277.69125f),
        Vector(193.27872f, 186.07806f),
        Vector(269.8293f, 80.50735f),
        Vector(293.33453f, -38.595524f),
        Vector(275.6875f, -170.98976f)
    ))

    world.addWaypointPath(World.createWaypointPath(
        "Dragon Path",
        Vector(137.00256f, 114.405716f, 38.999985f),
        Vector(209.01056f, 16.97841f, 37f),
        Vector(214.7295f, -78.03894f, 37f),
        Vector(180.22133f, -157.5344f, 37f),
        Vector(88.1502f, -238.6536f, 40f),
        Vector(-61.731857f, -240.48607f, 37f),
        Vector(-172.89912f, -166.25266f, 42f),
        Vector(-217.83444f, -47.670837f, 32f),
        Vector(-212.78978f, 67.4821f, 35.00006f),
        Vector(-142.77435f, 158.03029f, 45f),
        Vector(-38.00087f, 190.31392f, 47f),
        Vector(63.719822f, 168.0781f, 41f)
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        "Dragon Flee Point Set",
        World.createWorldPoint(5f, Vector(-1608.0801f, -1702.6455f), 0f, Vector.north)
    ))

    world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(asteroidPlayerIndex, dragonPlayerIndex))

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetupAsteroidBeltAction("Asteroid Group", "Asteroid Path", FollowMode.LOOP, true, 5f, 20f, 0.05f, 4f),
        World.createSetOwnerAction("Asteroid Group", "Asteroids"),
        World.createGroupFollowPathAction("Dragon Group", "Dragon Path", FollowMode.LOOP, true),
        World.createSetOwnerAction("Dragon Group", "Dragons"),
        World.createSetDragonStanceAction("Dragon Group", Stance.AGGRESSIVE),
        World.createSetDragonDamageThresholdAction("Dragon Group", 0.8f),
        World.createSetGroupReturnZone("Dragon Group", "Dragon Flee Point Set"),
        World.createSetObjectiveTaskStateAction("Kill All", true),
        World.createPlayMusicAction("BTL_Dragon02_FullFast", 1f, 2f, 2f, false)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Dragons", "IDGS_TPMAPTEXTITEMS_GENERAL_ISLANDS_FORSAKEN_RANGE", Vector(-3.243332f, -17.338562f)))

    println(world.build())
}

fun shadow() {
    val world = World.create(
        game,
        "Shadow Dance",
        "IDGS_TPWORLDNAMES_MP_NEBULA_WAR",
        "IDGS_TPWORLDDESCRIPTION_MP_SHADOW_DANCE",
        Vector(1900f, 1900f, 1000f),
        1,
        Color(0.152941f, 0.152941f, 0.152941f),
        Vector(-0.219754f, -0.815193f, -0.535881f),
        Color(0.203922f, 0.192157f, 0.423529f),
        Color(0.862745f, 0.980392f, 0.968627f),
        "Map_ShadowDance",
        randomSeed = -1526094716,
        bufferSize = 200f
    )

    fun addPlayer(id: Int, color: Color, start: Vector, direction: Vector) {
        world.addPlayer("Player $id", -1, color, start, direction, Faction.ANY, Formation.DIAGONAL_V)
    }

    addPlayer(1, Color(0f, 0.502f, 0f), Vector(-541.1266f, 630.96155f), Vector.dir(1f, 0f))
    addPlayer(2, Color(0f, 0f, 0.502f), Vector(622.7023f, 592.0886f), Vector.dir(1f, 0f))
    addPlayer(3, Color(0f, 0.502f, 0.502f), Vector(6.498825f, 882.9153f), Vector.dir(1f, 0f))
    addPlayer(4, Color(0.651f, 0.7922f, 0.9412f), Vector(799.8519f, -26.944336f), Vector.dir(1f, 0f))
    addPlayer(5, Color(0.9059f, 0.5373f, 0.0392f), Vector(18.560791f, -810.7614f), Vector.dir(1f, 0f))
    addPlayer(6, Color(1f, 0f, 0f), Vector(603.85004f, -508.37128f), Vector.dir(1f, 0f))
    addPlayer(7, Color(0f, 0f, 1f), Vector(-720.58923f, 176.86047f), Vector.dir(1f, 0f))
    addPlayer(8, Color(1f, 1f, 0f), Vector(-475.92407f, -529.2828f), Vector.dir(1f, 0f))

    val nebulaObjectID = world.addWorldObject("Terrain_Nebula", null, null, Vector(34.784943f, 47.516388f))

    fun addIsland(typeID: String, position: Vector, cos: Float = 1f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(acos(cos)))
    }

    addIsland("Island01", Vector(-649.7423f, 1027.5746f))
    addIsland("Island02", Vector(-441.42435f, 1172.3391f))
    addIsland("Island03", Vector(-153.73283f, 1220.6642f), 0.139173f)
    addIsland("Island04", Vector(159.30537f, 1186.1365f, -0.000244f))
    addIsland("Island05", Vector(383.55704f, 1076.261f))
    addIsland("Island06", Vector(542.7456f, 936.31195f))
    addIsland("Island07", Vector(-916.1911f, 703.7328f, 0.000244f))
    addIsland("Island08", Vector(888.22754f, 560.2029f))
    addIsland("Island08", Vector(1022.2626f, 249.93701f))
    addIsland("Island09", Vector(1096.4343f, -65.76062f, 0.000244f))
    addIsland("Island10", Vector(1056.2955f, -312.54553f))
    addIsland("Island11", Vector(956.46545f, -533.6833f))
    addIsland("Island12", Vector(771.95044f, -767.37634f))
    addIsland("Island13", Vector(554.7782f, -974.2602f))
    addIsland("Island14", Vector(92.87059f, -1105.2847f))
    addIsland("Island_Rocky_01", Vector(-279.39697f, -980.0161f))
    addIsland("Island_Dragon_07", Vector(-674.81915f, -751.10645f), 0.939693f)
    addIsland("Island_Dragon_01", Vector(-949.8212f, -332.29706f), -0.275637f)
    addIsland("Island_Rocky_03", Vector(-1067.1179f, 112.55988f, 0.000244f))
    addIsland("Island_Small01", Vector(-1060.196f, 468.01663f))
    addIsland("Island_Rocky_06", Vector(698.668f, 781.9974f))
    addIsland("Island_Rocky_08", Vector(-818.13605f, 917.51086f))

    fun addWhale(position: Vector) {
        world.addWorldObject("Animal_SpaceWhale", null, "Whale Group", position, Matrix.rotationZ(0.34906474f))
    }

    addWhale(Vector(-458.10767f, -919.8695f, -0.000031f))
    addWhale(Vector(-479.84482f, -961.45966f))
    addWhale(Vector(-428.18384f, -962.39734f))
    addWhale(Vector(-441.9633f, -1008.9878f))

    fun addDerelict(typeID: String, position: Vector, rotation: Matrix = Matrix.identity) {
        world.addWorldObject("Ship_${typeID}_Wrecked", null, "Derelict Group", position, rotation)
    }

    addDerelict("Civilian_Barque", Vector(-138.69653f, 112.54449f), Matrix.rotationZ(acos(0.838671f)))
    addDerelict("Civilian_Galleon", Vector(131.05696f, -233.56627f), Matrix(
        -0.643565f, 0.764314f, -0.040642f,
        -0.628794f, -0.558238f, -0.541287f,
        -0.436399f, -0.322797f, 0.839856f
    ))
    addDerelict("Pirate_FastFrigate", Vector(219.05482f, 307.33923f), Matrix(
        0.568634f, -0.781172f, 0.257737f,
        0.81216f, 0.483413f, -0.326664f,
        0.130587f, 0.395076f, 0.90932f
    ))
    addDerelict("Pirate_JollyRoger", Vector(107.59898f, 60.13287f, 0.000031f), Matrix(
        0.787765f, -0.327514f, 0.521694f,
        0.594988f, 0.623792f, -0.50683f,
        -0.159434f, 0.709663f, 0.686268f
    ))

    world.addWaypointPath(World.createWaypointPath(
        "Whale Path",
        Vector(-476.54776f, -908.5619f),
        Vector(-507.09027f, -841.03516f),
        Vector(-612.07965f, -587.5864f),
        Vector(-854.8967f, -216.02747f),
        Vector(-1216.9668f, 54.63208f),
        Vector(-1312.6748f, 315.88477f),
        Vector(-1365.723f, 576.553f),
        Vector(-1319.328f, 677.8706f),
        Vector(-1093.0818f, 671.4138f),
        Vector(-897.8308f, 595.2329f),
        Vector(-748.39954f, 684.34326f),
        Vector(-627.40625f, 824.301f),
        Vector(-545.5033f, 997.9199f),
        Vector(-555.1889f, 1279.6873f),
        Vector(-456.21164f, 1481.9824f),
        Vector(-302.15555f, 1517.1401f),
        Vector(-153.366f, 1473.2595f),
        Vector(-2.176825f, 1191.6482f),
        Vector(107.15542f, 1001.5842f),
        Vector(235.45662f, 957.9199f),
        Vector(406.88058f, 965.1477f),
        Vector(540.15247f, 1129.3542f),
        Vector(792.9584f, 1121.6672f),
        Vector(896.7057f, 1023.6904f),
        Vector(975.20435f, 838.0432f),
        Vector(992.3482f, 545.7366f),
        Vector(926.34875f, 296.05176f),
        Vector(930.2272f, 141.78223f),
        Vector(971.9022f, 61.04785f),
        Vector(1057.4847f, -182.08301f),
        Vector(1121.3546f, -298.24878f),
        Vector(1114.8103f, -501.20703f),
        Vector(1024.9f, -612.48584f),
        Vector(876.6885f, -626.9935f),
        Vector(717.50006f, -616.48584f),
        Vector(562.8734f, -748.55066f),
        Vector(452.96356f, -877.8993f),
        Vector(373.4039f, -1050.9402f),
        Vector(265.71936f, -1169.5652f),
        Vector(133.29494f, -1202.2246f),
        Vector(-55.164078f, -1194.749f),
        Vector(-225.98544f, -1172.102f),
        Vector(-331.31174f, -1133.607f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Nebula Polygon",
        Coord(-484.43982f, 348.28317f),
        Coord(-570.4739f, 51.524334f),
        Coord(-490.3123f, -252.31458f),
        Coord(-262.51105f, -479.47018f),
        Coord(32.53079f, -559.22284f),
        Coord(344.3778f, -475.55334f),
        Coord(555.2445f, -262.29047f),
        Coord(640.04376f, 38.81142f),
        Coord(556.7212f, 349.3838f),
        Coord(331.84933f, 573.2532f),
        Coord(27.883099f, 654.2556f),
        Coord(-272.7998f, 565.21735f)
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        "Nebula Point Set",
        World.createWorldPoint(577.4005f, Vector(30.162416f, 40.36957f), 1f, Vector(0f, 1f))
    ))

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetupNebulaAction(
            nebulaObjectID,
            "Nebula Weather",
            "Nebula Polygon",
            "Nebula Point Set",
            energyDrain = true,
            occlusion = true,
            300f
        ),
        World.createGroupFollowPathAction("Whale Group", "Whale Path", FollowMode.LOOP, true),
        World.createSetGroupSpeedAction("Whale Group", 8),
        World.createSetObjectiveTaskStateAction("Kill All", true),
        World.createPlayMusicAction("NEUTRL_DragonGoo", 0.7f, 2f, 2f)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Nebula", "IDGS_TPMAPTEXTITEMS_MP_SHADOW_DANCE_NEBULA", Vector(19.76284f, 48.68347f)))
    world.addMapText(World.createMapText("Islands", "IDGS_TPMAPTEXTITEMS_BTUT_SHADOW_ISLANDS", Vector(-301.43805f, -1010.7231f)))

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

    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.black, Vector(-145.1806f, -362.53516f)))

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
        World.createPlayMusicAction("BTL_NavyBig_BassnDrums", 0.7f, 2f, 2f)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Black Hole", "IDGS_TPMAPTEXTITEMS_MP_ARENA_SMALL", Vector(1.299599f, 4.263611f)))

    println(world.build())
}

fun test() {
    val world = World(game, "Shadow Dance")

    val worldInfo = world.root.get<NestedNode>("WorldInfo")
    val players = worldInfo?.get<FlatNode>("Players")

    val gameNode = world.root.get<NestedNode>("Game")
    val time = gameNode?.get<NestedNode>("Time")

    val worldNode = world.root.get<NestedNode>("World")
    val playerList = worldNode?.get<PlayerListNode>("PlayerList")
    val worldObjects = worldNode?.get<WorldObjectsListNode>("WorldObjects")

    val gameSpecific = worldNode?.get<NestedNode>("GameSpecific")
    val waypointPathInfo = gameSpecific?.get<ArrayNode>("Waypoint Path Info Vector")
    val worldPolygonInfo = gameSpecific?.get<ArrayNode>("World Polygons Vectors")
    val worldPointSetInfo = gameSpecific?.get<ArrayNode>("World Point Sets Vector")
    val groups = gameSpecific?.get<GroupsListNode>("Num Groups")
    val worldRules = gameSpecific?.get<NestedNode>("World Rules")
    val ruleList = worldRules?.get<RuleListNode>("Rule List")

    val gameImpl = world.root.get<NestedNode>("GameImpl")

    println(ruleList)
}

fun main() {
    //diablo()
    //dragons()
    //shadow()
    //zemyatin()
    //test()
}
