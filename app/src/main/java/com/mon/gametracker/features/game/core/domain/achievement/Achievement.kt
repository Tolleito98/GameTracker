package com.mon.gametracker.features.game.core.domain.achievement

import com.mon.gametracker.features.game.core.domain.game.GameId
import java.time.LocalDate

data class Achievement(
    val key: AchievementKey,
    val name: String,
    val description: String,
    val isCompleted: Boolean = false,
    val completionDate: LocalDate? = null,
    val guideURL: String
) {
    fun toggleCompletion(): Achievement {
        return if (!isCompleted) {
            this.copy(
                isCompleted = true,
                completionDate = LocalDate.now()
            )
        } else {
            this.copy(
                isCompleted = false,
                completionDate = null
            )
        }
    }
}

data class AchievementKey(
    val gameId: GameId,
    val achievementId: AchievementId
)

@JvmInline
value class AchievementId(val value: String)