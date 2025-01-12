package com.flyinpancake.dndspells.ui.logic

import com.flyinpancake.dndspells.model.DndCharacter
import com.flyinpancake.dndspells.model.DndClass
import com.flyinpancake.dndspells.model.Spell

data class SpellFilter (
    val nameFilter: String = "",
    val levelFilter: Float = 0f,
    val levelFilterEnabled: Boolean = false,
    val componentFilter: String = "",
    val componentFilterEnabled: Boolean = false,
    val classFilter: DndClass,
    val classFilterEnabled: Boolean = false,
) {
    fun filterSpells(spells: List<Spell>): List<Spell> {
        return spells.filter { spell ->
            nameFilter.isBlank() || spell.name.contains(nameFilter.trim(), ignoreCase = true)
        }.filter { spell ->
            !levelFilterEnabled || spell.level == levelFilter.toInt()
        }.filter { spell ->
            !componentFilterEnabled || componentFilter.all { char -> spell.components.contains(char)}
        }.filter { spell ->
            !classFilterEnabled || spell.classes.split(",").any {
                it.contains(classFilter.legibleName.split(" ")[0])
            }
        }
    }
}