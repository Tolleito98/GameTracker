package com.mon.gametracker.features.game.data

import android.util.Log
import com.mon.gametracker.features.game.domain.achievement.Achievement
import com.mon.gametracker.features.game.domain.achievement.AchievementId
import com.mon.gametracker.features.game.domain.achievement.AchievementKey
import com.mon.gametracker.features.game.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.domain.game.GameId
import kotlinx.coroutines.delay
import java.time.LocalDate
import javax.inject.Inject

class MockAchievementRepositoryImpl
@Inject constructor() : AchievementRepository {

    private val achievementsGame1 = mutableListOf(
        Achievement(
            key = AchievementKey(
                gameId = GameId("1"),
                achievementId = AchievementId("1")
            ),
            name = "Achievement 1",
            description = "example description game 1",
            isCompleted = true,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("1"),
                achievementId = AchievementId("2")
            ),
            name = "Achievement 2",
            description = "example description game 1",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("1"),
                achievementId = AchievementId("3")
            ),
            name = "Achievement 3",
            description = "example description game 1",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("1"),
                achievementId = AchievementId("4")
            ),
            name = "Achievement 4",
            description = "example description game 1",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("1"),
                achievementId = AchievementId("5")
            ),
            name = "Achievement 5",
            description = "example description game 1",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
    )

    private val achievementsGame2 = mutableListOf(
        Achievement(
            key = AchievementKey(
                gameId = GameId("2"),
                achievementId = AchievementId("1")
            ),
            name = "Achievement 1",
            description = "example description game 2",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("2"),
                achievementId = AchievementId("2")
            ),
            name = "Achievement 2",
            description = "example description game 2",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("2"),
                achievementId = AchievementId("3")
            ),
            name = "Achievement 3",
            description = "example description game 2",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("2"),
                achievementId = AchievementId("4")
            ),
            name = "Achievement 4",
            description = "example description game 2",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
        Achievement(
            key = AchievementKey(
                gameId = GameId("2"),
                achievementId = AchievementId("5")
            ),
            name = "Achievement 5",
            description = "example description game 2",
            isCompleted = false,
            completionDate = null,
            guideURL = "example.com"
        ),
    )

    private val achievementsByGame = mutableMapOf(
        GameId("1") to achievementsGame1,
        GameId("2") to achievementsGame2
    )


    override suspend fun getAchievements(gameId: GameId): List<Achievement> {
        delay(1000)
        return achievementsByGame[gameId]?.toList() ?: emptyList()
    }


    override suspend fun getAchievementById(
        gameId: GameId,
        achievementId: AchievementId
    ): Achievement? {
        return achievementsByGame[gameId]?.find {
            it.key.achievementId == achievementId
        }
    }

    override suspend fun setCompleted(
        gameId: GameId,
        achievementId: AchievementId,
        isCompleted: Boolean
    ): Boolean {
        val achievements = achievementsByGame[gameId]?: return false
        val achievement = achievements.find { it.key.achievementId == achievementId }?: return false
        val index = achievements.indexOf(achievement)
        achievements[index] = achievement.copy(
            isCompleted = isCompleted,
            completionDate = if (isCompleted) LocalDate.now() else null
        )
        return true
    }
}

