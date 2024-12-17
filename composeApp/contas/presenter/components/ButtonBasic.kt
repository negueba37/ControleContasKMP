package contas.presenter.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonBasic(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label:String = "Button Basic"
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(label)
    }
}