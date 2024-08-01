package dodd.terran

import dodd.terran.translation.*
import dodd.terran.util.Helpers.node
import dodd.terran.util.Helpers.stringNode
import dodd.terran.value.*

class World(private val game: Game, val root: RootNode) {

    companion object {

        private fun getFile(game: Game, location: String) = game.resource("$location.twt")

        fun create(
            game: Game,
            worldName: String,
            worldID: String,
            worldDescription: String,
            worldSize: Vector,
            skyboxID: Int,
            ambientColor: Color,
            roofLightOrientation: Vector,
            floorLightColor: Color,
            roofLightColor: Color,
            mapTextureName: String,
            isMultiplayerMap: Boolean = true,
            mustAssembleFleet: Boolean = true,
            canAssembleFleet: Boolean = true,
            isCampaign: Boolean = false,
            allianceChangeAllowed: Boolean = true,
            islandsMakeSounds: Boolean = true,
            customWorldName: String? = null,
            customWorldDescription: String? = null,
            journalMusic: String = "GameMusicTemp",
            playEndMovie: Boolean = false,
            randomSeed: Int = 0,
            bufferSize: Float = 500f
        ): World {
            val root = RootNode()
            val world = World(game, root)

            root.addRaw("# Terran API")

            root["WorldInfo"] = NestedNode(
                "IsMultiplayerMap" to isMultiplayerMap.node,
                "MustAssembleFleet" to mustAssembleFleet.node,
                "World Description" to worldDescription.node,
                "WorldNameID" to worldID.node,
                "Object Count" to 0.node,
                "Team List" to ArrayNode(
                ),
                "Number of Players" to 0.node,
                "Players" to FlatNode(
                ),
                "IsCampaign" to isCampaign.node,
                "Use Custom World Name" to (customWorldName != null).node,
                "Custom World Name" to (customWorldName ?: "").node,
                "Use Custom World Description" to (customWorldDescription != null).node,
                "Custom World Description" to (customWorldDescription ?: "").node
            )

            root["Game"] = NestedNode(
                "Time" to NestedNode(
                    "Game Tick" to 0.node,
                    "Game Time" to 0.0.node
                ),
                "Frame" to (-1).node,
                "Paused" to false.node,
                "ActivePlayerIndex" to (-1).node
            )

            root["World"] = NestedNode(
                "WorldName" to worldName.node,
                "Random Seed" to randomSeed.node,
                "World Size - Min" to (-worldSize).node,
                "World Size - Max" to worldSize.node,
                RawNode("# Player List").entry,
                "PlayerList" to PlayerListNode(
                ),
                "NextID" to 0.node,
                RawNode("# World Object List").entry,
                "WorldObjects" to WorldObjectsListNode(
                ),
                "GameSpecific" to NestedNode(
                    "World Description Sting ID" to worldDescription.node,
                    "World Name Sting ID" to worldID.node,
                    "Effect Event Keeper" to NestedNode(
                        "NumEffectEventInfoChunks" to 0.node
                    ),
                    "Skybox mesh name" to "skybox_${skyboxID.toString().padStart(2, '0')}_Layer1".node,
                    "Ambient Light" to ambientColor.node,
                    "Vector for roof light orientation" to roofLightOrientation.node,
                    "Hemispherical floor light color" to floorLightColor.node,
                    "Hemispherical roof light color" to roofLightColor.node,
                    "World Initialized State" to false.node,
                    "World Buffer Size" to bufferSize.node,
                    "Waypoint Path Info Vector" to ArrayNode(
                    ),
                    "World Polygons Vectors" to ArrayNode(
                    ),
                    "World Point Sets Vector" to ArrayNode(
                    ),
                    "Flag List" to ArrayNode(
                    ),
                    "Timer List" to ArrayNode(
                    ),
                    "Speech Event List" to ArrayNode(
                    ),
                    "PlayerAllianceInfoVector" to ArrayNode(
                    ),
                    "Team List" to ArrayNode(
                    ),
                    "Winning Team" to (-1).node,
                    "Num Groups" to GroupsListNode(
                    ),
                    "World Rules" to NestedNode(
                        "Rule List" to RuleListNode(
                        )
                    ),
                    "Objective System" to NestedNode(
                        "Current Objective Point" to (-1).node,
                        "Current Point Visible On StarMap" to true.node,
                        "Objective Point Info" to ArrayNode(
                        ),
                        "Objective Task Array" to ArrayNode(
                        )
                    ),
                    "Rope" to NestedNode(
                        "RopeInfo" to ArrayNode(
                        )
                    ),
                    "Grappled Objects" to NestedNode(
                        "Grappled Objects Info" to ArrayNode(
                        )
                    ),
                    "Boarding Actions" to NestedNode(
                        "Boarding Actions Info" to ArrayNode(
                        )
                    ),
                    "Journal Entry" to NestedNode(
                        "Page Info" to ArrayNode(
                        ),
                        "Title StringID" to "IDGS_TPJOURNALSCREEN_DUMMY_TITLE".node
                    ),
                    "World Map" to NestedNode(
                        "Backdrop Texture Name" to mapTextureName.node
                    ),
                    "Can Assemble Fleets" to canAssembleFleet.node,
                    "World Crew List" to ArrayNode(
                    ),
                    "World Arms List" to ArrayNode(
                    ),
                    "MapText System" to NestedNode(
                        "MapText Point Info" to ArrayNode(
                        )
                    ),
                    "READAIENTITYCOUNTS" to false.node,
                    "Journal Music Name" to journalMusic.node,
                    "PlayEndMovie" to playEndMovie.node,
                    "IsCampaign" to isCampaign.node,
                    "Is Alliance Change Allowed" to allianceChangeAllowed.node,
                    "Use Custom World Name" to (customWorldName != null).node,
                    "Custom World Name" to (customWorldName ?: "").node,
                    "Use Custom World Description" to (customWorldDescription != null).node,
                    "Custom World Description" to (customWorldDescription ?: "").node,
                    "Islands Make Sounds" to islandsMakeSounds.node,
                    "DATA_NEBULA_CAMERA_EFFECT" to 0.node,
                    "DATA_NEXT_NEBULA_CAMERA_EFFECT" to 0.node,
                    "DATA_NEBULA_CAMERA_EFFECT_FADE_IN_TIMER" to NestedNode(
                        "StartTime" to 0.0.node
                    ),
                    "DATA_NEBULA_CAMERA_EFFECT_SPIN_TIMER" to NestedNode(
                        "StartTime" to 0.0.node
                    )
                )
            )

            root["GameImpl"] = createGameImpl()

            return world
        }

        fun createPlayerInfoTuple(playerName: String, teamIndex: Int) = TupleNode(
            "PlayerInfo - Player Name" to playerName.node,
            "PlayerInfo - TeamIndex" to teamIndex.node
        )

        fun createPlayerListElement(playerName: String, teamIndex: Int, color: Color, isPlayable: Boolean, start: Vector, direction: Vector, faction: Faction, formation: Formation) = NestedNode(
            "Name" to playerName.node,
            "Color" to color.node,
            "IsPlayable" to isPlayable.node,
            "Is Used In Game" to false.node,
            "Multiplayer Name" to "".node,
            "StartPoint" to start.node,
            "StartPointForwardVector" to direction.node,
            "Race" to faction.node,
            "Points" to 0f.node,
            "TeamIndex" to teamIndex.node,
            "FormationType" to formation.node,
            "FleetAI" to createFleetAI(),
            "FlagIndex" to 0.node
        )

        fun createAIFleetElement(playerName: String, teamIndex: Int, color: Color, start: Vector, direction: Vector, formation: Formation): NestedNode {
            return createPlayerListElement(playerName, teamIndex, color, false, start, direction, Faction.ANY, formation)
        }

        fun createFakeFleetElement(playerName: String, color: Color, start: Vector): NestedNode {
            return createPlayerListElement(playerName, -1, color, false, start, Vector.east, Faction.ANY, Formation.NONE)
        }

        fun createFleetAI(formationType: String = "None", holdingFire: Boolean = false) = NestedNode(
            "UPDATETIMER" to NestedNode(
                "StartTime" to 0.0.node
            ),
            "OFFSETTIMER" to NestedNode(
                "StartTime" to 0.0.node
            ),
            "OFFSETTIME" to 0.0625f.node,
            "UPDATETIME" to 0.5f.node,
            "FORMATION" to NestedNode(
                "FORMATIONTYPE" to formationType.node
            ),
            "SHIPINFO" to ArrayNode(
            ),
            "HOLDFIREACTIVE" to holdingFire.node,
            "AITYPE" to "AIFLEET".node
        )

        fun createWorldObject(objectID: Int, typeID: String, ownerIndex: Int, position: Vector, rotation: Matrix = Matrix.identity) = TupleNode(
            "ID" to objectID.node,
            "Type" to typeID.node,
            "State" to NestedNode(
                "HasState" to false.node,
                "Position" to position.node,
                "Orientation" to rotation.node,
                "PlayerIndex" to ownerIndex.node,
                RawNode("# AIEntity").entry,
                "# AIEntityType" to TupleNode(
                    "Type" to "".node
                ),
                RawNode("# RenderEntity").entry,
                "# RenderEntityType" to TupleNode(
                    "Type" to "".node
                ),
                RawNode("# PhysicsEntity").entry,
                "# PhysicsEntityType" to TupleNode(
                    "Type" to "".node
                ),
                RawNode("# CollisionEntity").entry,
                "# CollisionEntityType" to TupleNode(
                    "Type" to "".node
                ),
                RawNode("# CustomInfoEntity").entry,
                "# CustomInfoEntityType" to TupleNode(
                    "Type" to "".node
                )
            )
        )

        fun createWaypointPath(pathName: String, vararg pathPoints: Vector) = NestedNode(
            "Waypoint Path Name" to pathName.node,
            "Waypoint Path Points" to ArrayNode(pathPoints.mapTo(mutableListOf()) { it.node })
        )

        fun createWorldPolygon(polygonName: String, vararg polygonCoords: Coord) = NestedNode(
            "Name" to polygonName.node,
            "Points" to PointsListNode(polygonCoords.mapTo(mutableListOf()) { it.node })
        )

        fun createWorldPointSet(pointSetName: String, vararg worldPoints: NestedNode) = NestedNode(
            "Name" to pointSetName.node,
            "World Points" to ArrayNode(*worldPoints)
        )

        fun createWorldPoint(pointMagnitude: Float, position: Vector, lookLength: Float, forward: Vector, up: Vector = Vector.up) = NestedNode(
            "World Point Magnitude" to pointMagnitude.node,
            "World Point Basis" to NestedNode(
                "Position" to position.node,
                "LookAt Vector Length" to lookLength.node,
                "Orientation - Cross" to forward.cross(up).node,
                "Orientation - Forward" to forward.node,
                "Orientation - Up" to up.node
            )
        )

        fun createGroup(groupName: String, vararg objectIDs: Int) = NestedNode(
            "Name" to groupName.node,
            "World Object IDs" to ArrayNode(objectIDs.mapTo(mutableListOf()) { it.node })
        )

        fun createWorldRule(ruleName: String, runOnce: Boolean, isActive: Boolean, conditions: ConditionListNode, actions: ActionListNode) = TupleNode(
            "Rule Name" to ruleName.node,
            "Run Once" to runOnce.node,
            "Is Active" to isActive.node,
            "Conditions" to conditions,
            "Actions" to actions
        )

        fun createInitializationWorldRule(ruleName: String, vararg actions: ActionNode) = createWorldRule(
            "Init World $ruleName",
            runOnce = true,
            isActive = true,
            ConditionListNode(
                ConditionNode(
                    "Type" to "World Initialize".node
                )
            ),
            ActionListNode(*actions)
        )

        fun createSkirmishCompleteWorldRule(ruleName: String) = createWorldRule(
            ruleName,
            runOnce = true,
            isActive = true,
            ConditionListNode(
                ConditionNode(
                    "Type" to "Skirmish Game Complete".node
                )
            ),
            ActionListNode(
                createEndGameAction(null, null, true)
            )
        )

        fun createSetupEtheriumCurrentAction(objectID: Int, pathName: String) = ActionNode(
            "Type" to "*State Init* Setup Etherium Current".node,
            "World Object ID" to objectID.node,
            "Etherium Name" to "New $pathName".node,
            "Etherium Path" to pathName.node,
        )

        fun createSetupAsteroidBeltAction(
            groupName: String,
            pathName: String?,
            followMode: FollowMode,
            findClosestPoint: Boolean,
            minVelocity: Float,
            maxVelocity: Float,
            minAngularVelocity: Float,
            maxAngularVelocity: Float
        ) = ActionNode(
            "Type" to "Setup Asteroid Belt".node,
            "Group Name" to groupName.node,
            "Path Name" to (pathName ?: "NO PATH").node,
            "Follow Mode" to followMode.node,
            "Find Closest Point" to findClosestPoint.stringNode(),
            "Velocity Upper m/sec" to maxVelocity.node,
            "Velocity Lower m/sec" to minVelocity.node,
            "Tumble Upper Rads/sec" to maxAngularVelocity.node,
            "Tumble Lower Rads/sec" to minAngularVelocity.node
        )

        fun createSetOwnerAction(groupName: String, ownerName: String) = ActionNode(
            "Type" to "Set Group/Unit Owner".node,
            "Group/Unit Name" to groupName.node,
            "New Owner" to ownerName.node
        )

        fun createSetupWeatherAction(
            objectID: Int,
            weatherName: String,
            polygonName: String,
            nebulaPointSetName: String?,
            solarStormPointSetName: String?,
            meteorShowerPointSetName: String?,
            nebulaEnergyDrain: Boolean,
            nebulaOcclusion: Boolean,
            lightningRechargeTime: Float?,
            stormAngularVelocity: Float,
            stormDamageRate: Float,
            meteorRechargeTime: Float?,
            maxAmbientSoundDistance: Float
        ) = ActionNode(
            "Type" to "*State Init* Setup Nebula".node,
            "World Object ID" to objectID.node,
            "New Nebula Name" to weatherName.node,
            "Polygon Name" to polygonName.node,
            "Lightning On/Off" to (lightningRechargeTime != null).stringNode(),
            "Lightning Blast Recharge Time" to (lightningRechargeTime ?: 0f).node,
            "Meteors On/Off" to (meteorRechargeTime != null).stringNode(),
            "Meteor Strike Recharge Time" to (meteorRechargeTime ?: 0f).node,
            "Nebula Cloud Effect Name" to (if (nebulaPointSetName == null) "EFFECT" else "Nebula").node,
            "Solar Storm Effect Name" to (if (solarStormPointSetName == null) "EFFECT" else "SolarStorm").node,
            "Meteor Shower Effect Name" to (if (meteorShowerPointSetName == null) "EFFECT" else "MeteorStorm").node,
            "Nebula Cloud Point Set Name" to (nebulaPointSetName ?: "POINT SET").node,
            "Solar Storm Point Set Name" to (solarStormPointSetName ?: "POINT SET").node,
            "Meteor Shower Point Set Name" to (meteorShowerPointSetName ?: "POINT SET").node,
            "Rotational Winds On/Off" to (stormAngularVelocity != 0f).stringNode(),
            "Wind Magnitude" to stormAngularVelocity.node,
            "Nebula Cloud Energy Drain On/Off" to nebulaEnergyDrain.stringNode(),
            "Nebula Occlusion On/Off" to nebulaOcclusion.stringNode(),
            "Solar Storm Wind Damage Frequency" to stormDamageRate.node,
            "Ambient sound max distance" to maxAmbientSoundDistance.node
        )

        fun createSetupNebulaAction(
            objectID: Int,
            weatherName: String,
            polygonName: String,
            pointSetName: String,
            energyDrain: Boolean,
            occlusion: Boolean,
            maxAmbientSoundDistance: Float
        ) = createSetupWeatherAction(
            objectID,
            weatherName,
            polygonName,
            pointSetName,
            null,
            null,
            energyDrain,
            occlusion,
            null,
            0f,
            0f,
            null,
            maxAmbientSoundDistance
        )

        fun createSetupSolarStormAction(
            objectID: Int,
            weatherName: String,
            polygonName: String,
            pointSetName: String,
            lightningRechargeTime: Float?,
            angularVelocity: Float,
            damageRate: Float,
            maxAmbientSoundDistance: Float
        ) = createSetupWeatherAction(
            objectID,
            weatherName,
            polygonName,
            null,
            pointSetName,
            null,
            nebulaEnergyDrain = false,
            nebulaOcclusion = false,
            lightningRechargeTime,
            angularVelocity,
            damageRate,
            null,
            maxAmbientSoundDistance
        )

        fun createSetupMeteorShowerAction(
            objectID: Int,
            weatherName: String,
            polygonName: String,
            pointSetName: String,
            rechargeTime: Float?,
            maxAmbientSoundDistance: Float
        ) = createSetupWeatherAction(
            objectID,
            weatherName,
            polygonName,
            null,
            null,
            pointSetName,
            nebulaEnergyDrain = false,
            nebulaOcclusion = false,
            null,
            0f,
            0f,
            rechargeTime,
            maxAmbientSoundDistance
        )

        fun createGroupFollowPathAction(groupName: String, pathName: String, followMode: FollowMode, findClosestPoint: Boolean) = ActionNode(
            "Type" to "Group to follow path".node,
            "Group Name" to groupName.node,
            "Path Name" to pathName.node,
            "Follow Mode" to followMode.node,
            "Find Closest Point" to findClosestPoint.stringNode()
        )

        fun createSetGroupSpeedAction(groupName: String, speed: Int) = ActionNode(
            "Type" to "Set Group Space Objects velocity".node,
            "Group Name" to groupName.node,
            "Velocity" to speed.node
        )

        fun createSetObjectiveTaskStateAction(taskName: String, state: Boolean) = ActionNode(
            "Type" to "Set Objective Task Active State".node,
            "Objective Task" to taskName.node,
            "Active State" to state.stringNode()
        )

        fun createPlayMusicAction(musicID: String, volume: Float, fadeInTime: Float = 0f, fadeOutTime: Float = 0f) = ActionNode(
            "Type" to "Play Music Track".node,
            "File Name" to musicID.node,
            "Crossfade transition" to (fadeInTime > 0f || fadeOutTime > 0f).stringNode(),
            "Fade Out Time ( secs )" to fadeOutTime.node,
            "Fade In Time ( secs )" to fadeInTime.node,
            "New Volume ( 0 to 1 )" to volume.node
        )

        fun createEndGameAction(winMessageID: String?, loseMessageID: String?, showStats: Boolean) = ActionNode(
            "Type" to "End Game".node,
            "Use Custom Message" to (winMessageID != null || loseMessageID != null).stringNode(),
            "Winner - Custom Message String ID" to (winMessageID ?: "GAME STRING").node,
            "Loser - Custom Message String ID" to (loseMessageID ?: "GAME STRING").node,
            "Show Stats Screen" to showStats.stringNode()
        )

        fun createObjectivePoint(pointName: String, position: Vector) = NestedNode(
            "Name" to pointName.node,
            "Position" to position.node
        )

        fun createObjectiveTask(taskName: String, descriptionID: String, isActive: Boolean = false, isCompleted: Boolean = false, isFailed: Boolean = false) = NestedNode(
            "Name" to taskName.node,
            "TextStringID" to descriptionID.node,
            "Active" to isActive.node,
            "Completed" to isCompleted.node,
            "Failed" to isFailed.node
        )

        fun createJournalPage(textID: String, speechID: String, image: String) = NestedNode(
            "TextStringID" to textID.node,
            "SpeechEventFileName" to speechID.node,
            "PictureTexture" to image.node
        )

        fun createMapText(textName: String, stringID: String, position: Vector, visible: Boolean = true) = NestedNode(
            "Name" to textName.node,
            "DisplayedText" to stringID.node,
            "Position" to position.node,
            "Visible" to visible.node
        )

        fun createGameImpl(
            nisBarsOpen: Boolean = false,
            cameraIncrement: Float = -431602080f,
            transitionTargetMode: Int = -842150451,
            minSwoopCameraTargetDistance: Float = 2f,
            maxSwoopCameraTargetDistance: Float = 300f,
            minSwoopCameraDistanceRestriction: Boolean = true,
            maxSwoopCameraDistanceRestriction: Boolean = true
        ) = NestedNode(
            "SelectedWorldObjectID" to (-1).node,
            "GameStats" to NestedNode(
                "PlayerKillInfo" to ArrayNode(
                ),
                "PlayerDockInfo" to ArrayNode(
                ),
                "PlayerLifeBoatSavedInfo" to ArrayNode(
                ),
                "FleetSizeInfo" to ArrayNode(
                ),
                "PlayerDamageInfo" to ArrayNode(
                ),
                "SinglePlayer Points" to 0.node
            ),
            "SinglePlayerVictoryPoints" to 0.node,
            "SinglePlayerCrewList" to ArrayNode(
            ),
            "SinglePlayerArmsList" to ArrayNode(
            ),
            "HUD" to NestedNode(
                "Are NIS Bars Open" to nisBarsOpen.node,
                "Skip Current Speech Event" to false.node,
                "Active Speech Event Timer" to NestedNode(
                    "StartTime" to 0.0.node
                ),
                "Speech Event Active" to false.node,
                "Speech Event Queue" to ArrayNode(
                ),
                "Artificial Pause Between Speech Events Active" to false.node,
                "Artificial Pause Between Speech Events Timer" to NestedNode(
                    "StartTime" to 0.0.node
                ),
                "Request To Close NIS Bars" to false.node
            ),
            "Game Complete" to false.node,
            "Custom Game Complete" to false.node,
            "Game Complete Timer" to NestedNode(
                "StartTime" to 0.0.node
            ),
            "Custom Game Complete String" to "".node,
            "Game Winner Players" to ArrayNode(
            ),
            "User Interface Disabled" to false.node,
            "Is Campaign Game" to false.node,
            "Show Stats Screen" to true.node,
            "Goto Next Level Enabled" to false.node,
            "Next Level World Name" to "".node,
            "Next Level Display Timer" to NestedNode(
                "StartTime" to 0.0.node
            ),
            "Already Created Game" to false.node,
            "Game Camera" to NestedNode(
                "FlyCameraController" to NestedNode(
                    "Camera" to NestedNode(
                        "AspectRatio" to 1f.node,
                        "FieldOfView" to 0.785398f.node,
                        "NearPlane" to 0.5f.node,
                        "FarPlane" to 3000f.node,
                        "UsingResolutionBasedAspectRatio" to true.node,
                        "Orthographic" to false.node,
                        "OrthographicZoom" to 1f.node,
                        "LocalBasis" to NestedNode(
                            "Position" to Vector.zero.node,
                            "LookAt Vector Length" to 1f.node,
                            "Orientation - Cross" to Vector.east.node,
                            "Orientation - Forward" to Vector.north.node,
                            "Orientation - Up" to Vector.up.node
                        )
                    ),
                    "TargetInfo" to NestedNode(
                        "TargetID" to (-1).node,
                        "TargetPoint" to Vector.zero.node
                    ),
                    "GameTimeUpdateTimer" to NestedNode(
                        "StartTime" to 0.0.node
                    ),
                    "NewFocusData" to true.node
                ),
                "SplineCameraController" to NestedNode(
                    "Camera" to NestedNode(
                        "AspectRatio" to 1f.node,
                        "FieldOfView" to 0.785398f.node,
                        "NearPlane" to 0.5f.node,
                        "FarPlane" to 3000f.node,
                        "UsingResolutionBasedAspectRatio" to true.node,
                        "Orthographic" to false.node,
                        "OrthographicZoom" to 1f.node,
                        "LocalBasis" to NestedNode(
                            "Position" to Vector.zero.node,
                            "LookAt Vector Length" to 10f.node,
                            "Orientation - Cross" to Vector.west.node,
                            "Orientation - Forward" to Vector.south.node,
                            "Orientation - Up" to Vector.up.node
                        )
                    ),
                    "TargetInfo" to NestedNode(
                        "TargetID" to (-1).node,
                        "TargetPoint" to Vector.zero.node
                    ),
                    "GameTimeUpdateTimer" to NestedNode(
                        "StartTime" to 0.0.node
                    ),
                    "NewFocusData" to true.node,
                    "PositionInterpolater" to NestedNode(
                        "Position" to Vector.zero.node,
                        "StartPointControlPoint" to Vector.zero.node,
                        "EndPointControlPoint" to Vector.zero.node,
                        "ControlIndex" to 0.node,
                        "Acceleration" to 10f.node,
                        "MaxSpeed" to 30f.node,
                        "Speed" to 0f.node,
                        "Distance" to 0f.node,
                        "Percentage" to cameraIncrement.node,
                        "ControlPointsInfo" to ArrayNode(
                        )
                    ),
                    "PrevTargetPoint" to Vector(0f, -10f, 0f).node,
                    "LockPosition" to false.node,
                    "LockOrientation" to false.node,
                    "ForceOrientation" to false.node,
                    "PercentIncrement" to cameraIncrement.node
                ),
                "SwoopCameraController" to NestedNode(
                    "Camera" to NestedNode(
                        "AspectRatio" to 1f.node,
                        "FieldOfView" to 0.785398f.node,
                        "NearPlane" to 0.5f.node,
                        "FarPlane" to 3000f.node,
                        "UsingResolutionBasedAspectRatio" to true.node,
                        "Orthographic" to false.node,
                        "OrthographicZoom" to 1f.node,
                        "LocalBasis" to NestedNode(
                            "Position" to Vector.zero.node,
                            "LookAt Vector Length" to 10f.node,
                            "Orientation - Cross" to Vector.west.node,
                            "Orientation - Forward" to Vector.south.node,
                            "Orientation - Up" to Vector.up.node
                        )
                    ),
                    "TargetInfo" to NestedNode(
                        "TargetID" to (-1).node,
                        "TargetPoint" to Vector.zero.node
                    ),
                    "GameTimeUpdateTimer" to NestedNode(
                        "StartTime" to 0.0.node
                    ),
                    "NewFocusData" to true.node,
                    "DistanceToTarget" to 20f.node,
                    "DesiredToTarget" to 20f.node,
                    "HorizontalRotation" to 0f.node,
                    "MaxHorizontalRotation" to 0f.node,
                    "MaxDistanceToTarget" to maxSwoopCameraTargetDistance.node,
                    "MinHorizontalRotation" to 0f.node,
                    "MinDistanceToTarget" to minSwoopCameraTargetDistance.node,
                    "RestrictMinHorizontal" to false.node,
                    "RestrictMinDistance" to minSwoopCameraDistanceRestriction.node,
                    "RestrictMaxHorizontal" to false.node,
                    "RestrictMaxDistance" to maxSwoopCameraDistanceRestriction.node,
                    "PercentIncrement" to cameraIncrement.node,
                    "TargetPoint" to Vector.zero.node,
                    "JumpToFocusPoint" to false.node
                ),
                "TransitionCameraController" to NestedNode(
                    "Camera" to NestedNode(
                        "AspectRatio" to 1f.node,
                        "FieldOfView" to 0.785398f.node,
                        "NearPlane" to 0.5f.node,
                        "FarPlane" to 3000f.node,
                        "UsingResolutionBasedAspectRatio" to true.node,
                        "Orthographic" to false.node,
                        "OrthographicZoom" to 1f.node,
                        "LocalBasis" to NestedNode(
                            "Position" to Vector.zero.node,
                            "LookAt Vector Length" to 10f.node,
                            "Orientation - Cross" to Vector.west.node,
                            "Orientation - Forward" to Vector.south.node,
                            "Orientation - Up" to Vector.up.node
                        )
                    ),
                    "TargetInfo" to NestedNode(
                        "TargetID" to (-1).node,
                        "TargetPoint" to Vector.zero.node
                    ),
                    "GameTimeUpdateTimer" to NestedNode(
                        "StartTime" to 0.0.node
                    ),
                    "NewFocusData" to true.node,
                    "PositionInterpolater" to NestedNode(
                        "Position" to Vector.zero.node,
                        "StartPointControlPoint" to Vector.zero.node,
                        "EndPointControlPoint" to Vector.zero.node,
                        "ControlIndex" to 0.node,
                        "Acceleration" to 10f.node,
                        "MaxSpeed" to 30f.node,
                        "Speed" to 0f.node,
                        "Distance" to 0f.node,
                        "Percentage" to cameraIncrement.node,
                        "ControlPointsInfo" to ArrayNode(
                        )
                    ),
                    "PrevTargetPoint" to Vector(0f, -10f, 0f).node,
                    "LockPosition" to false.node,
                    "LockOrientation" to false.node,
                    "ForceOrientation" to false.node,
                    "PercentIncrement" to cameraIncrement.node
                ),
                "AttachCameraController" to NestedNode(
                    "Camera" to NestedNode(
                        "AspectRatio" to 1f.node,
                        "FieldOfView" to 0.785398f.node,
                        "NearPlane" to 0.5f.node,
                        "FarPlane" to 3000f.node,
                        "UsingResolutionBasedAspectRatio" to true.node,
                        "Orthographic" to false.node,
                        "OrthographicZoom" to 1f.node,
                        "LocalBasis" to NestedNode(
                            "Position" to Vector.zero.node,
                            "LookAt Vector Length" to 10f.node,
                            "Orientation - Cross" to Vector.west.node,
                            "Orientation - Forward" to Vector.south.node,
                            "Orientation - Up" to Vector.up.node
                        )
                    ),
                    "TargetInfo" to NestedNode(
                        "TargetID" to (-1).node,
                        "TargetPoint" to Vector.zero.node
                    ),
                    "GameTimeUpdateTimer" to NestedNode(
                        "StartTime" to 0.0.node
                    ),
                    "NewFocusData" to true.node,
                    "AttachedObjectID" to (-1).node,
                    "Distance" to 1f.node,
                    "AngleYZ" to 0f.node,
                    "AngleXY" to 0f.node,
                    "LockOrientation" to false.node,
                    "PercentIncrement" to cameraIncrement.node
                ),
                "CameraMode" to 0.node,
                "TargetModeForTransition" to transitionTargetMode.node,
                "InTransitionMode" to false.node,
                "TurnOffNISWhenDoneTransition" to true.node,
                "TransitionStartPoint" to Vector.zero.node,
                "LastTargetPosition" to Vector.zero.node,
                "LastCameraPosition" to Vector.zero.node
            ),
            "SoundPlayer" to NestedNode(
                "CurrentMix" to MixNode(
                    "0 PercentageVolume" to 1f.node,
                    "1 PercentageVolume" to 1f.node,
                    "2 PercentageVolume" to 1f.node,
                    "3 PercentageVolume" to 1f.node,
                    "4 PercentageVolume" to 1f.node,
                    "5 PercentageVolume" to 1f.node
                ),
                "UserMix" to MixNode(
                    "0 PercentageVolume" to 1f.node,
                    "1 PercentageVolume" to 1f.node,
                    "2 PercentageVolume" to 1f.node,
                    "3 PercentageVolume" to 1f.node,
                    "4 PercentageVolume" to 1f.node,
                    "5 PercentageVolume" to 1f.node
                )
            ),
            "MusicPlayer" to NestedNode(
                "Is Playing" to false.node
            ),
            "TPSound" to NestedNode(
                "MusicVolume" to 1f.node,
                "SpeechOcurring" to false.node,
                "CreatedSoundData" to ArrayNode(
                ),
                "Abyss Volume" to 0f.node,
                "Inside Abyss" to false.node,
            ),
            "Radar Active" to true.node,
            "Crew Speech Silent State" to false.node
        )
    }

