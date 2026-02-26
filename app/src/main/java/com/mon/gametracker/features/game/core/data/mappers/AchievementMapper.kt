package com.mon.gametracker.features.game.core.data.mappers

import com.mon.gametracker.features.game.core.data.dto.AchievementDTO
import com.mon.gametracker.features.game.core.data.local.achievement.AchievementEntity
import com.mon.gametracker.features.game.core.domain.achievement.Achievement
import com.mon.gametracker.features.game.core.domain.achievement.AchievementId
import com.mon.gametracker.features.game.core.domain.achievement.AchievementKey
import com.mon.gametracker.features.game.core.domain.game.GameId
import java.time.LocalDate

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

//##################ROOM##################//
fun Achievement.toEntity(gameId: GameId): AchievementEntity {
    return AchievementEntity(
        id = this.key.achievementId.value,
        gameId = gameId.value,
        name = this.name,
        description = this.description,
        isCompleted = this.isCompleted,
        completionDate = this.completionDate?.toString()
    )
}

fun AchievementEntity.toDomain(): Achievement {
    return Achievement(
        name = this.name,
        description = this.description,
        isCompleted = this.isCompleted,
        key = AchievementKey(
            gameId = GameId(this.gameId),
            achievementId = AchievementId(this.id)
        ),
        completionDate = this.completionDate?.let { LocalDate.parse(it) },
        guideURL = "Example guide url",
    )
}