package `in`.crypto.core_ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

@Composable
fun LogCompositions(tag: String) {
    Log.d("CPulse", "Executed -> $tag")
//    val count = remember { mutableIntStateOf(0) }
//
//    SideEffect {
//        count.intValue++
//        Log.d("CPulse", "$tag recomposed ${count.intValue} times")
//    }
}