    constructor(game: Game, name: String) : this(game, DefinitionParser(getFile(game, "World/$name")).result())

    fun build() = root.build()

    fun export(location: String) = getFile(game, location).writeText(build().toString())

    fun addPlayerListElement(playerListElement: NestedNode) {
        root.get<NestedNode>("World")!!.get<PlayerListNode>("PlayerList")!!.add(playerListElement)
    }

    fun addPlayer(playerName: String, teamIndex: Int, color: Color, start: Vector, direction: Vector, faction: Faction, formation: Formation) {
        val worldInfo = root.get<NestedNode>("WorldInfo")!!

        worldInfo.get<IntNode>("Number of Players")!!.value += 1
        worldInfo.get<FlatNode>("Players")!!.add(createPlayerInfoTuple(playerName, teamIndex))

        addPlayerListElement(createPlayerListElement(playerName, teamIndex, color, true, start, direction, faction, formation))
    }

    fun addWorldObject(typeID: String, ownerName: String?, groupName: String?, position: Vector, rotation: Matrix = Matrix.identity): Int {
        val world = root.get<NestedNode>("World")!!
        val nextID = world.get<IntNode>("NextID")!!
        val objectID = nextID.value

        nextID.value += 1
        root.get<NestedNode>("WorldInfo")!!.get<IntNode>("Object Count")!!.value += 1

        val ownerID = if (ownerName == null) -1 else world.get<PlayerListNode>("PlayerList")!!.indexOf { it is NestedNode && it.get<StringNode>("Name")?.value == ownerName }
        world.get<WorldObjectsListNode>("WorldObjects")!!.add(createWorldObject(objectID, typeID, ownerID, position, rotation))

        if (groupName != null) {
            val groupsList = world.get<NestedNode>("GameSpecific")!!.get<GroupsListNode>("Num Groups")!!
            val predicate: (Node) -> Boolean = { it is NestedNode && it.get<StringNode>("Name")?.value == groupName }
            groupsList.addIfAbsent(createGroup(groupName), predicate)
            (groupsList.firstOf(predicate) as NestedNode).get<ArrayNode>("World Object IDs")!!.add(objectID.node)
        }

        return objectID
    }

