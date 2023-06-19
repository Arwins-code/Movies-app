package com.arwin.mymovieapps.screens.account

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.arwin.mymovieapps.R
import com.arwin.mymovieapps.model.AccountItem
import com.arwin.mymovieapps.model.accountItems
import com.arwin.mymovieapps.screens.destinations.AboutScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    navController: NavController
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val showSocialsDialog = remember { mutableStateOf(false) }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.movietextlogo),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .padding(top = 16.dp, bottom = 16.dp)
            )
            LazyColumn(
                contentPadding = innerPadding,
                content = {
                    items(accountItems) { item ->
                        AccountItems(
                            accountItem = item,
                            onClick = {
                                when (item.title) {
                                    "About" -> {
                                        navigator.navigate(AboutScreenDestination)
                                    }
                                    "Rate Us" -> {
//                                    val rateIntent = Intent(
//                                        Intent.ACTION_VIEW,
//                                        Uri.parse("market://details?id=" + context.packageName)
//                                    )
//                                    startActivity(context, rateIntent, null)
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "Under Maintenance"
                                            )
                                        }
                                    }
                                    "Share" -> {
                                        scope.launch {
                                            snackbarHostState.showSnackbar(
                                                "Under Maintenance"
                                            )
                                        }
                                    }
                                    "Stay connected" -> {
                                        showSocialsDialog.value = true
                                    }
                                }
                            }
                        )
                    }
                }
            )
            if (showSocialsDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onDismissRequest.
                        showSocialsDialog.value = false
                    },
                    title = {
                        Text(text = "Stay Connected")
                    },
                    text = {
                        Column {
                            val intent = Intent(Intent.ACTION_VIEW)
                            Divider()
                            DialogsItems(
                                icon = Icons.Filled.Person,
                                title = "Linkedin",
                                onClick = {
                                    intent.data =
                                        Uri.parse("https://www.linkedin.com/in/arwinsyah-putra-072b37153/")
                                    ContextCompat.startActivity(context, intent, null)
                                }
                            )
                            DialogsItems(
                                icon = Icons.Filled.Person,
                                title = "Github",
                                onClick = {
                                    intent.data =
                                        Uri.parse("https://github.com/Arwins-code")
                                    ContextCompat.startActivity(context, intent, null)
                                }
                            )
                            DialogsItems(
                                icon = Icons.Filled.Person,
                                title = "Twitter",
                                onClick = {
                                    intent.data =
                                        Uri.parse("https://twitter.com/Arwins_Putra")
                                    ContextCompat.startActivity(context, intent, null)
                                }
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showSocialsDialog.value = false
                            }
                        ) {
                            Text("Under Maintenance")
                        }
                    },
                )
            }
        }

    }
}

@Composable
fun AccountItems(
    accountItem: AccountItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = accountItem.icon,
            contentDescription = accountItem.title,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(text = accountItem.title)
    }
    Divider()
}

@Composable
fun DialogsItems(
    onClick: () -> Unit,
    icon: ImageVector,
    title: String
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(top = 16.dp)
            .fillMaxWidth()

    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(text = title)
    }
}