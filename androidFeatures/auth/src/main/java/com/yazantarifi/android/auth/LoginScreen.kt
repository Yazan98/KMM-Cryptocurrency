package com.yazantarifi.android.auth


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.yazantarifi.android.auth.composables.LoginEmailTextField
import com.yazantarifi.android.auth.composables.LoginPasswordTextField
import com.yazantarifi.android.auth.viewModel.AuthAction
import com.yazantarifi.android.auth.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.yazantarifi.android.core.BaseScreen
import com.yazantarifi.android.core.composables.ApplicationLoadingComposable
import com.yazantarifi.android.core.composables.ApplicationToolbar
import com.yazantarifi.android.core.navigation.CoinaScreenNavigation
import com.yazantarifi.android.core.ui.ApplicationColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreen : BaseScreen() {

    companion object {
        fun startScreen(context: ComponentActivity) {
            context.startActivity(Intent(context, LoginScreen::class.java))
            context.finish()
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Composable
    override fun OnScreenContent(savedInstanceState: Bundle?) {
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val viewModel: AuthViewModel = hiltViewModel()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ApplicationToolbar(
                    stringResource(id = R.string.app_name),
                    true,
                    this
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                if (viewModel.loginStateListener.value) {
                    CoinaScreenNavigation.startScreen(this@LoginScreen, CoinaScreenNavigation.HOME_SCREEN)
                    finish()
                }

                LaunchedEffect(viewModel.loginErrorMessageListener.value) {
                    if (viewModel.loginErrorMessageListener.value.isNotEmpty()) {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(viewModel.loginErrorMessageListener.value)
                            viewModel.loginErrorMessageListener.value = ""
                        }
                    }
                }

                if (viewModel.loginLoadingState.value) {
                    ApplicationLoadingComposable(message = stringResource(id = R.string.loading_message))
                } else {
                    Column(
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = stringResource(id = R.string.welcome_back),
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = stringResource(id = R.string.welcome_back_sub),
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )

                        Column(modifier = Modifier.padding(20.dp)) {
                            Spacer(modifier = Modifier.height(10.dp))
                            LoginEmailTextField(viewModel)
                            Spacer(modifier = Modifier.height(10.dp))
                            LoginPasswordTextField(viewModel)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.forget_password),
                                textAlign = TextAlign.End
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            OutlinedButton(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                onClick = { onLoginButtonClicked(
                                    viewModel,
                                    this@LoginScreen,
                                    coroutineScope
                                ) },
                                shape = RoundedCornerShape(15),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.White,
                                    backgroundColor = ApplicationColors.getApplicationColor()
                                )
                            ) {
                                Text(text = stringResource(id = R.string.login))
                            }

                            Spacer(modifier = Modifier.height(30.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Divider(
                                    color = ApplicationColors.getOutlineColor(),
                                    thickness = 1.dp,
                                    modifier = Modifier.width(75.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = stringResource(id = R.string.continue_with),
                                    modifier = Modifier.wrapContentWidth()
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Divider(
                                    color = ApplicationColors.getOutlineColor(),
                                    thickness = 1.dp,
                                    modifier = Modifier.width(75.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                FloatingActionButton(
                                    onClick = {

                                    },
                                    backgroundColor = ApplicationColors.getScreenBackgroundColor(),
                                    contentColor = Color.White
                                ) {
                                    AsyncImage(
                                        model = R.drawable.facebook,
                                        contentDescription = "Login By Facebook",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(2.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(35.dp))

                                FloatingActionButton(
                                    onClick = {

                                    },
                                    backgroundColor = ApplicationColors.getScreenBackgroundColor(),
                                    contentColor = Color.White
                                ) {
                                    AsyncImage(
                                        model = R.drawable.google,
                                        contentDescription = "Login By Facebook",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(40.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = stringResource(id = R.string.not_a_member))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = stringResource(id = R.string.register_now), color = Color(0xFF3a75ea))
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun onLoginButtonClicked(
    viewModel: AuthViewModel,
    context: ComponentActivity,
    coroutineScope: CoroutineScope
) {
    viewModel.executeAction(AuthAction.LoginAction)
}