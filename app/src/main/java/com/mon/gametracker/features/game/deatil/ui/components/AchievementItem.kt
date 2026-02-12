package com.mon.gametracker.features.game.deatil.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mon.gametracker.features.game.domain.achievement.Achievement

@Composable
fun AchievementItem(
    achievement: Achievement,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    AchievementItemContent(
        achievement = achievement,
        expanded = expanded,
        onExpandClick = {
            expanded = !expanded
        },
        onToggle = onToggle,
        modifier = modifier
    )
}


@Composable
private fun AchievementItemContent(
    achievement: Achievement,
    expanded: Boolean,
    onExpandClick: () -> Unit,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = achievement.name,
                    style = MaterialTheme.typography.titleMedium,
                    color =
                        if (achievement.isCompleted)
                            MaterialTheme.colorScheme.primary
                        else Color.Unspecified,
                    modifier = Modifier.weight(1F)
                )

                IconButton(
                    onClick = onExpandClick
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand",
                        modifier = Modifier.rotate(if (expanded) 180f else 0f)
                    )
                }

                Checkbox(
                    checked = achievement.isCompleted,
                    onCheckedChange = { onToggle(it) }
                )

            }

            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                AchievementInfo(
                    achievement
                )

            }
        }
    }
}

@Composable
private fun AchievementInfo(
    achievement: Achievement
){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(

        ) {
            Text(
                text = achievement.description,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "guide: Url a la guía",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
