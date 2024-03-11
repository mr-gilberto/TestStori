package com.gilberto.test.features.home.description

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gilberto.domain.common.base.toStringOrEmpty
import com.gilberto.domain.models.MovementEntity
import com.gilberto.test.R
import com.gilberto.test.theme.AppTheme
import com.gilberto.test.theme.Colors
import com.gilberto.test.util.convertToDateFormat

@Preview
@Composable
fun MovementDetailPreview() {
    AppTheme {
    }
}

@Composable
fun MovementDetail(
    movement: MovementEntity?,
    onBackPressed: () -> Unit = {},
) {
    Scaffold(topBar = { ToolBar(onBackPressed) }, content = { innerPadding ->
        MovementDetailsScreen(
            innerPadding,
            movement,
        )
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(onBackPressed: () -> Unit) {
    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    stringResource(R.string.app_name),
                    color = Colors.ColorPrimary,
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onBackPressed.invoke()
                }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        null,
                        tint = Colors.ColorPrimary,
                    )
                }
            },
            actions = {
            },
        )
    }
}

@Composable
fun MovementDetailsScreen(
    innerPadding: PaddingValues,
    movement: MovementEntity?,
) {
    LazyColumn(
        contentPadding = innerPadding,
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            movement?.let {
                MovementCard(movement = it)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MovementCard(movement: MovementEntity) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))

                .background(MaterialTheme.colorScheme.background),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .background(Colors.ColorAccent)
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBalance,
                        contentDescription = null,
                        tint = Colors.ColorPrimary,
                        modifier = Modifier.size(24.dp),
                    )

                    Text(
                        text = stringResource(R.string.details),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Colors.ColorPrimary,
                    )



                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = Colors.ColorPrimary,
                        modifier = Modifier.size(34.dp),
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    Row {
                        Text(
                            text = stringResource(R.string.identifier),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = movement.transactionNumber,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Row {
                        Text(
                            text = stringResource(R.string.description),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = movement.description,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Row {
                        Text(
                            text = stringResource(R.string.date),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = convertToDateFormat(movement.date),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Column {
                        Text(
                            text = stringResource(R.string.balance),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = movement.balance.toStringOrEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Column {
                        Text(
                            text = stringResource(R.string.amount),
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Text(
                            text = movement.amount.toStringOrEmpty(),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}
