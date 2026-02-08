package com.mon.gametracker.features.game.domain.achievement

import java.time.LocalDate

data class Achievement(
    val id: AchievementId,
    val gameId: String,
    val description: String,
    val isCompleted: Boolean = false,
    val completionDate: LocalDate? = null,
    val guideURL: String
) {
    fun toggleCompletion() : Achievement {
        return if (!isCompleted){
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

@JvmInline
value class AchievementId(val value: String)