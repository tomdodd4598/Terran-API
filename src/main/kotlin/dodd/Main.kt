package dodd

import dodd.terran.Game
import dodd.terran.World
import dodd.terran.translation.*
import dodd.terran.util.Helpers.forEachPair
import dodd.terran.util.Helpers.of
import dodd.terran.util.Helpers.titlecase
import dodd.terran.value.*
import kotlin.math.acos

val game = Game("C:/Program Files (x86)/Steam/steamapps/common/Treasure Planet Battle at Procyon", "English")

fun border() {
    val world = World.create(
        game,
        "Border_Dispute",
        "IDGS_TPWORLDNAMES_MP_BORDER_DISPUTE",
        "IDGS_TPWORLDDESCRIPTION_MP_BORDER_DISPUTE",
        Vector(2250f, 2250f, 1000f),
        4,
        Color(0.219608f, 0.219608f, 0.219608f),
        Vector(-0.571849f, -0.446337f, -0.688311f),
        Color(0.556863f, 0.309804f, 0.835294f),
        Color(0.870588f, 0.909804f, 0.972549f),
        "Map_Border_Dispute",
        allianceChangeAllowed = false
    )

    val navyTeamID = "IDGS_TPTEAMNAMES_NAVY"
    val procyonTeamID = "IDGS_TPTEAMNAMES_PROCYON"

    val navyTeamIndex = world.addTeam(World.createTeam(navyTeamID, Faction.NAVY))
    val procyonTeamIndex = world.addTeam(World.createTeam(procyonTeamID, Faction.PROCYON))

    val navyAllianceIndices = mutableListOf<Int>()
    val procyonAllianceIndices = mutableListOf<Int>()

    fun addPlayer(faction: Faction, id: Int, color: Color, start: Vector, direction: Vector, formation: Formation) {
        val factionPair = if (faction == Faction.NAVY) navyTeamIndex to navyAllianceIndices else procyonTeamIndex to procyonAllianceIndices
        factionPair.second.add(world.addPlayer("${faction.name.titlecase()} $id", factionPair.first, color, start, direction, faction, formation))
    }

    addPlayer(Faction.NAVY, 1, Color(0f, 0f, 1f), Vector(435.6725f, -837.33856f), Vector.dir(-0.592208f, 0.805785f), Formation.SIDE_BY_SIDE)
    addPlayer(Faction.PROCYON, 1, Color(0f, 1f, 0f), Vector(-649.6093f, 775.55676f), Vector.dir(0.602041f, -0.798465f), Formation.LINE)
    addPlayer(Faction.NAVY, 2, Color(0f, 1f, 1f), Vector(739.2612f, -641.6178f), Vector.dir(-0.598932f, 0.8008f), Formation.SIDE_BY_SIDE)
    addPlayer(Faction.PROCYON, 2, Color(0f, 0.501961f, 0f), Vector(-235.88f, 1047.5782f), Vector.dir(0.381282f, -0.924459f), Formation.DIAGONAL_V)
    addPlayer(Faction.NAVY, 3, Color(0.65098f, 0.792157f, 0.941176f), Vector(416.74933f, -1185.8826f), Vector.dir(-0.211376f, 0.977405f), Formation.SIDE_BY_SIDE)
    addPlayer(Faction.PROCYON, 3, Color(0.752941f, 0.862745f, 0.752941f), Vector(59.84198f, 1271.3126f), Vector.dir(0.557207f, -0.830374f), Formation.LINE)
    addPlayer(Faction.NAVY, 4, Color(0.25098f, 0f, 0.501961f), Vector(1122.5618f, -745.0312f), Vector.dir(-0.593589f, 0.804768f), Formation.SIDE_BY_SIDE)
    addPlayer(Faction.PROCYON, 4, Color(0.043137f, 0.392157f, 0.094118f), Vector(-1033.7277f, 1103.3523f), Vector.dir(0.505127f, -0.863045f), Formation.LINE)

    navyAllianceIndices.add(world.addPlayerListElement(World.createFakeFleetElement("Navy Base", Color.blue, Vector(924.07874f, -1053.405f))))
    procyonAllianceIndices.add(world.addPlayerListElement(World.createFakeFleetElement("Procyon Base", Color(0.043137f, 0.392157f, 0.094118f), Vector(-693.76605f, 1286.6199f))))

    val navyBaseObjectID = world.addWorldObject("Base_Grant", "Navy Base", "Navy Base Group", Vector(746.5273f, -835.4745f), Matrix.rotationZ(-acos(0.829038f)))
    val procyonBaseObjectID = world.addWorldObject("Base_Procyon1", "Procyon Base", "Procyon Base Group", Vector(-549.45374f, 1083.6077f), Matrix.rotationZ(-acos(0.453991f)))

    fun addIsland(typeID: String, position: Vector, xx: Float = 1f, yx: Float = 0f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(xx, yx))
    }

    addIsland("Island_Med01", Vector(-1682.0643f, 247.44098f))
    addIsland("Island_Med02", Vector(-1422.3075f, 1.916485f), -0.052336f, -0.99863f)
    addIsland("Island_Med03", Vector(-1519.716f, -517.098f))
    addIsland("Island_Med04", Vector(333.21024f, -1443.0612f), -0.798636f, 0.601816f)
    addIsland("Island_Med05", Vector(1578.002f, -1509.9657f))
    addIsland("Island_Med06", Vector(1512.8148f, 270.6461f), 0.34202f, -0.939693f)
    addIsland("Island_Med07", Vector(-1094.5066f, -840.8569f), -0.978149f, -0.207912f)
    addIsland("Island_Med08", Vector(1081.5355f, 992.1099f))
    addIsland("Island_Mega_06", Vector(-634.19904f, -154.00272f), -0.970297f, -0.241923f)
    addIsland("Island_Mega_05", Vector(657.4457f, 269.49f), -0.788011f, -0.615662f)
    addIsland("Island_Mega_03", Vector(-1174.9667f, -1209.3888f), -0.999848f, -0.017452f)
    addIsland("Island_Mega_02", Vector(1252.7269f, 1398.4803f), -0.422618f, -0.906308f)
    addIsland("Island14", Vector(1182.4011f, 531.8025f))
    addIsland("Island13", Vector(1363.2633f, -94.21065f), 0.98769f, 0.156435f)
    addIsland("Island12", Vector(1653.4932f, -340.90073f), 0.559193f, -0.829038f)
    addIsland("Island11", Vector(1149.8627f, -924.71094f))
    addIsland("Island10", Vector(742.98083f, -1331.3135f))
    addIsland("Island09", Vector(369.46997f, -434.84863f))
    addIsland("Island08", Vector(-179.07996f, 124.71167f))
    addIsland("Island07", Vector(512.019f, -72.26687f))
    addIsland("Island06", Vector(-1082.0189f, -165.89053f))
    addIsland("Island05", Vector(-889.0033f, -1498.1262f))
    addIsland("Island04", Vector(1604.9197f, -663.32526f))
    addIsland("Island_Lighthouse", Vector(580.4044f, -583.74084f))
    addIsland("Island_Large01", Vector(9.199231f, -2.628757f), 0.681998f, -0.731354f)
    addIsland("Island_Icy_01", Vector(177.24965f, 1588.8839f), 0.669131f, -0.743145f)
    addIsland("Island_Icy_02", Vector(-305.4605f, 1412.5447f))
    addIsland("Island_Icy_03", Vector(-944.90906f, 1374.6497f))
    addIsland("Island_Icy_04", Vector(-1404.1014f, 1397.1714f))
    addIsland("Island_Med01", Vector(124.50447f, 938.5067f))
    addIsland("Island_Med06", Vector(143.93956f, 758.11523f))

    val nebulaObjectID = world.addWorldObject("Terrain_Nebula", null, null, Vector.zero)

    fun addMine(faction: Faction, position: Vector) {
        world.addWorldObject("Bullet_StarMine_Med", null, "${faction.name.titlecase()} Mine Group", position)
    }

    addMine(Faction.NAVY, Vector(234.83942f, -619.04095f))
    addMine(Faction.NAVY, Vector(263.1985f, -604.13684f))
    addMine(Faction.NAVY, Vector(303.71832f, -581.9927f))
    addMine(Faction.NAVY, Vector(343.25085f, -556.4949f))
    addMine(Faction.NAVY, Vector(389.38245f, -529.0404f))
    addMine(Faction.NAVY, Vector(419.50238f, -509.14163f))
    addMine(Faction.NAVY, Vector(449.37396f, -491.36398f))
    addMine(Faction.NAVY, Vector(484.6767f, -470.35403f))
    addMine(Faction.NAVY, Vector(524.7485f, -442.5504f))
    addMine(Faction.NAVY, Vector(550.58453f, -427.17444f))
    addMine(Faction.NAVY, Vector(594.4977f, -399.05804f))
    addMine(Faction.NAVY, Vector(633.9606f, -375.5723f))
    addMine(Faction.NAVY, Vector(671.15845f, -351.4495f))
    addMine(Faction.NAVY, Vector(705.68567f, -328.91315f))
    addMine(Faction.NAVY, Vector(739.7554f, -308.63715f))
    addMine(Faction.NAVY, Vector(774.3587f, -286.0525f))
    addMine(Faction.NAVY, Vector(807.08966f, -266.57324f))
    addMine(Faction.NAVY, Vector(835.7291f, -249.52887f))
    addMine(Faction.NAVY, Vector(867.85657f, -232.39972f))
    addMine(Faction.NAVY, Vector(901.1905f, -210.57056f))
    addMine(Faction.NAVY, Vector(937.95905f, -184.69763f))
    addMine(Faction.NAVY, Vector(970.738f, -165.18982f))
    addMine(Faction.NAVY, Vector(1024.6489f, -135.10236f))
    addMine(Faction.NAVY, Vector(1005.4421f, -95.707825f))
    addMine(Faction.NAVY, Vector(976.2514f, -100.59546f))
    addMine(Faction.NAVY, Vector(942.0528f, -123.036804f))
    addMine(Faction.NAVY, Vector(893.1998f, -152.11066f))
    addMine(Faction.NAVY, Vector(849.92993f, -177.86194f))
    addMine(Faction.NAVY, Vector(804.43774f, -202.84705f))
    addMine(Faction.NAVY, Vector(751.8866f, -232.03009f))
    addMine(Faction.NAVY, Vector(700.57764f, -266.74615f))
    addMine(Faction.NAVY, Vector(656.3685f, -290.96777f))
    addMine(Faction.NAVY, Vector(584.13293f, -331.86548f))
    addMine(Faction.NAVY, Vector(533.80646f, -361.8164f))
    addMine(Faction.NAVY, Vector(473.6938f, -397.59125f))
    addMine(Faction.NAVY, Vector(440.95123f, -421.25803f))
    addMine(Faction.NAVY, Vector(400.47125f, -445.34888f))
    addMine(Faction.NAVY, Vector(337.4416f, -484.9454f))
    addMine(Faction.NAVY, Vector(276.57806f, -514.901f))
    addMine(Faction.NAVY, Vector(235.89703f, -541.20337f))
    addMine(Faction.NAVY, Vector(189.69403f, -570.78906f))
    addMine(Faction.NAVY, Vector(141.52917f, -533.19543f))
    addMine(Faction.NAVY, Vector(173.35193f, -505.4746f))
    addMine(Faction.NAVY, Vector(206.21234f, -488.11926f))
    addMine(Faction.NAVY, Vector(250.7826f, -459.39343f))
    addMine(Faction.NAVY, Vector(277.11682f, -437.09866f))
    addMine(Faction.NAVY, Vector(308.93616f, -415.9476f))
    addMine(Faction.NAVY, Vector(337.15338f, -390.2624f))
    addMine(Faction.NAVY, Vector(372.72375f, -373.54666f))
    addMine(Faction.NAVY, Vector(402.1568f, -362.68387f))
    addMine(Faction.NAVY, Vector(429.3454f, -342.17673f))
    addMine(Faction.NAVY, Vector(454.9474f, -326.3264f))
    addMine(Faction.NAVY, Vector(474.58536f, -312.43542f))
    addMine(Faction.NAVY, Vector(495.82022f, -298.75055f))
    addMine(Faction.NAVY, Vector(516.90173f, -286.7827f))
    addMine(Faction.NAVY, Vector(538.96185f, -270.75375f))
    addMine(Faction.NAVY, Vector(565.8772f, -253.66663f))
    addMine(Faction.NAVY, Vector(581.7327f, -244.60849f))
    addMine(Faction.NAVY, Vector(608.88245f, -222.53741f))
    addMine(Faction.NAVY, Vector(629.1914f, -206.42465f))
    addMine(Faction.NAVY, Vector(655.8188f, -189.53375f))
    addMine(Faction.NAVY, Vector(688.94305f, -176.33856f))
    addMine(Faction.NAVY, Vector(717.62805f, -159.12372f))
    addMine(Faction.NAVY, Vector(736.3948f, -141.22177f))
    addMine(Faction.NAVY, Vector(725.4971f, -160.64258f))
    addMine(Faction.NAVY, Vector(750.2384f, -142.49847f))
    addMine(Faction.NAVY, Vector(774.71576f, -126.286896f))
    addMine(Faction.NAVY, Vector(804.0005f, -110.97107f))
    addMine(Faction.NAVY, Vector(841.0712f, -78.647644f))
    addMine(Faction.NAVY, Vector(863.982f, -60.36847f))
    addMine(Faction.NAVY, Vector(823.6831f, -45.619568f))
    addMine(Faction.NAVY, Vector(798.50635f, -44.756287f))
    addMine(Faction.NAVY, Vector(761.5998f, -53.83313f))
    addMine(Faction.NAVY, Vector(712.5383f, -84.05054f))
    addMine(Faction.NAVY, Vector(669.67224f, -110.59216f))
    addMine(Faction.NAVY, Vector(629.2813f, -136.73294f))
    addMine(Faction.NAVY, Vector(589.65424f, -162.16324f))
    addMine(Faction.NAVY, Vector(550.5669f, -190.75049f))
    addMine(Faction.NAVY, Vector(511.65176f, -213.6438f))
    addMine(Faction.NAVY, Vector(466.54523f, -245.57907f))
    addMine(Faction.NAVY, Vector(418.15094f, -287.77655f))
    addMine(Faction.NAVY, Vector(360.27072f, -321.4985f))
    addMine(Faction.NAVY, Vector(317.49716f, -338.1225f))
    addMine(Faction.NAVY, Vector(269.48062f, -370.51917f))
    addMine(Faction.NAVY, Vector(210.20337f, -405.03757f))
    addMine(Faction.NAVY, Vector(151.54419f, -442.72467f))
    addMine(Faction.NAVY, Vector(97.87036f, -483.728f))
    addMine(Faction.NAVY, Vector(33.357544f, -469.0082f))
    addMine(Faction.NAVY, Vector(51.820435f, -442.8434f))
    addMine(Faction.NAVY, Vector(95.37421f, -406.8833f))
    addMine(Faction.NAVY, Vector(122.02826f, -391.69025f))
    addMine(Faction.NAVY, Vector(174.07898f, -364.2922f))
    addMine(Faction.NAVY, Vector(209.3494f, -339.49704f))
    addMine(Faction.NAVY, Vector(234.3862f, -317.66583f))
    addMine(Faction.NAVY, Vector(273.51053f, -289.492f))

    addMine(Faction.PROCYON, Vector(-86.3808f, 311.7428f))
    addMine(Faction.PROCYON, Vector(-100.19641f, 278.7166f))
    addMine(Faction.PROCYON, Vector(-60.493042f, 283.576f))
    addMine(Faction.PROCYON, Vector(-51.444885f, 321.20782f))
    addMine(Faction.PROCYON, Vector(-89.2926f, 336.38446f))
    addMine(Faction.PROCYON, Vector(-123.09119f, 312.99677f))
    addMine(Faction.PROCYON, Vector(-110.241455f, 272.95105f))
    addMine(Faction.PROCYON, Vector(-72.49951f, 258.34003f))
    addMine(Faction.PROCYON, Vector(-16.069275f, 295.75546f))
    addMine(Faction.PROCYON, Vector(-20.001404f, 330.7337f))
    addMine(Faction.PROCYON, Vector(-62.068542f, 366.18323f))
    addMine(Faction.PROCYON, Vector(-373.2716f, 108.86225f))
    addMine(Faction.PROCYON, Vector(-415.01038f, 63.985046f))
    addMine(Faction.PROCYON, Vector(-388.8363f, 44.52482f))
    addMine(Faction.PROCYON, Vector(-356.1753f, 47.81601f))
    addMine(Faction.PROCYON, Vector(-326.1194f, 65.6501f))
    addMine(Faction.PROCYON, Vector(-326.36365f, 98.96935f))
    addMine(Faction.PROCYON, Vector(-349.7932f, 108.098404f))
    addMine(Faction.PROCYON, Vector(-389.74194f, 107.29155f))
    addMine(Faction.PROCYON, Vector(-379.9159f, 77.61385f))
    addMine(Faction.PROCYON, Vector(-660.98926f, 53.74621f))
    addMine(Faction.PROCYON, Vector(-619.4967f, 1.92355f))
    addMine(Faction.PROCYON, Vector(-586.376f, 26.432981f))
    addMine(Faction.PROCYON, Vector(-606.8541f, 52.32503f))
    addMine(Faction.PROCYON, Vector(-643.6105f, 51.15978f))
    addMine(Faction.PROCYON, Vector(-663.80334f, 7.091452f))
    addMine(Faction.PROCYON, Vector(-620.1824f, 18.788055f))
    addMine(Faction.PROCYON, Vector(-628.3137f, 38.020184f))
    addMine(Faction.PROCYON, Vector(-280.63333f, 402.78046f))
    addMine(Faction.PROCYON, Vector(-350.20422f, 385.39087f))
    addMine(Faction.PROCYON, Vector(-350.58453f, 359.06558f))
    addMine(Faction.PROCYON, Vector(-318.1537f, 365.7803f))
    addMine(Faction.PROCYON, Vector(-317.60278f, 396.45694f))
    addMine(Faction.PROCYON, Vector(-335.9558f, 406.50577f))
    addMine(Faction.PROCYON, Vector(-331.6836f, 384.1857f))
    addMine(Faction.PROCYON, Vector(-343.68384f, 356.4478f))
    addMine(Faction.PROCYON, Vector(-385.28577f, 372.2294f))
    addMine(Faction.PROCYON, Vector(-368.57568f, 404.2706f))
    addMine(Faction.PROCYON, Vector(-306.5426f, 371.60178f))
    addMine(Faction.PROCYON, Vector(-355.39557f, 346.24115f))
    addMine(Faction.PROCYON, Vector(-564.65137f, 245.54771f))
    addMine(Faction.PROCYON, Vector(-546.8397f, 229.35606f))
    addMine(Faction.PROCYON, Vector(-527.9231f, 254.88084f))
    addMine(Faction.PROCYON, Vector(-545.4098f, 262.3164f))
    addMine(Faction.PROCYON, Vector(-589.8252f, 255.54047f))
    addMine(Faction.PROCYON, Vector(-584.0502f, 233.19014f))
    addMine(Faction.PROCYON, Vector(-549.51697f, 236.06201f))
    addMine(Faction.PROCYON, Vector(-511.49304f, 282.79108f))
    addMine(Faction.PROCYON, Vector(-543.6937f, 286.59778f))
    addMine(Faction.PROCYON, Vector(-568.1044f, 274.46976f))
    addMine(Faction.PROCYON, Vector(-537.2517f, 237.71185f))
    addMine(Faction.PROCYON, Vector(-1552.2222f, 859.0943f))
    addMine(Faction.PROCYON, Vector(-1540.4592f, 819.22894f))
    addMine(Faction.PROCYON, Vector(-1527.2883f, 814.0695f))
    addMine(Faction.PROCYON, Vector(-1502.2454f, 819.28864f))
    addMine(Faction.PROCYON, Vector(-1478.4434f, 840.02264f))
    addMine(Faction.PROCYON, Vector(-1518.457f, 863.4107f))
    addMine(Faction.PROCYON, Vector(-1562.3899f, 775.18976f))
    addMine(Faction.PROCYON, Vector(-1540.6597f, 769.26447f))
    addMine(Faction.PROCYON, Vector(-1522.0801f, 802.00995f))
    addMine(Faction.PROCYON, Vector(-1515.3743f, 824.4478f))
    addMine(Faction.PROCYON, Vector(-1491.9055f, 795.21027f))
    addMine(Faction.PROCYON, Vector(735.5168f, 1348.0823f))
    addMine(Faction.PROCYON, Vector(698.76733f, 1307.8965f))
    addMine(Faction.PROCYON, Vector(744.2504f, 1302.9221f))
    addMine(Faction.PROCYON, Vector(751.2367f, 1313.0472f))
    addMine(Faction.PROCYON, Vector(759.06415f, 1340.1088f))
    addMine(Faction.PROCYON, Vector(735.91473f, 1356.6042f))
    addMine(Faction.PROCYON, Vector(697.4f, 1327.1334f))
    addMine(Faction.PROCYON, Vector(737.6675f, 1301.2222f))
    addMine(Faction.PROCYON, Vector(792.6378f, 1320.9464f))
    addMine(Faction.PROCYON, Vector(776.76385f, 1347.426f))
    addMine(Faction.PROCYON, Vector(749.36676f, 1364.2377f))
    addMine(Faction.PROCYON, Vector(701.95465f, 1331.1027f))
    addMine(Faction.PROCYON, Vector(739.52997f, 1294.7753f))
    addMine(Faction.PROCYON, Vector(787.68713f, 1308.614f))
    addMine(Faction.PROCYON, Vector(772.88025f, 1356.2081f))
    addMine(Faction.PROCYON, Vector(-845.9105f, 834.45654f))
    addMine(Faction.PROCYON, Vector(-892.7704f, 941.00024f))
    addMine(Faction.PROCYON, Vector(-899.49414f, 1013.9105f))
    addMine(Faction.PROCYON, Vector(-882.96356f, 1107.9391f))
    addMine(Faction.PROCYON, Vector(-853.215f, 1198.1849f))
    addMine(Faction.PROCYON, Vector(-409.33365f, 1386.2104f))
    addMine(Faction.PROCYON, Vector(-347.32825f, 1354.3208f))
    addMine(Faction.PROCYON, Vector(-292.5978f, 1294.0575f))
    addMine(Faction.PROCYON, Vector(-280.86832f, 1212.958f))
    addMine(Faction.PROCYON, Vector(-287.72012f, 1109.3075f))
    addMine(Faction.PROCYON, Vector(-309.81827f, 1240.8453f))
    addMine(Faction.PROCYON, Vector(-380.21072f, 1294.6212f))
    addMine(Faction.PROCYON, Vector(-843.4169f, 1039.1022f))
    addMine(Faction.PROCYON, Vector(-755.09186f, 921.3562f))

    val showerObjectIDs = mutableListOf<Int>()
    val showerGroupNames = (1..2).map { "Shower Group $it" }

    showerObjectIDs.add(world.addWorldObject("Terrain_Nebula", null, showerGroupNames[0], Vector(647.7959f, 1707.9968f)))
    showerObjectIDs.add(world.addWorldObject("Terrain_Nebula", null, showerGroupNames[1], Vector(1704.0906f, 564.6169f)))

    val showerPathNames = (1..2).map { "Shower Path $it" }

    world.addWaypointPath(World.createWaypointPath(
        showerPathNames[0],
        Vector(452.5258f, 1957.0424f, 56f),
        Vector(887.6821f, 1106.1946f, 10f),
        Vector(1149.4152f, 680.0929f, 10f),
        Vector(1316.4767f, 282.13177f, 10f),
        Vector(1401.2804f, -289.06128f, 10f),
        Vector(1359.4467f, -758.7969f, 10f),
        Vector(1195.3807f, -1123.909f, 10f),
        Vector(910.2529f, -1445.5765f, 10f),
        Vector(502.95874f, -1741.7031f, 10f),
        Vector(171.99916f, -2034.5923f, 111f)
    ))

    world.addWaypointPath(World.createWaypointPath(
        showerPathNames[1],
        Vector(1230.112f, 2080.9968f),
        Vector(1401.8367f, 1434.771f),
        Vector(1518.0369f, 837.1177f),
        Vector(1601.5962f, 338.75473f),
        Vector(1645.5916f, 30.035957f, 0.000488f),
        Vector(1638.7766f, -406.64932f),
        Vector(1550.4106f, -927.317f),
        Vector(1377.5493f, -1493.6298f),
        Vector(1125.3262f, -2091.8994f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Nebula Polygon",
        Coord(-1408.7205f, -573.4634f),
        Coord(-1598.5299f, -780.49023f),
        Coord(-1683.587f, -1094.2014f),
        Coord(-1621.4523f, -1342.815f),
        Coord(-1419.7076f, -1541.0769f),
        Coord(-1117.3258f, -1614.1127f),
        Coord(-839.4972f, -1546.2665f),
        Coord(-642.08844f, -1343.9851f),
        Coord(-559.01996f, -1056.9912f),
        Coord(-627.3743f, -809.0779f),
        Coord(-831.2482f, -587.42426f),
        Coord(-1115.7654f, -499.8809f)
    ))

    val showerPolygonNames = (1..2).map { "Shower Polygon $it" }

    world.addWorldPolygon(World.createWorldPolygon(
        showerPolygonNames[0],
        Coord(500.51746f, 1761.0692f),
        Coord(523.2655f, 1634.9855f),
        Coord(654.6542f, 1545.5994f),
        Coord(800.4823f, 1618.837f),
        Coord(809.41174f, 1767.3157f),
        Coord(696.50226f, 1847.1144f),
        Coord(562.8585f, 1856.667f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        showerPolygonNames[1],
        Coord(1528.683f, 473.4338f),
        Coord(1608.3169f, 393.05347f),
        Coord(1709.1399f, 372.33282f),
        Coord(1816.6765f, 400.69962f),
        Coord(1892.2883f, 476.72726f),
        Coord(1914.5774f, 575.5007f),
        Coord(1885.2017f, 680.73596f),
        Coord(1807.8915f, 751.0505f),
        Coord(1711.0084f, 775.1596f),
        Coord(1611.4899f, 755.57983f),
        Coord(1534.2493f, 679.48065f),
        Coord(1501.9365f, 581.02234f)
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        "Nebula Point Set",
        World.createWorldPoint(557.4004f, Vector(-1119.9517f, -1059.0275f), 0f, Vector.north)
    ))

    val showerPointSetNames = (1..2).map { "Shower Point Set $it" }

    world.addWorldPointSet(World.createWorldPointSet(
        showerPointSetNames[0],
        World.createWorldPoint(155.8f, Vector(649.0459f, 1707.9666f), 0f, Vector(-0.450909f, -0.890678f, -0.05814f), Vector(-0.056754f, -0.036395f, 0.997729f))
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        showerPointSetNames[1],
        World.createWorldPoint(200.1999f, Vector(1712.557f, 570.7459f), 0f, Vector(-0.452358f, -0.890596f, -0.047137f), Vector(-0.060142f, -0.022271f, 0.997947f))
    ))

    navyAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }
    procyonAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }

    val showerNames = (1..2).map { "Shower $it" }

    fun createSetupShowerAction(showerIndex: Int, rechargeTime: Float) = World.createSetupMeteorShowerAction(
        showerObjectIDs[showerIndex],
        showerNames[showerIndex],
        showerPolygonNames[showerIndex],
        showerPointSetNames[showerIndex],
        rechargeTime,
        0f
    )

    fun createShowerGroupFollowPathAction(showerIndex: Int) = World.createGroupFollowPathAction(
        showerGroupNames[showerIndex],
        showerPathNames[showerIndex],
        FollowMode.TELEPORT_LOOP,
        true
    )

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetupNebulaAction(
            nebulaObjectID,
            "Boranaz Nebula",
            "Nebula Polygon",
            "Nebula Point Set",
            energyDrain = true,
            occlusion = true,
            0f
        ),
        createSetupShowerAction(0, 0.4f),
        createSetupShowerAction(1, 1f),
        World.createSetupIslandAction(navyBaseObjectID, 100, "Navy Base", Skill.ELITE, Stance.AGGRESSIVE),
        World.createSetupIslandAction(procyonBaseObjectID, 150, "Procyon Base", Skill.AVERAGE, Stance.AGGRESSIVE),
        createShowerGroupFollowPathAction(0),
        createShowerGroupFollowPathAction(1),
        World.createSetGroupSpeedAction(showerGroupNames[0], 15),
        World.createSetGroupSpeedAction(showerGroupNames[1], 12),
        World.createSetGroupOwnerAction("Navy Mine Group", "Navy Base"),
        World.createSetGroupOwnerAction("Procyon Mine Group", "Procyon Base"),
        World.createSetupTeamObjectiveAction(navyTeamID, "Navy Objective Point", "Navy Objective"),
        World.createSetupTeamObjectiveAction(procyonTeamID, "Procyon Objective Point", "Procyon Objective"),
        World.createPlayMusicAction("BTL_NavyBig_Full02", 0.7f, 2f, 2f)
    ))

    fun addWorldRule(ruleName: String, condition: ConditionNode, vararg actions: ActionNode) {
        world.addWorldRule(World.createWorldRule(ruleName, runOnce = true, isActive = true, ConditionListNode(condition), ActionListNode(*actions)))
    }

    addWorldRule(
        "Navy Win 1",
        World.createGroupVitalSectionDamageCondition("Procyon Base Group", VitalSection.MISSION, Equivalence.EQUAL_TO, 1f),
        World.createTeamWinsAction(navyTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_NAVYDEFEAT", true)
    )

    addWorldRule(
        "Procyon Win 1",
        World.createGroupVitalSectionDamageCondition("Navy Base Group", VitalSection.MISSION, Equivalence.EQUAL_TO, 1f),
        World.createTeamWinsAction(procyonTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PROCYONDEFEAT", true)
    )

    addWorldRule(
        "Navy Win 2",
        World.createTeamCaptureGroupCondition(navyTeamID, "Procyon Base Group"),
        World.createTeamWinsAction(navyTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_NAVYDEFEAT", true)
    )

    addWorldRule(
        "Procyon Win 2",
        World.createTeamCaptureGroupCondition(procyonTeamID, "Navy Base Group"),
        World.createTeamWinsAction(procyonTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PROCYONDEFEAT", true)
    )

    addWorldRule(
        "Computer Win",
        World.createNoHumanControlledFleetCondition(),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_LOST", "IDGS_TPINGAMEMESSAGE_GAME_LOST", true)
    )

    addWorldRule(
        "Draw",
        World.createNoTeamHasShipsCondition(),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_LOST", "IDGS_TPINGAMEMESSAGE_GAME_LOST", true)
    )

    world.addObjectivePoint(World.createObjectivePoint("Navy Objective Point", Vector(-540.16626f, 1086.5442f)))
    world.addObjectivePoint(World.createObjectivePoint("Procyon Objective Point", Vector(740.709f, -859.0983f)))

    world.addObjectiveTask(World.createObjectiveTask("Navy Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYBASE"))
    world.addObjectiveTask(World.createObjectiveTask("Procyon Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYBASE"))

    world.addMapText(World.createMapText("Navy Base", "IDGS_TPMAPTEXTITEMS_GENERAL_OUTPOST_PERSEUS", Vector(752.3391f, -844.2909f)))
    world.addMapText(World.createMapText("Procyon Base", "IDGS_TPMAPTEXTITEMS_GENERAL_STARKEEP_CEREBRUS", Vector(-547.654f, 1085.5195f)))
    world.addMapText(World.createMapText("Nebula", "IDGS_TPMAPTEXTITEMS_GENERAL_MOURNING_NEBULA", Vector(-1084.6155f, -1091.3252f)))
    world.addMapText(World.createMapText("Civilian Island", "IDGS_TPMAPTEXTITEMS_GENERAL_ISLANDS_ORPHEUS_COVE", Vector(-0.442902f, -5.559692f)))

    println(world.build())
}

fun convoy() {
    val world = World.create(
        game,
        "Convoy Raid",
        "IDGS_TPWORLDNAMES_SCEN_CONVOY_RAID",
        "IDGS_TPWORLDDESCRIPTION_MP_CONVOY_RAID",
        Vector(2000f, 2000f, 1000f),
        8,
        Color(0.219608f, 0.219608f, 0.219608f),
        Vector(-0.725368f, 0.3079f, -0.615662f),
        Color(0.745098f, 0.396078f, 0.588235f),
        Color(0.960784f, 0.956863f, 0.878431f),
        "Map_ConvoyRaid",
        allianceChangeAllowed = false,
        randomSeed = 1473591045,
        bufferSize = 250f
    )

    val navyTeamID = "IDGS_TPTEAMNAMES_NAVY"
    val pirateTeamID = "IDGS_TPTEAMNAMES_PIRATES"

    val navyTeamIndex = world.addTeam(World.createTeam(navyTeamID, Faction.NAVY))
    val pirateTeamIndex = world.addTeam(World.createTeam(pirateTeamID, Faction.PIRATE))

    val navyAllianceIndices = mutableListOf<Int>()
    val pirateAllianceIndices = mutableListOf<Int>()

    fun addPlayer(faction: Faction, id: Int, color: Color, start: Vector, direction: Vector) {
        val factionPair = if (faction == Faction.NAVY) navyTeamIndex to navyAllianceIndices else pirateTeamIndex to pirateAllianceIndices
        factionPair.second.add(world.addPlayer("${faction.name.titlecase()} $id", factionPair.first, color, start, direction, faction, Formation.LINE))
    }

    addPlayer(Faction.NAVY, 1, Color(0f, 0f, 1f), Vector(1070.8525f, -971.36255f), Vector.dir(-0.007847f, 0.999969f))
    addPlayer(Faction.PIRATE, 1, Color(1f, 0f, 0f), Vector(1628.3295f, 830.49884f), Vector.dir(-0.086672f, -0.996237f))
    addPlayer(Faction.NAVY, 2, Color(0f, 1f, 1f), Vector(1138.813f, -1257.1587f), Vector.dir(0.099995f, 0.994988f))
    addPlayer(Faction.PIRATE, 2, Color(0.501961f, 0f, 0f), Vector(329.9577f, 759.6592f), Vector.dir(0.4438f, -0.896126f))
    addPlayer(Faction.NAVY, 3, Color(0f, 0f, 0.501961f), Vector(1366.5822f, -1044.3699f), Vector.dir(-0.369247f, 0.929331f))
    addPlayer(Faction.PIRATE, 3, Color(1f, 0f, 1f), Vector(-68.60469f, 869.2412f), Vector.dir(0.538336f, -0.84273f))
    addPlayer(Faction.NAVY, 4, Color(0f, 0.501961f, 0.501961f), Vector(931.2105f, -1127.0912f), Vector.dir(0.12174f, 0.992562f))
    addPlayer(Faction.PIRATE, 4, Color(0.87451f, 0.372549f, 0.12549f), Vector(-460.15494f, 644.50604f), Vector.dir(0.541563f, -0.84066f))

    val convoyPlayerIndex = world.addPlayerListElement(World.createAIFleetElement("Convoy", -1, Color.white, Vector(1351.6436f, -1222.8716f), Vector.east, Formation.NONE))

    navyAllianceIndices.add(convoyPlayerIndex)

    navyAllianceIndices.add(world.addPlayerListElement(World.createFakeFleetElement("Navy Base", Color.blue, Vector(1235.3624f, -1709.1705f))))

    val pirateBasePlayerIndex = world.addPlayerListElement(World.createFakeFleetElement("Pirate Base", Color.red, Vector(-1486.9426f, 1285.6729f)))

    pirateAllianceIndices.add(pirateBasePlayerIndex)

    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.black, Vector(-387.71265f, -736.7732f)))

    val etheriumCurrentObjectIDs = mutableListOf<Int>()

    etheriumCurrentObjectIDs.add(world.addWorldObject("Terrain_Etherium_Current", null, null, Vector.zero))
    etheriumCurrentObjectIDs.add(world.addWorldObject("Terrain_Etherium_Current", null, null, Vector.zero))

    val nebulaObjectID = world.addWorldObject("Terrain_Nebula", null, null, Vector(-1319.1901f, 65.70297f))
    val showerObjectID = world.addWorldObject("Terrain_Nebula", null, null, Vector(48.86757f, -887.18225f))

    val navyBaseObjectID = world.addWorldObject("Base_Grant", "Navy Base", "Navy Base Group", Vector(1275.2861f, -1484.9083f))

    val pirateBaseObjectID = world.addWorldObject("Base_Pirate", "Pirate Base", "Pirate Base Group", Vector(-1051.8273f, 927.063f), Matrix.rotationZ(-0.707107f, -0.707107f))
    val pirateStockadeObjectID = world.addWorldObject("Base_PirateStockade", "Pirate Base", "Pirate Base Group", Vector(-779.19916f, 1053.5538f))

    val convoyGroupNames = (1..3).map { "Convoy Group $it" }

    fun addConvoyShip(type: String, convoyIndex: Int, position: Vector, xx: Float = 1f, yx: Float = 0f) = world.addWorldObject(
        "Ship_Civilian_$type", "Convoy", convoyGroupNames[convoyIndex], position, Matrix.rotationZ(xx, yx)
    )

    val convoyLabradorObjectID = addConvoyShip("Barque", 0, Vector(1240.7277f, -600.7911f))
    val convoyCapeBretonObjectID = addConvoyShip("Barque", 0, Vector(1190.1398f, -782.6989f))
    val convoyPrinceEdwardObjectID = addConvoyShip("Barque", 0, Vector(1163.8994f, -996.15344f))
    val convoyNovaScotiaObjectID = addConvoyShip("Barque", 0, Vector(1163.9807f, -1179.9341f))

    val convoyMarseillesObjectID = addConvoyShip("Schooner", 1, Vector(1636.2905f, -238.87029f), 0.996195f, 0.087155f)
    val convoyVeneziaObjectID = addConvoyShip("Packet", 1, Vector(1594.0209f, -294.08804f), 0.996195f, 0.087155f)
    val convoyRomaObjectID = addConvoyShip("Packet", 1, Vector(1682.8848f, -296.36462f), 0.996195f, 0.087155f)
    val convoyParisObjectID = addConvoyShip("Schooner", 1, Vector(1640.4474f, -358.22717f), 0.996195f, 0.087155f)
    val convoyTuscanyObjectID = addConvoyShip("Packet", 1, Vector(1638.0419f, -296.26855f), 0.996195f, 0.087155f)

    val convoyVesuviusObjectID = addConvoyShip("Barque", 2, Vector(1082.3011f, -939.26105f), 0.798636f, 0.601815f)
    val convoyEtnaObjectID = addConvoyShip("Barque", 2, Vector(1166.1431f, -1008.114f), 0.798636f, 0.601815f)
    val convoyStHelensObjectID = addConvoyShip("Barque", 2, Vector(1182.6407f, -1134.8147f), 0.798636f, 0.601815f)
    val convoyPeleeObjectID = addConvoyShip("Barque", 2, Vector(1300.0966f, -1168.4252f), 0.798636f, 0.601815f)
    val convoyOlympusMonsObjectID = addConvoyShip("Barque", 2, Vector(1347.3795f, -1295.4619f), 0.798636f, 0.601815f)

    fun addIsland(typeID: String, position: Vector, xx: Float = 1f, yx: Float = 0f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(xx, yx))
    }

    addIsland("Island_Med01", Vector(-515.9323f, 176.48253f))
    addIsland("Island_Rocky_05", Vector(-835.62805f, 1436.5825f))
    addIsland("Island_Rocky_07", Vector(167.5988f, 791.37036f))
    addIsland("Island09", Vector(-1039.0927f, 600.308f))
    addIsland("Island09", Vector(-1493.1831f, 668.13025f))
    addIsland("Island06", Vector(-1068.8003f, 509.72607f))
    addIsland("Island01", Vector(-240.34485f, 1673.3171f))
    addIsland("Island03", Vector(-279.60876f, 967.2634f))
    addIsland("Island08", Vector(-894.8638f, 22.90242f))
    addIsland("Island13", Vector(753.84436f, -1595.9357f), -0.309017f, 0.951056f)
    addIsland("Island14", Vector(12.352453f, 902.99316f))
    addIsland("Island_Large01", Vector(479.54922f, -158.94626f), -0.987688f, 0.156434f)
    addIsland("Island_Mega_01", Vector(998.3333f, 763.0642f), 0.848048f, -0.52992f)
    addIsland("Island_Rocky_01", Vector(574.0402f, -1415.6887f))
    addIsland("Island_Rocky_02", Vector(768.2968f, -956.73206f))
    addIsland("Island_Rocky_03", Vector(731.8384f, -1235.2286f), 0.838671f, -0.544639f)
    addIsland("Island_Med09", Vector(-499.89706f, 1174.5803f), 0.515038f, 0.857167f)
    addIsland("Island_Med08", Vector(-542.11035f, 579.2472f))
    addIsland("Island_Med07", Vector(-354.0318f, 755.39966f), -0.224951f, -0.97437f)
    addIsland("Island_Med04", Vector(0.650169f, 155.88147f), -0.034899f, 0.999392f)
    addIsland("Island_Med05", Vector(152.87741f, -1632.6182f))
    addIsland("Island_Med08", Vector(1036.616f, -235.68431f))
    addIsland("Island_Med06", Vector(903.1714f, -430.37982f), 0.945519f, -0.325568f)
    addIsland("Island_Med05", Vector(925.99646f, -51.018005f))
    addIsland("Island_Med04", Vector(1569.5809f, 1625.6509f))

    fun addAsteroid(size: String, position: Vector) {
        world.addWorldObject("Asteroid_$size", "Asteroids", "Asteroid Group", position)
    }

    addAsteroid("Huge", Vector(616.6716f, -754.7658f))
    addAsteroid("Large", Vector(450.7571f, -1310.0688f))
    addAsteroid("Med", Vector(465.85114f, -1235.9243f))
    addAsteroid("Huge", Vector(524.7055f, -1196.5165f))
    addAsteroid("Large", Vector(565.11646f, -1086.0525f))
    addAsteroid("Huge", Vector(566.1437f, -976.43823f))
    addAsteroid("Large", Vector(634.8987f, -917.7726f))
    addAsteroid("Med", Vector(603.7166f, -778.7594f))
    addAsteroid("Med", Vector(602.3712f, -655.8087f))
    addAsteroid("Large", Vector(572.89764f, -579.2717f))
    addAsteroid("Huge", Vector(525.4095f, -476.6897f))
    addAsteroid("Large", Vector(393.10614f, -383.3401f))
    addAsteroid("Med", Vector(329.6398f, -284.96082f))
    addAsteroid("Med", Vector(191.47836f, -263.56177f))
    addAsteroid("Med", Vector(46.70999f, -236.62866f))
    addAsteroid("Huge", Vector(-141.48932f, -269.90283f))
    addAsteroid("Huge", Vector(-256.4646f, -330.11902f))
    addAsteroid("Large", Vector(-378.5241f, -374.39124f))
    addAsteroid("Med", Vector(-378.57147f, -472.15088f))
    addAsteroid("Med", Vector(-434.7268f, -459.45837f))
    addAsteroid("Large", Vector(362.9984f, -1335.2526f))
    addAsteroid("Med", Vector(302.75873f, -1388.1636f))
    addAsteroid("Large", Vector(256.6183f, -1367.8284f))
    addAsteroid("Huge", Vector(205.66005f, -1407.8333f))
    addAsteroid("Large", Vector(85.45654f, -1394.249f))
    addAsteroid("Med", Vector(15.503029f, -1416.6835f))
    addAsteroid("Large", Vector(-84.17912f, -1362.9421f))
    addAsteroid("Huge", Vector(-160.26334f, -1367.4161f))
    addAsteroid("Large", Vector(-240.8226f, -1294.7603f))
    addAsteroid("Med", Vector(-330.70032f, -1268.8616f))
    addAsteroid("Large", Vector(-366.61316f, -1195.3237f))
    addAsteroid("Huge", Vector(-449.19336f, -1130.925f))
    addAsteroid("Med", Vector(-439.40247f, -1040.6904f))
    addAsteroid("Large", Vector(-515.4529f, -942.85547f))
    addAsteroid("Huge", Vector(-490.5528f, -872.6468f))
    addAsteroid("Large", Vector(-544.1848f, -738.1589f))
    addAsteroid("Huge", Vector(-481.0541f, -657.0004f))
    addAsteroid("Med", Vector(-519.8541f, -558.8989f))
    addAsteroid("Large", Vector(-417.5039f, -557.2196f))
    addAsteroid("Huge", Vector(-4.424423f, -321.2732f))
    addAsteroid("Huge", Vector(116.31986f, -1329.8024f))
    addAsteroid("Large", Vector(320.3739f, -1282.6659f))
    addAsteroid("Large", Vector(446.51532f, -1126.7854f))
    addAsteroid("Large", Vector(550.56116f, -866.6896f))
    addAsteroid("Large", Vector(481.52982f, -574.16296f))
    addAsteroid("Large", Vector(364.0297f, -478.36548f))
    addAsteroid("Large", Vector(241.18515f, -366.2527f))
    addAsteroid("Large", Vector(139.57993f, -341.58557f))
    addAsteroid("Large", Vector(-117.56787f, -379.9121f))
    addAsteroid("Huge", Vector(-243.46484f, -431.97778f))
    addAsteroid("Huge", Vector(-340.3644f, -563.42334f))
    addAsteroid("Med", Vector(-452.07513f, -737.6969f))
    addAsteroid("Med", Vector(-399.4691f, -1010.36816f))
    addAsteroid("Med", Vector(-338.20895f, -1127.2671f))
    addAsteroid("Med", Vector(-235.20752f, -1222.8978f))
    addAsteroid("Med", Vector(-135.10963f, -1268.8468f))
    addAsteroid("Med", Vector(421.5588f, -1408.4077f))
    addAsteroid("Med", Vector(649.71484f, -1117.699f))
    addAsteroid("Med", Vector(702.6757f, -791.4512f))

    fun addArmedAsteroid(position: Vector, xx: Float = 1f, yx: Float = 0f) {
        world.addWorldObject("Asteroid_Large_Armed", "Pirate Base", "Pirate Base Group", position, Matrix.rotationZ(xx, yx))
    }

    addArmedAsteroid(Vector(-192.673f, 760.0504f))
    addArmedAsteroid(Vector(-22.918304f, 838.0873f))
    addArmedAsteroid(Vector(-247.52307f, 669.4498f))
    addArmedAsteroid(Vector(-368.87936f, 454.26337f))
    addArmedAsteroid(Vector(-414.3614f, 350.15067f))
    addArmedAsteroid(Vector(-577.5598f, 65.47653f))
    addArmedAsteroid(Vector(-674.49915f, 894.9005f))
    addArmedAsteroid(Vector(-481.8105f, 235.59616f))
    addArmedAsteroid(Vector(-316.22296f, 551.8981f))
    addArmedAsteroid(Vector(-646.8971f, -38.136414f))
    addArmedAsteroid(Vector(-171.94223f, -55.565735f))
    addArmedAsteroid(Vector(-264.49896f, -60.867096f), 0.978148f, 0.207912f)
    addArmedAsteroid(Vector(-330.5645f, -7.393005f), 0.087156f, -0.996195f)
    addArmedAsteroid(Vector(-269.3781f, 79.55112f), -0.933581f, -0.358368f)
    addArmedAsteroid(Vector(-210.04187f, 110.44711f), -0.990268f, 0.139173f)
    addArmedAsteroid(Vector(-131.88504f, 193.7586f), -0.809017f, 0.587785f)

    val etheriumCurrentPathNames = (1..2).map { "Etherium Current Path $it" }

    world.addWaypointPath(World.createWaypointPath(
        etheriumCurrentPathNames[0],
        Vector(1905.5027f, -318.56094f),
        Vector(1558.1089f, -575.24677f),
        Vector(1461.1335f, -693.1725f),
        Vector(1447.5256f, -796.1757f),
        Vector(1445.9396f, -926.65906f),
        Vector(1472.5399f, -1012.27844f),
        Vector(1526.9854f, -1138.6248f),
        Vector(1585.0021f, -1232.2225f),
        Vector(1925.5165f, -1539.371f)
    ))

    world.addWaypointPath(World.createWaypointPath(
        etheriumCurrentPathNames[1],
        Vector(-127.036705f, -1809.5618f),
        Vector(-670.9184f, -1436.8914f),
        Vector(-926.28564f, -1325.8363f),
        Vector(-1106.7025f, -1243.3434f),
        Vector(-1313.6432f, -1117.5674f),
        Vector(-1437.7421f, -1010.6151f),
        Vector(-1504.9562f, -818.9338f),
        Vector(-1704.484f, -571.8143f),
        Vector(-1978.1212f, -190.88043f)
    ))

    world.addWaypointPath(World.createWaypointPath(
        "Asteroid Path",
        Vector(-79.228516f, -266.9878f),
        Vector(-303.91174f, -335.03214f),
        Vector(-474.95422f, -538.7092f),
        Vector(-529.8523f, -774.0176f),
        Vector(-492.2041f, -987.2825f),
        Vector(-371.26086f, -1225.0225f),
        Vector(-185.39825f, -1339.2827f),
        Vector(-14.640015f, -1413.9434f),
        Vector(180.36534f, -1415.986f),
        Vector(372.87604f, -1345.6665f),
        Vector(510.49573f, -1211.8635f),
        Vector(572.6483f, -1062.9421f),
        Vector(632.27936f, -900.407f),
        Vector(592.1744f, -638.072f),
        Vector(492.3526f, -445.12964f),
        Vector(280.13004f, -286.6006f),
        Vector(55.835144f, -235.64844f)
    ))

    val convoyPathNames = (1..3).map { "Convoy Path $it" }

    world.addWaypointPath(World.createWaypointPath(
        convoyPathNames[0],
        Vector(1218.7784f, -554.2097f),
        Vector(1277.847f, -121.53637f),
        Vector(1267.6536f, 50.017334f),
        Vector(1220.8174f, 191.95508f),
        Vector(1141.7454f, 281.0647f),
        Vector(1046.9917f, 311.53247f),
        Vector(959.9724f, 271.6753f),
        Vector(730.71924f, 129.23401f),
        Vector(633.1344f, 124.922f),
        Vector(526.885f, 125.09119f),
        Vector(413.2921f, 171.29456f),
        Vector(186.68298f, 351.80725f),
        Vector(81.576294f, 415.45715f),
        Vector(-69.47473f, 424.29504f),
        Vector(-210.1112f, 270.61926f),
        Vector(-323.53473f, 131.54272f),
        Vector(-430.41742f, -28.75122f),
        Vector(-619.1405f, -328.9126f),
        Vector(-774.2743f, -533.9004f),
        Vector(-900.0504f, -697.4612f),
        Vector(-1085.7715f, -810.272f),
        Vector(-1202.8691f, -839.2158f),
        Vector(-1354.8641f, -838.89233f),
        Vector(-1997.1635f, -238.75015f)
    ))

    world.addWaypointPath(World.createWaypointPath(
        convoyPathNames[1],
        Vector(1634.4154f, -181.12067f),
        Vector(1638.5873f, 925.6022f),
        Vector(1524.3741f, 1149.0844f),
        Vector(1297.04f, 1339.2075f),
        Vector(947.3711f, 1388.1616f),
        Vector(511.91968f, 1356.0432f),
        Vector(-132.48682f, 635.8618f),
        Vector(-215.48682f, 383.86182f),
        Vector(-1143.1375f, -693.34204f),
        Vector(-1410.2834f, -664.64734f),
        Vector(-1715.2454f, -576.7003f),
        Vector(-1998.364f, -196.38245f)
    ))

    world.addWaypointPath(World.createWaypointPath(
        convoyPathNames[2],
        Vector(1073.8816f, -938.06616f),
        Vector(860.44836f, -744.50684f),
        Vector(728.7037f, -568.7245f),
        Vector(592.89246f, -358.28906f),
        Vector(410.61005f, -213.69592f),
        Vector(-87.537994f, -125.72705f),
        Vector(-304.13965f, -186.9231f),
        Vector(-646.15533f, -509.2539f),
        Vector(-794.53345f, -686.7051f),
        Vector(-888.5448f, -790.0791f),
        Vector(-1042.638f, -861.5073f),
        Vector(-1218.8536f, -840.0051f),
        Vector(-1377.8235f, -787.01074f),
        Vector(-1647.8835f, -638.85645f),
        Vector(-1995.0377f, -169.7375f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Nebula Polygon",
        Coord(-1122.1559f, 622.5877f),
        Coord(-1410.2573f, 703.2494f),
        Coord(-1667.7268f, 633.87445f),
        Coord(-1868.7247f, 425.35684f),
        Coord(-1952.7521f, 139.6059f),
        Coord(-1849.0609f, -231.2956f),
        Coord(-1591.0739f, -490.80264f),
        Coord(-1280.648f, -571.84344f),
        Coord(-1017.3764f, -498.05673f),
        Coord(-769.7389f, -244.38866f),
        Coord(-685.6281f, 84.95043f),
        Coord(-856.16833f, 369.09662f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Shower Polygon",
        Coord(-112.725494f, -538.15686f),
        Coord(-256.17688f, -654.01965f),
        Coord(-345.02866f, -807.2618f),
        Coord(-352.75772f, -1050.1868f),
        Coord(-210.31952f, -1219.0768f),
        Coord(-65.705505f, -1307.4874f),
        Coord(141.25688f, -1307.3861f),
        Coord(334.6712f, -1219.5437f),
        Coord(454.24774f, -1071.0177f),
        Coord(496.35193f, -770.2368f),
        Coord(263.5285f, -529.4249f),
        Coord(55.423492f, -466.87708f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Convoy Theft Polygon",
        Coord(-1021.14105f, 1118.1697f),
        Coord(-1124.3322f, 1077.3267f),
        Coord(-1210.3428f, 1008.53656f),
        Coord(-1272.8163f, 930.7525f),
        Coord(-1274.692f, 809.6049f),
        Coord(-1187.6125f, 741.39014f),
        Coord(-977.6232f, 660.95337f),
        Coord(-832.2729f, 718.6839f),
        Coord(-729.76874f, 805.27893f),
        Coord(-640.13007f, 872.5682f),
        Coord(-549.60175f, 939.7831f),
        Coord(-526.61835f, 1035.9674f),
        Coord(-659.10443f, 1179.8185f),
        Coord(-847.3455f, 1178.8031f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Convoy Safety Polygon",
        Coord(-1392.7418f, -806.6031f),
        Coord(-1607.2126f, -898.9988f),
        Coord(-1762.271f, -801.0354f),
        Coord(-1886.0508f, -631.3992f),
        Coord(-1971.8049f, -440.70023f),
        Coord(-1972.5032f, -288.88208f),
        Coord(-1849.0942f, -234.68164f)
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        "Nebula Point Set",
        World.createWorldPoint(636.9999f, Vector(-1328.9067f, 60.80042f), 0f, Vector.north)
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        "Shower Point Set",
        World.createWorldPoint(416.59988f, Vector(87.97014f, -905.9183f), 0f, Vector.north)
    ))

    world.addTimer(World.createTimer(
        "Convoy Timer",
        false,
        622.974
    ))

    navyAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }
    pirateAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }

    world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(convoyPlayerIndex, pirateBasePlayerIndex))

    fun createSetupEtheriumCurrentAction(etheriumCurrentIndex: Int) = World.createSetupEtheriumCurrentAction(etheriumCurrentObjectIDs[etheriumCurrentIndex], etheriumCurrentPathNames[etheriumCurrentIndex])

    fun createSetConvoyGroupHoldPositionAction(convoyIndex: Int, holdPosition: Boolean) = World.createSetGroupHoldPositionAction(convoyGroupNames[convoyIndex], holdPosition)

    fun createSetConvoyGroupVisibilityAction(convoyIndex: Int, isVisible: Boolean) = World.createSetGroupVisibilityAction(convoyGroupNames[convoyIndex], isVisible)

    fun createConvoyGroupFollowPathAction(convoyIndex: Int) = World.createGroupFollowPathAction(convoyGroupNames[convoyIndex], convoyPathNames[convoyIndex], FollowMode.TO_END, true)

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        createSetupEtheriumCurrentAction(0),
        createSetupEtheriumCurrentAction(1),
        World.createSetupNebulaAction(
            nebulaObjectID,
            "Mourning Nebula",
            "Nebula Polygon",
            "Nebula Point Set",
            energyDrain = true,
            occlusion = true,
            0f
        ),
        World.createSetupMeteorShowerAction(
            showerObjectID,
            "Shower",
            "Shower Polygon",
            "Shower Point Set",
            1f,
            50f
        ),
        World.createStartTimerAction("Convoy Timer"),
        createSetConvoyGroupHoldPositionAction(1, true),
        createSetConvoyGroupHoldPositionAction(2, true),
        createSetConvoyGroupVisibilityAction(1, false),
        createSetConvoyGroupVisibilityAction(2, false),
        createConvoyGroupFollowPathAction(0),
        World.createPlayMusicAction("BTL_DeadlyPirate_Full01", 0.8f, 2f, 2f)
    ))

    fun addWorldRule(ruleName: String, runOnce: Boolean, condition: ConditionNode, vararg actions: ActionNode) {
        world.addWorldRule(World.createWorldRule(ruleName, runOnce, true, ConditionListNode(condition), ActionListNode(*actions)))
    }

    fun addNavyFailWorldRule(convoyIndex: Int, points: Int) = addWorldRule(
        "Navy Fail ${convoyIndex + 1}",
        true,
        World.createTeamDestroyGroupCondition(navyTeamID, convoyGroupNames[convoyIndex]),
        World.createGrantTeamPointsAction(pirateTeamID, points)
    )

    addNavyFailWorldRule(0, 70)
    addNavyFailWorldRule(1, 50)
    addNavyFailWorldRule(2, 70)

    world.addObjectiveTask(World.createObjectiveTask("Navy Objective 1", "IDGS_TPOBJECTIVES2_MP_CONVOY_RAID_ESCORT_CONVOY"))
    world.addObjectiveTask(World.createObjectiveTask("Navy Objective 2", "IDGS_TPOBJECTIVES2_MP_CONVOY_RAID_ESCORT_CONVOY"))
    world.addObjectiveTask(World.createObjectiveTask("Pirate Objective", "IDGS_TPOBJECTIVES2_MP_CONVOY_RAID_CAPTURE_CONVOY"))

    world.addMapText(World.createMapText("Etherium Current 1", "IDGS_TPMAPTEXTITEMS_M04_MCCULLOUGH_CURRENT", Vector(1460.1858f, -791.14136f)))
    world.addMapText(World.createMapText("Etherium Current 2", "IDGS_TPMAPTEXTITEMS_MP_CONVOY_RAID_CURRENT_LABEL", Vector(-865.2241f, -1386.5819f)))
    world.addMapText(World.createMapText("Asteroids", "IDGS_TPMAPTEXTITEMS_M04_CAUTION_ASTEROIDS", Vector(-524.314f, -774.0017f)))
    world.addMapText(World.createMapText("Nebula", "IDGS_TPMAPTEXTITEMS_GENERAL_MOURNING_NEBULA", Vector(-1313.0574f, -2.641357f)))
    world.addMapText(World.createMapText("Town", "IDGS_TPMAPTEXTITEMS_GENERAL_ISLANDS_BILGE_BAY", Vector(434.44177f, -220.37134f)))
    world.addMapText(World.createMapText("Navy Base", "IDGS_TPMAPTEXTITEMS_GENERAL_OUTPOST_PERSEUS", Vector(1258.1836f, -1487.6672f)))
    world.addMapText(World.createMapText("Pirate Base", "IDGS_TPMAPTEXTITEMS_GENERAL_CAMP_STRONGBOX", Vector(-1059.449f, 1082.1455f)))

    println(world.build())
}

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
    addPlayer(6, Color(1f, 1f, 0f), Vector(207.9324f, 378.60486f), Vector.dir(-0.952522f, 0.30447f))
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

    val etheriumCurrentObjectID = world.addWorldObject("Terrain_Etherium_Current", null, null, Vector(-95f, 2.999973f))

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
        "Etherium Current Path",
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
        World.createSetupEtheriumCurrentAction(etheriumCurrentObjectID, "Etherium Current Path"),
        World.createSetupAsteroidBeltAction("Asteroid Group", null, FollowMode.TO_END, false, 0f, 0f, 0.02f, 3.02f),
        World.createSetObjectiveTaskStateAction("Kill All Objective", true),
        World.createPlayMusicAction("NEUTRL_AbyssGoo", 1f, 2f, 2f)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Etherium Current", "IDGS_TPMAPTEXTITEMS_MP_DIABLO_STRAIT_CURRENT", Vector(-186.8336f, 9.868164f)))

    println(world.build())
}

