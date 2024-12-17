package contas.presenter.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import contas.api.Account
import contas.api.ServerAPI
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun DashboardPrincipal() {
    val server = ServerAPI()
    var ativarTela by remember { mutableStateOf(false) }
    var accountList by remember {
        mutableStateOf(
            mutableListOf<Account>()
        )
    }
    LaunchedEffect(Unit) {
        server.BuscarContas().collect { accountServer ->
            accountList = accountServer
            ativarTela = true
        }
    }
    Scaffold {
        Column(modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp))
        {
            Text("Bem vindo Gabriel")
            Text("Controle de Contas")
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                repeat(5) {
                    DashboardCard()
                }
            }

                DropDown()
                DropDown()
            Column(modifier = Modifier.fillMaxWidth().verticalScroll(state = rememberScrollState()))
            {
                //            repeat(10){
                //                CardConta()
                //            }
                repeat(accountList.size) {
                    CardConta(accountList[it])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDown() {
    val list = listOf("2023", "2024", "2025")
    var selected by remember {
        mutableStateOf(list[0])
    }
    var isExpended by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth().padding(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ExposedDropdownMenuBox(expanded = isExpended,
            onExpandedChange = { isExpended = !isExpended }) {
            TextField(value = selected, onValueChange = {}, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpended)
            })
            ExposedDropdownMenu(expanded = isExpended, onDismissRequest = { isExpended = false }) {
                list.forEachIndexed { index, value ->

                    DropdownMenuItem(content ={Text(value)}, onClick = {
                        selected = list[index]
                        isExpended = false
                    })


                }
            }
        }
        Text("Item selecionado ${selected}")
    }
}

@Composable
fun CardConta(account: Account) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier

                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Green)
        ) {

        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(modifier = Modifier.fillMaxWidth())
        {
            Text("${account.description}")
            Text("Compra: 01/01/2025")
            Text("R$: 1.585,69")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardCard() {
    Card(
        modifier = Modifier.padding(20.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Color.DarkGray,
                spotColor = Color.DarkGray,
                shape = RoundedCornerShape(16.dp),
            ),
        onClick = {}
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth())
            {
                Column(modifier = Modifier.fillMaxWidth())
                {
                    Row(modifier = Modifier.fillMaxWidth())
                    {
                        Text("Nubank", modifier = Modifier)
                        VerMais()
                    }

                    Text("Próximo vencimento:01/01/2025")
                }
            }
            Text("R$ 7.500,68")
            Text("Valores podem sofrer alteração")
        }
    }

}

@Composable
fun VerMais(modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(3) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
    }
}