package dodd.terran

import dodd.terran.translation.DefinitionParser
import dodd.terran.translation.RootNode

class WorldObject(game: Game, name: String) {

    private val root: RootNode

    init {
        val file = game.resource("WorldObjects/$name.wot")
        root = DefinitionParser(file).result()
    }
}