    fun addWaypointPath(waypointPath: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<ArrayNode>("Waypoint Path Info Vector")!!.add(waypointPath)
    }

    fun addWorldPolygon(worldPolygon: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<ArrayNode>("World Polygons Vectors")!!.add(worldPolygon)
    }

    fun addWorldPointSet(worldPointSet: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<ArrayNode>("World Point Sets Vector")!!.add(worldPointSet)
    }

    fun addGroup(group: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<GroupsListNode>("Num Groups")!!.add(group)
    }

    fun addWorldRule(worldRule: TupleNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<NestedNode>("World Rules")!!.get<RuleListNode>("Rule List")!!.add(worldRule)
    }

    fun addObjectivePoint(objectivePoint: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<NestedNode>("Objective System")!!.get<ArrayNode>("Objective Point Info")!!.add(objectivePoint)
    }

    fun addObjectiveTask(objectiveTask: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<NestedNode>("Objective System")!!.get<ArrayNode>("Objective Task Array")!!.add(objectiveTask)
    }

    fun addJournalPage(journalPage: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<NestedNode>("Journal Entry")!!.get<ArrayNode>("Page Info")!!.add(journalPage)
    }

    fun setJournalTitle(titleID: String) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<NestedNode>("Journal Entry")!!["Title StringID"] = titleID.node
    }

    fun addMapText(mapText: NestedNode) {
        root.get<NestedNode>("World")!!.get<NestedNode>("GameSpecific")!!.get<NestedNode>("MapText System")!!.get<ArrayNode>("MapText Point Info")!!.add(mapText)
    }

    override fun toString() = root.toString()
}