fun dragon() {
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

    fun addIsland(typeID: String, position: Vector, xx: Float = 1f, yx: Float = 0f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(xx, yx))
    }

    addIsland("Island11", Vector(593.9529f, 205.11626f), 0.32556f, 0.945521f)
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
        World.createGroupFollowPathAction("Dragon Group", "Dragon Path", FollowMode.LOOP, true),
        World.createSetDragonStanceAction("Dragon Group", Stance.AGGRESSIVE),
        World.createSetDragonDamageThresholdAction("Dragon Group", 0.8f),
        World.createSetGroupReturnZoneAction("Dragon Group", "Dragon Flee Point Set"),
        World.createSetObjectiveTaskStateAction("Kill All Objective", true),
        World.createPlayMusicAction("BTL_Dragon02_FullFast", 1f, 2f, 2f, false)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Dragons", "IDGS_TPMAPTEXTITEMS_GENERAL_ISLANDS_FORSAKEN_RANGE", Vector(-3.243332f, -17.338562f)))

    println(world.build())
}

fun mousetrap() {
    val world = World.create(
        game,
        "Mousetrap",
        "IDGS_TPWORLDNAMES_MP_MOUSETRAP",
        "IDGS_TPWORLDDESCRIPTION_MP_MOUSETRAP",
        Vector(1750f, 1750f, 1000f),
        1,
        Color(0.141176f, 0.141176f, 0.141176f),
        Vector(-0.77906f, 0.253133f, -0.573576f),
        Color(0.219608f, 0.282353f, 0.47451f),
        Color(0.866667f, 0.909804f, 0.972549f),
        "Map_Mousetrap",
        allianceChangeAllowed = false,
        randomSeed = 1649599747
    )

    val pirateTeamID = "IDGS_TPTEAMNAMES_PIRATES"
    val procyonTeamID = "IDGS_TPTEAMNAMES_PROCYON"

    val pirateTeamIndex = world.addTeam(World.createTeam(pirateTeamID, Faction.PIRATE))
    val procyonTeamIndex = world.addTeam(World.createTeam(procyonTeamID, Faction.PROCYON))

    val pirateAllianceIndices = mutableListOf<Int>()
    val procyonAllianceIndices = mutableListOf<Int>()

    fun addPlayer(faction: Faction, id: Int, color: Color, start: Vector, direction: Vector) {
        val factionPair = if (faction == Faction.PIRATE) pirateTeamIndex to pirateAllianceIndices else procyonTeamIndex to procyonAllianceIndices
        factionPair.second.add(world.addPlayer("${faction.name.titlecase()} $id", factionPair.first, color, start, direction, faction, Formation.LINE))
    }

    addPlayer(Faction.PIRATE, 1, Color(1f, 0f, 0f), Vector(101.075745f, 739.63544f), Vector.dir(-0.061652f, -0.998098f))
    addPlayer(Faction.PROCYON, 1, Color(0f, 0.501961f, 0f), Vector(-334.47076f, -325.06653f), Vector.dir(0.10545f, 0.994425f))
    addPlayer(Faction.PIRATE, 2, Color(1f, 0f, 1f), Vector(-119.999084f, 664.41284f), Vector.dir(0.063015f, -0.998013f))
    addPlayer(Faction.PROCYON, 2, Color(0f, 0.501961f, 0.501961f), Vector(-103.3332f, -353.976f), Vector.dir(0.005383f, 0.999986f))
    addPlayer(Faction.PIRATE, 3, Color(0.701961f, 0.023529f, 0.278431f), Vector(-21.555115f, 518.6657f), Vector.dir(0.02612f, -0.999659f))
    addPlayer(Faction.PROCYON, 3, Color(0.039216f, 0.721569f, 0.447059f), Vector(73.63226f, -361.6137f), Vector.dir(-0.165934f, 0.986137f))
    addPlayer(Faction.PIRATE, 4, Color(0.509804f, 0.039216f, 0.023529f), Vector(258.27625f, 557.71155f), Vector.dir(-0.03774f, -0.999288f))
    addPlayer(Faction.PROCYON, 4, Color(0.109804f, 0.4f, 0.101961f), Vector(198.13431f, -367.82397f), Vector.dir(-0.265867f, 0.96401f))

    procyonAllianceIndices.add(world.addPlayerListElement(World.createAIFleetElement("Procyon Backup", -1, Color(0.701961f, 1f, 0.701961f), Vector(76.66661f, -1034.9675f), Vector.dir(0.067777f, 0.9977f), Formation.NONE)))
    pirateAllianceIndices.add(world.addPlayerListElement(World.createFakeFleetElement("Pirate Base", Color.white, Vector(-7.950306f, 1013.2767f))))
    world.addPlayerListElement(World.createFakeFleetElement("Asteroids", Color.black, Vector(-741.1809f, 253.76825f)))

    fun addIsland(typeID: String, position: Vector, cos: Float = 1f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(acos(cos)))
    }

    addIsland("Island_Rocky_01", Vector(149.29332f, 578.94824f), 0.798635f)
    addIsland("Island_Rocky_02", Vector(203.62634f, 293.7744f))
    addIsland("Island_Rocky_03", Vector(-241.91376f, 131.38405f), 0.945519f)
    addIsland("Island_Rocky_04", Vector(493.10443f, -398.79443f))
    addIsland("Island_Rocky_05", Vector(322.135f, -105.73882f))
    addIsland("Island_Rocky_06", Vector(571.5546f, 403.82465f))
    addIsland("Island_Rocky_07", Vector(-175.90335f, 467.94138f))
    addIsland("Island_Rocky_07", Vector(938.54126f, -634.8638f))
    addIsland("Island_Rocky_08", Vector(715.7526f, -692.4789f))
    addIsland("Island_Volcano_01", Vector(796.26495f, -262.24585f))
    addIsland("Island_Volcano_03", Vector(977.13275f, -475.88684f))
    addIsland("Island_Volcano_04", Vector(744.2049f, 169.63837f))
    addIsland("Island_Volcano_05", Vector(1153.5331f, -231.18738f))
    addIsland("Island_Volcano_06", Vector(908.6904f, -2.35228f))
    addIsland("Island_Volcano_07", Vector(554.8513f, -110.29737f))
    addIsland("Island_Med01", Vector(-235.44313f, 793.4314f))
    addIsland("Island_Med02", Vector(-104.52045f, 1216.7156f), -0.984808f)
    addIsland("Island_Med05", Vector(342.4888f, 1163.342f), 0.913546f)
    addIsland("Island_Med04", Vector(628.0249f, 917.2531f), -0.190809f)
    addIsland("Island_Med07", Vector(-752.67474f, 609.3213f))
    addIsland("Island_Med08", Vector(-1056.9017f, 1065.3988f))
    addIsland("Island_Med09", Vector(393.89822f, 612.74084f))

    fun addAsteroid(size: String, position: Vector, rotation: Matrix = Matrix.identity) {
        world.addWorldObject("Asteroid_$size", "Asteroids", "Asteroid Group", position, rotation)
    }

    addAsteroid("Large", Vector(-489.19336f, 343.3853f, 0.000061f))
    addAsteroid("Med", Vector(-428.32233f, 1210.6251f, -0.999939f), Matrix(
        -0.000583f, 0.844984f, 0.534791f,
        0.121572f, -0.530765f, 0.838754f,
        0.992582f, 0.065504f, -0.102417f
    ))
    addAsteroid("Huge", Vector(-392.85846f, 1106.6501f, -10f), Matrix(
        -0.992766f, 0.018271f, -0.118668f,
        0.108556f, -0.285696f, -0.952152f,
        -0.0513f, -0.958146f, 0.281646f
    ))
    addAsteroid("Med", Vector(-363.06284f, 1184.59f, 6f), Matrix(
        0.457452f, 0.420884f, -0.783323f,
        -0.009737f, -0.878472f, -0.477694f,
        -0.889181f, 0.226149f, -0.397761f
    ))
    addAsteroid("Med", Vector(-360.2936f, 1044.421f, -15f), Matrix(
        -0.317889f, 0.211371f, 0.924267f,
        -0.222496f, 0.930981f, -0.289431f,
        -0.921652f, -0.297652f, -0.248919f
    ))
    addAsteroid("Huge", Vector(-397.33145f, 897.4307f, -16f), Matrix(
        0.0862f, -0.485892f, -0.869758f,
        0.910295f, 0.393201f, -0.129444f,
        0.404885f, -0.780578f, 0.476199f
    ))
    addAsteroid("Small", Vector(-390.33466f, 907.3111f, 1f), Matrix(
        -0.326641f, -0.045898f, 0.944034f,
        0.755311f, -0.613105f, 0.231533f,
        0.568165f, 0.788667f, 0.234933f
    ))
    addAsteroid("Huge", Vector(-399.63693f, 738.39777f, -13f), Matrix(
        -0.034864f, 0.353431f, -0.934811f,
        -0.547787f, 0.775593f, 0.313664f,
        0.835891f, 0.523013f, 0.166564f
    ))
    addAsteroid("Small", Vector(-400.45197f, 723.77277f, -19f), Matrix(
        -0.164474f, 0.365764f, 0.916059f,
        0.589904f, -0.707847f, 0.388543f,
        0.790545f, 0.604293f, -0.099343f
    ))
    addAsteroid("Med", Vector(-365.7281f, 709.70856f, 14f), Matrix(
        0.498707f, -0.375735f, -0.781098f,
        0.239487f, -0.806347f, 0.540787f,
        -0.833029f, -0.456757f, -0.312147f
    ))
    addAsteroid("Huge", Vector(-377.1962f, 615.8787f, -1f), Matrix(
        -0.161801f, 0.638783f, -0.752182f,
        -0.157451f, 0.73575f, 0.658697f,
        0.974181f, 0.22501f, -0.018468f
    ))
    addAsteroid("Small", Vector(-377.1962f, 607.8787f, -7f), Matrix(
        0.110516f, 0.993731f, -0.016883f,
        -0.44808f, 0.034655f, -0.893321f,
        -0.887136f, 0.106291f, 0.449101f
    ))
    addAsteroid("Med", Vector(-376.97473f, 602.6536f, 7f), Matrix(
        0.645189f, 0.675764f, 0.356476f,
        0.176971f, 0.321706f, -0.930154f,
        -0.743245f, 0.663211f, 0.087971f
    ))
    addAsteroid("Large", Vector(-398.20877f, 445.97375f, 16f), Matrix(
        -0.0051f, 0.007895f, -0.999956f,
        -0.24239f, 0.970138f, 0.008896f,
        0.970165f, 0.242425f, -0.003034f
    ))
    addAsteroid("Small", Vector(-480.6933f, 400.3424f, 14f), Matrix(
        0.011504f, -0.906063f, -0.422986f,
        -0.044152f, 0.422141f, -0.905454f,
        0.998959f, 0.029092f, -0.035148f
    ))
    addAsteroid("Med", Vector(-469.69342f, 395.3424f, -17f), Matrix(
        0.160054f, -0.985879f, 0.049245f,
        0.182575f, -0.019461f, -0.982999f,
        0.970077f, 0.166324f, 0.176882f
    ))
    addAsteroid("Huge", Vector(-498.45142f, 209.58235f, -6f), Matrix(
        0.119223f, -0.312589f, 0.942377f,
        -0.168978f, 0.928911f, 0.3295f,
        -0.978382f, -0.198525f, 0.057927f
    ))
    addAsteroid("Med", Vector(-497.00928f, 239.65535f, 5f), Matrix(
        0.236985f, 0.944921f, -0.225748f,
        0.221681f, 0.173642f, 0.959534f,
        0.945883f, -0.277439f, -0.16832f
    ))
    addAsteroid("Small", Vector(-493.337f, 225.32625f, 19f), Matrix(
        -0.389759f, 0.874692f, 0.2881f,
        0.082251f, 0.344654f, -0.93512f,
        -0.917236f, -0.340774f, -0.206277f
    ))
    addAsteroid("Huge", Vector(-580.26495f, 86.38741f, -4f), Matrix(
        0.177998f, 0.560913f, 0.808513f,
        -0.20474f, -0.782543f, 0.58797f,
        0.962496f, -0.270193f, -0.024449f
    ))
    addAsteroid("Small", Vector(-570.5405f, 73.4789f, 6f), Matrix(
        -0.288253f, 0.224781f, -0.930797f,
        0.209234f, -0.933781f, -0.290298f,
        -0.934415f, -0.278434f, 0.222133f
    ))
    addAsteroid("Huge", Vector(-577.97345f, -32.47938f, 12.000061f), Matrix(
        0.85472f, 0.477672f, 0.203182f,
        -0.291032f, 0.765085f, -0.574409f,
        -0.42983f, 0.431826f, 0.792951f
    ))
    addAsteroid("Small", Vector(-594.3218f, -37.83512f, 7f), Matrix(
        -0.070965f, 0.804887f, 0.589169f,
        0.727017f, -0.362669f, 0.583025f,
        0.682943f, 0.46971f, -0.55943f
    ))
    addAsteroid("Med", Vector(-599.6553f, -60.085083f, 6f), Matrix(
        -0.24098f, 0.144539f, 0.959707f,
        0.970462f, 0.047608f, 0.23651f,
        -0.011504f, 0.988353f, -0.151742f
    ))
    addAsteroid("Large", Vector(-728.5468f, -77.795074f, 1f), Matrix(
        0.220042f, 0.012111f, -0.975415f,
        0.405746f, -0.910458f, 0.080227f,
        -0.887103f, -0.413424f, -0.205253f
    ))
    addAsteroid("Small", Vector(-711.8203f, -216.15869f), Matrix(
        0.191909f, 0.736688f, 0.64843f,
        0.036021f, -0.665553f, 0.745481f,
        0.980751f, -0.119707f, -0.154262f
    ))
    addAsteroid("Large", Vector(-719.4763f, -209.6093f, 15f), Matrix(
        0.094295f, 0.480984f, -0.871644f,
        -0.027361f, 0.876467f, 0.480685f,
        0.995168f, -0.021477f, 0.095807f
    ))
    addAsteroid("Huge", Vector(-827.31305f, -248.46309f, -3f), Matrix(
        0.153201f, -0.495755f, -0.854843f,
        -0.978119f, -0.19929f, -0.059719f,
        -0.140756f, 0.845287f, -0.515439f
    ))
    addAsteroid("Med", Vector(-829.13477f, -409.68915f, 7f), Matrix(
        0.18835f, 0.842276f, 0.505069f,
        -0.188921f, 0.535742f, -0.822976f,
        -0.96376f, 0.059589f, 0.26003f
    ))
    addAsteroid("Med", Vector(-817.4718f, -400.24005f, -9f), Matrix(
        0.9347f, -0.310382f, -0.173202f,
        -0.106263f, 0.220985f, -0.969471f,
        0.339182f, 0.924569f, 0.173573f
    ))
    addAsteroid("Huge", Vector(-774.36664f, -338.0291f, -18f), Matrix(
        -0.05326f, 0.266178f, 0.962451f,
        -0.115835f, -0.95896f, 0.258802f,
        0.99184f, -0.097702f, 0.081907f
    ))
    addAsteroid("Small", Vector(-750.36664f, -309.0291f, -14f), Matrix(
        0.117043f, 0.730025f, 0.673323f,
        0.920071f, -0.334931f, 0.2032f,
        0.373858f, 0.595722f, -0.710876f
    ))
    addAsteroid("Med", Vector(-919.81616f, -378.32184f, -6f), Matrix(
        0.032796f, -0.151206f, 0.987958f,
        0.62635f, -0.767192f, -0.13821f,
        0.778852f, 0.62334f, 0.069547f
    ))
    addAsteroid("Huge", Vector(-942.5564f, -464.75098f, 13f), Matrix(
        0.900047f, -0.161948f, -0.404585f,
        0.278905f, 0.927414f, 0.24923f,
        0.334856f, -0.33716f, 0.879883f
    ))
    addAsteroid("Med", Vector(-972.5246f, -555.98096f, -17f), Matrix(
        0.505482f, -0.36804f, 0.780407f,
        0.369263f, -0.725179f, -0.581172f,
        0.779829f, 0.581947f, -0.230661f
    ))
    addAsteroid("Large", Vector(-969.5246f, -548.98096f, 10f), Matrix(
        0.338013f, -0.917391f, 0.210096f,
        -0.925115f, -0.364894f, -0.10495f,
        0.172943f, -0.158889f, -0.972031f
    ))
    addAsteroid("Huge", Vector(-1098.4724f, -544.06396f, -20.000122f), Matrix(
        -0.355294f, 0.533561f, -0.767515f,
        0.266671f, -0.729109f, -0.630307f,
        -0.895909f, -0.428618f, 0.116763f
    ))
    addAsteroid("Med", Vector(-1118.4724f, -539.06396f, -2.000122f), Matrix(
        -0.196191f, 0.972466f, 0.125775f,
        -0.387318f, 0.040984f, -0.921035f,
        -0.900829f, -0.229414f, 0.368613f
    ))
    addAsteroid("Small", Vector(-1024.3782f, -569.8095f, -3f), Matrix(
        0.490335f, -0.768877f, 0.410365f,
        0.570298f, -0.072988f, -0.818189f,
        0.659038f, 0.635217f, 0.402701f
    ))
    addAsteroid("Huge", Vector(-1020.37805f, -563.8095f, 7f), Matrix(
        0.000858f, -0.029142f, -0.999575f,
        -0.140751f, 0.989621f, -0.028973f,
        0.990045f, 0.140716f, -0.003252f
    ))
    addAsteroid("Large", Vector(-1147.6171f, -621.6997f, -4f), Matrix(
        0.593121f, -0.723698f, 0.3528f,
        0.618788f, 0.690103f, 0.375312f,
        -0.51508f, -0.004297f, 0.857131f
    ))
    addAsteroid("Med", Vector(-1179.6171f, -645.6997f, -5f), Matrix(
        -0.486851f, -0.855679f, -0.175469f,
        -0.443228f, 0.068903f, 0.893757f,
        -0.752679f, 0.512899f, -0.412806f
    ))
    addAsteroid("Med", Vector(-591.9486f, 140.36143f, -9f), Matrix(
        0.492356f, -0.860678f, -0.129684f,
        0.261165f, 0.288213f, -0.921263f,
        0.830288f, 0.419721f, 0.366682f
    ))
    addAsteroid("Large", Vector(-484.14423f, 404.3282f, 5f), Matrix(
        0.752477f, 0.533687f, -0.385948f,
        0.652233f, -0.68523f, 0.324116f,
        -0.091487f, -0.495618f, -0.863709f
    ))
    addAsteroid("Med", Vector(-457.92883f, 685.86926f, 9f), Matrix(
        0.114415f, -0.325942f, -0.938441f,
        0.335532f, -0.876454f, 0.345321f,
        -0.935055f, -0.354387f, 0.009085f
    ))
    addAsteroid("Large", Vector(-450.93402f, 678.7213f, 13f), Matrix(
        -0.417893f, 0.873571f, 0.249476f,
        0.831321f, 0.256936f, 0.492838f,
        0.36643f, 0.413348f, -0.83359f
    ))
    addAsteroid("Med", Vector(-453.3129f, 1214.0685f, -5f), Matrix(
        0.32856f, 0.844356f, 0.423216f,
        -0.728469f, -0.058656f, 0.682563f,
        0.60115f, -0.532563f, 0.595816f
    ))
    addAsteroid("Small", Vector(-440.31302f, 1216.0685f, -6f), Matrix(
        0.410884f, 0.857714f, -0.309033f,
        -0.270355f, -0.209089f, -0.939782f,
        -0.870679f, 0.46969f, 0.145976f
    ))
    addAsteroid("Huge", Vector(-445.21225f, 1186.0917f, -4f), Matrix(
        -0.71334f, -0.226127f, -0.663334f,
        -0.352158f, -0.702679f, 0.618245f,
        -0.605913f, 0.674617f, 0.421616f
    ))
    addAsteroid("Huge", Vector(-560.57477f, 315.37836f, 2f), Matrix(
        0.163362f, -0.980456f, -0.109627f,
        0.480268f, 0.176097f, -0.859263f,
        0.861775f, 0.08772f, 0.499649f
    ))
    addAsteroid("Large", Vector(-662.58875f, 82.97916f, 9f), Matrix(
        0.014473f, 0.786087f, -0.617946f,
        -0.018088f, 0.618116f, 0.785879f,
        0.999732f, -0.000196f, 0.023165f
    ))
    addAsteroid("Large", Vector(-789.04474f, -121.29259f, 14f), Matrix(
        -0.666115f, 0.444236f, -0.599121f,
        -0.166426f, -0.871549f, -0.461199f,
        -0.727044f, -0.207502f, 0.654484f
    ))
    addAsteroid("Small", Vector(-791.96704f, -122.65582f, 12f), Matrix(
        0.175729f, -0.969007f, 0.173621f,
        0.344655f, -0.104644f, -0.932879f,
        0.922134f, 0.223774f, 0.315584f
    ))

    addIsland("Island_Icy_02", Vector(-246.97375f, -648.4757f), 0.406737f)
    addIsland("Island_Icy_03", Vector(409.2289f, -782.4858f))
    addIsland("Island_Icy_04", Vector(624.3841f, -981.3234f))
    addIsland("Island_Icy_05", Vector(-586.479f, -945.08374f))

    fun addProcyonBackupShip(type: String, position: Vector, cos: Float = 1f) = world.addWorldObject(
        "Ship_Procyon_$type", "Procyon Backup", "Procyon Backup Group", position, Matrix.rotationZ(acos(cos))
    )

    val procyonSiriusObjectID = addProcyonBackupShip("Tender", Vector(-78.84101f, -772.5997f), 0.0349f)
    val procyonMajorisObjectID = addProcyonBackupShip("Tender", Vector(161.22073f, -767.93f))
    val procyonFrostKnifeObjectID = addProcyonBackupShip("Sloop", Vector(29.993025f, -647.2096f))
    val procyonCrescentBearObjectID = addProcyonBackupShip("Frigate", Vector(277.96152f, -696.3086f))
    val procyonNovaMaceObjectID = addProcyonBackupShip("Frigate", Vector(-100.592316f, -685.2343f))

    val pirateBaseObjectID = world.addWorldObject("Base_PirateMP_3", "Pirate Base", "Pirate Base Group", Vector(-10.458221f, 913.86884f), Matrix.rotationZ(acos(-0.978148f)))

    world.addWaypointPath(World.createWaypointPath(
        "Asteroid Path",
        Vector(-1415.4812f, -811.323f, -1f),
        Vector(-1075.7678f, -594.89685f, -1f),
        Vector(-904.03046f, -464.4145f, -1f),
        Vector(-627.7066f, -22.675903f, -1f),
        Vector(-528.86365f, 213.86151f, -1f),
        Vector(-420.0671f, 639.1012f, -1f),
        Vector(-422.16617f, 993.6296f, -1f),
        Vector(-439.6226f, 1475.2617f, -1f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Procyon Backup Polygon",
        Coord(-28.952734f, -283.88074f),
        Coord(407.64624f, -334.20575f),
        Coord(561.1523f, -585.0706f),
        Coord(744.90533f, -983.9179f),
        Coord(409.58154f, -991.2429f),
        Coord(-20.896837f, -968.78534f),
        Coord(-187.47314f, -985.67993f),
        Coord(-410.2114f, -955.60474f),
        Coord(-494.77856f, -943.1487f),
        Coord(-557.4419f, -869.9709f),
        Coord(-510.21692f, -528.7655f),
        Coord(-378.51797f, -339.55597f)
    ))

    pirateAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }
    procyonAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }

    fun createSetupProcyonBackupShipAction(objectID: Int, name: String, crewSkill: Skill, displayNameID: String) = World.createSetupShipAction(
        objectID, "PSR $name", null, FollowMode.TO_END, Stance.AGGRESSIVE, "Procyon Backup", false, crewSkill, true, displayNameID
    )

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        createSetupProcyonBackupShipAction(procyonSiriusObjectID, "Sirius", Skill.GREEN, "IDGS_TPSHIPNAMEPROCYON00_SIRIUS"),
        createSetupProcyonBackupShipAction(procyonMajorisObjectID, "Majoris", Skill.AVERAGE, "IDGS_TPSHIPNAMEPROCYON00_MAJORIS"),
        createSetupProcyonBackupShipAction(procyonFrostKnifeObjectID, "Frost Knife", Skill.AVERAGE, "IDGS_TPSHIPNAMEPROCYON01_FROSTKNIFE"),
        createSetupProcyonBackupShipAction(procyonCrescentBearObjectID, "Crescent Bear", Skill.AVERAGE, "IDGS_TPSHIPNAMEPROCYON00_CRESCENTBEAR"),
        createSetupProcyonBackupShipAction(procyonNovaMaceObjectID, "Nova Mace", Skill.AVERAGE, "IDGS_TPSHIPNAMEPROCYON01_NOVAMACE"),
        World.createSetupIslandAction(pirateBaseObjectID, 100, "Pirate Base", Skill.ELITE, Stance.AGGRESSIVE),
        World.createSetupAsteroidBeltAction("Asteroid Group", "Asteroid Path", FollowMode.TELEPORT_LOOP, true, 10f, 25f, 0.2f, 3f),
        World.createSetGroupHoldPositionAction("Procyon Backup Group", true),
        World.createSetupTeamObjectiveAction(pirateTeamID, null, "Pirate Objective"),
        World.createSetupTeamObjectiveAction(procyonTeamID, "Procyon Objective Point", "Procyon Objective"),
        World.createPlayMusicAction("BTL_DeadlyPirate_AltSlow", 0.7f, 2f, 2f)
    ))

    fun addWorldRule(ruleName: String, condition: ConditionNode, vararg actions: ActionNode) {
        world.addWorldRule(World.createWorldRule(ruleName, runOnce = true, isActive = true, ConditionListNode(condition), ActionListNode(*actions)))
    }

    fun createSetProcyonBackupShipHoldPositionAction(name: String) = World.createSetGroupHoldPositionAction("PSR $name" of "Procyon Backup Group", false)

    addWorldRule(
        "Pirates Approach Procyon Backup",
        World.createTeamMemberEntersPolygonCondition(pirateTeamID, "Procyon Backup Polygon"),
        createSetProcyonBackupShipHoldPositionAction("Frost Knife"),
        createSetProcyonBackupShipHoldPositionAction("Crescent Bear"),
        createSetProcyonBackupShipHoldPositionAction("Nova Mace"),
        World.createPlayMusicAction("BTL_Pirate02_SlowVARI", 0.7f, 2f, 2f)
    )

    addWorldRule(
        "Pirate Win",
        World.createTeamHasNoShipsCondition(procyonTeamID),
        World.createTeamWinsAction(pirateTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PRIVATEERDEFEAT", true)
    )

    addWorldRule(
        "Procyon Win 1",
        World.createGroupVitalSectionDamageCondition("Pirate Base Group", VitalSection.MISSION, Equivalence.EQUAL_TO, 1f),
        World.createTeamWinsAction(procyonTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PROCYONDEFEAT", true)
    )

    addWorldRule(
        "Procyon Win 2",
        World.createTeamCaptureGroupCondition(procyonTeamID, "Pirate Base Group"),
        World.createTeamWinsAction(procyonTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PROCYONDEFEAT", true)
    )

    addWorldRule(
        "Computer Win",
        World.createNoHumanControlledFleetCondition(),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_LOST", "IDGS_TPINGAMEMESSAGE_GAME_LOST", true)
    )

    world.addObjectivePoint(World.createObjectivePoint("Procyon Objective Point", Vector(-11.270416f, 861.66724f)))

    world.addObjectiveTask(World.createObjectiveTask("Pirate Objective", "IDGS_TPOBJECTIVES2_MP_DEFENDBASE"))
    world.addObjectiveTask(World.createObjectiveTask("Procyon Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYBASE"))

    world.addMapText(World.createMapText("Pirate Base", "IDGS_TPMAPTEXTITEMS_GENERAL_CAMP_STRONGBOX", Vector(-9.998058f, 870.7727f)))
    world.addMapText(World.createMapText("Asteroid Belt", "IDGS_TPMAPTEXTITEMS_M04_CAUTION_ASTEROIDS", Vector(-697.8396f, -26.898315f)))
    world.addMapText(World.createMapText("Islands", "IDGS_TPMAPTEXTITEMS_GENERAL_ISLANDS_BLACKHEART_BELT", Vector(553.6045f, -67.6073f)))

    println(world.build())
}

fun rover() {
    val world = World.create(
        game,
        "Red Rover",
        "IDGS_TPWORLDNAMES_MP_BASE_WAR",
        "IDGS_TPWORLDDESCRIPTION_MP_RED_ROVER",
        Vector(2100f, 1750f, 750f),
        9,
        Color(0.141176f, 0.141176f, 0.141176f),
        Vector(0.637654f, 0.273401f, -0.720173f),
        Color(0.494118f, 0.203922f, 0.25098f),
        Color(0.976471f, 0.886275f, 0.866667f),
        "Map_RedRover",
        allianceChangeAllowed = false
    )

    val navyTeamID = "IDGS_TPTEAMNAMES_NAVY"
    val pirateTeamID = "IDGS_TPTEAMNAMES_PIRATES"

    val navyTeamIndex = world.addTeam(World.createTeam(navyTeamID, Faction.NAVY))
    val pirateTeamIndex = world.addTeam(World.createTeam(pirateTeamID, Faction.PIRATE))

    val navyAllianceIndices = mutableListOf<Int>()
    val pirateAllianceIndices = mutableListOf<Int>()

    fun addPlayer(faction: Faction, id: Int, color: Color, start: Vector, direction: Vector) {
        val factionPair = if (faction == Faction.NAVY) navyTeamIndex to navyAllianceIndices else pirateTeamIndex to pirateAllianceIndices
        factionPair.second.add(world.addPlayer("${faction.name.titlecase()} $id", factionPair.first, color, start, direction, faction, Formation.SIDE_BY_SIDE))
    }

    addPlayer(Faction.NAVY, 1, Color(0f, 0f, 1f), Vector(-383.76883f, -429.0005f), Vector.dir(-0.251591f, 0.967834f))
    addPlayer(Faction.PIRATE, 1, Color(1f, 0f, 0f), Vector(-420.6589f, 328.1404f), Vector.dir(-0.013906f, -0.999903f))
    addPlayer(Faction.NAVY, 2, Color(0f, 1f, 1f), Vector(-129.49927f, -354.1483f), Vector.dir(-0.115964f, 0.993253f))
    addPlayer(Faction.PIRATE, 2, Color(1f, 0f, 1f), Vector(-150.57951f, 370.4875f), Vector.dir(0.075158f, -0.997172f))
    addPlayer(Faction.NAVY, 3, Color(0f, 0f, 0.501961f), Vector(270.40744f, -395.27737f), Vector.dir(0.007071f, 0.999975f))
    addPlayer(Faction.PIRATE, 3, Color(0.501961f, 0f, 0f), Vector(221.83421f, 361.33368f), Vector.dir(-0.028636f, -0.99959f))
    addPlayer(Faction.NAVY, 4, Color(0.65098f, 0.792157f, 0.941176f), Vector(612.3666f, -332.14923f), Vector.dir(-0.01444f, 0.999896f))
    addPlayer(Faction.PIRATE, 4, Color(0.501961f, 0f, 0.501961f), Vector(572.2812f, 371.9907f), Vector.dir(0.00491f, -0.999988f))

    navyAllianceIndices.add(world.addPlayerListElement(World.createFakeFleetElement("Navy Base", Color.blue, Vector(-43.37744f, -970.33057f))))
    pirateAllianceIndices.add(world.addPlayerListElement(World.createFakeFleetElement("Pirate Base", Color.red, Vector(10.601166f, 799.95386f))))

    val navyBaseObjectID = world.addWorldObject("Base_Montgomery", "Navy Base", "Navy Base Group", Vector(-3.516921f, -789.1884f))
    val pirateBaseObjectID = world.addWorldObject("Base_PirateMP", "Pirate Base", "Pirate Base Group", Vector(-11.945926f, 748.3289f))

    fun addIsland(typeID: String, position: Vector, xx: Float = 1f, yx: Float = 0f) {
        world.addWorldObject(typeID, null, "Island Group", position, Matrix.rotationZ(xx, yx))
    }

    addIsland("Island02", Vector(-350.8146f, -617.7819f), -0.945519f, 0.325568f)
    addIsland("Island05", Vector(827.4059f, -371.00574f))
    addIsland("Island08", Vector(729.1585f, 423.69644f), 0.694658f, -0.71934f)
    addIsland("Island10", Vector(-422.2526f, 524.50244f))
    addIsland("Island13", Vector(429.8865f, 170.63754f), 0.173648f, -0.984808f)
    addIsland("Island14", Vector(325.9055f, -555.53735f))
    addIsland("Island_Mega_02", Vector(-1102.3771f, -11.739556f))
    addIsland("Island_Mega_03", Vector(1077.4265f, 54.727417f), 0.75471f, -0.656059f)
    addIsland("Island02", Vector(-559.7831f, 160.6191f), 0.374607f, 0.927184f)
    addIsland("Island03", Vector(-329.0289f, 690.86066f), -0.788011f, 0.615662f)
    addIsland("Island04", Vector(366.47916f, 570.8144f))
    addIsland("Island05", Vector(58.47438f, -192.31421f))
    addIsland("Island06", Vector(19.976303f, 90.62695f))
    addIsland("Island10", Vector(-503.68036f, -302.98242f), -0.766045f, 0.642788f)
    addIsland("Island11", Vector(693.1832f, -112.446075f), 0.669131f, 0.743145f)
    addIsland("Island12", Vector(-145.9896f, -71.698975f))
    addIsland("Island08", Vector(894.0706f, 53.878674f), 0.559193f, -0.829038f)
    addIsland("Island07", Vector(446.33618f, -155.9292f))

    val stormObjectID = world.addWorldObject("Terrain_Nebula", null, "Storm Group", Vector(-969.9366f, -213.41267f))

    fun addMine(faction: Faction, position: Vector) {
        world.addWorldObject("Bullet_StarMine_Med", null, "${faction.name.titlecase()} Mine Group", position)
    }

    addMine(Faction.NAVY, Vector(-69.46014f, -535.5013f))
    addMine(Faction.NAVY, Vector(-66.40013f, -556.4369f))
    addMine(Faction.NAVY, Vector(-64.1711f, -584.10236f))
    addMine(Faction.NAVY, Vector(-68.77954f, -600.48346f))
    addMine(Faction.NAVY, Vector(-77.19307f, -601.48224f))
    addMine(Faction.NAVY, Vector(-87.953514f, -577.6816f))
    addMine(Faction.NAVY, Vector(-88.77256f, -562.05383f))
    addMine(Faction.NAVY, Vector(-92.03715f, -541.23047f))
    addMine(Faction.NAVY, Vector(-107.415016f, -538.4546f))
    addMine(Faction.NAVY, Vector(-108.575096f, -572.48944f))
    addMine(Faction.NAVY, Vector(-116.02456f, -556.6597f))
    addMine(Faction.NAVY, Vector(-117.434654f, -584.22186f))
    addMine(Faction.NAVY, Vector(-116.188896f, -603.0324f))
    addMine(Faction.NAVY, Vector(-130.90138f, -603.9013f))
    addMine(Faction.NAVY, Vector(-132.10258f, -587.4759f))
    addMine(Faction.NAVY, Vector(-135.46788f, -570.75336f))
    addMine(Faction.NAVY, Vector(-140.9871f, -540.25116f))
    addMine(Faction.NAVY, Vector(-157.30373f, -539.92474f))
    addMine(Faction.NAVY, Vector(-155.27693f, -564.34607f))
    addMine(Faction.NAVY, Vector(-151.65242f, -582.35223f))
    addMine(Faction.NAVY, Vector(-153.08562f, -603.4575f))
    addMine(Faction.NAVY, Vector(-155.99615f, -630.9419f))
    addMine(Faction.NAVY, Vector(35.862026f, -637.043f))
    addMine(Faction.NAVY, Vector(60.880585f, -636.41254f))
    addMine(Faction.NAVY, Vector(89.02017f, -636.97546f))
    addMine(Faction.NAVY, Vector(114.03315f, -637.4759f))
    addMine(Faction.NAVY, Vector(141.00632f, -640.2762f))
    addMine(Faction.NAVY, Vector(141.83722f, -605.86383f))
    addMine(Faction.NAVY, Vector(139.59586f, -588.1785f))
    addMine(Faction.NAVY, Vector(120.23661f, -591.3426f))
    addMine(Faction.NAVY, Vector(119.71961f, -603.086f))
    addMine(Faction.NAVY, Vector(97.592926f, -599.1307f))
    addMine(Faction.NAVY, Vector(67.79219f, -603.2155f))
    addMine(Faction.NAVY, Vector(46.583782f, -606.2886f))
    addMine(Faction.NAVY, Vector(42.503925f, -587.4213f))
    addMine(Faction.NAVY, Vector(62.404373f, -564.494f))
    addMine(Faction.NAVY, Vector(89.498634f, -569.70197f))
    addMine(Faction.NAVY, Vector(124.036194f, -570.39685f))
    addMine(Faction.NAVY, Vector(134.59364f, -563.5106f))
    addMine(Faction.NAVY, Vector(117.90497f, -540.0503f))
    addMine(Faction.NAVY, Vector(71.167114f, -533.4637f))
    addMine(Faction.NAVY, Vector(37.64701f, -534.3932f))

    addMine(Faction.PIRATE, Vector(-147.8819f, 627.30493f))
    addMine(Faction.PIRATE, Vector(-141.74643f, 614.44934f))
    addMine(Faction.PIRATE, Vector(-120.89363f, 607.4785f))
    addMine(Faction.PIRATE, Vector(-101.895065f, 585.0486f))
    addMine(Faction.PIRATE, Vector(-82.19043f, 564.5722f))
    addMine(Faction.PIRATE, Vector(-57.682724f, 545.7967f))
    addMine(Faction.PIRATE, Vector(36.90167f, 542.18274f))
    addMine(Faction.PIRATE, Vector(92.27823f, 534.3883f))
    addMine(Faction.PIRATE, Vector(55.82492f, 530.4828f))
    addMine(Faction.PIRATE, Vector(83.16316f, 539.2935f))
    addMine(Faction.PIRATE, Vector(128.43663f, 561.45483f))
    addMine(Faction.PIRATE, Vector(152.584f, 586.2487f))
    addMine(Faction.PIRATE, Vector(151.15515f, 607.9912f))
    addMine(Faction.PIRATE, Vector(152.28868f, 638.11206f))
    addMine(Faction.PIRATE, Vector(158.78221f, 666.4979f))
    addMine(Faction.PIRATE, Vector(158.25038f, 684.73285f))
    addMine(Faction.PIRATE, Vector(148.17586f, 708.0047f))
    addMine(Faction.PIRATE, Vector(127.50655f, 636.0851f))
    addMine(Faction.PIRATE, Vector(98.7068f, 591.3682f))
    addMine(Faction.PIRATE, Vector(74.214096f, 566.94586f))
    addMine(Faction.PIRATE, Vector(86.56736f, 572.6796f))
    addMine(Faction.PIRATE, Vector(67.880554f, 570.3095f))
    addMine(Faction.PIRATE, Vector(47.643867f, 571.2619f))
    addMine(Faction.PIRATE, Vector(-72.200424f, 569.3218f))
    addMine(Faction.PIRATE, Vector(-123.13936f, 582.0731f))
    addMine(Faction.PIRATE, Vector(-144.58336f, 611.5174f))
    addMine(Faction.PIRATE, Vector(-151.87238f, 655.69f))
    addMine(Faction.PIRATE, Vector(-173.97667f, 685.91925f))
    addMine(Faction.PIRATE, Vector(-182.37283f, 728.31604f))
    addMine(Faction.PIRATE, Vector(-64.41963f, 511.67844f))
    addMine(Faction.PIRATE, Vector(-80.64432f, 502.3941f))
    addMine(Faction.PIRATE, Vector(-112.07738f, 500.052f))
    addMine(Faction.PIRATE, Vector(-137.96512f, 515.6564f))
    addMine(Faction.PIRATE, Vector(-158.18915f, 543.18695f))
    addMine(Faction.PIRATE, Vector(-165.72495f, 566.09827f))
    addMine(Faction.PIRATE, Vector(-176.07272f, 611.08344f))
    addMine(Faction.PIRATE, Vector(-129.72864f, 550.65283f))
    addMine(Faction.PIRATE, Vector(-135.19547f, 510.3459f))
    addMine(Faction.PIRATE, Vector(-104.59488f, 477.22986f))
    addMine(Faction.PIRATE, Vector(-65.40337f, 465.6233f))
    addMine(Faction.PIRATE, Vector(-118.4401f, 481.49307f))
    addMine(Faction.PIRATE, Vector(-155.10803f, 515.7401f))
    addMine(Faction.PIRATE, Vector(-169.71292f, 542.54767f))
    addMine(Faction.PIRATE, Vector(37.20407f, 477.98114f))
    addMine(Faction.PIRATE, Vector(68.272354f, 438.26605f))
    addMine(Faction.PIRATE, Vector(79.32813f, 447.11786f))
    addMine(Faction.PIRATE, Vector(67.85328f, 478.03336f))
    addMine(Faction.PIRATE, Vector(89.58985f, 484.51028f))
    addMine(Faction.PIRATE, Vector(117.126274f, 504.70038f))
    addMine(Faction.PIRATE, Vector(124.435036f, 512.9806f))
    addMine(Faction.PIRATE, Vector(133.74915f, 476.11444f))
    addMine(Faction.PIRATE, Vector(129.74759f, 458.1223f))
    addMine(Faction.PIRATE, Vector(173.51727f, 464.1103f))
    addMine(Faction.PIRATE, Vector(178.9189f, 481.4165f))
    addMine(Faction.PIRATE, Vector(182.0066f, 524.50726f))
    addMine(Faction.PIRATE, Vector(187.91762f, 554.34436f))
    addMine(Faction.PIRATE, Vector(204.85419f, 603.93835f))
    addMine(Faction.PIRATE, Vector(153.10023f, 537.47205f))
    addMine(Faction.PIRATE, Vector(-195.66708f, 561.69385f))
    addMine(Faction.PIRATE, Vector(-218.52823f, 617.9369f))
    addMine(Faction.PIRATE, Vector(-193.44266f, 630.46564f))
    addMine(Faction.PIRATE, Vector(-175.7868f, 606.36725f))
    addMine(Faction.PIRATE, Vector(-211.56091f, 671.3533f))
    addMine(Faction.PIRATE, Vector(-198.98636f, 686.6376f))
    addMine(Faction.PIRATE, Vector(-186.84103f, 656.34985f))
    addMine(Faction.PIRATE, Vector(-213.09549f, 719.4363f))
    addMine(Faction.PIRATE, Vector(194.92157f, 536.70386f))
    addMine(Faction.PIRATE, Vector(193.01064f, 599.8473f))
    addMine(Faction.PIRATE, Vector(-25.326767f, 536.96814f))
    addMine(Faction.PIRATE, Vector(-18.304504f, 518.1846f))
    addMine(Faction.PIRATE, Vector(-12.269424f, 470.39407f))
    addMine(Faction.PIRATE, Vector(14.289749f, 514.70654f))
    addMine(Faction.PIRATE, Vector(88.08124f, 394.14493f))
    addMine(Faction.PIRATE, Vector(24.191544f, 378.42752f))
    addMine(Faction.PIRATE, Vector(-58.772003f, 398.34787f))
    addMine(Faction.PIRATE, Vector(-109.52354f, 431.00845f))
    addMine(Faction.PIRATE, Vector(-163.08582f, 466.45193f))
    addMine(Faction.PIRATE, Vector(-206.5254f, 515.8932f))
    addMine(Faction.PIRATE, Vector(-230.21399f, 578.87115f))
    addMine(Faction.PIRATE, Vector(132.96991f, 402.2622f))
    addMine(Faction.PIRATE, Vector(64.29106f, 408.6316f))
    addMine(Faction.PIRATE, Vector(7.039093f, 447.8936f))
    addMine(Faction.PIRATE, Vector(-9.949677f, 490.9685f))
    addMine(Faction.PIRATE, Vector(-37.973755f, 564.4686f))
    addMine(Faction.PIRATE, Vector(-59.9327f, 501.3525f))
    addMine(Faction.PIRATE, Vector(-107.4671f, 437.49127f))
    addMine(Faction.PIRATE, Vector(107.44003f, 550.67395f))
    addMine(Faction.PIRATE, Vector(38.830475f, 425.93564f))
    addMine(Faction.PIRATE, Vector(-54.85518f, 421.63562f))
    addMine(Faction.PIRATE, Vector(109.102295f, 450.09177f))
    addMine(Faction.PIRATE, Vector(-100.8789f, 456.01813f))
    addMine(Faction.PIRATE, Vector(-149.1767f, 443.2611f))
    addMine(Faction.PIRATE, Vector(15.866196f, 413.74194f))

    world.addWaypointPath(World.createWaypointPath(
        "Storm Path",
        Vector(-1522.598f, -121.33943f, -0.000244f),
        Vector(-871.89355f, -240.41629f),
        Vector(-509.5989f, -205.39372f),
        Vector(-186.61134f, -179.80734f),
        Vector(257.3846f, -202.97325f),
        Vector(662.38885f, -200.04483f),
        Vector(968.0924f, -218.49506f),
        Vector(1477.3295f, -198.53111f),
        Vector(1563.1445f, -4.845786f),
        Vector(1450.9529f, 175.03302f),
        Vector(1025.0057f, 176.54645f),
        Vector(710.71594f, 173.26581f, -40f),
        Vector(440.6012f, 151.85167f, -41f),
        Vector(-70.10861f, 167.13458f, 1f),
        Vector(-429.61276f, 179.1533f, -0.000244f),
        Vector(-690.83716f, 165.45692f),
        Vector(-1047.6066f, 187.55072f),
        Vector(-1476.6965f, 83.422264f),
        Vector(-1550.0707f, -7.425808f)
    ))

    world.addWorldPolygon(World.createWorldPolygon(
        "Storm Polygon",
        Coord(-1407.9183f, -5.875975f),
        Coord(-1472.8485f, -90.06288f),
        Coord(-1489.3164f, -212.77191f),
        Coord(-1424.4624f, -305.97818f),
        Coord(-1319.8971f, -345.74292f),
        Coord(-1235.7233f, -298.0713f),
        Coord(-1206.4392f, -204.21124f),
        Coord(-1221.2573f, -72.96643f),
        Coord(-1304.981f, -8.748769f)
    ))

    world.addWorldPointSet(World.createWorldPointSet(
        "Storm Point Set",
        World.createWorldPoint(190.39987f, Vector(-1340.9951f, -148.5154f, 20f), 1f, Vector.north)
    ))

    navyAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }
    pirateAllianceIndices.forEachPair { x, y -> world.addPlayerAllianceInfo(World.createPlayerAllianceInfo(x, y)) }

    world.addWorldRule(World.createInitializationWorldRule(
        "All",
        World.createSetupSolarStormAction(
            stormObjectID,
            "Storm Weather",
            "Storm Polygon",
            "Storm Point Set",
            2f,
            12f,
            1f,
            300f
        ),
        World.createSetupIslandAction(navyBaseObjectID, 60, "Navy Base", Skill.GREEN, Stance.AGGRESSIVE),
        World.createSetupIslandAction(pirateBaseObjectID, 80, "Pirate Base", Skill.ELITE, Stance.AGGRESSIVE),
        World.createGroupFollowPathAction("Storm Group", "Storm Path", FollowMode.LOOP, false),
        World.createSetGroupSpeedAction("Storm Group", 10),
        World.createSetGroupOwnerAction("Navy Mine Group", "Navy Base"),
        World.createSetGroupOwnerAction("Pirate Mine Group", "Pirate Base"),
        World.createSetupTeamObjectiveAction(navyTeamID, "Navy Objective Point", "Navy Objective 1"),
        World.createSetupTeamObjectiveAction(navyTeamID, null, "Navy Objective 2"),
        World.createSetupTeamObjectiveAction(pirateTeamID, "Pirate Objective Point", "Pirate Objective 1"),
        World.createSetupTeamObjectiveAction(pirateTeamID, null, "Pirate Objective 2"),
        World.createPlayMusicAction("BTL_Escape_Full", 0.8f, 2f, 2f)
    ))

    fun addWorldRule(ruleName: String, condition: ConditionNode, vararg actions: ActionNode) {
        world.addWorldRule(World.createWorldRule(ruleName, runOnce = true, isActive = true, ConditionListNode(condition), ActionListNode(*actions)))
    }

    addWorldRule(
        "Navy Win 1",
        World.createGroupVitalSectionDamageCondition("Pirate Base Group", VitalSection.MISSION, Equivalence.EQUAL_TO, 1f),
        World.createTeamWinsAction(navyTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_NAVYDEFEAT", true)
    )

    addWorldRule(
        "Pirate Win 1",
        World.createGroupVitalSectionDamageCondition("Navy Base Group", VitalSection.MISSION, Equivalence.EQUAL_TO, 1f),
        World.createTeamWinsAction(pirateTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PIRATEDEFEAT", true)
    )

    addWorldRule(
        "Navy Win 2",
        World.createTeamCaptureGroupCondition(navyTeamID, "Pirate Base Group"),
        World.createTeamWinsAction(navyTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_NAVYDEFEAT", true)
    )

    addWorldRule(
        "Pirate Win 2",
        World.createTeamCaptureGroupCondition(pirateTeamID, "Navy Base Group"),
        World.createTeamWinsAction(pirateTeamID),
        World.createEndGameAction("IDGS_TPINGAMEMESSAGE_GAME_WON", "IDGS_TPINGAMEMESSAGE_GAME_GENERAL_PIRATEDEFEAT", true)
    )

    world.addWorldRule(World.createTeamGameCompleteWorldRule("End"))

    world.addObjectivePoint(World.createObjectivePoint("Navy Objective Point", Vector(-11.51619f, 751.74243f)))
    world.addObjectivePoint(World.createObjectivePoint("Pirate Objective Point", Vector(-2.84774f, -787.7022f)))

    world.addObjectiveTask(World.createObjectiveTask("Navy Objective 1", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYBASE"))
    world.addObjectiveTask(World.createObjectiveTask("Navy Objective 2", "IDGS_TPOBJECTIVES2_MP_DEFENDBASE"))
    world.addObjectiveTask(World.createObjectiveTask("Pirate Objective 1", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYBASE"))
    world.addObjectiveTask(World.createObjectiveTask("Pirate Objective 2", "IDGS_TPOBJECTIVES2_MP_DEFENDBASE"))

    world.addMapText(World.createMapText("Navy Base", "IDGS_TPMAPTEXTITEMS_MP_REDROVER", Vector(-7.850983f, -778.7959f)))
    world.addMapText(World.createMapText("Pirate Base", "IDGS_TPMAPTEXTITEMS_GENERAL_CAMP_LATCHKEY", Vector(-20.348267f, 760.36f)))
    world.addMapText(World.createMapText("Islands", "IDGS_TPMAPTEXTITEMS_GENERAL_ARCHIPELAGO_SUMMERS", Vector(-139.97562f, -48.782104f)))

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

    fun addPlayer(id: Int, color: Color, start: Vector) {
        world.addPlayer("Player $id", -1, color, start, Vector.east, Faction.ANY, Formation.DIAGONAL_V)
    }

    addPlayer(1, Color(0f, 0.502f, 0f), Vector(-541.1266f, 630.96155f))
    addPlayer(2, Color(0f, 0f, 0.502f), Vector(622.7023f, 592.0886f))
    addPlayer(3, Color(0f, 0.502f, 0.502f), Vector(6.498825f, 882.9153f))
    addPlayer(4, Color(0.651f, 0.7922f, 0.9412f), Vector(799.8519f, -26.944336f))
    addPlayer(5, Color(0.9059f, 0.5373f, 0.0392f), Vector(18.560791f, -810.7614f))
    addPlayer(6, Color(1f, 0f, 0f), Vector(603.85004f, -508.37128f))
    addPlayer(7, Color(0f, 0f, 1f), Vector(-720.58923f, 176.86047f))
    addPlayer(8, Color(1f, 1f, 0f), Vector(-475.92407f, -529.2828f))

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
        World.createWorldPoint(577.4005f, Vector(30.162416f, 40.36957f), 1f, Vector.north)
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
        World.createSetObjectiveTaskStateAction("Kill All Objective", true),
        World.createPlayMusicAction("NEUTRL_DragonGoo", 0.7f, 2f, 2f)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

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
        Vector(0.501989f, 0.145542f, -0.85254f),
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
        World.createSetObjectiveTaskStateAction("Kill All Objective", true),
        World.createPlayMusicAction("BTL_NavyBig_BassnDrums", 0.7f, 2f, 2f)
    ))
    world.addWorldRule(World.createSkirmishCompleteWorldRule("End"))

    world.addObjectiveTask(World.createObjectiveTask("Kill All Objective", "IDGS_TPOBJECTIVES2_MP_DESTROYENEMYSHIPS"))

    world.addMapText(World.createMapText("Black Hole", "IDGS_TPMAPTEXTITEMS_MP_ARENA_SMALL", Vector(1.299599f, 4.263611f)))

    println(world.build())
}

fun test() {
    val world = World(game, "Convoy Raid_Navy")

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
    //border()
    convoy()
    //diablo()
    //dragon()
    //mousetrap()
    //rover()
    //shadow()
    //zemyatin()
    //test()
}
