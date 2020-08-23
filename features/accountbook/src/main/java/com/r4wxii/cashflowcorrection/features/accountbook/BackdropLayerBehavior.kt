package com.r4wxii.cashflowcorrection.features.accountbook

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BackdropLayerBehavior<V: View>(context: Context, attributeSet: AttributeSet?) : BottomSheetBehavior<V>(context, attributeSet) {
    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {}

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean = false

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        event: MotionEvent
    ): Boolean = false

    override fun onTouchEvent(parent: CoordinatorLayout, child: V, event: MotionEvent): Boolean = false
}