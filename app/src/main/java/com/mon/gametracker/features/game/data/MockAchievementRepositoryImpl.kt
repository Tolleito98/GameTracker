package com.mon.gametracker.features.game.data

import com.mon.gametracker.features.game.domain.achievement.Achievement
import com.mon.gametracker.features.game.domain.achievement.AchievementId
import com.mon.gametracker.features.game.domain.achievement.AchievementKey
import com.mon.gametracker.features.game.domain.achievement.AchievementRepository
import com.mon.gametracker.features.game.domain.game.GameId
import javax.inject.Inject

class MockAchievementRepositoryImpl
@Inject constructor() : AchievementRepository {

    private val achievementsGame1 = listOf(
        Achievement(
            key = AchievementKey(
                gameId = GameId("1"),
                achievementId = AchievementId("1")
            ),
            name = "Achievement 1",
            description = "example description game 1",
            isCompleted = false,
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

    private val achievementsGame2 = listOf(
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

    private val achievementsByGame = mapOf(
        GameId("1") to achievementsGame1,
        GameId("2") to achievementsGame2
    )


    override suspend fun getAchievements(gameId: GameId): List<Achievement> {
        return achievementsByGame[gameId] ?: emptyList()
    }


    override suspend fun getAchievementById(
        gameId: GameId,
        achievementId: AchievementId
    ): Achievement? {
        return achievementsByGame[gameId]?.find {
            it.key.achievementId == achievementId
        }
    }
}

