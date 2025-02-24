package com.fdlr.testemb.coreds.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ContainerAnimation(
    content: @Composable () -> Unit
) {
    val isContainerVisible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isContainerVisible.value = true
    }

    AnimatedVisibility(
        visible = isContainerVisible.value,
        enter = fadeIn(animationSpec = tween(durationMillis = 700)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        content()
    }
}