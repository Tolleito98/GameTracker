package com.mon.gametracker.features.game.core.data.mappers

import com.mon.gametracker.features.game.core.data.dto.AchievementDTO
import com.mon.gametracker.features.game.core.domain.achievement.Achievement
import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.achievement.AchievementKey
import com.mon.gametracker.features.game.core.domain.game.GameId

fun AchievementDTO.toDomain(gameId: GameId): Achievement {
    return Achievement(
        key = AchievementKey(
            gameId = gameId,
            achievementId = AchievementId(this.id.toString())
        ),
        name = this.name,
        description = this.description ?: "",
        isCompleted = false,
        completionDate = null,
        guideURL = "guide url "
    )
}