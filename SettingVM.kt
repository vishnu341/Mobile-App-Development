package com.example.myfinalappimplementation.components

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfinalappimplementation.ui.theme.darkGray
import com.example.myfinalappimplementation.ui.theme.white
import kotlin.math.*

@Composable


fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    initialValue:Int,
    primaryColor: Color,
    secondaryColor: Color,
    minValue:Int = 0,
    maxValue:Int ,
    circleRadius:Float,
    onPositionChange:(Int)->Unit
) {
    val maximumValue :Int=maxValue

    var maxValue by remember {
        mutableStateOf(maxValue) }
    var circleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var positionValue by remember {
        mutableStateOf(initialValue)
    }
    val percent = if (maximumValue > 0) {
        ((initialValue.toFloat() / maximumValue.toFloat() * 100).toInt())

    } else {
        0
    }

    val sweepAngleTest= ((360f / maximumValue) * initialValue.toFloat())

    Log.d("CustomCircularProgressIndicator", "initialValue: $initialValue, maxValue: $maximumValue,")
    Log.d("CustomCircularProgressIndicator", "percent: $percent,")
    Log.d("CustomCircularProgressIndicator", "sweepAngle: $sweepAngleTest")

    Column(modifier = Modifier
        .padding(5.dp)
        , verticalArrangement = Arrangement.Top) {
       /* Box(
            modifier = Modifier
                //.fillMaxSize()
                .background(white),
            contentAlignment = Alignment.TopCenter

        )
        {*/

            Canvas(
                modifier = Modifier
                    .padding(10.dp)
                    //.fillMaxWidth()
                    .aspectRatio(1.3f)
            ) {
                val width = size.width
                val height = size.height
                val circleThickness = width / 25f
                circleCenter = Offset(x = width / 2f, y = height / 2f)





                drawCircle(
                    style = Stroke(
                        width = circleThickness
                    ),
                    color = secondaryColor,
                    radius = circleRadius,
                    center = circleCenter
                )

                drawArc(
                    color = primaryColor,
                    startAngle = 90f,
                    sweepAngle = (360f / maximumValue) * initialValue.toFloat(),
                    style = Stroke(
                        width = circleThickness,
                        cap = StrokeCap.Round
                    ),

                    useCenter = false,
                    size = Size(
                        width = circleRadius * 2f,
                        height = circleRadius * 2f
                    ),
                    topLeft = Offset(
                        (width - circleRadius * 2f) / 2f,
                        (height - circleRadius * 2f) / 2f
                    )

                )

                val outerRadius = circleRadius + circleThickness / 2f
                val gap = 15f
                for (i in 0..(maximumValue - minValue)) {
                    val color =
                        if (i < positionValue - minValue) primaryColor else primaryColor.copy(alpha = 0.3f)
                    val angleInDegrees = i * 360f / (maximumValue - minValue).toFloat()
                    val angleInRad = angleInDegrees * PI / 180f + PI / 2f

                    val yGapAdjustment = cos(angleInDegrees * PI / 180f) * gap
                    val xGapAdjustment = -sin(angleInDegrees * PI / 180f) * gap

                    val start = Offset(
                        x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                        y = (outerRadius * sin(angleInRad) + circleCenter.y + yGapAdjustment).toFloat()
                    )

                    val end = Offset(
                        x = (outerRadius * cos(angleInRad) + circleCenter.x + xGapAdjustment).toFloat(),
                        y = (outerRadius * sin(angleInRad) + circleThickness + circleCenter.y + yGapAdjustment).toFloat()
                    )


                }

                drawContext.canvas.nativeCanvas.apply {
                    drawIntoCanvas {
                        drawText(
                            "$percent %",
                            circleCenter.x,
                            circleCenter.y + 45.dp.toPx() / 3f,
                            Paint().apply {
                                textSize = 26.sp.toPx()
                                textAlign = Paint.Align.CENTER
                                color = darkGray.toArgb()
                                isFakeBoldText = true
                            }
                        )
                    }
                }

            }



    }

}
