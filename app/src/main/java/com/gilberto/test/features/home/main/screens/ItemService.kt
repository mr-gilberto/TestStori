package com.gilberto.test.features.home.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gilberto.domain.models.MovementEntity
import com.gilberto.test.R
import com.gilberto.test.theme.AppTheme
import com.gilberto.test.theme.Colors
import com.gilberto.test.util.convertToDateFormat

@Preview
@Composable
fun ItemServicePreview() {
    AppTheme {
        Box(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun ItemService(
    serviceOrder: MovementEntity,
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .shadow(5.dp, shape = RoundedCornerShape(8.dp)),
    ) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .clickable { onItemClick() }
                .background(MaterialTheme.colorScheme.background),
        ) {

            val color = if (serviceOrder.type) {
                Colors.ColorSecondary
            } else {
                Colors.colorNotStarted
            }

            Box(
                modifier = Modifier
                    .background(color)
                    .fillMaxHeight()
                    .width(50.dp),
                contentAlignment = Alignment.Center,
            ) {

                val icon = if (serviceOrder.type) {
                    R.drawable.baseline_arrow_upward_24
                } else {
                    R.drawable.baseline_arrow_downward_24
                }

                Icon(
                    painterResource(id = icon),
                    contentDescription = null,
                    tint = Colors.ColorWhite,
                    modifier = Modifier.size(24.dp),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
            ) {


                Text(
                    text = serviceOrder.transactionNumber,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleSmall,
                )

                if (serviceOrder.type) {
                    Text(
                        text = "Ingreso",
                        style = MaterialTheme.typography.bodySmall,
                    )
                } else {
                    Text(
                        text = "Salida",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

                Text(
                    text = convertToDateFormat(serviceOrder.date),
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = serviceOrder.amount.toString(),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
