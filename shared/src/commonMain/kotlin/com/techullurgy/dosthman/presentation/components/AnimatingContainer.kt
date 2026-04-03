package com.techullurgy.dosthman.presentation.components

import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch

private enum class AnimationAnchors {
    Open, Closed
}

@Composable
internal fun AnimatingContainer(
    isOpen: Boolean,
    onIsOpenChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    var drawerWidth by remember { mutableFloatStateOf(0f) }

    val anchorState = retain(drawerWidth) {
        AnchoredDraggableState(
            initialValue = AnimationAnchors.Closed,
            anchors = DraggableAnchors {
                AnimationAnchors.Closed at 0f
                AnimationAnchors.Open at (drawerWidth * 0.65f)
            }
        )
    }

    LaunchedEffect(isOpen) {
        val desiredTarget = if(isOpen) {
            AnimationAnchors.Open
        } else {
            AnimationAnchors.Closed
        }

        if(anchorState.settledValue != desiredTarget) {
            anchorState.animateTo(desiredTarget)
        }
    }

    LaunchedEffect(anchorState.settledValue) {
        if(anchorState.settledValue == AnimationAnchors.Closed) {
            onIsOpenChange(false)
        } else {
            onIsOpenChange(true)
        }
    }

    Box(
        modifier = Modifier
            .onSizeChanged {
                drawerWidth = it.width.toFloat()
            }
            .then(modifier)
            .graphicsLayer {
                translationX = anchorState.requireOffset()

                val scale = lerp(1f, 0.8f, anchorState.requireOffset() / drawerWidth)
                scaleX = scale
                scaleY = scale

                val percent = lerp(0, 20, anchorState.requireOffset() / drawerWidth)
                shape = RoundedCornerShape(percent)
                clip = true
            }
            .anchoredDraggable(
                state = anchorState,
                orientation = Orientation.Horizontal,
                flingBehavior = AnchoredDraggableDefaults.flingBehavior(
                    state = anchorState,
                    animationSpec = spring()
                ),
            )
    ) {
        content.invoke()

        if(anchorState.settledValue == AnimationAnchors.Open) {
            Box(
                Modifier.matchParentSize()
                    .clickable(
                        onClick = {
                            scope.launch {
                                anchorState.animateTo(AnimationAnchors.Closed)
                            }
                        }
                    )
            )
        }
    }
}
